package net.itorbit.pesaram.core.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import net.itorbit.pesaram.core.model.FileData;
import net.itorbit.pesaram.core.service.FileDataService;
import net.itorbit.pesaram.core.utils.BoundaryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.MongoTransactionException;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileDataController {

    private final FileDataService fileDataService;

    @Autowired
    public FileDataController(FileDataService fileDataService) {
        this.fileDataService = fileDataService;
    }

    @PostMapping
    public void upload(@RequestParam("file") MultipartFile file) {
        FileData fileData;
        try {
            System.out.println("Received request on Core service.");
            System.out.println("Uploading file to core service...");
            FileData fd = new FileData(file);
            fileData = fileDataService.saveData(fd);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Couldn't save data on database!"
            );
        }

        System.out.println("Sending file to filemanager service...");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(
                        "multipart/form-data; boundary=" + BoundaryGenerator.generateDefaultBoundary()
                )
        );
        headers.setContentLength(file.getSize());

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        try {
            body.add("file", file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (fileData == null) {
            throw new MongoTransactionException("Failed to store file on database!");
        }

        HttpEntity<?> entity = new HttpEntity<Object>(body, headers);

        String uri = "http://filemanager:1231/file";
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> res = rt.exchange(
                uri,
                HttpMethod.POST,
                entity,
                String.class
        );

        //Updating the entity
        String fileManagerUUID = res.getBody();
        fileData.setFileManagerUUID(fileManagerUUID);
        fileDataService.update(fileData);
        System.out.println(fileManagerUUID);
    }

    @GetMapping
    public ResponseEntity<Resource> download(@RequestParam String uuid) throws FileNotFoundException {
        FileData fileData = fileDataService.findByFileManagerUUID(uuid);
        if (fileData == null) {
            System.out.println("Couldn't find file with fileManagerUUID " + uuid);
            throw new FileNotFoundException("Could not find file!");
        }
        System.out.println("Found the file: " + fileData.getFileName());

        System.out.println("Sending uuid to filemanager service to get file...");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        String uri = "http://filemanager:1231/file";
        String uriTemplate = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("uuid", "{uuid}")
                .encode()
                .toUriString();
        Map<String, String> params = new HashMap<>();
        params.put("uuid", uuid);
        RestTemplate rt = new RestTemplate();
        return rt.exchange(
                uriTemplate,
                HttpMethod.GET,
                entity,
                Resource.class,
                params
        );
    }
}

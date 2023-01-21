package net.itorbit.pesaram.core.controller;

import net.itorbit.pesaram.core.model.FileData;
import net.itorbit.pesaram.core.service.FileDataService;
import net.itorbit.pesaram.core.utils.BoundaryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

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
        try {
            System.out.println("Received request on Core service.");
            System.out.println("Uploading file to core service...");
            FileData fd = new FileData(file);
            fileDataService.saveData(fd);

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

        HttpEntity<?> entity = new HttpEntity<Object>(body, headers);

        String uri = "http://filemanager:1231/file";
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> res = rt.exchange(
                uri,
                HttpMethod.POST,
                entity,
                String.class
        );

        System.out.println(res.getBody());
    }

    @GetMapping
    public void download(@RequestBody String uuid) {
        //TODO
    }
}

package net.itorbit.pesaram.core.controller;

import net.itorbit.pesaram.core.model.FileData;
import net.itorbit.pesaram.core.service.FileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/upload")
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
    }
}
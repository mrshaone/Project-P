package net.itorbit.pesaram.filemanager.controller;

import net.itorbit.pesaram.filemanager.model.PFile;
import net.itorbit.pesaram.filemanager.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return fileService.saveFile(file);
    }

    @GetMapping
    public ResponseEntity<Resource> downloadFile(@RequestParam("uuid") String uuid) {
        PFile pfile = fileService.getFile(uuid);
        if (pfile == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            File file = pfile.getFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (IOException e) {
            throw new HttpClientErrorException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Couldn't read file from source please contact support!"
            );
        }
    }
}

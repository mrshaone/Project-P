package net.itorbit.pesaram.filemanager.service;

import jakarta.servlet.http.Part;
import net.itorbit.pesaram.filemanager.model.PFile;
import net.itorbit.pesaram.filemanager.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public String saveFile(Part file) {
        PFile pFile = new PFile(file);
        if (pFile.getSavedStatus()) {
            System.out.println("File Saved on host.");
            System.out.println("Saving on database...");
            try {
                fileRepository.save(pFile);
            } catch (Exception e) {
                System.out.println("Failed to make transaction with database!");
                throw new RuntimeException(e);
            }
            System.out.println("File saved successfully on host and db");
            System.out.println("Sending UUID back...");
            return pFile.getId();
        } else {
            throw new RuntimeException("Failed to save file please try again!");
        }
    }

    @Transactional
    public PFile getFile(String uuid) {
        System.out.println("Given UUID: " + uuid);
        System.out.println("Starting transaction with database with given UUID...");

        try {
            PFile pFile;
            if (fileRepository.findById(uuid).isPresent()) {
                pFile = fileRepository.findById(uuid).get();
                System.out.println("Found file with UUID " + uuid + "!");
                System.out.println("Sending file back to client...");
                return pFile;
            } else {
                System.out.println("Couldn't find file with UUID: " + uuid);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Something went wrong during database transaction!");
            throw new RuntimeException(e);
        }
    }
}

package net.itorbit.pesaram.core.service;

import net.itorbit.pesaram.core.model.FileData;
import net.itorbit.pesaram.core.repository.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoTransactionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class FileDataService {

    private final FileDataRepository fileDataRepository;

    @Autowired
    public FileDataService(FileDataRepository fileDataRepository) {
        this.fileDataRepository = fileDataRepository;
    }


    @Transactional
    public FileData saveData(FileData fileData) {
        System.out.println("Successfully uploaded the file, saving file details...");
        FileData result = fileDataRepository.save(fileData);
        System.out.println("File data saved successfully.");
        return result;
    }

    public void update(FileData fileData) {
        System.out.println("Updating foreign key...");
        try {
            fileDataRepository.save(fileData);
            System.out.println("Updated " + fileData.getId() + "with filemanagerUUID: " + fileData.getFileManagerUUID());
        } catch (MongoTransactionException e) {
            System.out.println("Updating entity failed!");
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public FileData findByFileManagerUUID(String fileManagerUUID) {
        if (fileManagerUUID.isBlank()) {
            return null;
        }
        System.out.println("Fining file data on database...");
        return fileDataRepository.findByFileManagerUUID(fileManagerUUID);
    }
}

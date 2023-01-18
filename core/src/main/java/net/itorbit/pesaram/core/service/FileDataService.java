package net.itorbit.pesaram.core.service;

import net.itorbit.pesaram.core.model.FileData;
import net.itorbit.pesaram.core.repository.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Service
public class FileDataService {

    private final FileDataRepository fileDataRepository;

    @Autowired
    public FileDataService(FileDataRepository fileDataRepository) {
        this.fileDataRepository = fileDataRepository;
    }


    @Transactional
    public void saveData(FileData fileData){
            System.out.println("Successfully uploaded the file, saving file details...");
            fileDataRepository.save(fileData);
            System.out.println("File data saved successfully.");
    }
}

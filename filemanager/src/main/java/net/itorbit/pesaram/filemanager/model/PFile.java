package net.itorbit.pesaram.filemanager.model;

import jakarta.servlet.http.Part;
import net.itorbit.pesaram.filemanager.env.Environments;
import net.itorbit.pesaram.filemanager.utils.FileUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Document("pfile")
public class PFile {
    @Id
    private String id;
    private String fileData;
    private String filePath;
    @Transient
    private boolean savedStatus = false;

    public PFile(Part file) {
        this.id = UUID.randomUUID().toString();
        String fileName = file.getName();
        String BASE_DIR = Environments.UPLOAD_DIRECTORY;
        this.filePath = BASE_DIR + "/" + fileName;
        try {
            this.fileData = new String(file.getInputStream().readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            this.save();
        } catch (IOException e) {
            System.out.println("Could not save file!");
            throw new RuntimeException(e);
        }
        this.savedStatus = true;
    }

    public PFile(String id, String fileData, String filePath) {
        this.id = id;
        this.fileData = fileData;
        this.filePath = filePath;
    }

    //Don't remove this or codes will break!
    public PFile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean getSavedStatus() {
        return savedStatus;
    }

    public void setSavedStatus(boolean savedStatus) {
        this.savedStatus = savedStatus;
    }

    public void save() throws IOException {
        System.out.println("Saving to: " + this.filePath);
        FileUtils.createFile(this.getFilePath());
        FileUtils.writeFile(this.getFilePath(), this.getFileData());
    }

    public File getFile() throws IOException {
        return new File(this.filePath);
    }
}

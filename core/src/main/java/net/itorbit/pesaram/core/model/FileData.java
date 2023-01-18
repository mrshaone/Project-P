package net.itorbit.pesaram.core.model;

import net.itorbit.pesaram.core.utils.CustomString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.util.UUID;

@Document("fileData")
public class FileData {
    @Id
    private String id;
    private String fileName;
    private String fileExtension;
    private long fileSizeInBytes;
    private LocalDate dateModified;
    private String fileManagerUUID;

    public FileData(MultipartFile file) {
        this.id = UUID.randomUUID().toString();
        String rawFileName = file.getOriginalFilename() == null ? "" : file.getOriginalFilename();
        this.fileName = CustomString.replaceLast(rawFileName, "\\.\\w+", "");
        setFileExtension(rawFileName);
        this.fileSizeInBytes = file.getSize();
        this.dateModified = LocalDate.now();
    }

    public FileData(String id, String fileName, String fileExtension, long fileSizeInBytes, LocalDate dateModified) {
        this.id = id;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.fileSizeInBytes = fileSizeInBytes;
        this.dateModified = dateModified;
    }

    public FileData(String id, String fileName, String fileExtension, long fileSizeInBytes, LocalDate dateModified, String fileManagerUUID) {
        this.id = id;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.fileSizeInBytes = fileSizeInBytes;
        this.dateModified = dateModified;
        this.fileManagerUUID = fileManagerUUID;
    }

    public FileData() {}

    public String getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileName) {
        String[] split = fileName.split("\\.");
        this.fileExtension = split[split.length - 1];
    }

    public long getFileSizeInBytes() {
        return fileSizeInBytes;
    }

    public void setFileSizeInBytes(long fileSizeInBytes) {
        this.fileSizeInBytes = fileSizeInBytes;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    public String getFileManagerUUID() {
        return fileManagerUUID;
    }

    public void setFileManagerUUID(String fileManagerUUID) {
        this.fileManagerUUID = fileManagerUUID;
    }
}

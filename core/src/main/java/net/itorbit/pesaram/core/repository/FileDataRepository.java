package net.itorbit.pesaram.core.repository;

import net.itorbit.pesaram.core.model.FileData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDataRepository
        extends MongoRepository<FileData, String> {

    @Query("{'fileManagerUUID':  '?0'}")
    public FileData findByFileManagerUUID (String FileManagerUUID);
}

package net.itorbit.pesaram.core.repository;

import net.itorbit.pesaram.core.model.FileData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDataRepository
        extends MongoRepository<FileData, String> {

}

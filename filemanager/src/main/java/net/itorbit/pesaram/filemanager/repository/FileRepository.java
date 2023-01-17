package net.itorbit.pesaram.filemanager.repository;

import net.itorbit.pesaram.filemanager.model.PFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileRepository extends MongoRepository<PFile, String> {
}

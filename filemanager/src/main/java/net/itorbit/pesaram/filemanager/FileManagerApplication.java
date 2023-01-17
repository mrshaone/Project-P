package net.itorbit.pesaram.filemanager;

import net.itorbit.pesaram.filemanager.env.Environments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.URL;

/**
 * FileManager stores file on local storage and generates UUID <br/><br/>
 * <h3>Default Port: <a href="http://localhost:1231">1231</a></h3>
 * If project not initialized through docker, don't forget to change upload directory
 * to your preferred location in Environments class!
 * @see net.itorbit.pesaram.filemanager.env.Environments
 * @author <a href="mailto:sh.damghanpour@itorbit.net">MrSha1</a>
 * @version 0.1
 */
@SpringBootApplication
@EnableMongoRepositories
public class FileManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileManagerApplication.class, args);
        System.out.println("Spring Server started, setting environment variables...");
        System.out.println(Environments.UPLOAD_DIRECTORY);
		System.out.println("Running on http://localhost:1231");
    }

}

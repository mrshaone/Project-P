package net.itorbit.pesaram.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Main Entry of Project P<br/><br/>
 * <h3>Default Port: <a href="http://localhost:1230">1230</a></h3>
 * @author <a href="mailto:sh.damghanpour@itorbit.net">MrSha1</a>
 * @version 0.1
 */
@SpringBootApplication
@EnableMongoRepositories
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
		System.out.println("Running on http://localhost:1230");
	}

}

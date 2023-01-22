package net.itorbit.pesaram.rabbitp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Service for RabbitMQ implementation <br/><br/>
 * <h3>Default Port: <a href="http://localhost:1232">1233</a></h3>
 * @author <a href="mailto:a.rahmani@itorbit.net">Amir Mohammad</a>
 * @version 0.1
 */
@SpringBootApplication
public class RabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }

}

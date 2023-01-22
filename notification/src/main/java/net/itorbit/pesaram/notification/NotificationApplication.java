package net.itorbit.pesaram.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Notification Service entry point<br/><br/>
 * <h3>Default Port: <a href="http://localhost:1232">1232</a></h3>
 * @author <a href="mailto:a.rahmani@itorbit.net">Amir Mohammad</a>
 * @version 0.1
 */
@SpringBootApplication
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
        System.out.println("Running on http://localhost:1232");
    }


}

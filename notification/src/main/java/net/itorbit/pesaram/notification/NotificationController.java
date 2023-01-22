package net.itorbit.pesaram.notification;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component


public class NotificationController {

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String fileBody) {
        System.out.println("Get uuid From Queue ...");
        if (fileBody.isEmpty()) {
            System.out.println("uuid is null");
        } else {
            System.out.println("File Saved Successfully!,\nuuid: " + fileBody);
        }

    }

}

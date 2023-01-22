package net.itorbit.pesaram.rabbitp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

@Component
@EnableAutoConfiguration
public class QueueSender {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    @Autowired
    public QueueSender(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public void send(String uuid) {
        rabbitTemplate.convertAndSend(this.queue.getName(), uuid);
    }

}
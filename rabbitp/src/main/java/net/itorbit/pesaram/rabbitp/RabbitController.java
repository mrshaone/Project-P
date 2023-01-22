package net.itorbit.pesaram.rabbitp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
@RequestMapping("/rabbit")
public class RabbitController {
    private final RabbitTemplate queueSender;

    @Autowired
    public RabbitController(RabbitTemplate queueSender) {
        this.queueSender = queueSender;
    }

    @GetMapping
    public String getUid(@RequestParam("uuid") String uuid) {
        System.out.println("Got uuid: " + uuid);
        queueSender.convertAndSend("direct-exchange", "file-routing-key", uuid);
        return "ok, done";
    }
}

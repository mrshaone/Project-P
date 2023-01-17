package net.itorbit.pesaram.notification;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(path = "/notification")

public class NotificationController {
    @GetMapping
    public String getUid(@RequestParam("uid") Optional<String> uid) {
        if (uid.isPresent()) {
            if (uid.get().isEmpty()) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "request failed"
                );
            }
            return "Upload Successful";
        } else {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "request failed"
            );
        }
    }

}

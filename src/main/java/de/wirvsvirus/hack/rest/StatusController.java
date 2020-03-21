package de.wirvsvirus.hack.rest;


import de.wirvsvirus.hack.rest.dto.UpdateStatusRequest;
import de.wirvsvirus.hack.spring.UserInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mystatus")
@Slf4j
public class StatusController {

    @PutMapping
    public void updateStatus(@RequestBody UpdateStatusRequest request) {

        // TODO
        log.info("IMPLEMENT ME - update status to " + request.getSentiment() + " for user " + UserInterceptor.getCurrentUserId());
    }

}

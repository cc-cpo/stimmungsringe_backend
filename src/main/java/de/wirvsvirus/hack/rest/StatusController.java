package de.wirvsvirus.hack.rest;


import de.wirvsvirus.hack.rest.dto.UpdateStatusRequest;
import de.wirvsvirus.hack.spring.UserInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mystatus")
public class StatusController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusController.class);

    @PutMapping
    public void updateStatus(@RequestBody UpdateStatusRequest request) {

        // TODO
        LOGGER.info("IMPLEMENT ME - update status to " + request.getSentiment() + " for user " + UserInterceptor.getCurrentUserId());
    }

}

package de.wirvsvirus.hack.rest;

import de.wirvsvirus.hack.rest.dto.ConfigurationResponse;
import de.wirvsvirus.hack.service.PushNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConfigurationController {

    @Autowired
    private PushNotificationService pushNotificationService;

    @GetMapping("/configuration")
    public ConfigurationResponse configurationResponse() {
        return ConfigurationResponse.builder().notificationServiceSenderId(this.pushNotificationService.getSenderId()).build();
    }

}

package de.wirvsvirus.hack.service.impl;

import de.wirvsvirus.hack.service.PushNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
@Slf4j
public class PushNotificationServiceImpl implements PushNotificationService {

    @Value("${notification.service.url:}")
    private String notificationServiceUrl;

    @Value("${notification.sender.id:}")
    private String notificationSenderId;

    @Value("${notification.auth.key:}")
    private String notificationAuthKey;

    @PostConstruct
    public void initialize() {
        log.info("Notification Service Url: " + this.notificationServiceUrl);
        log.info("Notification Sender Id: " + this.notificationSenderId);
        log.info("Nofitication Auth Key is set: " + StringUtils.isNoneBlank(this.notificationAuthKey));
    }

    @Override
    public String getSenderId() {
        return this.notificationSenderId;
    }

    @Override
    public void sendMessage(String receiptId, String title, String body) {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "key=" + this.notificationAuthKey);

        final HttpEntity<String> requestEntity = new HttpEntity<>(buildNotificationMessage(receiptId, title, body), headers);
        final ResponseEntity<String> responseEntity = restTemplate.exchange(this.notificationServiceUrl, HttpMethod.POST, requestEntity, String.class);
        if(responseEntity.getStatusCode() != HttpStatus.OK) {
            log.error(responseEntity.getBody());
        }
    }

    private String buildNotificationMessage(final String receiptId, final String title, final String body) {
        return "{\n" +
                "  \"data\": {\n" +
                "    \"notification\": {\n" +
                "        \"title\": \"" + title + "\",\n" +
                "        \"body\": \"" + body + "\",\n" +
                "        \"icon\": \"" + "" + "\",\n" +
                "    }\n" +
                "  },\n" +
                "  \"to\": \"" + receiptId + "\"\n" +
                "}\n";
    }
}

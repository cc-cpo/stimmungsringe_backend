package de.wirvsvirus.hack.rest;

import de.wirvsvirus.hack.rest.dto.SampleResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleRestEndpoint {

    @GetMapping("/sample")
    public SampleResponse sample() {
        return SampleResponse.builder()
                .message("Hello")
                .build();
    }

}

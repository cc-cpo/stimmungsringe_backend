package de.wirvsvirus.hack.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class MyStatusPageResponse {

    private UserMinimalResponse user;

    private SentimentStatusResponse sentimentStatus;

    private List<SuggestionResponse> suggestions;

}

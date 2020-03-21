package de.wirvsvirus.hack.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class OtherStatusPageResponse {

    private UserMinimalResponse user;

    private SentimentStatusResponse sentimentStatus;

    private List<SuggestionResponse> suggestions;

}

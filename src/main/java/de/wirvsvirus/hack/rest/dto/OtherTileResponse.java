package de.wirvsvirus.hack.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class OtherTileResponse {

    private UserMinimalResponse user;

    private SentimentStatusResponse sentimentStatus;

}

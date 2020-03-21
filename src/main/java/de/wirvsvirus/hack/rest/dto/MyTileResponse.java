package de.wirvsvirus.hack.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class MyTileResponse {

    private UserMinimalResponse user;

    private SentimentStatusResponse sentimentStatus;

}

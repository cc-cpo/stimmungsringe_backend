package de.wirvsvirus.hack.rest.dto;

import lombok.Data;

@Data
public class SentimentStatusResponse {

    // TODO should be a score
    /**
     * e.g. schlechtes Wetter
     */
    private String sentimentText;

}

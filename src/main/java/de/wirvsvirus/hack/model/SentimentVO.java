package de.wirvsvirus.hack.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SentimentVO {

    // TODO should be a score
    /**
     * e.g. schlechtes Wetter
     */
    private String sentimentText;

}

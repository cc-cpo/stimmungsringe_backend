package de.wirvsvirus.hack.rest.dto;

import de.wirvsvirus.hack.model.SentimentVO;
import lombok.Data;

@Data
public class UpdateStatusRequest {

    private SentimentVO sentiment;

}

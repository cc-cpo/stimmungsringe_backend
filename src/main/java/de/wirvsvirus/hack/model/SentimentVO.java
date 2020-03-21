package de.wirvsvirus.hack.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SentimentVO {

    @NotNull
    private Sentiments sentimentCode;

}

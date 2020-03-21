package de.wirvsvirus.hack.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UserMinimalResponse {

    @NotNull
    private UUID userId;
    @NotEmpty
    private String displayName;

}


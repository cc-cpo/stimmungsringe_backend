package de.wirvsvirus.hack.rest.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class RegisterUserRequest {

    @NotEmpty
    private String requestedUsername;

}

package de.wirvsvirus.hack.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ApiError {

    private List<String> errors;

}

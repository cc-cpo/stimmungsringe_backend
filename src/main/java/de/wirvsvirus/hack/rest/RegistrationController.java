package de.wirvsvirus.hack.rest;

import com.google.common.collect.Lists;
import de.wirvsvirus.hack.rest.dto.ApiError;
import de.wirvsvirus.hack.rest.dto.RegisterUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    @PostMapping("/new-user")
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody @Valid RegisterUserRequest request) {

        LOGGER.info("Trying to register new user with name <{}>", request.getRequestedUsername());
        // TODO

        if (!Pattern.compile("[\\p{IsLatin}]{2,20}", Pattern.CASE_INSENSITIVE).matcher(request.getRequestedUsername()).matches()) {
            throw new RegistrationFailedException("Der Benutzername " + request.getRequestedUsername() + " hat falschem Format!");
        }

    }

    @ExceptionHandler(RegistrationFailedException.class)
    public ResponseEntity<ApiError> handleRegistrationError(RegistrationFailedException rfe) {
        final ApiError apiError = ApiError.builder()
                .errors(Lists.newArrayList(rfe.getUsername()))
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }

}

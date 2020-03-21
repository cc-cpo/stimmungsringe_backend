package de.wirvsvirus.hack.rest;

public class RegistrationFailedException extends RuntimeException {
    private final String username;

    public RegistrationFailedException(final String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

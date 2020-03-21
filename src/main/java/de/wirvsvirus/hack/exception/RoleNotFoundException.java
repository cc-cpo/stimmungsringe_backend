package de.wirvsvirus.hack.exception;

public class RoleNotFoundException extends Exception {

    public RoleNotFoundException(String identifier) {
        super("Role for identifier '" + identifier + "' not found.");
    }
}

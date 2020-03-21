package de.wirvsvirus.hack.model;

import de.wirvsvirus.hack.exception.RoleNotFoundException;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public enum Role {
    Arbeitnehmer("Arbeitnehmer"), Elternteil("Elternteil"), Partner("Partner"), Kind("Kind"), Me_Time("Me-Time");

    private final String identifier;

    private Role(final String identifier) {
        this.identifier = identifier;
    }

    public static Role ofIdentifier(String identifier) throws RoleNotFoundException {
        final Optional<Role> optRole = Arrays.stream(Role.values()).filter(r -> StringUtils.equalsAnyIgnoreCase(r.identifier, identifier)).findFirst();
        if (optRole.isPresent()) {
            return optRole.get();
        } else {
            throw new RoleNotFoundException(identifier);
        }
    }
}

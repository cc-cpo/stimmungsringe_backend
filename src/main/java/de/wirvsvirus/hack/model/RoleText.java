package de.wirvsvirus.hack.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleText {

    private Role role;
    private String forOthers;
    private String forMe;

}

package de.wirvsvirus.hack.service;

import de.wirvsvirus.hack.model.Role;
import de.wirvsvirus.hack.model.RoleInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface RoleBasedTextSuggestionsService {

    List<String> forMe(Role role);

    List<String> forOthers(Role role);
}

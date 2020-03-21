package de.wirvsvirus.hack.service;

import de.wirvsvirus.hack.model.RoleText;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface RoleTextImporter {

    List<RoleText> importData(InputStream inputStream) throws IOException;
}

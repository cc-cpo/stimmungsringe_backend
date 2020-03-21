package de.wirvsvirus.hack.service;

import de.wirvsvirus.hack.model.Role;
import de.wirvsvirus.hack.model.RoleText;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class RoleTextImporterTest {

    private static final String TESTFILE = "/data/role_text.csv";

    @Autowired
    private RoleTextImporter roleTextImporter;

    @Test
    public void role_text_csvImportTest() throws IOException {
        final List<RoleText> roleTexts = this.roleTextImporter.importData(RoleTextImporterTest.class.getResourceAsStream(TESTFILE));
        assertThat(roleTexts.size(), is(2));

        assertThat(roleTexts.get(0).getRole(), is(Role.Arbeitnehmer));
        assertThat(roleTexts.get(0).getForOthers(), is("Bring mir (deiner Mutter) doch einen Kaffee"));
        assertThat(roleTexts.get(0).getForMe(), is("Morgen ist auch noch ein Tag"));

        assertThat(roleTexts.get(1).getRole(), is(Role.Kind));
        assertThat(roleTexts.get(1).getForOthers(), is("Ich will spielen"));
        assertThat(roleTexts.get(1).getForMe(), is("Ruh dich gut aus"));
    }
}

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
        assertThat(roleTexts.size(), is(1));

        assertThat(roleTexts.get(0).getRole(), is(Role.Arbeitnehmer));
        assertThat(roleTexts.get(0).getExpectation(), is("Ich möchte berufliche Zusagen zeitlich korrekt einhalten."));
        assertThat(roleTexts.get(0).getSelfRecommendation(), is("Plane Pufferzeiten ein und setze realistische Zeitziele."));
        assertThat(roleTexts.get(0).getForMe(), is("Du schaffst es, gut Ding will Weile haben!"));
        assertThat(roleTexts.get(0).getThirdPartyRecommendation(), is("Ist es wirklich wichtig die Zeit einzuhalten oder kannst Du Dich auch verspäten?"));
        assertThat(roleTexts.get(0).getForOthers(), is("Trink ruhig erst einmal ein Glas Wasser oder schnappe kurz frische Luft!"));
    }
}

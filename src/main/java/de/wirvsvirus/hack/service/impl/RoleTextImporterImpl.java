package de.wirvsvirus.hack.service.impl;

import de.wirvsvirus.hack.exception.RoleNotFoundException;
import de.wirvsvirus.hack.model.Role;
import de.wirvsvirus.hack.model.RoleText;
import de.wirvsvirus.hack.service.RoleTextImporter;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RoleTextImporterImpl implements RoleTextImporter {

    private static final char DELIMITER = '\t';

    private static final int COLUMN_ROLLE = 0;
    private static final int COLUMN_FOR_OTHERS = 1;
    private static final int COLUMN_FOR_ME = 2;

    @Override
    public List<RoleText> importData(InputStream inputStream) throws IOException {
        final List<RoleText> roleTexts = new ArrayList<>();

        final Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withDelimiter(DELIMITER)
                .withFirstRecordAsHeader()
                .parse(new InputStreamReader(inputStream));

        for (CSVRecord record : records) {
            final String role = record.get(COLUMN_ROLLE);
            final String forOthers = record.get(COLUMN_FOR_OTHERS);
            final String forMe = record.get(COLUMN_FOR_ME);

            try {
                roleTexts.add(RoleText.builder()
                        .role(Role.ofIdentifier(role))
                        .forOthers(forOthers)
                        .forMe(forMe)
                        .build());
            } catch (RoleNotFoundException e) {
                log.error(e.getMessage());
            }
        }
        return roleTexts;
    }
}

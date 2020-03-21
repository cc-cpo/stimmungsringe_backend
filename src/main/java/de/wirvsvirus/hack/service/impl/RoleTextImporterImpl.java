package de.wirvsvirus.hack.service.impl;

import de.wirvsvirus.hack.exception.RoleNotFoundException;
import de.wirvsvirus.hack.model.Role;
import de.wirvsvirus.hack.model.RoleText;
import de.wirvsvirus.hack.service.RoleTextImporter;
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

    private static final int COLUMN_ROLE = 0;
    private static final int COLUMN_EXPECTATION = 1;
    private static final int COLUMN_SELF_RECOMMENDATION = 2;
    private static final int COLUMN_FOR_ME = 3;
    private static final int COLUMN_THIRD_PARTY_RECOMMENDATION = 4;
    private static final int COLUMN_FOR_OTHERS = 5;

    @Override
    public List<RoleText> importData(InputStream inputStream) throws IOException {
        final List<RoleText> roleTexts = new ArrayList<>();

        final Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withDelimiter(DELIMITER)
                .withFirstRecordAsHeader()
                .parse(new InputStreamReader(inputStream));

        for (CSVRecord record : records) {
            try {
                roleTexts.add(RoleText.builder()
                        .role(Role.ofIdentifier(record.get(COLUMN_ROLE)))
                        .expectation(record.get(COLUMN_EXPECTATION))
                        .selfRecommendation(record.get(COLUMN_SELF_RECOMMENDATION))
                        .forMe(record.get(COLUMN_FOR_ME))
                        .thirdPartyRecommendation(record.get(COLUMN_THIRD_PARTY_RECOMMENDATION))
                        .forOthers(record.get(COLUMN_FOR_OTHERS))
                        .build());
            } catch (RoleNotFoundException e) {
                log.error(e.getMessage());
            }
        }
        return roleTexts;
    }
}

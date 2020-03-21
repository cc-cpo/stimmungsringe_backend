package de.wirvsvirus.hack.rest;

import de.wirvsvirus.hack.model.SentimentVO;
import de.wirvsvirus.hack.rest.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/otherstatuspage")
@Slf4j
public class OtherStatusPageController {

    @GetMapping("/{otherUserId}")
    public OtherStatusPageResponse viewOtherStatusPage(@PathVariable @NotNull  UUID otherUserId) {
        OtherStatusPageResponse response = new OtherStatusPageResponse();

        final UserMinimalResponse me = new UserMinimalResponse();
        me.setDisplayName("Mutti");

        final SentimentStatusResponse sentimentStatusResponse = new SentimentStatusResponse();
        sentimentStatusResponse.setSentiment(new SentimentVO("Sonnenschein"));
        final List<SuggestionResponse> suggestions = new ArrayList<>();

        {
            final SuggestionResponse sugg = new SuggestionResponse();
            sugg.setText("Erzähl deiner Mutter/deinem Vater, wie es dir gerade geht");
            suggestions.add(sugg);
        }

        response.setUser(me);
        response.setSentimentStatus(sentimentStatusResponse);
        response.setSuggestions(suggestions);

        return response;
    }
}

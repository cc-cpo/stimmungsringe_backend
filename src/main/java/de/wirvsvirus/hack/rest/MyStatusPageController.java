package de.wirvsvirus.hack.rest;

import de.wirvsvirus.hack.rest.dto.*;
import de.wirvsvirus.hack.spring.UserInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mystatuspage")
public class MyStatusPageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyStatusPageController.class);

    @GetMapping
    public MyStatusPageResponse viewMyStatusPage() {
        System.out.println("currentuser" + UserInterceptor.getCurrentUserId());

        MyStatusPageResponse response = new MyStatusPageResponse();

        final UserMinimalResponse me = new UserMinimalResponse();
        me.setDisplayName("Timmy");

        final SentimentStatusResponse sentimentStatusResponse = new SentimentStatusResponse();
        sentimentStatusResponse.setSentimentText("Wolken");
        final List<SuggestionResponse> suggestions = new ArrayList<>();

        {
            final SuggestionResponse sugg = new SuggestionResponse();
            sugg.setText("Spiel alleine in deinem Zimmer");
            suggestions.add(sugg);
        }
        {
            final SuggestionResponse sugg = new SuggestionResponse();
            sugg.setText("Ruf einen Freund an");
            suggestions.add(sugg);
        }

        response.setUser(me);
        response.setSentimentStatus(sentimentStatusResponse);
        response.setSuggestions(suggestions);

        return response;
    }

}

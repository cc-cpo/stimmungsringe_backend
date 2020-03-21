package de.wirvsvirus.hack.rest;

import de.wirvsvirus.hack.rest.dto.*;
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

//    @GetMapping("/view")
//    public DashboardResponse dashboardView() {
//        DashboardResponse response = new DashboardResponse();
//
//        final UserMinimalResponse me = new UserMinimalResponse();
//        me.setDisplayName("Stefan");
//
//        final SentimentStatusResponse sentimentStatusResponse = new SentimentStatusResponse();
//        sentimentStatusResponse.setSentimentText("Regen und Donner");
//        final List<SuggestionResponse> suggestions = new ArrayList<>();
//        final SuggestionResponse sugg1 = new SuggestionResponse();
//        sugg1.
//                suggestions.add()
//
//        MyStatusPageResponse myStatusPageResponse = new MyStatusPageResponse();
//        myStatusPageResponse.setUser(me);
//        myStatusPageResponse.setSentimentStatus(sentimentStatusResponse);
//        myStatusPageResponse.setSuggestions(suggestions);
//
//        response.setMyStatusPage(myStatusPage);
//        return response;
//    }

}

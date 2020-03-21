package de.wirvsvirus.hack.rest;

import de.wirvsvirus.hack.model.Role;
import de.wirvsvirus.hack.model.SentimentVO;
import de.wirvsvirus.hack.model.User;
import de.wirvsvirus.hack.model.UserRepository;
import de.wirvsvirus.hack.rest.dto.*;
import de.wirvsvirus.hack.service.RoleBasedTextSuggestionsService;
import de.wirvsvirus.hack.spring.UserInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mystatuspage")
@Slf4j
public class MyStatusPageController {

    @Autowired
    private RoleBasedTextSuggestionsService suggestionsService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public MyStatusPageResponse viewMyStatusPage() {

        final User currentUser = userRepository.findByUserId(UserInterceptor.getCurrentUserId());

        MyStatusPageResponse response = new MyStatusPageResponse();

        final UserMinimalResponse me = Mappers.mapResponseFromDomain(currentUser);

        final SentimentStatusResponse sentimentStatusResponse = new SentimentStatusResponse();
        sentimentStatusResponse.setSentiment(new SentimentVO("Wolken"));
        final List<SuggestionResponse> suggestions = new ArrayList<>();

        currentUser.getRoles().stream()
            .flatMap(role -> suggestionsService.forMe(role).stream())
            .map(text -> {
                final SuggestionResponse sugg = new SuggestionResponse();
                sugg.setText(text);
                return sugg;
            }).forEach(suggestions::add);

        response.setUser(me);
        response.setSentimentStatus(sentimentStatusResponse);
        response.setSuggestions(suggestions);

        return response;
    }

}

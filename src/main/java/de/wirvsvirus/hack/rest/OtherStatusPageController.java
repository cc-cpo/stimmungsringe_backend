package de.wirvsvirus.hack.rest;

import com.google.common.base.Preconditions;
import de.wirvsvirus.hack.model.SentimentVO;
import de.wirvsvirus.hack.model.Sentiment;
import de.wirvsvirus.hack.model.User;
import de.wirvsvirus.hack.model.UserRepository;
import de.wirvsvirus.hack.rest.dto.*;
import de.wirvsvirus.hack.service.RoleBasedTextSuggestionsService;
import de.wirvsvirus.hack.spring.UserInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/otherstatuspage")
@Slf4j
public class OtherStatusPageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleBasedTextSuggestionsService suggestionsService;

    @GetMapping(value = "/{otherUserId}")
    public OtherStatusPageResponse viewOtherStatusPage(
            @PathVariable("otherUserId") @NotNull  UUID otherUserId) {

        Preconditions.checkState(
            !otherUserId.equals(UserInterceptor.getCurrentUserId()),
                "Cannot have others' perspective on your own page");

        final User otherUser = userRepository.findByUserId(otherUserId);

        OtherStatusPageResponse response = new OtherStatusPageResponse();

        final UserMinimalResponse me = Mappers.mapResponseFromDomain(otherUser);

        final SentimentStatusResponse sentimentStatusResponse = new SentimentStatusResponse();
        sentimentStatusResponse.setSentiment(new SentimentVO(userRepository.findSentimentByUserId(otherUserId)));
        final List<SuggestionResponse> suggestions = new ArrayList<>();

        otherUser.getRoles().stream()
                .flatMap(role -> suggestionsService.forOthers(role).stream())
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

package de.wirvsvirus.hack.rest;

import com.google.common.collect.Lists;
import de.wirvsvirus.hack.model.SentimentVO;
import de.wirvsvirus.hack.model.Sentiment;
import de.wirvsvirus.hack.model.User;
import de.wirvsvirus.hack.model.UserRepository;
import de.wirvsvirus.hack.rest.dto.*;
import de.wirvsvirus.hack.spring.UserInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
@Slf4j
public class DashboardController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public DashboardResponse dashboardView() {
        final User currentUser = userRepository.findByUserId(UserInterceptor.getCurrentUserId());

        DashboardResponse response = new DashboardResponse();

        {
            final UserMinimalResponse me = Mappers.mapResponseFromDomain(currentUser);

            final SentimentStatusResponse regen = new SentimentStatusResponse();
            regen.setSentiment(new SentimentVO(Sentiment.CLOUD_RAIN));

            MyTileResponse myTileResponse = new MyTileResponse();
            myTileResponse.setUser(me);
            myTileResponse.setSentimentStatus(regen);

            response.setMyTile(myTileResponse);
        }

        final List<User> otherUsersInGroup = userRepository.findOtherUsersInGroup(currentUser.getId());

        for (final User otherUser : otherUsersInGroup) {
            final UserMinimalResponse other = Mappers.mapResponseFromDomain(otherUser);

            final SentimentStatusResponse sturm = new SentimentStatusResponse();
            sturm.setSentiment(new SentimentVO(Sentiment.SMOG));

            OtherTileResponse tileResponse = new OtherTileResponse();
            tileResponse.setUser(other);
            tileResponse.setSentimentStatus(sturm);

            response.setOtherTiles(Lists.newArrayList(tileResponse));
        }

        return response;
    }


}

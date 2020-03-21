package de.wirvsvirus.hack.rest;

import com.google.common.collect.Lists;
import de.wirvsvirus.hack.model.SentimentVO;
import de.wirvsvirus.hack.rest.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@Slf4j
public class DashboardController {

    @GetMapping
    public DashboardResponse dashboardView() {
        DashboardResponse response = new DashboardResponse();

        {
            final UserMinimalResponse me = new UserMinimalResponse();
            me.setDisplayName("Timmy");

            final SentimentStatusResponse regen = new SentimentStatusResponse();
            regen.setSentiment(new SentimentVO("Regen und Donner"));

            MyTileResponse myTileResponse = new MyTileResponse();
            myTileResponse.setUser(me);
            myTileResponse.setSentimentStatus(regen);

            response.setMyTile(myTileResponse);
        }

        {
            final UserMinimalResponse mutti = new UserMinimalResponse();
            mutti.setDisplayName("Mutti");

            final SentimentStatusResponse sonnenschein = new SentimentStatusResponse();
            sonnenschein.setSentiment(new SentimentVO("Sonnenschein"));

            OtherTileResponse muttiTileResponse = new OtherTileResponse();
            muttiTileResponse.setUser(mutti);
            muttiTileResponse.setSentimentStatus(sonnenschein);

            response.setOtherTiles(Lists.newArrayList(muttiTileResponse));
        }

        {
            final UserMinimalResponse mutti = new UserMinimalResponse();
            mutti.setDisplayName("Vater");

            final SentimentStatusResponse sturm = new SentimentStatusResponse();
            sturm.setSentiment(new SentimentVO("Sturm"));

            OtherTileResponse vattiTileResponse = new OtherTileResponse();
            vattiTileResponse.setUser(mutti);
            vattiTileResponse.setSentimentStatus(sturm);

            response.setOtherTiles(Lists.newArrayList(vattiTileResponse));
        }


        return response;
    }


}

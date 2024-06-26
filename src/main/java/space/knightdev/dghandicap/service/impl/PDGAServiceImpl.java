package space.knightdev.dghandicap.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import space.knightdev.dghandicap.service.PDGAService;

import java.io.IOException;

@Slf4j
@Service
public class PDGAServiceImpl implements PDGAService {

    @Value("pdga.player.lookup:https://www.pdga.com/player/")
    private String pdgaPlayerUri;

    @Override
    public Integer getPdgaRatingForPlayer(final Integer pdgaNumber) {
        try {
            Connection session = Jsoup.connect(pdgaPlayerUri + pdgaNumber);
            Document httpResponse = session.get();
            Element documentBody = httpResponse.body();
            Element ratingElement = documentBody.getElementsByClass("current-rating").first();
            return Integer.valueOf(ratingElement.wholeOwnText().trim());
        } catch (IOException ioe) {
            log.error(ioe.toString());
        } catch (NullPointerException npe) {
            log.error("No rating found for player, even though the player has a PDGA number of {}", pdgaNumber);
        }
        return Integer.MIN_VALUE;
    }
}

package space.knightdev.dghandicap.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import space.knightdev.dghandicap.service.PDGAService;

import java.io.IOException;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class PDGAServiceImpl implements PDGAService {

    private static final String PDGA_PLAYER_URI = "https://www.pdga.com/player/";

    @Override
    public Integer getPdgaRatingForPlayer(final Integer pdgaNumber) {
        try {
            Connection session = Jsoup.connect(PDGA_PLAYER_URI + pdgaNumber);
            Document httpResponse = session.get();
            Element documentBody = httpResponse.body();
            Element ratingElement = documentBody.getElementsByClass("current-rating").first();
            return Integer.valueOf(ratingElement.wholeOwnText().trim());
        } catch (IOException ioe) {
            log.error(ioe.toString());
        } catch (NullPointerException npe) {
            log.error("No rating found for player.");
        }
        return Integer.MIN_VALUE;
    }
}

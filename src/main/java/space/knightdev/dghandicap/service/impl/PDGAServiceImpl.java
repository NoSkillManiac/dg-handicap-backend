package space.knightdev.dghandicap.service.impl;

import org.springframework.stereotype.Service;
import space.knightdev.dghandicap.service.PDGAService;

@Service
public class PDGAServiceImpl implements PDGAService {

    private static final String PDGA_PLAYER_URI = "https://www.pdga.com/player/";

    @Override
    public Integer getPdgaRatingForPlayer(Integer pdgaNumber) {
        //1. GET Request https://www.pdga.com/player/{playerNumber}
        

        //2. Get element with tag current-rating which will be in the format of <strong>Current Rating:</strong> xxx [etc]
        //3. Parse out the numerical value from the list element


        return null;
    }
}

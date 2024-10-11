package space.knightdev.dghandicap.service.impl

import spock.lang.Specification

class PDGAServiceImplSpec extends Specification {

    def "Expect PDGA Player rating to be returned from PDGA website"() {
        when:
        PDGAServiceImpl service = new PDGAServiceImpl()
        service.pdgaPlayerUri = "https://www.pdga.com/player/"
        int pdgaRating = service.getPdgaRatingForPlayer(playerNumber)

        then:
        pdgaRating == expectedRating

        where:
        playerNumber || expectedRating
        -1           || Integer.MIN_VALUE
        1            || Integer.MIN_VALUE
        127708       || 924
        134240       || 983
        134417       || 956
        134711       || 953
        156823       || 967
        273026       || Integer.MIN_VALUE
    }
}

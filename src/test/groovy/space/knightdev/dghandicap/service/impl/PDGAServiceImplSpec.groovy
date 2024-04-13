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
        134711       || 946
        127708       || 944
        273026       || Integer.MIN_VALUE
    }
}

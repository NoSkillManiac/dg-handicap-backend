package space.knightdev.dghandicap.service.impl

import spock.lang.Specification

class PDGAServiceImplSpec extends Specification {

    def "Expect PDGA Player rating to be returned from PDGA website"() {
        when:
        PDGAServiceImpl service = new PDGAServiceImpl()
        int pdgaRating = service.getPdgaRatingForPlayer(playerNumber)

        then:
        pdgaRating == expectedRating

        where:
        playerNumber || expectedRating
        134711       || 939
        127708       || 937
        273026       || Integer.MIN_VALUE
    }
}

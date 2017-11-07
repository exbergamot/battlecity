package dkgames.battlecity.ai

import spock.lang.Specification

class RandomBotAITest extends Specification {
    private static final int DIRECTION_THRESHOLD = RandomBotAI.CHANGE_DIRECTION_THRESHOLD;
    TankAI ai = new RandomBotAI();


    def "testChooseDirection"() {
        given:
        dkgames.battlecity.entity.Vector direction = null;

        when:
        for (int i = 0; i < repeat; i++) {
            direction = ai.chooseDirection();
            println direction
        }

        then:
        (direction == null) == shouldBeNull

        where:
        repeat              ||   shouldBeNull
        DIRECTION_THRESHOLD ||   false
    }

    def "ShouldShot"() {
    }
}

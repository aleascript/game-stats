import core.Dice;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static org.junit.jupiter.api.Assertions.assertTrue;


public class DiceTest {

    private static final Logger log = LoggerFactory.getLogger(DiceTest.class);

    @Test
    public void testDiceRolls() {
        for (int i=0; i < 100; i++) {
            Dice dice = new Dice(6);
            int roll = dice.roll() ;
            log.info("Dice roll = {}", roll);
            assertTrue(roll >= 1);
            assertTrue(roll <= 6);
        }
    }

}

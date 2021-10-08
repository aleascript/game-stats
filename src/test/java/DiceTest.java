import core.Dice;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;


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

    @Test
    public void testDiceDistribution() {
        Dice dice = new Dice(6);
        Map<Integer,Integer> distrib = new HashMap<>();
        for (int i=0; i < 1000000; i++) {
            int roll = dice.roll() ;
            if (!distrib.containsKey(roll)) {
                distrib.put(roll, 1);
            } else {
                distrib.put(roll, distrib.get(roll)+1);
            }
        }
        log.info("Distrib=\n");
        distrib.entrySet().stream()
                .forEach(e -> {
                    log.info("Result of {}  = {}", e.getKey(), e.getValue());
                });
    }

}

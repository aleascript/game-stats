import core.Dice;
import hadesv2.*;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HadesV2 {

    private static final Logger log = LoggerFactory.getLogger(HadesV2.class);
    private static final int ITERATIONS = 10 ;

    @Test
    @Ignore
    public void testEnums() {
        Result result = Result.valueOf(-1);
        log.info("Result = {}", result);
    }

    @Test
    @Ignore
    public void testD12() {

        EngineDefinition engineDefinition = EngineGenerator.d12();
        for (int numOfDice: new int[] {1, 2, 3, 4}) {
            Roll roll = new Roll(engineDefinition, numOfDice);
            log.info(">>>>>>>>>>>>     {} Dice    <<<<<<<<<<<<<<<<", numOfDice);
            for (int i=1; i <= ITERATIONS; i++) {
                log.info("Result={}", roll.getResult());
            }
        }

    }

    @Test
    public void testStatsD12() {
        EngineDefinition engineDefinition = EngineGenerator.d12();
        RollStats stats = new RollStats(engineDefinition);
        stats.setIterations(100000);
        for (int numOfDice: new int[] {1, 2, 3, 4}) {
            log.info(">>>>>>>>>>>>     {} D12   <<<<<<<<<<<<<<<<", numOfDice);
            log.info(stats.calculate(numOfDice).toString());
        }
    }

    @Test
    public void testStatsD6() {
        EngineDefinition engineDefinition = EngineGenerator.d6();
        RollStats stats = new RollStats(engineDefinition);
        stats.setIterations(100000);
        for (int numOfDice: new int[] {1, 2, 3, 4}) {
            log.info(">>>>>>>>>>>>     {} D6   <<<<<<<<<<<<<<<<", numOfDice);
            log.info(stats.calculate(numOfDice).toString());
        }
    }

}

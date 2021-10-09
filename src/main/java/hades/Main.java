package hades;


import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Main {

    private static int ITERATIONS = 1000000;

    public static void main(String... args) {

        //simpleStats(new ProtagonistResolutionStrategy());
        //simpleStats(new BoundedProtagonistResolutionStrategy());
        //simpleStats(new BoundedMinAndMaxProtagonistResolutionStrategy());
        //simpleStats(new ResolutionStrategyWithChaos());
        //log.info("-------------------");
        //testStats();
        //log.info("------------------");
        //simpleStats(new ResolutionStategySimpliest());
        //log.info("------------------");
        //simpleStats(new ResolutionStategySimplier());
        //log.info("------------------");
        //simpleStats(new ResolutionStrategyWithSimpleChaos());
        //log.info("------------------");
        //simpleStats(new NewResolution());

        /*log.info("------------------");
        simpleStats(new SimpliestNewResolution());
        log.info("------------------");
        simpleStats(new SimpliestNewResolutionWithoutChaos());*/
        //log.info("--------------------");
        //simpleStats(new SimpliestNewResolutionWithNewFortuneRules());

        log.info("--------------------");
        simpleStats(new ResolutionAvecLissageWithoutChance(), 6);
        log.info("--------------------");
        simpleStats(new ResolutionAvecLissageWithoutChance(), 10);

        log.info("--------------------");
        simpleStats(new ResolutionAvecLissage(), 6);
        log.info("--------------------");
        simpleStats(new ResolutionAvecLissage(), 10);

        log.info("--------------------");
        simpleStats(new ResolutionAvecLissageMultiChance(), 6);
        log.info("--------------------");
        simpleStats(new ResolutionAvecLissageMultiChance(), 10);


    }


    public static void testStats() {
        log.info(SimpleTest.class.getSimpleName());
        SimpleTestStats simpleTestStats = new SimpleTestStats();
        simpleTestStats.setIterations(ITERATIONS);
        log.info(simpleTestStats.asMarkdowHeader());
        for (int i = 1; i < 10; i++) {
            simpleTestStats.setSimpleTest(new SimpleTest(i));
            simpleTestStats.doStats();
            log.info(simpleTestStats.asMarkdownRaw());
        }
    }

    public static void simpleStats(ResolutionStrategy strategy, int diceSize) {
        log.info("{}\n",strategy.getClass().getSimpleName());
        //List<Integer> diceTypes = List.of(6, 10);
        List<Integer> diceTypes = List.of(diceSize);
        for (Integer diceType : diceTypes) {
            log.info("Stats for D{}\n", diceType);
            Stats stats = new Stats();
            stats.setIterations(ITERATIONS);
            log.info(stats.asMarkdowHeader());

            calculateAndDisplay(new Confrontation(1, 1, diceType), strategy);
            calculateAndDisplay(new Confrontation(2, 2, diceType), strategy);
            calculateAndDisplay(new Confrontation(3, 3, diceType), strategy);
            calculateAndDisplay(new Confrontation(4, 4, diceType), strategy);
            calculateAndDisplay(new Confrontation(5, 5, diceType), strategy);
            calculateAndDisplay(new Confrontation(6, 6, diceType), strategy);
            calculateAndDisplay(new Confrontation(7, 7, diceType), strategy);
            calculateAndDisplay(new Confrontation(8, 8, diceType), strategy);
            calculateAndDisplay(new Confrontation(16, 16, diceType), strategy);

            calculateAndDisplay(new Confrontation(1, 1, diceType), strategy);
            calculateAndDisplay(new Confrontation(1, 2, diceType), strategy);
            calculateAndDisplay(new Confrontation(1, 3 , diceType), strategy);
            calculateAndDisplay(new Confrontation(1, 4, diceType), strategy);
            calculateAndDisplay(new Confrontation(1, 5, diceType), strategy);

            calculateAndDisplay(new Confrontation(1, 1, diceType), strategy);
            calculateAndDisplay(new Confrontation(2, 1, diceType), strategy);
            calculateAndDisplay(new Confrontation(3, 1, diceType), strategy);
            calculateAndDisplay(new Confrontation(4, 1, diceType), strategy);
            calculateAndDisplay(new Confrontation(5, 1, diceType), strategy);

            calculateAndDisplay(new Confrontation(1, 2, diceType), strategy);
            calculateAndDisplay(new Confrontation(2, 3, diceType), strategy);
            calculateAndDisplay(new Confrontation(3, 4, diceType), strategy);
            calculateAndDisplay(new Confrontation(4, 5, diceType), strategy);
            calculateAndDisplay(new Confrontation(7, 8, diceType), strategy);

            calculateAndDisplay(new Confrontation(2, 1, diceType), strategy);
            calculateAndDisplay(new Confrontation(3, 2, diceType), strategy);
            calculateAndDisplay(new Confrontation(4, 3, diceType), strategy);
            calculateAndDisplay(new Confrontation(5, 4, diceType), strategy);
            calculateAndDisplay(new Confrontation(8, 7, diceType), strategy);

            calculateAndDisplay(new Confrontation(1, 3, diceType), strategy);
            calculateAndDisplay(new Confrontation(2, 4, diceType), strategy);
            calculateAndDisplay(new Confrontation(3, 5, diceType), strategy);
            calculateAndDisplay(new Confrontation(4, 6, diceType), strategy);
            calculateAndDisplay(new Confrontation(7, 9, diceType), strategy);

            calculateAndDisplay(new Confrontation(3, 1, diceType), strategy);
            calculateAndDisplay(new Confrontation(4, 2, diceType), strategy);
            calculateAndDisplay(new Confrontation(5, 3, diceType), strategy);
            calculateAndDisplay(new Confrontation(6, 4, diceType), strategy);
            calculateAndDisplay(new Confrontation(9, 7, diceType), strategy);

            calculateAndDisplay(new Confrontation(1, 4, diceType), strategy);
            calculateAndDisplay(new Confrontation(2, 5, diceType), strategy);
            calculateAndDisplay(new Confrontation(3, 6, diceType), strategy);
            calculateAndDisplay(new Confrontation(4, 7, diceType), strategy);
            calculateAndDisplay(new Confrontation(7, 10, diceType), strategy);

            calculateAndDisplay(new Confrontation(4, 1, diceType), strategy);
            calculateAndDisplay(new Confrontation(5, 2, diceType), strategy);
            calculateAndDisplay(new Confrontation(6, 3, diceType), strategy);
            calculateAndDisplay(new Confrontation(7, 4, diceType), strategy);
            calculateAndDisplay(new Confrontation(10, 7, diceType), strategy);


        }

    }

    private static void calculateAndDisplay(Confrontation confrontation, ResolutionStrategy strategy) {
        Stats stats = new Stats();
        stats.setIterations(ITERATIONS);
        stats.setConfrontation(confrontation);
        stats.doStats(strategy);
        log.info(stats.asMarkdownRaw());
    }


    public static void displayConfrontation(Confrontation confrontation,
                                            ResolutionStrategy strategy) {
        log.info("Sample for confrontation {}d vs {}d",
                confrontation.getProtagonist(),
                confrontation.getAntagonist());
        for (int i = 1; i <= ITERATIONS; i++) {
            log.info(strategy.resolve(confrontation).toString());
        }
    }
}

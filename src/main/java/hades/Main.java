package hades;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
public class Main {

    private static long ITERATIONS = 100 ;

    public static void main(String... args) {
        //simpleMain();
        simpleStats(new SimpliestResolutionStrategy());
        //simpleStats(new EscalationResolutionStrategy());
    }

    public static void simpleStats(ResolutionStrategy strategy) {
        log.info(strategy.getClass().getSimpleName());
        int iterations = 100000 ;
        Stats stats = new Stats();
        stats.setIterations(iterations);
        stats.setConfrontation(new Confrontation(1, 1));
        stats.doStats(strategy);
        log.info(stats.toString());
        stats.setConfrontation(new Confrontation(2, 2));
        stats.doStats(strategy);
        log.info(stats.toString());
        stats.setConfrontation(new Confrontation(3, 3));
        stats.doStats(strategy);
        log.info(stats.toString());
        stats.setConfrontation(new Confrontation(4, 4));
        stats.doStats(strategy);
        log.info(stats.toString());

        stats.setConfrontation(new Confrontation(1, 1));
        stats.doStats(strategy);
        log.info(stats.toString());
        stats.setConfrontation(new Confrontation(1, 2));
        stats.doStats(strategy);
        log.info(stats.toString());
        stats.setConfrontation(new Confrontation(1, 3));
        stats.doStats(strategy);
        log.info(stats.toString());

        stats.setConfrontation(new Confrontation(2, 1));
        stats.doStats(strategy);
        log.info(stats.toString());
        stats.setConfrontation(new Confrontation(3, 2));
        stats.doStats(strategy);
        log.info(stats.toString());
        stats.setConfrontation(new Confrontation(4, 3));
        stats.doStats(strategy);
        log.info(stats.toString());
        stats.setConfrontation(new Confrontation(5,4 ));
        stats.doStats(strategy);
        log.info(stats.toString());

        stats.setConfrontation(new Confrontation(3, 1));
        stats.doStats(strategy);
        log.info(stats.toString());
        stats.setConfrontation(new Confrontation(4, 2));
        stats.doStats(strategy);
        log.info(stats.toString());
        stats.setConfrontation(new Confrontation(5, 3));
        stats.doStats(strategy);
        log.info(stats.toString());
        stats.setConfrontation(new Confrontation(6,4 ));
        stats.doStats(strategy);
        log.info(stats.toString());

    }

    public static void simpleMain() {
        ResolutionStrategy strategy = new SimpliestResolutionStrategy();
        displayConfrontation(new Confrontation(1,1), strategy);
        displayConfrontation(new Confrontation(2,2), strategy);
        displayConfrontation(new Confrontation(3,3), strategy);
    }

    public static void displayConfrontation(Confrontation confrontation,
                                     ResolutionStrategy strategy) {
        log.info("Sample for confrontation {}d vs {}d",
                confrontation.getProtagonist(),
                confrontation.getAntagonist());
        for (int i=1; i <=ITERATIONS ; i++) {
            log.info(strategy.resolve(confrontation).toString());
        }
    }
}

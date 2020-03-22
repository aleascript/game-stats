package soloplay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) {
        Calculator calculator = new Calculator();
        log.info("Calculator initialized with size={}", calculator.getSituations().size());
        /*for (Situation s: calculator.getSortedSituations()) {
        //for (Situation s: calculator.getSituations()) {
            log.info(s.toString());
        }*/
        Map<Situation, List<Situation>> iterations = calculator.getIterations();
        for (Situation s: calculator.getSortedSituations()) {
            log.info("{} : {}", s, iterations.get(s).size());
        }
    }

}

package soloplay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) {
        printSecondDigitStrategy();
        //printSumAllStrategy();
    }

    private static void printSumAllStrategy() {
        Calculator calculator = new Calculator(0,9);
        log.info("Calculator (0,9) initialized with size={}", calculator.getSituations().size());
        IterationStrategy strategy = new SumAllStrategy(9, true);
        log.info("Iterate with sum all strategy and zero is transformed to max");
        printResult(calculator,strategy);
        strategy = new SumAllStrategy(9, false);
        log.info("Iterate with sum all strategy and zero is NOT transformed to max");
        printResult(calculator,strategy);
        calculator = new Calculator(1,10);
        log.info("Calculator (1,10) initialized with size={}", calculator.getSituations().size());
        strategy = new SumAllStrategy(10, true);
        log.info("Iterate with sum all strategy and zero is transformed to max");
        printResult(calculator,strategy);
        strategy = new SumAllStrategy(10, false);
        log.info("Iterate with sum all strategy and zero is NOT transformed to max");
        printResult(calculator,strategy);
    }

    private static void printSecondDigitStrategy() {
        Calculator calculator = new Calculator(0,9);
        log.info("Calculator (0,9) initialized with size={}", calculator.getSituations().size());
        IterationStrategy strategy = new SecondDigitOnlyStrategy(9);
        log.info("Iterate with second digit strategy");
        printResult(calculator,strategy);
        calculator = new Calculator(1,10);
        log.info("Calculator (1,10) initialized with size={}", calculator.getSituations().size());
        strategy = new SecondDigitOnlyStrategy(10);
        log.info("Iterate with sum all strategy and zero is transformed to max");
        printResult(calculator,strategy);
    }

    private static void printResult(Calculator calculator, IterationStrategy strategy) {
        Map<Situation, List<Situation>> iterations = calculator.getIterations(strategy);
        for (Situation s: calculator.getSortedSituations()) {
            System.out.println(s.toString()+": "+iterations.get(s).size());
        }

    }

}

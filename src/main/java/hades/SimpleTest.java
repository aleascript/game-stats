package hades;

import core.Helpers;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SimpleTest {

    int numberOfDice ;

    public SimpleTest(int numberOfDice) {
       this.numberOfDice = numberOfDice ;
    }

    public int resolve() {
        List<Integer> roll = Helpers.poolRoll(numberOfDice);
        List<Integer> withChaosRoll = Helpers.withSimpleChaos(roll);
        int successes = Helpers.getSuccesses(withChaosRoll);
        int failures = Helpers.getFailures(withChaosRoll);
        if (successes >= 3) {
            return 3 ;
        } else if (successes > 0) {
            return successes ;
        } else {
            return 0 ;
        }
    }

}

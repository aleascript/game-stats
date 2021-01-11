package hadesv2;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;

@Getter
@Setter
@ToString
@Slf4j
public class Roll {

    int numOfDice ;
    EngineDefinition engineDefinition ;

    public Roll(EngineDefinition engineDefinition, int numOfDice) {
        this.engineDefinition = engineDefinition;
        this.numOfDice = numOfDice ;
    }

    public Pair<Integer, Integer> getRoll() {
        // First we roll the dice
        List<Integer> result = new ArrayList<>();
        for (int i=1; i <= numOfDice; i++) {
            Integer roll = engineDefinition.getDice().roll();
            result.add(roll);
        }
        //log.info("DEBUG: roll was {}", result);
        return new Pair<>(getRollForAntagonist(result), getRollForProtagonist(result));
    }

    public Pair<Result,Result> getResult() {
        Pair<Integer, Integer> roll = getRoll();
        Pair<Result,Result> result = new Pair<Result,Result>(
                getResult(roll.getValue0()),
                getResult(roll.getValue1()));
        return result ;
    }

    public Result getResult(Integer digit) {
        return engineDefinition.getResolutionDefinition().getResultMap().get(digit);
    }

    public List<Integer> getPairs(List<Integer> results) {
        List<Integer> pairs = new ArrayList<>();
        for (Integer i: results) {
            if (i % 2 == 0) {
                pairs.add(i);
            }
        }
        return pairs ;
    }

    public List<Integer> getImpairs(List<Integer> results) {
        List<Integer> impairs = new ArrayList<>();
        for (Integer i: results) {
            if (i % 2 != 0) {
                impairs.add(i);
            }
        }
        return impairs ;
    }

    public Integer getMax(List<Integer> results) {
        return  results
                .stream()
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
    }

    public Integer getMin(List<Integer> results) {
        return  results
                .stream()
                .mapToInt(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
    }

    public Integer getRollForProtagonist(List<Integer> results) {
        List<Integer> pairs = getPairs(results);
        if (!pairs.isEmpty()) {
            return getMax(pairs);
        } else {
            List<Integer> impairs = getImpairs(results);
            return getMin(impairs);
        }
    }

    public Integer getRollForAntagonist(List<Integer> results) {
        List<Integer> impairs = getImpairs(results);
        if (!impairs.isEmpty()) {
            return getMax(impairs);
        } else {
            List<Integer> pairs = getPairs(results);
            return getMin(pairs);
        }
    }

}

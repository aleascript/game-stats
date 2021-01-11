package hadesv2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.javatuples.Pair;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class RollStats {

    private static final int DEFAULT_ITERATIONS = 100 ;
    private int iterations = DEFAULT_ITERATIONS ;
    private EngineDefinition engineDefinition ;

    public RollStats(EngineDefinition engineDefinition) {
        this.engineDefinition = engineDefinition ;
    }


    Map<Result, Long> initResultMap() {
        Map<Result,Long> map = new HashMap<Result,Long>();
        for (Result result: Result.values()) {
            map.put(result, 0L);
        }
        return map ;
    }

    public Pair<StatResult,StatResult> calculate(int numOfDice) {
        Roll roll = new Roll(engineDefinition, numOfDice);
        Map<Result,Long> antagonistResultMap = initResultMap() ;
        Map<Result,Long> protagonistResultMap = initResultMap() ;
        for (int i=1; i <= iterations; i++) {
            Pair<Result, Result> resultAB = roll.getResult();
            Result antagonistResult = resultAB.getValue0();
            Long initialAntagonist = antagonistResultMap.get(antagonistResult);
            antagonistResultMap.put(antagonistResult, initialAntagonist+1 );
            Result protagonistResult = resultAB.getValue1();
            Long initialProtagonist = protagonistResultMap.get(protagonistResult);
            protagonistResultMap.put(protagonistResult, initialProtagonist+1);
        }
        StatResult antagonistResult = new StatResult(
                antagonistResultMap.get(Result.INFORTUNE) * 100 / iterations,
                antagonistResultMap.get(Result.DEFAITE) * 100 / iterations,
                antagonistResultMap.get(Result.REVERS) * 100 / iterations,
                antagonistResultMap.get(Result.REUSSITE) * 100 / iterations,
                antagonistResultMap.get(Result.VICTOIRE) * 100 / iterations,
                antagonistResultMap.get(Result.FORTUNE) * 100 / iterations
        );
        StatResult protagonistResult = new StatResult(
                protagonistResultMap.get(Result.INFORTUNE) * 100 / iterations,
                protagonistResultMap.get(Result.DEFAITE) * 100 / iterations,
                protagonistResultMap.get(Result.REVERS) * 100 / iterations,
                protagonistResultMap.get(Result.REUSSITE) * 100 / iterations,
                protagonistResultMap.get(Result.VICTOIRE) * 100 / iterations,
                protagonistResultMap.get(Result.FORTUNE) * 100 / iterations
        );
        return new Pair<StatResult,StatResult>(antagonistResult, protagonistResult) ;
    }


}

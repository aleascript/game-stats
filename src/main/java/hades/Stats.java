package hades;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class Stats {

    private static final int DEFAULT_ITERATIONS = 100 ;
    private int iterations = DEFAULT_ITERATIONS ;

    private List<Pair<Integer,Double>> resultDistribution = new ArrayList<Pair<Integer,Double>>() ;
    private List<Pair<Integer,Double>> resultRollNumbers = new ArrayList<Pair<Integer,Double>>() ;
    private Double equality ;
    private Double success  ;
    private Double failure  ;

    private Confrontation confrontation ;

    public void setConfrontation(Confrontation confrontation) {
        this.confrontation = confrontation ;
        resultDistribution = new ArrayList<Pair<Integer,Double>>() ;
        resultRollNumbers = new ArrayList<Pair<Integer,Double>>() ;
    }

    public void doStats(ResolutionStrategy strategy) {
        if (confrontation == null) {
            throw new RuntimeException("You should set a Confrontation");
        }

        List<Resolution> sample = new ArrayList<Resolution>();
        for (int i = 1; i <= iterations; i++) {
            Resolution resolution = strategy.resolve(confrontation);
            sample.add(resolution);
        }
        Map<Integer,List<Resolution>> sampleByResult = sample.stream().collect(Collectors.groupingBy(
                resolution -> resolution.getResult()
        ));
        TreeMap<Integer, List<Resolution>> sampleByResultSorted = new TreeMap<>();
        sampleByResultSorted.putAll(sampleByResult);

        for (Integer result : sampleByResultSorted.keySet()) {
            resultDistribution.add(new Pair<Integer, Double>(result, (double) 100 * sampleByResultSorted.get(result).size()/iterations));
        }

        success = 0.0 ;
        for (Integer result : sampleByResultSorted.keySet()) {
            if (result > 0) {
                success += sampleByResultSorted.get(result).size();
            }
        }
        success = 100 * success / iterations ;
        failure = 0.0 ;
        for (Integer result : sampleByResultSorted.keySet()) {
            if (result < 0) {
                failure += sampleByResultSorted.get(result).size();
            }
        }
        failure = 100 * failure / iterations ;
        if (sampleByResultSorted.containsKey(0)) {
            equality = (double) 100 * sampleByResultSorted.get(0).size()/iterations;
        } else {
            equality = 0.0 ;
        }


        Map<Integer, List<Resolution>> sampleByRolls = sample.stream().collect(Collectors.groupingBy(
                resolution -> resolution.getRolls().size()
        ));
        for (Integer result : sampleByRolls.keySet()) {
            resultRollNumbers.add(new Pair<Integer, Double>(result, (double) 100 * sampleByRolls.get(result).size()/iterations));
        }


    }

}

package hades;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.javatuples.Pair;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString(exclude = {"equality", "iterations", "resultRollNumbers"})
public class Stats {

    private static final int DEFAULT_ITERATIONS = 100;
    private int iterations = DEFAULT_ITERATIONS;

    private List<Pair<Integer, Double>> resultDistribution = new ArrayList<Pair<Integer, Double>>();
    private List<Pair<Integer, Double>> resultRollNumbers = new ArrayList<Pair<Integer, Double>>();

    private Double equality;
    private Double success;
    private Double failure;
    private Double fortunes;

    private Confrontation confrontation;

    public void setConfrontation(Confrontation confrontation) {
        this.confrontation = confrontation;
        resultDistribution = new ArrayList<Pair<Integer, Double>>();
        resultRollNumbers = new ArrayList<Pair<Integer, Double>>();
        equality = 0.0;
        success = 0.0;
        failure = 0.0;
        fortunes = 0.0;
    }

    public void doStats(ResolutionStrategy strategy) {
        if (confrontation == null) {
            throw new RuntimeException("You should set a Confrontation");
        }

        List<Resolution> sample = new ArrayList<Resolution>();
        Double countFortunes = 0.0;
        for (int i = 1; i <= iterations; i++) {
            Resolution resolution = strategy.resolve(confrontation);
            sample.add(resolution);
            countFortunes += resolution.getFortune();
        }

        fortunes = 100 * countFortunes / iterations;


        Map<Integer, List<Resolution>> sampleByResult = sample.stream().collect(Collectors.groupingBy(
                resolution -> resolution.getResult()
        ));
        TreeMap<Integer, List<Resolution>> sampleByResultSorted = new TreeMap<>();
        sampleByResultSorted.putAll(sampleByResult);

        for (Integer result : sampleByResultSorted.keySet()) {
            resultDistribution.add(new Pair<Integer, Double>(result, (double) 100 * sampleByResultSorted.get(result).size() / iterations));
        }

        success = 0.0;
        for (Integer result : sampleByResultSorted.keySet()) {
            if (result > 0) {
                success += sampleByResultSorted.get(result).size();
            }
        }
        success = 100 * success / iterations;
        failure = 0.0;
        for (Integer result : sampleByResultSorted.keySet()) {
            if (result < 0) {
                failure += sampleByResultSorted.get(result).size();
            }
        }
        failure = 100 * failure / iterations;
        if (sampleByResultSorted.containsKey(0)) {
            equality = (double) 100 * sampleByResultSorted.get(0).size() / iterations;
        } else {
            equality = 0.0;
        }


        Map<Integer, List<Resolution>> sampleByRolls = sample.stream().collect(Collectors.groupingBy(
                resolution -> resolution.getRolls().size()
        ));
        for (Integer result : sampleByRolls.keySet()) {
            resultRollNumbers.add(new Pair<Integer, Double>(result, (double) 100 * sampleByRolls.get(result).size() / iterations));
        }


    }

    public Double getPercentage(int score) {
        for (Pair<Integer, Double> pair : resultDistribution) {
            if (pair.getValue0() == score) {
                return pair.getValue1();
            }
        }
        return 0.0;
    }

    public String getPercentageAsString(Double percentage) {
        if (percentage.equals(0.0)) {
            return "N/A";
        } else if (percentage < 1.0) {
            return "~0";
        } else {
            DecimalFormat df = new DecimalFormat("##");
            return df.format(percentage);
        }
    }


    public String asMarkdowHeader() {
        return "|Protagoniste|Obstacle|Succes|Echec|-3|-2|-1|+1|+2|+3|Fortunes|"
                + "\n"
                + "|---|---|---|---|---|---|---|---|---|---|---|";
    }

    public String asMarkdownRaw() {
        String template = "|%sD|%sD|%s|%s|%s|%s|%s|%s|%s|%s|%s|";
        return String.format(template,
                confrontation.getProtagonist(),
                confrontation.getAntagonist(),
                getPercentageAsString(success),
                getPercentageAsString(failure),
                getPercentageAsString(getPercentage(-3)),
                getPercentageAsString(getPercentage(-2)),
                getPercentageAsString(getPercentage(-1)),
                getPercentageAsString(getPercentage(1)),
                getPercentageAsString(getPercentage(2)),
                getPercentageAsString(getPercentage(3)),
                getPercentageAsString(fortunes)
        );
    }

}

package hades;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SimpleTestStats {

    private static final int DEFAULT_ITERATIONS = 100 ;
    private int iterations = DEFAULT_ITERATIONS ;
    private SimpleTest simpleTest ;

    private Double equality ;
    private Double presque  ;
    private Double success  ;
    private Double exploit ;


    public void setSimpleTest(SimpleTest simpleTest) {
        this.simpleTest = simpleTest ;
        equality = 0.0 ;
        presque = 0.0 ;
        success = 0.0 ;
        exploit = 0.0 ;
    }

    public void doStats() {

        Double zeros = 0.0;
        Double ones = 0.0;
        Double twos = 0.0;
        Double threes = 0.0;

        List<Integer> sample = new ArrayList<>();
        for (int i = 1; i <= iterations; i++) {
            int result = simpleTest.resolve() ;
            switch (result) {
                case 0:
                    zeros += 1 ;
                    break ;
                case 1:
                    ones += 1 ;
                    break;
                case 2:
                    twos += 1;
                    break ;
                case 3:
                    threes += 1;
                    break ;
            }
        }

        equality = 100 * zeros / iterations ;
        presque = 100 * ones / iterations ;
        success = 100 * twos / iterations ;
        exploit = 100 * threes / iterations ;

    }

    public String asMarkdowHeader() {
        return "|Test|0|+1|+2|+3|"
                +"\n"
                +"|---|---|---|---|---|";
    }

    public String asMarkdownRaw() {
        String template = "|%sD|%s|%s|%s|%s|";
        return String.format(template,
                simpleTest.getNumberOfDice(),
                equality,
                presque,
                success,
                exploit
        );
    }

}

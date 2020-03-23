package soloplay;

public class SecondDigitOnlyStrategy extends IterationStrategy {

    public SecondDigitOnlyStrategy(int maxDigit) {
        super(maxDigit);
    }

    @Override
    public Situation iterate(Situation initialSituation) {
        int delta = Math.abs(initialSituation.getMax() - initialSituation.getMin());
        int sum = reduceValue(initialSituation.getMin()+initialSituation.getMax());
        return new Situation(delta, sum);
    }

    private int reduceValue(int value) {
        if (value <= maxDigit) {
            return value ;
        } else {
            String valueAsString = Integer.toString(value);
            int secondDigit = Character.getNumericValue(valueAsString.charAt(1));
            return secondDigit ;
        }
    }
}

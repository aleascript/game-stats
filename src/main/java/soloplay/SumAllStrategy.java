package soloplay;

public class SumAllStrategy extends IterationStrategy {

    private boolean transformZeroToMax = false ;

    public SumAllStrategy(int maxDigit, boolean transformZeroToMax) {
        super(maxDigit);
        this.transformZeroToMax = transformZeroToMax;
    }

    @Override
    public Situation iterate(Situation initialSituation) {
        int delta = Math.abs(reduceValue(valueForOperation(initialSituation.getMax()) - valueForOperation(initialSituation.getMin())));
        int sum = reduceValue(valueForOperation(initialSituation.getMin()) + valueForOperation(initialSituation.getMax())) ;
        return new Situation(delta, sum);
    }

    public boolean transformZeroToMax() {
        return transformZeroToMax;
    }

    public void transformZeroToMax(boolean transformZeroToMax) {
        this.transformZeroToMax = transformZeroToMax;
    }

    private int reduceValue(int value) {
        if (value <= maxDigit) {
            return value ;
        } else {
            String valueAsString = Integer.toString(value);
            int firstDigit = Character.getNumericValue(valueAsString.charAt(0));
            if (transformZeroToMax && firstDigit==0) firstDigit = 10 ;
            int secondDigit = Character.getNumericValue(valueAsString.charAt(1));
            if (transformZeroToMax && secondDigit==0) secondDigit = 10 ;
            int result = reduceValue(firstDigit+secondDigit);
            while (result > maxDigit) {
                result = reduceValue(result);
            }
            return result ;
        }
    }

    private int valueForOperation(int value) {
        if (transformZeroToMax && value==0) {
            return maxDigit+1 ;
        } else {
            return value ;
        }
    }
}

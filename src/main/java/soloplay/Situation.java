package soloplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Situation {

    private int min;
    private int max;

    public Situation(int a, int b) {
        if (a > b) {
            this.min = b ;
            this.max = a ;
        } else {
            this.min = a ;
            this.max = b ;
        }
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public Situation iterate() {
        int delta = Math.abs(reduceValue(valueForOperation(max) - valueForOperation(min)));
        int sum = reduceValue(valueForOperation(min) + valueForOperation(max)) ;
        return new Situation(delta, sum);
    }

    public List<Situation> getIterations() {
        List<Situation> result = new ArrayList<Situation>();
        Situation iteration = iterate();
        while (iteration.equals(this)==false && result.contains(iteration)==false) {
            result.add(iteration);
            iteration = iteration.iterate();
        }
        return result ;
    }

    private int reduceValue(int value) {
        if (value <= 9) {
            return value ;
        } else {
            String valueAsString = Integer.toString(value);
            int firstDigit = Character.getNumericValue(valueAsString.charAt(0));
            if (firstDigit==0) firstDigit = 10 ;
            int secondDigit = Character.getNumericValue(valueAsString.charAt(1));
            if (secondDigit==0) secondDigit = 10 ;
            int result = reduceValue(firstDigit+secondDigit);
            while (result > 9) {
                result = reduceValue(result);
            }
            return result ;
        }
    }

    private int valueForOperation(int value) {
        if (value==0) {
            return 10 ;
        } else {
            return value ;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Situation that = (Situation) o;
        return min == that.min &&
                max == that.max;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }

    @Override
    public String toString() {
        return "Situation{" +
                "a=" + min +
                ", b=" + max +
                '}';
    }
}

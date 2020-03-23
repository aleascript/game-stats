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

    public Situation iterate(IterationStrategy strategy) {
        return strategy.iterate(this);
    }

    public List<Situation> getIterations(IterationStrategy strategy) {
        List<Situation> result = new ArrayList<Situation>();
        Situation iteration = iterate(strategy);
        while (iteration.equals(this)==false && result.contains(iteration)==false) {
            result.add(iteration);
            iteration = iteration.iterate(strategy);
        }
        return result ;
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

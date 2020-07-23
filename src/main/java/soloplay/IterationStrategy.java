package soloplay;

import soloplay.Situation;

public abstract class IterationStrategy {

    protected int maxDigit ;

    public IterationStrategy(int maxDigit) {
        this.maxDigit = maxDigit ;
    }

    /*public int getMaxDigit() {
        return maxDigit;
    }

    public void setMaxDigit(int maxDigit) {
        this.maxDigit = maxDigit;
    }*/

    public abstract Situation iterate(Situation initialSituation) ;
}

package soloplay;

import java.util.Random;

public class Dice {

    private int size ;

    public Dice(int size) {
        this.size = size ;
    }

    public int roll() {
        Random  r = new Random();
        return r.nextInt(size);
    }
}

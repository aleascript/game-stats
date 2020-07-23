package core;

import java.util.ArrayList;
import java.util.List;

public class Helpers {

    public static int getSuccessTotal(List<Integer> result) {
        int total = 0 ;
        for (Integer i: result) {
            if (i % 2 == 0) {
                total += i ;
            }
        }
        return total ;
    }

    public static int getFailureTotal(List<Integer> result) {
        int total = 0 ;
        for (Integer i: result) {
            if (i % 2 != 0) {
                total += i ;
            }
        }
        return total ;
    }

    public static int getSuccesses(List<Integer> result) {
        int numSuccess = 0 ;
        for (Integer i: result) {
            if (i % 2 == 0) {
                numSuccess += 1 ;
            }
        }
        return numSuccess ;
    }

    public static int getFailures(List<Integer> result) {
        int numFailures = 0 ;
        for (Integer i: result) {
            if (i % 2 != 0) {
                numFailures += 1 ;
            }
        }
        return numFailures ;
    }

    public static List<Integer> poolRoll(int size) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i=1; i <= size; i++) {
            Dice dice = new Dice(6);
            result.add(dice.roll());
        }
        return result ;
    }
}

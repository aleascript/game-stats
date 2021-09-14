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

    public static List<Integer> poolRoll(int size, int diceType) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i=1; i <= size; i++) {
            Dice dice = new Dice(diceType);
            result.add(dice.roll());
        }
        return result ;
    }

    public static List<Integer> poolRoll(int size) {
        return poolRoll(size, 6);
    }

    public static List<Integer> withChaos(List<Integer> result) {
        return withSimpleChaos(result, 6);
    }

    public static List<Integer> withSimpleChaos(List<Integer> result, int diceType) {
        List<Integer> newResult = new ArrayList<>();
        for (Integer i : result) {
            newResult.add(i);
            if (i==diceType) {
                Dice dice = new Dice(diceType);
                newResult.add(dice.roll());
            }
        }
        return  newResult;
    }

    public static List<Integer> withSimpleChaos(List<Integer> result) {
        List<Integer> newResult = new ArrayList<>();
        for (Integer i : result) {
            newResult.add(i);
            if (i==6) {
                Dice dice = new Dice(6);
                newResult.add(dice.roll());
            }
        }
        return  newResult;
    }
}

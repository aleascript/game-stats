package core;

import hades.Resolution;
import org.javatuples.Pair;

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

    public static void applyChaos(Resolution resolution, int diceType) {

        while (resolution.getNextDices().equals(new Pair<>(0L,0L))==false) {
            List<Integer> newProtagonistResult = new ArrayList<>(resolution.getRoll().getValue0());
            if (resolution.getNextDices().getValue0()>0) {
                for (int i = 1; i <= resolution.getNextDices().getValue0(); i++) {
                    Dice dice = new Dice(diceType);
                    newProtagonistResult.add(dice.roll());
                }
            }
            List<Integer> newAntagonistResult = new ArrayList<>(resolution.getRoll().getValue1());
            if (resolution.getNextDices().getValue1()>0) {
                for (int i = 1; i <= resolution.getNextDices().getValue1(); i++) {
                    Dice dice = new Dice(diceType);
                    newAntagonistResult.add(dice.roll());
                }
            }
            resolution.addRoll(new Pair<>(newProtagonistResult, newAntagonistResult));
        }

    }


    private static boolean hasChaos(List<Integer> roll, int diceType) {
       return roll.contains(diceType);
    }

    private static boolean shouldApplyChaos(List<Integer> precedentRoll, List<Integer> newRoll, int diceType) {
        if (newRoll == null) {
            return precedentRoll.contains(diceType);
        } else if (newRoll.stream().filter(i -> i == diceType).count() == precedentRoll.stream().filter(i -> i == diceType).count()) {
            return false ;
        } else {
            return true;
        }
    }


    public static List<Integer> withSimpleChaos(List<Integer> result) {
        return withSimpleChaos(result, 6);
    }
}

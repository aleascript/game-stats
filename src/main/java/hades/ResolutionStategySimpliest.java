package hades;

import core.Helpers;
import org.javatuples.Pair;

import java.util.List;

public class ResolutionStategySimpliest implements ResolutionStrategy {

    @Override
    public Resolution resolve(Confrontation confrontation) {
        Resolution resolution = new Resolution();
        List<Integer> protagonistResult = Helpers.poolRoll(confrontation.getProtagonist(), confrontation.getDiceType());
        protagonistResult = Helpers.withSimpleChaos(protagonistResult, confrontation.getDiceType());
        List<Integer> antagonistResult = Helpers.poolRoll(confrontation.getAntagonist(), confrontation.getDiceType());
        antagonistResult = Helpers.withSimpleChaos(antagonistResult, confrontation.getDiceType());
        resolution.addRoll(new Pair<List<Integer>, List<Integer>>(protagonistResult,antagonistResult));
        int protagonistSuccess = Helpers.getSuccesses(protagonistResult);
        int antagonistSuccess = Helpers.getSuccesses(antagonistResult);

        if (protagonistSuccess==antagonistSuccess) {
            // Failures only
            if (protagonistSuccess == 0) {
                resolution.setResult(-1);
                // Egalite des success
            } else {
                resolution.setResult(1);
            }
        } else {
            int realResult = protagonistSuccess - antagonistSuccess ;
            if (realResult > 3) {
                resolution.setResult(3);
            } else if (realResult < -3) {
                resolution.setResult(-3);
            } else {
                resolution.setResult(realResult);
            }
        }

        // Calculate Fortunes now
        if (confrontation.getProtagonist()==confrontation.getAntagonist()) {
            if (resolution.getResult()==-3 || resolution.getResult()==3) {
                resolution.setFortune(1);
            }
        }
        if (confrontation.getProtagonist()>confrontation.getAntagonist()) {
            if (resolution.getResult()==-3 || resolution.getResult()==-2) {
                resolution.setFortune(1);
            }
        }
        if (confrontation.getProtagonist()<confrontation.getAntagonist()) {
            if (resolution.getResult()==3 || resolution.getResult()==2) {
                resolution.setFortune(1);
            }
        }

        return resolution ;
    }




}

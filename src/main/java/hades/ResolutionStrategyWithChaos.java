package hades;

import core.Helpers;
import org.javatuples.Pair;

import java.util.List;

public class ResolutionStrategyWithChaos implements ResolutionStrategy {

    @Override
    public Resolution resolve(Confrontation confrontation) {
        Resolution resolution = new Resolution();
        List<Integer> protagonistResult = Helpers.poolRoll(confrontation.getProtagonist());
        protagonistResult = Helpers.withChaos(protagonistResult);
        List<Integer> antagonistResult = Helpers.poolRoll(confrontation.getAntagonist());
        antagonistResult = Helpers.withChaos(antagonistResult);
        resolution.addRoll(new Pair<List<Integer>, List<Integer>>(protagonistResult,antagonistResult));
        int protagonistSuccess = Helpers.getSuccesses(protagonistResult);
        int protagonistSuccessTotal = Helpers.getSuccessTotal(protagonistResult);
        int protagonistFailureTotal = Helpers.getFailureTotal(protagonistResult);
        int antagonistSuccess = Helpers.getSuccesses(antagonistResult);
        int antagonistSuccessTotal = Helpers.getSuccessTotal(antagonistResult);
        int antagonistFailureTotal = Helpers.getFailureTotal(antagonistResult);

        if (protagonistSuccess==antagonistSuccess) {
            // Failures only
            if (protagonistSuccess == 0) {
                if (protagonistFailureTotal  >= antagonistFailureTotal) {
                    resolution.setResult(1);
                } else  {
                    resolution.setResult(-1);
                }
            // Egalite des success
            } else {
                if (protagonistSuccessTotal >= antagonistSuccessTotal) {
                    resolution.setResult(1);
                } else  {
                    resolution.setResult(-1);
                }
            }
        } else {
            int realResult = protagonistSuccess - antagonistSuccess ;
            if (realResult == -1) {
                resolution.setResult(-1);
            } else if (realResult == 1) {
                resolution.setResult(1);
            } else if (realResult == -2) {
                resolution.setResult(-2);
            } else if (realResult == 2) {
                resolution.setResult(2);
            } else if (realResult == -3) {
                resolution.setResult(-3);
            } else if (realResult == 3) {
                resolution.setResult(3);
            } else if (realResult > 3) {
                resolution.setResult(3);
            } else if (realResult < -3) {
                resolution.setResult(-3);
            }
        }

        return resolution ;
    }




}

package hades;

import core.Helpers;
import org.javatuples.Pair;

import java.util.List;

public class ProtagonistResolutionStrategy implements ResolutionStrategy {

    @Override
    public Resolution resolve(Confrontation confrontation) {
        Resolution result = new Resolution();
        List<Integer> protagonistResult = Helpers.poolRoll(confrontation.getProtagonist());
        List<Integer> antagonistResult = Helpers.poolRoll(confrontation.getAntagonist());
        result.addRoll(new Pair<List<Integer>, List<Integer>>(protagonistResult,antagonistResult));
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
                    result.setResult(1);
                } else  {
                    result.setResult(-1);
                }
            // Egalite des success
            } else {
                if (protagonistSuccessTotal >= antagonistSuccessTotal) {
                    result.setResult(1);
                } else  {
                    result.setResult(-1);
                }
            }
        } else {
            result.setResult(protagonistSuccess - antagonistSuccess);
        }

        return result ;
    }




}

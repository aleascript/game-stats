package hades;

import core.Helpers;
import org.javatuples.Pair;

import java.util.List;

public class EscalationResolutionStrategy implements  ResolutionStrategy {
    @Override
    public Resolution resolve(Confrontation confrontation) {
        Resolution result = new Resolution();
        List<Integer> protagonistResult = Helpers.poolRoll(confrontation.getProtagonist());
        List<Integer> antagonistResult = Helpers.poolRoll(confrontation.getAntagonist());
        result.addRoll(new Pair<List<Integer>, List<Integer>>(protagonistResult,antagonistResult));
        int protagonistSuccess = Helpers.getSuccesses(protagonistResult);
        int protagonistSuccessTotal = Helpers.getSuccessTotal(protagonistResult);
        int protagonistFailureTotal = Helpers.getFailureTotal(protagonistResult);
        int protagonistFailures = Helpers.getFailures(protagonistResult);
        int antagonistSuccess = Helpers.getSuccesses(antagonistResult);
        int antagonistSuccessTotal = Helpers.getSuccessTotal(antagonistResult);
        int antagonistFailureTotal = Helpers.getFailureTotal(antagonistResult);
        int antogonistFailures = Helpers.getFailures(antagonistResult);


        if (protagonistSuccess == 0 && antagonistSuccess == 0) {
            Confrontation newConfrontation = new Confrontation(
                    confrontation.getProtagonist()+1,
                    confrontation.getAntagonist()+1);
            List<Pair<List<Integer>, List<Integer>>> currentRolls = result.getRolls();
            result = resolve(newConfrontation);
            List<Pair<List<Integer>, List<Integer>>> newRolls = result.getRolls();
            currentRolls.addAll(newRolls);
            result.setRolls(currentRolls);
        } else if (protagonistSuccess == antagonistSuccess) {
            Confrontation newConfrontation ;
            if (protagonistSuccessTotal > antagonistSuccessTotal) {
                newConfrontation = new Confrontation(
                        confrontation.getProtagonist()+1,
                        confrontation.getAntagonist());
            } else if (protagonistSuccessTotal < antagonistSuccessTotal) {
                newConfrontation = new Confrontation(
                        confrontation.getProtagonist(),
                        confrontation.getAntagonist()+1);
            } else {
                newConfrontation = new Confrontation(
                        confrontation.getProtagonist()+1,
                        confrontation.getAntagonist()+1);
            }
            List<Pair<List<Integer>, List<Integer>>> currentRolls = result.getRolls();
            result = resolve(newConfrontation);
            List<Pair<List<Integer>, List<Integer>>> newRolls = result.getRolls();
            currentRolls.addAll(newRolls);
            result.setRolls(currentRolls);
        } else if (protagonistSuccess != antagonistSuccess) {
            result.setResult(protagonistSuccess - antagonistSuccess);
        }
        return result ;
    }
}

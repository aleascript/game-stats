package hades;

import core.Dice;
import core.Helpers;
import hades.Confrontation;
import hades.ResolutionStrategy;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class SimpliestResolutionStrategy implements ResolutionStrategy {

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
                if (protagonistFailureTotal > antagonistFailureTotal) {
                    result.setResult(1);
                } else if (protagonistFailureTotal == antagonistFailureTotal) {
                    Confrontation newConfrontation = new Confrontation(
                            confrontation.getProtagonist()+1,
                            confrontation.getAntagonist()+1);
                    List<Pair<List<Integer>, List<Integer>>> currentRolls = result.getRolls();
                    result = resolve(newConfrontation);
                    List<Pair<List<Integer>, List<Integer>>> newRolls = result.getRolls();
                    currentRolls.addAll(newRolls);
                    result.setRolls(currentRolls);
                } else if (protagonistFailureTotal < antagonistFailureTotal) {
                    result.setResult(-1);
                }
            // Egalite des success
            } else {
                if (protagonistSuccessTotal > antagonistSuccessTotal) {
                    result.setResult(1);
                } else if (protagonistSuccessTotal == antagonistSuccessTotal) {
                    Confrontation newConfrontation = new Confrontation(
                            confrontation.getProtagonist()+1,
                            confrontation.getAntagonist()+1);
                    List<Pair<List<Integer>, List<Integer>>> currentRolls = result.getRolls();
                    result = resolve(newConfrontation);
                    List<Pair<List<Integer>, List<Integer>>> newRolls = result.getRolls();
                    currentRolls.addAll(newRolls);
                    result.setRolls(currentRolls);
                } else if (protagonistSuccessTotal < antagonistSuccessTotal) {
                    result.setResult(-1);
                }
            }
        } else {
            result.setResult(protagonistSuccess - antagonistSuccess);
        }

        return result ;
    }




}

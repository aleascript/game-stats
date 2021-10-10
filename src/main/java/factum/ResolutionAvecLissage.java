package factum;

import core.Helpers;
import org.javatuples.Pair;

import java.util.List;

public class ResolutionAvecLissage implements ResolutionStrategy {

    @Override
    public Resolution resolve(Confrontation confrontation) {
        Resolution resolution = new Resolution();
        List<Integer> protagonistResult = Helpers.poolRoll(confrontation.getProtagonist(), confrontation.getDiceType());
        List<Integer> antagonistResult = Helpers.poolRoll(confrontation.getAntagonist(), confrontation.getDiceType());
        resolution.addRoll(new Pair<List<Integer>, List<Integer>>(protagonistResult,antagonistResult));
        List<Integer> chaosProtagonistResult = Helpers.withSimpleChaos(protagonistResult, confrontation.getDiceType());
        List<Integer> chaosAntagonistResult = Helpers.withSimpleChaos(antagonistResult, confrontation.getDiceType());
        if (protagonistResult.size()!=chaosProtagonistResult.size() || antagonistResult.size()!=chaosAntagonistResult.size()) {
            resolution.addRoll(new Pair<List<Integer>, List<Integer>>(chaosProtagonistResult,chaosAntagonistResult));
        }
        int protagonistSuccess = Helpers.getSuccesses(chaosProtagonistResult);
        int antagonistSuccess = Helpers.getSuccesses(chaosAntagonistResult);

        int realResult = protagonistSuccess - antagonistSuccess ;
        if (realResult==0) {
            // Failures only
            if (protagonistSuccess == 0) {
                resolution.setResult(-1);
                // Egalite des success
            } else {
                resolution.setResult(1);
            }
        } else if (realResult < 0) {
            if (protagonistSuccess == 0) {
                if (antagonistSuccess >=2) {
                    resolution.setResult(-3);
                }  else {
                    resolution.setResult(-2);
                }
            } else {
                if (antagonistSuccess > protagonistSuccess * 2) {
                    resolution.setResult(-3);
                } else {
                    resolution.setResult(-2);
                }
            }
        } else if (realResult > 0) {
            if (antagonistSuccess == 0) {
                if (protagonistSuccess >=2) {
                    resolution.setResult(3);
                }  else {
                    resolution.setResult(2);
                }
            } else {
                if (protagonistSuccess > antagonistSuccess * 2) {
                    resolution.setResult(3);
                } else {
                    resolution.setResult(2);
                }
            }
        }


        // Calculate Fortunes now
        if (confrontation.getProtagonist()>=confrontation.getAntagonist()) {
            if (resolution.getResult()==-3) {
                resolution.setFortune(1);
            }
        }
        if (confrontation.getProtagonist()<confrontation.getAntagonist()) {
            if (resolution.getResult()==3) {
                resolution.setFortune(1);
            }
        }


        return resolution;
    }
}

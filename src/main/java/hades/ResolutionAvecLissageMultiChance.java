package hades;

import core.Helpers;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ResolutionAvecLissageMultiChance implements ResolutionStrategy {


    private long countChaosResult(List<Integer> integers, int diceType) {
        return integers.stream().filter(i -> i==diceType).count();
    }

    @Override
    public Resolution resolve(Confrontation confrontation) {
        //log.info("Resolve {}", this);
        Resolution resolution = new Resolution();
        resolution.setDiceSize(confrontation.getDiceType());
        List<Integer> protagonistResult = Helpers.poolRoll(confrontation.getProtagonist(), confrontation.getDiceType());
        List<Integer> antagonistResult = Helpers.poolRoll(confrontation.getAntagonist(), confrontation.getDiceType());
        resolution.addRoll(new Pair<>(protagonistResult,antagonistResult));
        Helpers.applyChaos(resolution, confrontation.getDiceType());
        int protagonistSuccess = Helpers.getSuccesses(resolution.getRoll().getValue0());
        int antagonistSuccess = Helpers.getSuccesses(resolution.getRoll().getValue1());

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

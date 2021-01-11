package hadesv2;

import core.Dice;

public class EngineGenerator {

    public static EngineDefinition d12() {
        EngineDefinition engineDefinition = new EngineDefinition();
        engineDefinition.setDice(new Dice(12));
        ResolutionDefinition resolutionDefinition = new ResolutionDefinition();
        engineDefinition.setResolutionDefinition(resolutionDefinition);
        resolutionDefinition.putResultDefinition(Result.INFORTUNE, new Integer[] {11});
        resolutionDefinition.putResultDefinition(Result.DEFAITE, new Integer[] {7,9});
        resolutionDefinition.putResultDefinition(Result.REVERS, new Integer[] {1,3,5});
        resolutionDefinition.putResultDefinition(Result.REUSSITE, new Integer[] {2,4,6});
        resolutionDefinition.putResultDefinition(Result.VICTOIRE, new Integer[] {8,10});
        resolutionDefinition.putResultDefinition(Result.FORTUNE, new Integer[] {12});
        return engineDefinition ;
    }

    public static EngineDefinition d6() {
        EngineDefinition engineDefinition = new EngineDefinition();
        engineDefinition.setDice(new Dice(6));
        ResolutionDefinition resolutionDefinition = new ResolutionDefinition();
        engineDefinition.setResolutionDefinition(resolutionDefinition);
        resolutionDefinition.putResultDefinition(Result.INFORTUNE, new Integer[] {5});
        resolutionDefinition.putResultDefinition(Result.DEFAITE, new Integer[] {3});
        resolutionDefinition.putResultDefinition(Result.REVERS, new Integer[] {1});
        resolutionDefinition.putResultDefinition(Result.REUSSITE, new Integer[] {2});
        resolutionDefinition.putResultDefinition(Result.VICTOIRE, new Integer[] {4});
        resolutionDefinition.putResultDefinition(Result.FORTUNE, new Integer[] {6});
        return engineDefinition ;
    }
}

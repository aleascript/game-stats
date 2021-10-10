import factum.Confrontation;
import factum.ResolutionAvecLissageMultiChance;
import org.junit.Test;

public class ResolutionAvecLissageMultiChanceTest {

    @Test
    public void resolveTest() {
        Confrontation confrontation = new Confrontation(2, 2, 6);
        ResolutionAvecLissageMultiChance resolution = new ResolutionAvecLissageMultiChance();
        resolution.resolve(confrontation);
        System.out.println(resolution.toString());
    }

}

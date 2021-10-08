import hades.Resolution;
import org.javatuples.Pair;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;


public class ResolutionTest {

    @Test
    public void addRoleTest() {
        Resolution resolution = new Resolution();
        Integer delta = resolution.addRoll(new Pair(List.of(1,2,3), List.of(4,5)));
        assertTrue(delta.equals(5));
        assertTrue(resolution.countRolls()==1);
        delta = resolution.addRoll(new Pair(List.of(1,2,3), List.of(4,5,6)));
        assertTrue(delta.equals(1));
        assertTrue(resolution.countRolls()==2);
        delta = resolution.addRoll(new Pair(List.of(1,2,3), List.of(4,5,6)));
        assertTrue(delta.equals(0));
        assertTrue(resolution.countRolls()==2);
    }

}

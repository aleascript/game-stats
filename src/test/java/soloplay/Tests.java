package soloplay;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class Tests {

    private static Logger log = LoggerFactory.getLogger(Tests.class);

    @Test
    public void testReduction() {
        assertEquals(new Situation(3,5), new Situation(1,4).iterate());
        assertEquals(new Situation(1,2), new Situation(0,9).iterate());
        assertEquals(new Situation(0,3), new Situation(0,0).iterate());
        assertEquals(new Situation(6,3), new Situation(3,9).iterate());
    }

    @Test
    public void testIterations() {
        Situation s = new Situation(4,7);
        log.info(s.toString());
        for (Situation i: s.getIterations()) {
            log.info(i.toString());
        }
    }

}

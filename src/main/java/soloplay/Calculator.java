package soloplay;

import java.util.*;

public class Calculator {

    Set<Situation> situations = new HashSet<Situation>();

    public Calculator() {
        for (int i =0; i < 10; i++) {
            for (int j=0; j < 10; j++) {
                Situation situation = new Situation(i,j);
                situations.add(situation);
            }
        }
    }

    public Set<Situation> getSituations() {
        return situations;
    }

    public List<Situation> getSortedSituations() {
        List<Situation> result = new ArrayList<Situation>();
        Iterator<Situation> iterator = situations.stream().sorted((o1, o2) -> {
            if (o2.getMin() > o1.getMin()) {
                return -1 ;
            } else if (o2.getMin() < o1.getMin()) {
                return 1 ;
            } else if (o2.getMax() > o1.getMax()) {
                return -1;
            } else if (o2.getMax() < o1.getMax()) {
                return 1;
            } else {
                return 0;
            }
        }).iterator();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result ;
    }

    public Map<Situation, List<Situation>> getIterations() {
        Map<Situation,List<Situation>> result = new HashMap<Situation,List<Situation>>();
        for (Situation s: situations) {
            result.put(s, s.getIterations());
        }
        return result ;
    }


}

package hades;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Resolution {

    private int result ;
    private List<Pair<List<Integer>, List<Integer>>> rolls
            = new ArrayList<>();
    private int fortune = 0 ;

    public void addRoll(Pair<List<Integer>,List<Integer>> roll) {
        rolls.add(roll);
    }

    public int countRolls() {
        return rolls.size();
    }

}

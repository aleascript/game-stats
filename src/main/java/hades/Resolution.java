package hades;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Slf4j
public class Resolution {

    private int result ;
    private Pair<List<Integer>, List<Integer>> roll;
    private int rollCount = 0;
    private int fortune = 0 ;
    private Integer diceSize ;
    private Pair<Long,Long> nextDices;

    public void addRoll(Pair<List<Integer>,List<Integer>> roll) {
        rollCount ++ ;
        if (this.roll == null) {
            nextDices = new Pair(
                    roll.getValue0().stream().filter(i -> i == diceSize).count(),
                    roll.getValue1().stream().filter(i -> i == diceSize).count()
            );
        } else {
            nextDices = new Pair(
                    roll.getValue0().stream().filter(i -> i == diceSize).count() - this.roll.getValue0().stream().filter(i -> i == diceSize).count(),
                    roll.getValue1().stream().filter(i -> i == diceSize).count() - this.roll.getValue1().stream().filter(i -> i == diceSize).count()
            );
        }
        this.roll = new Pair<>(new ArrayList<>(roll.getValue0()), new ArrayList<>(roll.getValue1()));
    }



    public int countRolls() {
        return rollCount;
    }


}

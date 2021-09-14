package hades;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Confrontation {

    public Confrontation(int protagonist, int antagonist) {
        this.protagonist = protagonist;
        this.antagonist = antagonist;
        this.diceType = 6;
    }

    private final int protagonist ;
    private final int antagonist ;
    private final int diceType ;

}

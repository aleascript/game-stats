package hadesv2;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Result {

    INFORTUNE("Infortune", -3),
    DEFAITE("Defaite", -2),
    REVERS("Revers", -1),
    REUSSITE("Reussite", 1),
    VICTOIRE("Victoire", 2),
    FORTUNE("Fortune", 3);

    String label ;
    int digit ;

    Result(String label, int digit) {
        this.label = label ;
        this.digit = digit ;
    }

    public static Result valueOf(int i) {
        for (Result result: Result.values()) {
            if (result.getDigit()==i) {
                return result ;
            }
        }
        return null ;
    }

}

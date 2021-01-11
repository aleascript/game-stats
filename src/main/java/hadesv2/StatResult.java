package hadesv2;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class StatResult {

    double infortunes ;
    double defaites ;
    double revers ;
    double reussites ;
    double victoires ;
    double fortunes ;

    public double getSuccess() {
        return reussites + victoires + fortunes ;
    }

    public double getFailures() {
        return revers + defaites + infortunes ;
    }

    public double getSoftImpacts() {
        return reussites + revers ;
    }

    public double getHardImpacts() {
        return defaites + victoires ;
    }

    public double getStoryImpacts() {
        return infortunes + fortunes ;
    }


    public String toCSV() {
        return infortunes+":"+defaites+":"+revers+":"+reussites+":"+victoires+":"+fortunes+":"
                +getSuccess()+":"+getFailures()+":"
                +getSoftImpacts()+":"+getHardImpacts()+":"+getStoryImpacts();
    }

    @Override
    public String toString() {
        return "\n"
                +"Infortunes="+infortunes+"%\n"
                +"Defaites="+defaites+"%\n"
                +"Revers="+revers+"%\n"
                +"Reussites="+reussites+"%\n"
                +"Victoires="+victoires+"%\n"
                +"Fortunes="+fortunes+"%\n"
                +"Success="+getSuccess()+"% | Failures="+getFailures()+"%\n"
                +"Soft="+getSoftImpacts()+"% | Hard="+getHardImpacts()+"% | Story="+getStoryImpacts()+"%";
    }

}

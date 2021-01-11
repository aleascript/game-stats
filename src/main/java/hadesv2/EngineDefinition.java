package hadesv2;

import core.Dice;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EngineDefinition {

    /**
     * Set the reference dice
     */
    Dice dice ;

    /**
     * Set the resolutions (infortunes, defaites, revers, reussites, victoires, fortunes)
     */
    ResolutionDefinition resolutionDefinition ;


}

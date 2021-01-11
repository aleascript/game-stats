package hadesv2;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ResolutionDefinition {

    private Map<Integer, Result> resultMap = new HashMap<>();

    public void putResultDefinition(Result result, Integer[] rolls) {
        for (int i: rolls) {
            resultMap.put(i, result);
        }
    }

}

package kurylo.tech.calculator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.NONE)
public class Calculator {
    public static int add(String numbers){
        if(numbers.isEmpty()){
            return 0;
        } else {
            return Stream.of(numbers.split("[,\n]"))
                    .map(Integer::parseInt)
                    .reduce(0, Integer::sum);
        }
    }
}

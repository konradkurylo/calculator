package kurylo.tech.calculator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class Calculator {
    public static int add(String numbers){
        if(numbers.isEmpty()){
            return 0;
        } else {
            return Integer.parseInt(numbers);
        }
    }
}

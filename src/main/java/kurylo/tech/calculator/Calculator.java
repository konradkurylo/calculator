package kurylo.tech.calculator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.NONE)
public class Calculator {
    private static final String DEFAULT_DELIMITER = ",";

    public static int add(String numbers){
        if(numbers.isEmpty()){
            return 0;
        } else {
            return nonEmptyStringCalculation(numbers);
        }
    }

    private static Integer nonEmptyStringCalculation(String numbers) {
        boolean customDelimiterPresence = numbers.startsWith("//");
        String delimiter = customDelimiterPresence ? numbers.substring(2,3) : DEFAULT_DELIMITER;
        String inputToProcess = customDelimiterPresence ? numbers.substring(3) : numbers;
        if( inputToProcess.contains(delimiter + "\n") || inputToProcess.contains("\n" + delimiter) ) {
            throw new IllegalArgumentException("Delimiter and 'next line sign' are next to each other. Wrong input");
        } else if (inputToProcess.replaceAll("["+ delimiter + "\n]", "").matches("\\D*")){
            throw new IllegalArgumentException("Non numeric character present. Wrong input");
        } else {
            return Stream.of(inputToProcess.split("["+ delimiter + "\n]"))
                    .filter(e->!e.isEmpty())
                    .map(Integer::parseInt)
                    .reduce(0, Integer::sum);
        }
    }
}

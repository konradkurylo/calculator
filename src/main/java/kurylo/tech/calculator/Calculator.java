package kurylo.tech.calculator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.NONE)
public class Calculator {

    public static int add(String numbers){
        if(numbers.isEmpty()){
            return 0;
        } else {
            Preprocessor preprocessor = Preprocessor.of(numbers);
            validateInput(preprocessor.getInputToProcess(), preprocessor.getDelimiter());
            return nonEmptyStringCalculation(preprocessor.getInputToProcess(), preprocessor.getDelimiter());
        }
    }

    private static void validateInput(String inputToProcess, String delimiter){
        if( inputToProcess.contains(delimiter + "\n") || inputToProcess.contains("\n" + delimiter) ) {
            throw new IllegalArgumentException("Delimiter and 'next line sign' are next to each other. Wrong input");
        }
        if (inputToProcess.replaceAll("["+ delimiter + "\n]", "").matches("\\D*")){
            throw new IllegalArgumentException("Non numeric character present. Wrong input");
        }
    }

    private static Integer nonEmptyStringCalculation(String inputToProcess, String delimiter) {
        return Stream.of(inputToProcess.split("["+ delimiter + "\n]"))
                .filter(e->!e.isEmpty())
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    static class Preprocessor {
        private static final String DEFAULT_DELIMITER = ",";
        private final String delimiter;
        private final String inputToProcess;

        static Preprocessor of(String input){
            boolean customDelimiterPresence = input.startsWith("//");
            return new Preprocessor(
                    customDelimiterPresence ? input.substring(2,3) : DEFAULT_DELIMITER,
                    customDelimiterPresence ? input.substring(3) : input
            );
        }



    }
}

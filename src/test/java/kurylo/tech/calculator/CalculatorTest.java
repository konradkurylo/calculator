package kurylo.tech.calculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class CalculatorTest {

    @ParameterizedTest
    @EnumSource(CalculatorHappyPathScenario.class)
    public void parameterizedHappyPathTest(CalculatorHappyPathScenario calculatorScenario){
        Assertions
                .assertThat(Calculator.add(calculatorScenario.getInput()))
                .isEqualTo(calculatorScenario.getResult());
    }

    @ParameterizedTest
    @EnumSource(CalculatorUnHappyPathScenario.class)
    public void parameterizedUnHappyPathTest(CalculatorUnHappyPathScenario calculatorScenario){
        org.junit.jupiter.api.Assertions.assertThrows(
                calculatorScenario.getException(),
                ()->Calculator.add(calculatorScenario.getInput()),
                calculatorScenario.getExceptionMessage()
        );
    }

    @Getter
    @AllArgsConstructor
    public enum CalculatorHappyPathScenario {
        EMPTY_STRING("",0),
        ONE_NUMBER("1",1),
        ANOTHER_ONE_NUMBER("10000",10_000),
        TWO_NUMBERS("1,2", 3),
        TWO_ANOTHER_NUMBERS("-5,2", -3),
        THREE_NUMBERS("10,20,30", 60),
        THREE_ANOTHER_NUMBERS("10000,20000,30000", 60_000),
        NEW_LINE_DELIMITER("1\n2,3", 6);

        private final String input;
        private final int result;
    }

    @Getter
    @AllArgsConstructor
    public enum CalculatorUnHappyPathScenario {
        NON_NUMERIC_CHARACTERS_PRESENT("a", IllegalArgumentException.class, "Non numeric character present. Wrong input"),
        NON_NUMERIC_CHARACTERS_PRESENT_ANOTHER("1,!\n", IllegalArgumentException.class, "Non numeric character present. Wrong input"),
        NON_NUMERIC_CHARACTERS_PRESENT_YET_ANOTHER("\n),1\n", IllegalArgumentException.class, "Non numeric character present. Wrong input"),
        TWO_DELIMITERS_AFTER_EACH_OTHER("1,\n", IllegalArgumentException.class, "Delimiter and 'next line sign' are next to each other. Wrong input"),
        TWO_DELIMITERS_AFTER_EACH_OTHER_ANOTHER_CASE("1,4\n,", IllegalArgumentException.class, "Delimiter and 'next line sign' are next to each other. Wrong input");

        private final String input;
        private final Class<? extends Throwable> exception;
        private final String exceptionMessage;
    }

}

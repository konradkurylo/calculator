package kurylo.tech.calculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class CalculatorTest {

    @ParameterizedTest
    @EnumSource(CalculatorScenario.class)
    public void parameterizedTest(CalculatorScenario calculatorScenario){
        Assertions
                .assertThat(Calculator.add(calculatorScenario.getInput()))
                .isEqualTo(calculatorScenario.getResult());
    }

    @Getter
    @AllArgsConstructor
    public enum CalculatorScenario {
        EMPTY_STRING("",0),
        ONE_NUMBER("1",1),
        ANOTHER_ONE_NUMBER("10000",10_000),
        TWO_NUMBERS("1,2", 3),
        TWO_ANOTHER_NUMBERS("-5,2", -3),
        THREE_NUMBERS("10,20,30", 60),
        THREE_ANOTHER_NUMBERS("10000,20000,30000", 60_000);

        private final String input;
        private final int result;
    }

}

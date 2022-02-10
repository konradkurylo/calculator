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
        ANOTHER_ONE_NUMBER("10000",10_000);

        private final String input;
        private final int result;
    }

}

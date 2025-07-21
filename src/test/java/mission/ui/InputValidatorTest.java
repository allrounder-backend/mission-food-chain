package mission.ui;

import java.util.Map;
import mission.ui.exception.InputError;
import mission.ui.exception.InvalidInputException;
import mission.ui.validate.InputValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {
    @Test
    void 정상입력이면_파싱된_값을_그대로_리턴한다() {
        // given
        String input = "[고등어-2],[정어리-3]";

        // when
        Map<String, Integer> result = InputValidator.validate(input);

        // then
        assertEquals(2, result.get("고등어"));
        assertEquals(3, result.get("정어리"));
        assertEquals(2, result.size());
    }

    @Test
    void 입력이_null이면_NULL_INPUT_예외발생() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> InputValidator.validate(null)
        );
        assertEquals(InputError.NULL_INPUT.getMessage(), exception.getMessage());
    }

    @Test
    void 입력이_빈문자열이면_NULL_INPUT_예외발생() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> InputValidator.validate("     ")
        );
        assertEquals(InputError.NULL_INPUT.getMessage(), exception.getMessage());
    }

    @Test
    void 형식에_맞지않으면_INVALID_FORMAT_예외발생() {
        InvalidInputException exception = assertThrows(
                InvalidInputException.class,
                () -> InputValidator.validate("{3-정어리}")
        );
        assertEquals(InputError.INVALID_FORMAT, exception.getError());
    }
}

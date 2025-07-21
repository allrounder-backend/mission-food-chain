package mission.domain.fish;

import java.util.Map;
import mission.domain.fish.FishType;
import mission.domain.fish.exception.DuplicateFishException;
import mission.domain.fish.exception.FishError;
import mission.domain.fish.exception.InvalidFishException;
import mission.domain.fish.utils.FishParser;
import mission.domain.fish.validate.FishValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FishValidatorTest {

    @Test
    void 존재하는_물고기_이름이면_FishType을_반환한다() {
        FishType type = FishValidator.mapAndValidate("정어리");
        assertEquals(FishType.SARDINE, type);
    }

    @Test
    void 존재하지_않는_물고기_이름이면_예외를_던진다() {
        InvalidFishException exception = assertThrows(
                InvalidFishException.class,
                () -> FishValidator.mapAndValidate("불가사리")
        );
        assertEquals(FishError.INVALID_FISH_NAME, exception.getError());
    }

}

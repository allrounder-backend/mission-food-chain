package mission;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import api.TestEnvironment;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ApplicationTest extends TestEnvironment {
    @Test
    void testApplication() {
        run(List.of("[플랑크톤-15],[정어리-3],[멸치-3],[꽁치-2]"));
        assertTrue(output().contains("5일간 생존했습니다."));
    }

    @Test
    void 예외발생테스트() {
        assertThrows(IllegalArgumentException.class, () -> run("잘못된 입력"));
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}

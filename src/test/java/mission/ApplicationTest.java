package mission;

import static org.junit.jupiter.api.Assertions.assertTrue;

import api.TestEnvironment;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ApplicationTest extends TestEnvironment {
    // common
    @Test
    void testApplication() {
        run(List.of("[플랑크톤-10],[정어리-3],[고등어-2]"));
        assertTrue(output().contains("3일간 생존했습니다. "));
    }

    @Test
    void 예외발생테스트() {
        run(List.of("잘못된입력"));
        assertTrue(output().contains("입력 형식이 올바르지 않습니다."));
    }

    @Test
    void 빈문자열_입력시_예외메시지를_출력한다() {
        run(List.of("   "));
        assertTrue(output().contains("입력이 비어있습니다."));
    }

    @Test
    void 동일한_물고기타입이_중복되면_예외메시지를_출력한다() {
        run(List.of("[고등어-2],[고등어-3]"));
        System.out.println(output());
        assertTrue(output().contains("중복된 물고기 타입이 존재합니다."));
    }

    @Test
    void 음수수량입력시_예외메시지를_출력한다() {
        run(List.of("[고등어--3]"));
        assertTrue(output().contains("수량은 음수가 될 수 없습니다."));
    }

    @Test
    void 아무_먹이가_없어_포식자가_죽는_경우_출력한다() {
        run(List.of("[고등어-5]"));  // 플랑크톤 없음
        assertTrue(output().contains("0일간 생존했습니다."));
    }

    // 응용
    @Test
    void 식성이_적용된_먹이사슬에_따라_정확히_소비되는지_검증한다() {
        run(List.of("[플랑크톤-15],[정어리-3],[멸치-3],[꽁치-2]"));
        assertTrue(output().contains("5일간 생존했습니다."));
    }


    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}

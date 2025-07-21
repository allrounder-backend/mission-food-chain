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
        assertTrue(output().contains("1일간 생존했습니다."));
    }

    // 심화

    @Test
    void 최대_생존전략으로_4일간_생존한다() {
        run(List.of("[삼치-1],[고등어-1],[멸치-1],[플랑크톤-3]"));
        assertTrue(output().contains("4일간 생존했습니다."));
    }


    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}

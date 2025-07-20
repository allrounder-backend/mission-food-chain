package mission.domain.strategy.validate;

public class
StrategyValidator {

    public static void validateFedCount(int totalCount, int fedCount) {
        if (fedCount < 0 || fedCount > totalCount) {
            throw new IllegalArgumentException("잘못된 fedCount: 범위는 0~totalCount");
        }
    }
}

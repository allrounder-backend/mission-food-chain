package mission.common.validate;

import java.util.Collection;

public class Validator {
    public static <T> T notNull(T target, String message) {
        if (target == null) {
            throw new IllegalArgumentException(message);
        }
        return target;
    }

    public static String notBlank(String target, String message) {
        if (target == null || target.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return target;
    }

    public static <T extends Collection<?>> T notEmpty(T target, String message) {
        if (target == null || target.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return target;
    }

}

package mission.ui.validate;

import mission.common.validate.Validator;
import mission.ui.exception.InputError;
import mission.ui.exception.InvalidInputException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern PATTERN = Pattern.compile("\\[(.*?)-(\\d+)]");

    public static Map<String, Integer> validate(String input) {
        Validator.notBlank(input, InputError.NULL_INPUT.getMessage());

        String[] parts = input.trim().split(",");
        Map<String, Integer> result = new HashMap<>();

        for (String part : parts) {
            Matcher matcher = PATTERN.matcher(part.trim());
            if (!matcher.matches()) {
                throw new InvalidInputException(InputError.INVALID_FORMAT);
            }

            String name = matcher.group(1);
            int count = Integer.parseInt(matcher.group(2));
            result.put(name, count);
        }

        return result;
    }
}

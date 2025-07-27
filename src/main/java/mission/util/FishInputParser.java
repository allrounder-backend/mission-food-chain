package mission.util;

import mission.exception.InputErrorCode;
import mission.exception.InvalidInputException;

import java.util.HashMap;
import java.util.Map;

public class FishInputParser {

    public static Map<String, Integer> parseInput (String input) {
        try {
            Map<String, Integer> map = new HashMap<>();
            input = input.replaceAll("\\s+", "");

            String[] items = input.split("],\\[|\\[|]");

            for (String item : items) {
                if (item.isEmpty()) {
                    continue;
                }

                String[] parts = item.split("-");
                if (parts.length != 2) {
                    throw new InvalidInputException(InputErrorCode.INVALID_FORMAT);
                }

                String name = parts[0];
                int count;

                try {
                    count = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    throw new InvalidInputException(InputErrorCode.INVALID_FORMAT);
                }

                if (count < 0) {
                    throw new InvalidInputException(InputErrorCode.INVALID_COUNT);
                }

                map.put(name, count);
            }

            if (map.isEmpty()) {
                throw new InvalidInputException(InputErrorCode.EMPTY_INPUT);
            }

            return map;
        } catch (InvalidInputException e) {
            throw e;
        } catch (Exception e) {
            throw  new InvalidInputException(InputErrorCode.INVALID_FORMAT);
        }
    }
}
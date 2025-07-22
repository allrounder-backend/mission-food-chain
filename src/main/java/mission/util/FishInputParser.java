package mission.util;

import mission.exception.ErrorCode;
import mission.exception.InvalidException;

import java.util.HashMap;
import java.util.Map;

public class FishInputParser {

    public static Map<String, Integer> parseInput (String input) {
        try {
            Map<String, Integer> map = new HashMap<String, Integer>();
            input = input.replaceAll("\\s+", "");

            String[] items = input.split("],\\[|\\[|]");

            for (String item : items) {
                if (item.isEmpty()) {
                    continue;
                }

                String[] parts = item.split("-");
                if (parts.length != 2) {
                    throw new InvalidException(ErrorCode.INVALID_FORMAT);
                }

                String name = parts[0];
                int count;

                try {
                    count = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    throw new InvalidException(ErrorCode.INVALID_FORMAT);
                }

                if (count < 0) {
                    throw new InvalidException(ErrorCode.INVALID_COUNT);
                }

                map.put(name, count);
            }

            if (map.isEmpty()) {
                throw new InvalidException(ErrorCode.EMPTY_INPUT);
            }

            return map;
        } catch (InvalidException e) {
            throw e;
        } catch (Exception e) {
            throw  new InvalidException(ErrorCode.INVALID_FORMAT);
        }
    }
}
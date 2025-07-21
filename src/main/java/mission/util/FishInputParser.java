package mission.util;

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
                    throw new IllegalArgumentException("잘못된 입력");
                }

                String name = parts[0];
                int count;

                try {
                    count = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("숫자 형식이 잘못되었습니다: " + parts[1]);
                }

                if (count < 0) {
                    throw new IllegalArgumentException("잘못된 입력");
                }

                map.put(name, count);
            }

            if (map.isEmpty()) {
                throw new IllegalArgumentException("입력에 유효한 항목이 없습니다.");
            }

            return map;
        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 입력");
        }
    }
    }
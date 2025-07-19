package mission.util;

import java.util.HashMap;
import java.util.Map;

public class FishInputParser {

    public static Map<String, Integer> parseInput (String input){
        Map<String, Integer> map = new HashMap<String, Integer>();
        input = input.replaceAll("\\s+","");

        String[] items = input.split("],\\[|\\[|]");

        for(String item : items){
            if(item.isEmpty()){
                continue;
            }

            String[] parts = item.split("-");
            if(parts.length == 2){
                String name = parts[0];
                int count = Integer.parseInt(parts[1]);
                map.put(name, count);
            }
        }

        return map;
    }
}

package mission.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PredationGraphFactory {
    public static Map<Integer, List<Integer>> createDefaultGraph(){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(2, List.of(1));
        graph.put(3, List.of(1));
        graph.put(4, List.of(1));
        graph.put(5, List.of(2, 3));
        graph.put(6, List.of(3));
        graph.put(7, List.of(2, 4));
        graph.put(8, List.of(5, 6));
        graph.put(9, List.of(5, 6, 7));
        graph.put(10, List.of(6, 7));
        graph.put(11, List.of(8, 10));
        graph.put(12, List.of(9, 10));
        return graph;
    }
}

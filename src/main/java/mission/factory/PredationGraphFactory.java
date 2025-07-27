package mission.factory;

import mission.model.PredationRelationship;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PredationGraphFactory {
    public static Map<Integer, List<Integer>> createDefaultGraph(){
        Map<Integer, List<Integer>> predationGraph = new HashMap<>();

        for(PredationRelationship predationRelationship : PredationRelationship.values()){
            if(!predationRelationship.getPreyIds().isEmpty()){
                predationGraph.put(predationRelationship.getPreyIds().size(), predationRelationship.getPreyIds());
            }
        }
        return predationGraph;
    }
}

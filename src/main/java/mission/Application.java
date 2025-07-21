package mission;

import api.Console;
import mission.model.*;
import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        List<Fish> fishList = FishList.createFishList();
        FoodChainGraph graph = FishList.buildGraph(fishList);

        System.out.println("물고기 비로 내릴 물고기를 입력해주세요. (ex. [정어리-5],[고등어-2])");
        String input = Console.readLine();
        List<FishPopulation> list = Arrays.stream(input.split(","))
                .map(s -> s.replace("[", "").replace("]", ""))
                .map(s -> s.split("-"))
                .map(arr -> new FishPopulation(
                        fishList.stream().filter(f -> f.getName().equals(arr[0])).findFirst().orElse(null),
                        Integer.parseInt(arr[1])
                ))
                .collect(Collectors.toList());
        Pond pond = new Pond(graph, list);
        System.out.printf("%d일간 생존했습니다.\n", pond.simulateSurvivalDays());
    }
}



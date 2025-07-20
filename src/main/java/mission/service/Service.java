package mission.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import mission.model.Fish;
import mission.model.Pool;
import mission.view.View;

public class Service {
    public static Map<String, Integer> map = new HashMap<>();
    public static String[] toStringArray = new String[13];
    {
        map.put("플랑크톤", 1); map.put("멸치", 2);
        map.put("정어리", 3); map.put("빙어", 4);
        map.put("고등어", 5); map.put("전갱이", 6);
        map.put("꽁치", 7); map.put("삼치", 8);
        map.put("참치", 9); map.put("방어", 10);
        map.put("황새치", 11); map.put("상어", 12);

        toStringArray[1] = "플랑크톤"; toStringArray[2] = "멸치";
        toStringArray[3] = "정어리";   toStringArray[4] = "빙어";
        toStringArray[5] = "고등어";  toStringArray[6] = "전갱이";
        toStringArray[7] = "꽁치";    toStringArray[8] = "삼치";
        toStringArray[9] = "참치";    toStringArray[10] = "방어";
        toStringArray[11] = "황새치"; toStringArray[12] = "상어";
    }
    // 식사 순서 및 식성 정하기
    public void setEating(Pool pool, int[] order, int[][] arr){
        // 물고기 종류 수 맞는지 확인(플랑크톤은 안 먹으므로 제외)
        if(arr.length != Fish.Type.values().length-1){
            System.out.println("배열의 수가 맞지 않습니다.");
            return;
        }
        // 식사 순서 정하기(이게 먼저임)
        pool.setOrderOfEating(order);
        // 물고기 식성 정하기: 배열은 물고기 먹는 순서대로 넣는다.
        for(int i = 0; i < pool.getOrderOfEating().size(); i++){
            int TypeOrder = pool.getOrderOfEating().get(i).ordinal();
            for(Fish predator: pool.getFishList().get(TypeOrder)){
                predator.setPrey(arr[i]);
            }
        }
    }
    // 물고기 종류별 수를 배열로 변환
    public int[] StringToArray(String str){
        int[] arr = new int[Fish.Type.values().length];
        String[] strArr = str.split(",");

        // [] 단위로 순환해서 arr에 추가
        for(String s : strArr){
            String fishStr = s.replace("[", "")
                    .replace("]", "");
            int fishType = map.get(fishStr.split("-")[0]);
            int fishNum = Integer.parseInt(fishStr.split("-")[1]);

            arr[fishType - 1] = fishNum;
        }

        return arr;
    }
    // 물고기 비 내려서 물고기들 추가하는 함수
    public void fishRain(Pool pool, int[] arr){
        if(arr.length != Fish.Type.values().length){
            System.out.println("배열 크기가 잘못되었습니다.");
            return;
        }
        // 물고기 생성
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i]; j++) {
                new Fish(Fish.Type.values()[i], pool);
            }
        }
    }

    public void oneDay(Pool pool){
        // 순서대로 먹기 시작
        for(Fish.Type type : pool.getOrderOfEating()){
            List<Fish> predatorList = new LinkedList<>(pool.getFishList().get(type.ordinal()));
            for(Fish predator: predatorList){
                // 먹이 종류 리스트와 먹이
                List<Fish.Type> preyTypeList = predator.getPrey();
                Fish prey = null;
                // 먹이 찾기
                for(Fish.Type preyType : preyTypeList){
                    // 한 종류의 먹이 목록
                    List<Fish> preyList = pool.getFishList().get(preyType.ordinal());
                    // 먹이 찾음
                    if(!preyList.isEmpty()) {
                        prey = preyList.getFirst();
                        break;
                    }
                }
                // 먹이 없으면 죽음
                if(prey == null){
                    predator.die();
                    continue;
                }
                // 먹음
                predator.eat(prey);
            }
        }
        pool.addDay();
    }

    public int getSurvivalDay(Pool pool){
        int[] nums = pool.getNum();
        boolean noFish;
        // 물고기 없으면 끝나고 물고기 있으면 하루 지남
        while(true){
            noFish = true;
            for(int i = 1; i < nums.length; i++){
                if(nums[i] != 0){
                    noFish = false;
                    break;
                }
            }
            if(noFish)
                break;
            oneDay(pool);
        }

        return pool.getDay();
    }

}

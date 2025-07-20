package mission.model;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Pool {
    // 날짜
    private int day;
    // 식사 순서
    private List<Fish.Type> orderOfEating = new ArrayList<>();
    // 총 물고기 수
    private int totalNum;
    // 종류별 물고기 수(열거체 순서)
    private int[] num = new int[Fish.Type.values().length];
    // 종류별 물고기 연결 리스트(열거체 순서)
    private List<List<Fish>> fishList = new ArrayList<List<Fish>>();
    {
        // 멤버 변수랑 배열은 0으로 자동 초기화
        // 배열 리스트 각 요소에 연결 리스트 추가
        for(int i=0;i<Fish.Type.values().length;i++){
            fishList.add(new LinkedList<>());
        }
        // 식사 순서(플랑크톤 제외) 기본
        setOrderOfEating(Arrays.stream(Fish.Type.values())
                .skip(1) // 1개 뛰어넘음
                .toArray(Fish.Type[]::new)); // 생성자 참조
    }

    public Pool(){
    }

    public List<List<Fish>> getFishList(){
        return fishList;
    }

    public void setFishList(List<List<Fish>> fishList){
        this.fishList = fishList;
    }

    // 연못에 물고기 추가
    public void addFish(Fish fish){
        // 열거체 순서와 동일
        fishList.get(fish.getType().ordinal()).add(fish);
        totalNum++;
        num[fish.getType().ordinal()]++;
    }

    // 연못에 물고기 제거
    public void removeFish(Fish fish){
        // 물고기 종류는 리스트 순서와 열거체 순서가 동일
        fishList.get(fish.getType().ordinal()).remove(fish);
        totalNum--;
        num[fish.getType().ordinal()]--;
    }

    // 식사순서 정하는 메소드
    public void setOrderOfEating(Fish.Type... types){
        if(types.length != Fish.Type.values().length - 1){
            System.out.println("물고기 수가 틀렸습니다.");
        }
        this.orderOfEating = new ArrayList<>(Arrays.asList(types));
    }
    // 오버로딩
    public void setOrderOfEating(int... types){
        if(types.length != Fish.Type.values().length - 1){
            System.out.println("물고기 수가 틀렸습니다.");
        }
        // 비우고
        this.orderOfEating.clear();
        // 채우기
        for(int i : types){
//            System.out.print(Fish.Type.fromId(i)+" ");
            this.orderOfEating.add(Fish.Type.fromId(i));
        }
//        System.out.println("\n설정 완료");
    }

    // 식사순서 구하는 메소드
    public List<Fish.Type> getOrderOfEating(){
        return this.orderOfEating;
    }

    public int getTotalNum(){
        return this.totalNum;
    }

    public int[] getNum(){
        return this.num;
    }

    public void setDay(int day){
        this.day = day;
    }

    public int getDay(){
        return this.day;
    }

    public void addDay(){
        this.day++;
    }
}

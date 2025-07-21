package mission.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

// 다른 곳에서 쓰는 함수
// void eat(Fish fish)

public class Fish{
    // 생성 장소, 물고기 종류, 생존 여부, 먹이
    private Pool pool;
    private Type type;
    private boolean isAlive;
    private List<Type> prey = new ArrayList<>();

    public Fish(Type type, Pool pool){
        this.type = type;
        this.pool = pool;
        pool.addFish(this);
        // 생성될 때 살아있음
        isAlive = true;
    }

    // 먹이 정하기
    public void setPrey(Type... types){
        this.prey = new ArrayList<>(Arrays.asList(types));
    }
    // 오버로딩
    public void setPrey(int... types){
        this.prey.clear();
        for (int j : types) {
            this.prey.add(Type.fromId(j));
        }
    }

    public Pool getPool(){
        return this.pool;
    }

    public Type getType(){
        return this.type;
    }

    public List<Type> getPrey(){
        return this.prey;
    }

    public boolean isAlive(){
        return this.isAlive;
    }

    // 다른 물고기 먹는 메소드
    public void eat(Fish fish){
        Pool pool = fish.getPool();
        // 생성 장소가 다르다면 반환
        if(pool != fish.getPool()) {
            System.out.println("두 물고기의 장소가 다릅니다.");
            return;
        }
        // 먹힌 물고기 죽음
        if(!fish.isAlive()){
            System.out.println("죽은 물고기는 먹을 수 없습니다.");
            return;
        }
        fish.die();
    }
    // 물고기 죽음
    public void die(){
        if(!this.isAlive()){
            System.out.println("이미 죽었습니다.");
        }
        this.isAlive = false;
        this.getPool().removeFish(this);
    }

    // 물고기 종류
    public enum Type{
        PLANK(1), MYEOL(2), JUNG(3), BING(4), GO(5), JEON(6), GGONG(7), SAM(8), CHAM(9), BANG(10), HWANG(11), SANG(12);
        private final int id;
        Type(int id){
            this.id = id;
        }

        public int getId(){
            return this.id;
        }

        public static Type fromId(int id){
            for(Type type : Type.values()){
                if(type.getId() == id)
                    return type;
            }
            throw new IllegalArgumentException("숫자가 물고기 범위에 속하지 않습니다");
        }
    }
}
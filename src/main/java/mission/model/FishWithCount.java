package mission.model;

public class FishWithCount {
    private Fish fish;
    private int count;

    public FishWithCount(Fish fish, int count) {
        this.fish = fish;
        this.count = count;
    }

    public Fish getFish() {
        return fish;
    }

    public int getCount() {
        return count;
    }

    public void eaten(int n){
        this.count -= n;
        if(this.count < 0)
            this.count = 0;
    }

}

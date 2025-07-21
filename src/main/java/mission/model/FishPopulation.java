package mission.model;

public class FishPopulation {
    private final Fish fish;
    private final int count;

    public FishPopulation(Fish fish, int count) {
        this.fish = fish;
        this.count = count;
    }

    public Fish getFish() {
        return fish;
    }

    public int getCount() {
        return count;
    }
}
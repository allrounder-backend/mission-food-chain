package mission.model;

public class Fish {
    private int id;
    private String name;
    private int trophicLevel;

    public Fish(int id, String name, int trophicLevel) {
        this.id = id;
        this.name = name;
        this.trophicLevel = trophicLevel;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTrophicLevel() {
        return trophicLevel;
    }
}

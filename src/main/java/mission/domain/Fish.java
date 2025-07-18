package mission.domain;

public class Fish {
    private final int id;
    private final String name;
    private final int level;

    public Fish(int id, String name, int level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
}

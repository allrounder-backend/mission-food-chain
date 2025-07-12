package mission.application.domain.model;

public class Fish {
    private final int id;
    private final String name;
    private final int trophic;
    private int quantity;

    public Fish(int id, String name, int trophic, int quantity) {
        this.id = id;
        this.name = name;
        this.trophic = trophic;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTrophic() {
        return trophic;
    }

    public int getQuantity() {
        return quantity;
    }

    public void subQuantity() {
        this.quantity--;
    }

    public void subQuantity(int quantity) {
        this.quantity -= quantity;
    }
}

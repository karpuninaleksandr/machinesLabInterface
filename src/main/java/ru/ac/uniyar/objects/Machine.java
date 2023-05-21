package ru.ac.uniyar.objects;

public class Machine implements Entity {
    private int id;
    private String name;
    private int rentPrice;
    private int brandId;

    public Machine(int id, String name, int rentPrice, int brandId) {
        this.id = id;
        this.name = name;
        this.rentPrice = rentPrice;
        this.brandId = brandId;
    }

    public int id() {
        return this.id;
    }
    public String name() {
        return this.name;
    }
    public int rentPrice() {
        return rentPrice;
    }
    public int brandId() {
        return brandId;
    }
}

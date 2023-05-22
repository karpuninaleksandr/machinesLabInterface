package ru.ac.uniyar.objects;

public class Machine implements Entity {
    private final int id;
    private final String name;
    private final int rentPrice;
    private final int brandId;

    public Machine(int id, String name, int rentPrice, int brandId) {
        this.id = id;
        this.name = name;
        this.rentPrice = rentPrice;
        this.brandId = brandId;
    }

    public Machine(String name, int rentPrice, int brandId) {
        this.id = 0;
        this.name = name;
        this.rentPrice = rentPrice;
        this.brandId = brandId;
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public int getRentPrice() {
        return rentPrice;
    }
    public int getBrandId() {
        return brandId;
    }
}

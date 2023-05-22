package ru.ac.uniyar.objects;

public class Brand implements Entity {
    private final int id;
    private final String name;

    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
}

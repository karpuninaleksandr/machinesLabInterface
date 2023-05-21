package ru.ac.uniyar.objects;

public class Brand implements Entity {
    private final int id;
    private final String name;

    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int id() {
        return this.id;
    }
    public String name() {
        return this.name;
    }
}

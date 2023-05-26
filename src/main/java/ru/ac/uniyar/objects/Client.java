package ru.ac.uniyar.objects;

public class Client implements Entity {
    private final int id;
    private final String name;
    private final String address;
    private final String phoneNumber;

    public Client(int id, String name, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Client(String name, String address, String phoneNumber) {
        this.id = 0;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Client(int id, String name) {
        this.id = id;
        this.name = name;
        this.address = "";
        this.phoneNumber = "";
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getAddress() {
        return this.address;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
}


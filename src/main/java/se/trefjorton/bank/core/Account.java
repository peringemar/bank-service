package se.trefjorton.bank.core;

public class Account {
    private final Long id;
    private final String name;

    public Account(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
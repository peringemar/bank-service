package se.trefjorton.bank.db.entities;

public class AccountEntity {
    private final Long id;
    private final String name;

    public AccountEntity(Long id, String name) {
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

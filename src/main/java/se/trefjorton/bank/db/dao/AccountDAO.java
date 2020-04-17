package se.trefjorton.bank.db.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import se.trefjorton.bank.db.entities.AccountEntity;

public class AccountDAO extends AbstractDAO<AccountEntity> {
    public AccountDAO(SessionFactory factory) {
        super(factory);
    }

    public AccountEntity findById(Long id) {
        return get(id);
    }
}
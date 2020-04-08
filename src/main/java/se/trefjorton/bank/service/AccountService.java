package se.trefjorton.bank.service;

import se.trefjorton.bank.db.dao.AccountDAO;
import se.trefjorton.bank.mapping.AccountMapper;
import se.trefjorton.bank.model.Account;

public class AccountService {
    private final AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account findById(Long id) {
        return AccountMapper.INSTANCE.fromEntity(accountDAO.findById(id));
    }
}

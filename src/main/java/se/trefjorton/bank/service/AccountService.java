package se.trefjorton.bank.service;

import se.trefjorton.bank.core.Account;
import se.trefjorton.bank.db.AccountRepository;

import java.util.Optional;

public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> findById(Long id) {
        return Optional.empty();
    }
}

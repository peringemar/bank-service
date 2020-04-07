package se.trefjorton.bank.service;

import se.trefjorton.bank.db.repositories.AccountRepository;
import se.trefjorton.bank.domain.Account;
import se.trefjorton.bank.mapping.AccountMapper;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findById(Long id) {
        return AccountMapper.INSTANCE.fromEntity(accountRepository.findById(id));
    }
}

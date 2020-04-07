package se.trefjorton.bank.db.repositories;

import com.google.common.collect.ImmutableList;
import org.jvnet.hk2.annotations.Service;
import se.trefjorton.bank.db.entities.AccountEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountRepository {
    private List<AccountEntity> accounts;

    public AccountRepository(List<AccountEntity> accounts) {
        this.accounts = ImmutableList.copyOf(accounts);
    }

    public List<AccountEntity> findAll(int size) {
        return accounts.stream()
                .limit(size)
                .collect(Collectors.toList());
    }

    public AccountEntity findById(Long id) {
        return accounts.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
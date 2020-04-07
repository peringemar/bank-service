package se.trefjorton.bank.db;

import com.google.common.collect.ImmutableList;
import org.jvnet.hk2.annotations.Service;
import se.trefjorton.bank.core.Account;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountRepository {
    private final List<Account> brands;

    public AccountRepository(List<Account> brands) {
        this.brands = ImmutableList.copyOf(brands);
    }

    public List<Account> findAll(int size) {
        return brands.stream()
                .limit(size)
                .collect(Collectors.toList());
    }

    public Optional<Account> findById(Long id) {
        return brands.stream()
                .filter(brand -> brand.getId().equals(id))
                .findFirst();
    }
}
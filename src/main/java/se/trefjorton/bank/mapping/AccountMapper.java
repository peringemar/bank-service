package se.trefjorton.bank.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se.trefjorton.bank.db.entities.AccountEntity;
import se.trefjorton.bank.domain.Account;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account fromEntity(AccountEntity entity);
}

package org.back.systemklinikimedycznej.account.mapper;

import org.back.systemklinikimedycznej.account.dto.AccountData;
import org.back.systemklinikimedycznej.account.dto.AccountDto;
import org.back.systemklinikimedycznej.account.dto.AccountInfo;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto mapFromEntity(Account account);

    @Mappings({
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "email", source = "email")
    })
    AccountInfo mapFromAccountToAccountInfo(Account account);

    AccountData mapFromAccountToAccountData(Account account);
}

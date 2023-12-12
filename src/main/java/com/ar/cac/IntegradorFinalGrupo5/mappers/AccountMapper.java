package com.ar.cac.IntegradorFinalGrupo5.mappers;

import com.ar.cac.IntegradorFinalGrupo5.entities.Account;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.AccountDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {
    public static AccountDto accountToDto(Account account) {

        AccountDto dto = new AccountDto();

        dto.setId(account.getId());
        dto.setAlias(account.getAlias());
        dto.setType(account.getType());
        dto.setCbu(account.getCbu());
        dto.setAmount(account.getAmount());
        dto.setCreated_at(account.getCreated_at());
        dto.setUpdated_at(account.getUpdate_at());
        return dto;
    }

    public static Account dtoToAccount(AccountDto dto) {
        Account account = new Account();

        account.setId(dto.getId());
        account.setAlias(dto.getAlias());
        account.setType(dto.getType());
        account.setCbu(dto.getCbu());
        account.setAmount(dto.getAmount());
        account.setCreated_at(dto.getCreated_at());
        account.setUpdate_at(dto.getUpdated_at());
        return account;
    }
}

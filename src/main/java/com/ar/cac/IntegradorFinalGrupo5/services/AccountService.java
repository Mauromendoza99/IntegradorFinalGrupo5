package com.ar.cac.IntegradorFinalGrupo5.services;

import com.ar.cac.IntegradorFinalGrupo5.entities.Account;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.AccountDto;
import com.ar.cac.IntegradorFinalGrupo5.mappers.AccountMapper;
import com.ar.cac.IntegradorFinalGrupo5.mappers.UserMapper;
import com.ar.cac.IntegradorFinalGrupo5.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }


    //    RECUPERAR TODAS LAS CUENTAS
    public List<Account> getAccounts() {
        List<Account> accounts = repository.findAll();
        return accounts;
    }

    //    RECUPERA UNA CUENTA POR ID
    public Account getAcountById(Long id) {
        Account account = repository.findById(id).get();
        return account;
    }

    //    CREA UNA CUENTA
    public AccountDto createAccount(AccountDto account) {
        account.setCreated_at(LocalDateTime.now());
        account.setAmount(BigDecimal.ZERO);
        Account entity = AccountMapper.dtoToAccount(account);
        Account entiySaved = repository.save(entity);
        account = AccountMapper.accountToDto(entiySaved);
        return account;
    }

    //    BORRA UNA CUENTA
    public String deleteAccount(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "La cuenta " + " ha sido eliminada";
        } else {
            return "La cuenta " + " NO ha sido eliminada";
        }


    }

    //    MODIFICA UNA CUENTA TOTAL O PARCIALMENTE
    public AccountDto updateAccount(Long id, AccountDto dto) {
        if (repository.existsById(id)) {
            Account accountToModify = repository.findById(id).get();
            Boolean modified = false;
            if (dto.getAlias() != null) {
                accountToModify.setAlias(dto.getAlias());
                modified = true;
            }

            if (dto.getType() != null) {
                accountToModify.setType(dto.getType());
                modified = true;
            }

            if (dto.getAmount() != null) {
                accountToModify.setAmount(dto.getAmount());
                modified = true;
            }
//            SOLO SI SE MODIFICÓ SETEAMOS LA FECHA
            if (modified = true) {
                accountToModify.setUpdate_at(LocalDateTime.now());
            }

            Account accountModified = repository.save(accountToModify);
            return AccountMapper.accountToDto(accountModified);

        } else {
            return null;
        }
    }
}



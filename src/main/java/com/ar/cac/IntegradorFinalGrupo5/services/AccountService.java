package com.ar.cac.IntegradorFinalGrupo5.services;

import com.ar.cac.IntegradorFinalGrupo5.entities.Account;
import com.ar.cac.IntegradorFinalGrupo5.entities.User;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.AccountDto;
import com.ar.cac.IntegradorFinalGrupo5.mappers.AccountMapper;
import com.ar.cac.IntegradorFinalGrupo5.repositories.AccountRepository;
import com.ar.cac.IntegradorFinalGrupo5.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository repository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository repository, UserRepository userRepository, UserRepository userRepository1) {
        this.repository = repository;
        this.userRepository = userRepository1;
    }


    //    RECUPERAR TODAS LAS CUENTAS
    public List<AccountDto> getAccounts() {
        //COMPLETAMOS OTRA LISTA SOLO CON LOS REGISTROS HABILITADOS PARA NO MOSTRAR LOS DESHABILITADO
        List<AccountDto> accounts = repository.findAll().stream().map(AccountMapper::accountToDto).collect(Collectors.toList());
                List<AccountDto> cuentasFinales = new ArrayList<>();
                for (AccountDto accountDto : accounts){
                    if (AccountMapper.dtoToAccount(accountDto).getEnabled() == true){
                        cuentasFinales.add(accountDto);
                    }
                }
        return cuentasFinales;
    }

    //    RECUPERA UNA CUENTA POR ID
//    INDICAMOS QUE ES POSIBLE QUE SE ARROJE UNA EXCEPTION Y USAMOS UN DTO PARA ACCEDER AL METODO TOSTRING
    public AccountDto getAcountById(Long id) throws Exception {
        Account account = repository.findById(id).get();
        if (account != null && account.getEnabled() == true) {
            return AccountMapper.accountToDto(account);
        } else {
            return null;
        }

    }

    //    CREA UNA CUENTA
    public AccountDto createAccount(AccountDto account) throws Exception {

        Account entity = AccountMapper.dtoToAccount(account);
        //CON EL ID QUE RECIBIMOS EN EL JSON, RECUPERAMOS EL USER Y SE LO SETEAMOS A LA CUENTA
        User user = userRepository.findById(account.getOwnerid()).get();
        entity.setOwner(user);
        //SETEAMOS QUE LA CUENTA SE CREE HABILITADA
        entity.setEnabled(true);
        //SETEAMOS QUE LA CUENTA SE CREE SIN SALDO
        entity.setAmount(BigDecimal.ZERO);
        Account entiySaved = repository.save(entity);
        return AccountMapper.accountToDto(entiySaved);
    }

    //    BORRA UNA CUENTA
    public String deleteAccount(Long id) {
        //        BAJA LOGICA, OBTENEMOS LA CUENTA A ELIMINAR Y LE SETEAMOS EL VALOR FALSE EN ENABLED
        Account accountToDelete = repository.findById(id).get();
        if (repository.existsById(id)) {
            accountToDelete.setEnabled(false);
            repository.save(accountToDelete);
//            repository.deleteById(id);
            return "La cuenta " + " ha sido eliminada";
        } else {
            return "La cuenta " + " NO ha sido eliminada";
        }


    }

    //    MODIFICA UNA CUENTA TOTAL O PARCIALMENTE
    public AccountDto updateAccount(Long id, AccountDto dto) {
        Account accountToModify = repository.findById(id).get();
        if ((repository.existsById(id)) && (accountToModify.getEnabled() == true)) {
            //NO DEBEMOS PERMITIR LA ACTUALIZACIÓN DE UN USUARIO DESHABILITADO
            // VALIDACIONES
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



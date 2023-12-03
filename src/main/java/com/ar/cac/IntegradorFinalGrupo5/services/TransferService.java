package com.ar.cac.IntegradorFinalGrupo5.services;

import com.ar.cac.IntegradorFinalGrupo5.Exceptions.AccountNotExistException;
import com.ar.cac.IntegradorFinalGrupo5.Exceptions.InsufficientFoundsException;
import com.ar.cac.IntegradorFinalGrupo5.Exceptions.TransferNotFoundException;
import com.ar.cac.IntegradorFinalGrupo5.entities.Account;
import com.ar.cac.IntegradorFinalGrupo5.entities.Transfer;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.TransferDto;
import com.ar.cac.IntegradorFinalGrupo5.mappers.TransferMapper;
import com.ar.cac.IntegradorFinalGrupo5.repositories.AccountRepository;
import com.ar.cac.IntegradorFinalGrupo5.repositories.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {

    private final TransferRepository repository;

    //al hacer el merge con la rama develop va a funcionar esta linea
    //en mi archivo no esta creado el account repository
    private final AccountRepository accountRepository;

    private TransferService(TransferRepository repository, AccountRepository accountRepository){
        this.repository = repository;
        this.accountRepository = accountRepository;

    }


    public List<TransferDto> getTransfers() {
        List<Transfer> transf = repository.findAll();
        return transf.stream()
                .map(TransferMapper::transferToDto)
                .collect(Collectors.toList());
    }

    public TransferDto getTransferById(Long id) {
        Transfer transf = repository.findById(id).orElseThrow(() ->
            new TransferNotFoundException("Transfer with id: " + id + "not Found"));
        return TransferMapper.transferToDto(transf);
    }

    @Transactional
    public TransferDto makeTransfer(TransferDto dto) {

        Account originAccount = accountRepository.findById(dto.getOriginAccount())
                .orElseThrow(() -> new AccountNotExistException("Account not found with id: " + dto.getOriginAccount()));
        Account destinationAccount = accountRepository.findById(dto.getDestinityAccount())
                .orElseThrow(() -> new AccountNotExistException("Account not found with id: " + dto.getDestinityAccount()));

        // Comprobar si la cuenta de origen tiene fondos suficientes
        //Verificar que funcione esta parte al hacer el merge
        /*if (originAccount.getAmount().compareTo(dto.getAmount()) < 0) {
            throw new InsufficientFoundsException("Insufficient funds in the account with id: " + dto.getOrigin());
        }

        // Realizar la transferencia
        originAccount.setAmount(originAccount.getAmount().subtract(dto.getAmount()));
        destinationAccount.setAmount(destinationAccount.getAmount().add(dto.getAmount()));

        // Guardar las cuentas actualizadas
        accountRepository.save(originAccount);
        accountRepository.save(destinationAccount);*/

        // Crear la transferencia y guardarla en la base de datos
        Transfer transfer = new Transfer();
        /*// Creamos un objeto del tipo Date para obtener la fecha actual
        Date date = new Date();
        // Seteamos el objeto fecha actual en el transferDto
        transfer.setDone_at(date);
        transfer.setOrigin(originAccount.getId());
        transfer.setTarget(destinationAccount.getId());
        transfer.setAmount(dto.getAmount());
        transfer = repository.save(transfer);*/



        return TransferMapper.transferToDto(transfer);
    }


}

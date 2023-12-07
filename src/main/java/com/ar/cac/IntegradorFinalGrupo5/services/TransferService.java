package com.ar.cac.IntegradorFinalGrupo5.services;

import com.ar.cac.IntegradorFinalGrupo5.entities.Account;
import com.ar.cac.IntegradorFinalGrupo5.entities.Transfer;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.TransferDto;
import com.ar.cac.IntegradorFinalGrupo5.mappers.TransferMapper;
import com.ar.cac.IntegradorFinalGrupo5.repositories.AccountRepository;
import com.ar.cac.IntegradorFinalGrupo5.repositories.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.ar.cac.IntegradorFinalGrupo5.Exceptions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {

    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

    private TransferService(TransferRepository repository, AccountRepository accountRepository, TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }


    public List<TransferDto> getTransfers() {
        return transferRepository.findAll().stream()
                .map(TransferMapper::transferToDto)
                .collect(Collectors.toList());
    }

    public TransferDto getTransferById(Long id) {
        Transfer transf = transferRepository.findById(id).get();
        return TransferMapper.transferToDto(transf);
    }

    @Transactional
    public TransferDto makeTransfer(TransferDto dto) {
        // Comprobar si las cuentas de origen y destino existen
        Account originAccount = accountRepository.findById(dto.getOriginAccount())
                .orElseThrow(() -> new AccountNotExistException(("Account not found with id: " + dto.getOriginAccount())));
        Account destinationAccount = accountRepository.findById(dto.getDestinityAccount())
                .orElseThrow(() -> new AccountNotExistException("Account not found withid: " + dto.getDestinityAccount()));

        // Comprobar si la cuenta de origen tiene fondos suficientes
        if (originAccount.getAmount().compareTo(dto.getAmount()) < 0) {
            throw new InsufficientFoundsException("Insufficient funds in the account with id: " + dto.getOriginAccount());
        }

        // Realizar la transferencia
        originAccount.setAmount(originAccount.getAmount().subtract(dto.getAmount()));
        destinationAccount.setAmount(destinationAccount.getAmount().add(dto.getAmount()));

        // Guardar las cuentas actualizadas
        accountRepository.save(originAccount);
        accountRepository.save(destinationAccount);
        // Crear la transferencia y guardarla en la base de datos
        Transfer transfer = new Transfer();
        // Creamos un objeto del tipo Date para obtener la fecha actual
        LocalDateTime date;
        date = LocalDateTime.now();

        // Seteamos el objeto fecha actual en el transferDto
        transfer.setDone_at(date);
        transfer.setOriginAccount(originAccount.getId());
        transfer.setDestinityAccount(destinationAccount.getId());

        transfer.setAmount(dto.getAmount());
        transfer = transferRepository.save(transfer);

        // Devolver el DTO de la transferencia realizada
        return TransferMapper.transferToDto(transfer);
    }

}

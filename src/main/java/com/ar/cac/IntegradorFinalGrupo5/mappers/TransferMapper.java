package com.ar.cac.IntegradorFinalGrupo5.mappers;

import com.ar.cac.IntegradorFinalGrupo5.entities.Transfer;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.TransferDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransferMapper {

    public TransferDto transferToDto (Transfer transfer) {
        TransferDto dto = new TransferDto();
        dto.setId(transfer.getId());
        dto.setAmount(transfer.getAmount());
        dto.setOriginAccount(transfer.getOriginAccount());
        dto.setDestinityAccount(transfer.getDestinityAccount());
        dto.setDone_at(transfer.getDone_at());
        return dto;

    }

    public Transfer dtoToTransfer (TransferDto dto){
        Transfer transfer = new Transfer();
        transfer.setAmount(dto.getAmount());
        transfer.setOriginAccount(dto.getOriginAccount());
        transfer.setDestinityAccount(dto.getDestinityAccount());
        transfer.setDone_at(dto.getDone_at());

        return transfer;

    }
}

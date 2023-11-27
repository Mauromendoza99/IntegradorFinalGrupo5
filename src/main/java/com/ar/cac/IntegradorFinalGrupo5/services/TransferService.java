package com.ar.cac.IntegradorFinalGrupo5.services;

import com.ar.cac.IntegradorFinalGrupo5.entities.Transfer;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.TransferDto;
import com.ar.cac.IntegradorFinalGrupo5.mappers.TransferMapper;
import com.ar.cac.IntegradorFinalGrupo5.repositories.TransferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {

    private final TransferRepository repository;

    private TransferService(TransferRepository repository){
        this.repository = repository;

    }


    public List<Transfer> getTransfers() {
        List<Transfer> transfers = repository.findAll();
        return transfers;
    }

    public TransferDto getTransferById(Long id) {
        Transfer transf = repository.findById(id).get();
        return TransferMapper.transferToDto(transf);
    }
}

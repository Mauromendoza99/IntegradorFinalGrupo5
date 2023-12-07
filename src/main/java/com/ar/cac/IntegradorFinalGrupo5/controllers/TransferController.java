package com.ar.cac.IntegradorFinalGrupo5.controllers;

import com.ar.cac.IntegradorFinalGrupo5.entities.Transfer;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.TransferDto;
import com.ar.cac.IntegradorFinalGrupo5.services.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    //    LISTA DE TRANFERENCIAS
    @GetMapping
    public ResponseEntity<List<TransferDto>> getTransfers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTransfers());
    }

    //    TRANFERENCIA POR ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<TransferDto> getAccountById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTransferById(id));
    }

    @PostMapping
    public ResponseEntity<TransferDto> makeTransfer(@RequestBody TransferDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.makeTransfer(dto));

    }

}
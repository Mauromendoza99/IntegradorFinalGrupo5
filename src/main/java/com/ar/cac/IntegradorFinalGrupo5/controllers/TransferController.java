package com.ar.cac.IntegradorFinalGrupo5.controllers;

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

    public TransferController(TransferService service) {this.service = service; }

    @GetMapping
    public ResponseEntity<List<TransferDto>> getTransfers(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getTransfers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransferDto> getTransferById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getTransferById(id));
    }

    @PostMapping
    public ResponseEntity<TransferDto> makeTransfer(@RequestBody TransferDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.makeTransfer(dto));
    }

    /*
    @PutMapping(value="/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto account){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAccount(id, account));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteAccount(id));
    }*/
}

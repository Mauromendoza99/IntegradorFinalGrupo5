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

    public TransferController(TransferService service) {this.service = service; }

    @GetMapping
    public ResponseEntity<List<Transfer>> getTransfers(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getTransfers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransferDto> getAccountById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getTransferById(id));
    }

    //comento estas lineas para saber cuales otras mapping se pueden agregar

   /* @PostMapping
    public ResponseEntity<> createAccount(@RequestBody AccountDto account){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAccount(account));
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto account){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAccount(id, account));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteAccount(id));
    }*/
}

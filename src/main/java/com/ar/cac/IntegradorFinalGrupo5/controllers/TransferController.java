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
        @PutMapping(value = "/{id}")
        public String updateFullUser(){
            return "";
            @PutMapping(value="/{id}")
            public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto user){
                return ResponseEntity.status(HttpStatus.OK).body(service.updateUser(id, user));

                @DeleteMapping(value = "/{id}")
                public ResponseEntity<String> deleteUser(@PathVariable Long id){
                    return ResponseEntity.status(HttpStatus.OK).body(service.deleteUser(id));
                }
            }

            }
                    @PostMapping
                        public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto account){
                            return ResponseEntity.status(HttpStatus.CREATED).body(service.createAccount(account));
    }


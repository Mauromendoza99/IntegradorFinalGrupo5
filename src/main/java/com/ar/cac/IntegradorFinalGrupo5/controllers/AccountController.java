package com.ar.cac.IntegradorFinalGrupo5.controllers;

import com.ar.cac.IntegradorFinalGrupo5.entities.Account;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.AccountDto;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.UserDto;
import com.ar.cac.IntegradorFinalGrupo5.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    //inyección
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }


    //LISTA DE CUENTAS REGISTRADAS
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAccounts());
    }

    //CUENTA POR ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<String> getAcountById(@PathVariable Long id) {
        //DEVOLVEMOS UN STRING PARA PODER MOSTRAR SOLOS LOS DATOS MAS RELEVANTES A TRAVES DE UN METODO TOSTRIG Y ATRAPAMOS LA EXCEPTION
        try {
            return ResponseEntity.status(HttpStatus.OK).body("Cuenta encontrada: { " + service.getAcountById(id).toString() + " }");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existen Cuentas registradas con id: " + id);
        }

    }

    //CREAR CUENTA
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAccount(account));
    }

    //MODIFICA TOTAL Y PARCIALMENTE
    @PutMapping(value = "/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto account) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAccount(id, account));
    }

    //ELIMINAR CUENTA
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteAccount(id));
    }
}

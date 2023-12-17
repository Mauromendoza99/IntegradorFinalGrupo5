package com.ar.cac.IntegradorFinalGrupo5.controllers;

import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.AccountDto;
import com.ar.cac.IntegradorFinalGrupo5.mappers.UserMapper;
import com.ar.cac.IntegradorFinalGrupo5.repositories.UserRepository;
import com.ar.cac.IntegradorFinalGrupo5.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    //inyección
    private final AccountService service;
    private final UserRepository userRepository;

    public AccountController(AccountService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
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
            return ResponseEntity.status(HttpStatus.OK).body("Account not Found with Id: { " + service.getAcountById(id).toString() + " }");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Don't Exist Accounts With id: " + id);
        }

    }

    //CREAR CUENTA
    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody AccountDto account) {
        try{
            AccountDto act = service.createAccount(account);
//        CREAMOS UNA VARIABLE Y LE ALMACENAMOS LOS DATOS DEL USUARIO EN FORMATO PARA MOSTRAR EN LA RESPUESTA
            String usuario;
            if (act.getOwnerid() != null){
                usuario = "Users Data: \n" + UserMapper.userToDto(userRepository.findById(act.getOwnerid()).get()).toString();
            }else usuario = "";
            return ResponseEntity.status(HttpStatus.CREATED).body(act.toString() + "\n" + usuario);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The account: " + account + "could not be created");
        }

    }

    //MODIFICA TOTAL Y PARCIALMENTE
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateAccount(@PathVariable Long id, @RequestBody AccountDto account) {
        try{
            AccountDto accountModified = service.updateAccount(id, account);
            return ResponseEntity.status(HttpStatus.OK).body("Account details has been updated: \n"+ accountModified);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not update account "+ id);
        }

    }

    //ELIMINAR CUENTA
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.deleteAccount(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There are no registered accounts with Id: " + id);
        }

    }
}

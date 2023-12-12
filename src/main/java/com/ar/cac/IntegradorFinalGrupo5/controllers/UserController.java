package com.ar.cac.IntegradorFinalGrupo5.controllers;

import com.ar.cac.IntegradorFinalGrupo5.entities.User;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.UserDto;
import com.ar.cac.IntegradorFinalGrupo5.mappers.UserMapper;
import com.ar.cac.IntegradorFinalGrupo5.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }


    // GET: Obtener listado de usuarios
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.
                status(HttpStatus.OK).
                body(userService.getUsers());
    }


    // GET: Obtener usuario por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<String> getUserById(@PathVariable Long id) {
        //DEVOLVEMOS UN STRING PARA PODER MOSTRAR SOLOS LOS DATOS MAS RELEVANTES A TRAVES DE UN METODO TOSTRIG Y ATRAPAMOS LA EXCEPTION
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Usuario encontrado: { " + userService.getUserById(id).toString() + " }");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No eixisten Usuarios registrados con id: " + id);
        }
    }

    // POST: Crear usuario
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(userService.createUser(userDto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDto user) {
        try {
            if (userService.updateUser(id, user) != null) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Usuario modificado: " + userService.updateUser(id, user));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("No se realizó la modificación. " + userService.updateUser(id, user));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No eixisten Usuarios registrados con id: " + id);
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.deleteUser(id));
    }
}

package com.ar.cac.IntegradorFinalGrupo5.entities.dtos;

import com.ar.cac.IntegradorFinalGrupo5.entities.Account;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String dni;
    private String address;
    private LocalDate birthdate;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Boolean enabled;
    private List<Account> accounts;

    //    UTILIZAMOS UN METODO TOSTRING PARA MOSTRAR SOLO LOS DATOS RELEVANTES EN LAS BUSQUEDAS
    public String toString() {
        return "\n Nombre: " + this.getUsername() +
                ",\n Email: " + this.getEmail() +
                ",\n DNI: " + this.getDni() +
                ",\n Domicilio: " + this.getAddress() +
                ",\n Fecha de Nacimiento: " + this.getBirthdate() +
                ",\n Fecha de creación: " + this.getCreated_at() +
                ",\n Fecha de ultima actualización: " + this.getUpdated_at();
    }
}

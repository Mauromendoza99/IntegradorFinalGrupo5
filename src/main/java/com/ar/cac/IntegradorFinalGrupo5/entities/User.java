package com.ar.cac.IntegradorFinalGrupo5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column(name = "nombre_usuario")
    private String username;

    @Column(name = "contraseña")
    private String password;

    @Column(name = "mail")
    private String email;

    private String dni;

    @Column(name = "direccion")
    private String address;

    @Column(name = "fecha_nacimiento")
    private LocalDate birthdate;

    @Column(name = "fecha_creacion")
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column(name = "fecha_ultima_actualizacion")
    @UpdateTimestamp
    private LocalDateTime updated_at;

    @Column(name = "habilitado")
    private Boolean enabled;
    //LISTA DE CUENTAS, CON EL CascadeType.ALL ESTAMOS DICIENDO QUE AL ELIMINAR SE LLEVE TODOS LOS SUBNIVELES,
    //CON orphanRemoval PERMITIMOS ELIMINAR REGISTROS DE OTRAS TABLAS, SI ELIMINO UN REGISTRO DE USUARIO TAMBIEN TODAS SUS CTAS
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts;
}

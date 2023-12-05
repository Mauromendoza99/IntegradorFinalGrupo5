package com.ar.cac.IntegradorFinalGrupo5.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "transferencias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "monto")
    private BigDecimal amount;

    @Column(name = "cuenta_Origen")
    private long originAccount;

    @Column(name = "cuenta_Destino")
    private long destinityAccount;

    @Column(name = "fecha_transaccion")
    private Date done_at;
}

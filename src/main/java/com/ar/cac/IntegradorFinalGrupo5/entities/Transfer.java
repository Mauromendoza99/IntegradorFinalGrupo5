package com.ar.cac.IntegradorFinalGrupo5.entities;

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

    @column(name = "monto")
    private BigDecimal amount;

    @column(name = "cuenta_Origen")
    private String originAccount;

    @column(name = "cuenta_Destino")
    private String destinityAccount;

    @column(name = "fecha_transaccion")
    private LocalDateTime done_at;
}

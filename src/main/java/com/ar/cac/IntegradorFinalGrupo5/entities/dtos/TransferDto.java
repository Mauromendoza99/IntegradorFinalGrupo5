package com.ar.cac.IntegradorFinalGrupo5.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
    public class TransferDto {

    private long id;

    private BigDecimal amount;

    private String originAccount;

    private String destinityAccount;

    private LocalDateTime done_at;

}

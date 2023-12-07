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

    private Long id;

    private BigDecimal amount;

    private Long originAccount;

    private Long destinityAccount;

    private LocalDateTime done_at;

}

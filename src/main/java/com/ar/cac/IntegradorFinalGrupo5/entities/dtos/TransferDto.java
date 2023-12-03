package com.ar.cac.IntegradorFinalGrupo5.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
    public class TransferDto {

    private long id;

    private BigDecimal amount;

    private long originAccount;

    private long destinityAccount;

    private Date done_at;

}

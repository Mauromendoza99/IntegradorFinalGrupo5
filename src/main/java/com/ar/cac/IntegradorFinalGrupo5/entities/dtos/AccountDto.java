package com.ar.cac.IntegradorFinalGrupo5.entities.dtos;

import com.ar.cac.IntegradorFinalGrupo5.entities.User;
import com.ar.cac.IntegradorFinalGrupo5.entities.enums.AccountType;
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
public class AccountDto {

    private Long id;

    private AccountType type;

    private String cbu;

    private String alias;

    private BigDecimal amount;

    private Boolean enabled;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private Long owner;


    //    UTILIZAMOS UN METODO TOSTRING PARA MOSTRAR SOLO LOS DATOS RELEVANTES EN LAS BUSQUEDAS
    public String toString() {
        return "\n Nro de cuenta: " + this.getId() +
                ",\n Tipo: " + this.getType() +
                ",\n CBU: " + this.getCbu() +
                ",\n ALIAS: " + this.getAlias() +
                ",\n Monto: " + this.getAmount() +
                ",\n Fecha de creación: " + this.getCreated_at() +
                ",\n Fecha de ultima actualización: " + this.getUpdated_at();
    }
}

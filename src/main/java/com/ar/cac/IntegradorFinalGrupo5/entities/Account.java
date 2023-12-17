package com.ar.cac.IntegradorFinalGrupo5.entities;

import com.ar.cac.IntegradorFinalGrupo5.entities.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cuentas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo")
    private AccountType type;

    private String cbu;

    private String alias;

    @Column(name = "monto")
    private BigDecimal amount;

    private Boolean enabled;

    @Column(name = "fecha_creacion")
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column(name = "fecha_ult_modificacion")
    @UpdateTimestamp
    private LocalDateTime update_at;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User owner;

}

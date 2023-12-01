package com.ar.cac.IntegradorFinalGrupo5.repositories;

import com.ar.cac.IntegradorFinalGrupo5.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}

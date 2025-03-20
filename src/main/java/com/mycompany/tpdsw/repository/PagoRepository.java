package com.mycompany.tpdsw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.tpdsw.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {

}

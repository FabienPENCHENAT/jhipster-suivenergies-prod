package com.suivenergies.app.repository;

import com.suivenergies.app.domain.Client;
import com.suivenergies.app.domain.Facture;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Facture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
	
    @Query("select distinct facture from Facture facture left join fetch facture.client")
    List<Facture> findAllWithEagerRelationships();
}

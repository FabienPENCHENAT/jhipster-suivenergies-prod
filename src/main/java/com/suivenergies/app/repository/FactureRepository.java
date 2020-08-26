package com.suivenergies.app.repository;

import com.suivenergies.app.domain.Client;
import com.suivenergies.app.domain.Facture;
import com.suivenergies.app.domain.InfoDPE;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Facture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
    @Query("select distinct facture from Facture facture left join fetch facture.client")
    List<Facture> findAllWithEagerRelationships();

    @Query(
        "select distinct facture from Facture facture left join fetch facture.client client where client.id =:id ORDER BY facture.type, facture.annee"
    )
    Optional<List<Facture>> findAllByClientId(@Param("id") Long id);
}

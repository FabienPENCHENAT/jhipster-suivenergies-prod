package com.suivenergies.app.repository;

import com.suivenergies.app.domain.Client;
import com.suivenergies.app.domain.InfoDPE;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InfoDPE entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InfoDPERepository extends JpaRepository<InfoDPE, Long> {
    @Query("select info_dpe from InfoDPE info_dpe left join fetch info_dpe.client client where client.id =:id ORDER BY info_dpe.dateDPE")
    Optional<InfoDPE> findLastOneByClientId(@Param("id") Long id);
}

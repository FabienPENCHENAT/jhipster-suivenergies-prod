package com.suivenergies.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.suivenergies.app.domain.InfoDPE;

/**
 * Spring Data  repository for the InfoDPE entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InfoDPERepository extends JpaRepository<InfoDPE, Long> {
    @Query(value = "select * from info_dpe info_dpe where client_id =:id ORDER BY info_dpe.id DESC LIMIT 1", nativeQuery = true)
    Optional<InfoDPE> findLastOneByClientId(@Param("id") Long id);
}

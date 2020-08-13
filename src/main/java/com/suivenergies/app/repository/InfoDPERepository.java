package com.suivenergies.app.repository;

import com.suivenergies.app.domain.InfoDPE;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InfoDPE entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InfoDPERepository extends JpaRepository<InfoDPE, Long> {
}

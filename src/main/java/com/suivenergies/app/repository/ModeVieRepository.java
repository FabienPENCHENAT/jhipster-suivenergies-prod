package com.suivenergies.app.repository;

import com.suivenergies.app.domain.ModeVie;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ModeVie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModeVieRepository extends JpaRepository<ModeVie, Long> {
}

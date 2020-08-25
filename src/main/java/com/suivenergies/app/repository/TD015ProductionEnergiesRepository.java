package com.suivenergies.app.repository;

import com.suivenergies.app.domain.TD015ProductionEnergies;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TD015ProductionEnergies entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TD015ProductionEnergiesRepository extends JpaRepository<TD015ProductionEnergies, Long> {
}

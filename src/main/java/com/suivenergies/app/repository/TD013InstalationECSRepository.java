package com.suivenergies.app.repository;

import com.suivenergies.app.domain.TD013InstalationECS;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TD013InstalationECS entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TD013InstalationECSRepository extends JpaRepository<TD013InstalationECS, Long> {
}

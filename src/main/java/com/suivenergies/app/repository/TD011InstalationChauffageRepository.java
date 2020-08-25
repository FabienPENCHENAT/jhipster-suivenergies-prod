package com.suivenergies.app.repository;

import com.suivenergies.app.domain.TD011InstalationChauffage;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TD011InstalationChauffage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TD011InstalationChauffageRepository extends JpaRepository<TD011InstalationChauffage, Long> {
}

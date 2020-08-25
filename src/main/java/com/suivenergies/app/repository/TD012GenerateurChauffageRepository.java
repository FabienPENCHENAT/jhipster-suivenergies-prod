package com.suivenergies.app.repository;

import com.suivenergies.app.domain.TD012GenerateurChauffage;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TD012GenerateurChauffage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TD012GenerateurChauffageRepository extends JpaRepository<TD012GenerateurChauffage, Long> {
}

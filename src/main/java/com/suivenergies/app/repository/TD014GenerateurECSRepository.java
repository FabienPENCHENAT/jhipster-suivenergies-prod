package com.suivenergies.app.repository;

import com.suivenergies.app.domain.TD014GenerateurECS;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TD014GenerateurECS entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TD014GenerateurECSRepository extends JpaRepository<TD014GenerateurECS, Long> {
}

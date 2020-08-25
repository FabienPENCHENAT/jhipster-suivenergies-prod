package com.suivenergies.app.repository;

import com.suivenergies.app.domain.TD006Batiment;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TD006Batiment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TD006BatimentRepository extends JpaRepository<TD006Batiment, Long> {
}

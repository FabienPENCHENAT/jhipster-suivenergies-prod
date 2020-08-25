package com.suivenergies.app.repository;

import com.suivenergies.app.domain.TD008Baie;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TD008Baie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TD008BaieRepository extends JpaRepository<TD008Baie, Long> {
}

package com.suivenergies.app.repository;

import com.suivenergies.app.domain.TD010PontsThermiques;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TD010PontsThermiques entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TD010PontsThermiquesRepository extends JpaRepository<TD010PontsThermiques, Long> {
}

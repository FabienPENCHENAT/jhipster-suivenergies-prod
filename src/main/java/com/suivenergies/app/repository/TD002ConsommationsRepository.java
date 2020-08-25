package com.suivenergies.app.repository;

import com.suivenergies.app.domain.TD002Consommations;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TD002Consommations entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TD002ConsommationsRepository extends JpaRepository<TD002Consommations, Long> {
}

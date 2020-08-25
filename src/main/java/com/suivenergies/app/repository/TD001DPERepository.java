package com.suivenergies.app.repository;

import com.suivenergies.app.domain.TD001DPE;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TD001DPE entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TD001DPERepository extends JpaRepository<TD001DPE, Long> {
}

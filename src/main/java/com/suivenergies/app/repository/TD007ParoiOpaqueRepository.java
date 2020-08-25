package com.suivenergies.app.repository;

import com.suivenergies.app.domain.TD007ParoiOpaque;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TD007ParoiOpaque entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TD007ParoiOpaqueRepository extends JpaRepository<TD007ParoiOpaque, Long> {
}

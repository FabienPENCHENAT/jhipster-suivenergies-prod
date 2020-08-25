package com.suivenergies.app.repository;

import com.suivenergies.app.domain.TD017ConsommationNeuf;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TD017ConsommationNeuf entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TD017ConsommationNeufRepository extends JpaRepository<TD017ConsommationNeuf, Long> {
}

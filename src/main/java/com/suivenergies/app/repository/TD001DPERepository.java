package com.suivenergies.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suivenergies.app.domain.TD001DPE;

/**
 * Spring Data  repository for the TD001DPE entity.
 */
@Repository
public interface TD001DPERepository extends JpaRepository<TD001DPE, Long> {
	
    TD001DPE findBynumeroDpe(String numeroDpe);
}

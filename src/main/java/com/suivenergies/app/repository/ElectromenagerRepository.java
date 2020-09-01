package com.suivenergies.app.repository;

import com.suivenergies.app.domain.Electromenager;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Electromenager entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ElectromenagerRepository extends JpaRepository<Electromenager, Long> {
    @Query("select distinct electromenager from Electromenager electromenager where electromenager.byDefault = TRUE")
    List<Electromenager> findAllDefault();
}

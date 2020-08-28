package com.suivenergies.app.repository;

import com.suivenergies.app.domain.InfoDPE;
import com.suivenergies.app.domain.ModeVie;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ModeVie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModeVieRepository extends JpaRepository<ModeVie, Long> {
    @Query("select mode_vie from ModeVie mode_vie left join fetch mode_vie.client client where client.id =:id")
    Optional<ModeVie> findOneByClientId(@Param("id") Long id);
}

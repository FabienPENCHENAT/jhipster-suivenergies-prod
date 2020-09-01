package com.suivenergies.app.repository;

import com.suivenergies.app.domain.Confort;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Confort entity.
 */
@Repository
public interface ConfortRepository extends JpaRepository<Confort, Long> {
    @Query(
        value = "select distinct confort from Confort confort left join fetch confort.electromenagers",
        countQuery = "select count(distinct confort) from Confort confort"
    )
    Page<Confort> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct confort from Confort confort left join fetch confort.electromenagers")
    List<Confort> findAllWithEagerRelationships();

    @Query("select confort from Confort confort left join fetch confort.electromenagers where confort.id =:id")
    Optional<Confort> findOneWithEagerRelationships(@Param("id") Long id);

    @Query(
        "select confort from Confort confort left join fetch confort.electromenagers left join fetch confort.client client where client.id =:id"
    )
    Confort findOneByClientId(@Param("id") Long id);
}

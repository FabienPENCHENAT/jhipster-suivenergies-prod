package com.suivenergies.app.repository;

import com.suivenergies.app.domain.Client;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Client entity.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query(
        value = "select distinct client from Client client left join fetch client.users",
        countQuery = "select count(distinct client) from Client client"
    )
    Page<Client> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct client from Client client left join fetch client.users")
    List<Client> findAllWithEagerRelationships();

    @Query("select client from Client client left join fetch client.users where client.id =:id")
    Optional<Client> findOneWithEagerRelationships(@Param("id") Long id);

    @Query("select client from Client client left join fetch client.users user where user.id =:id")
    Optional<Client> findOneByUsersId(@Param("id") Long id);
}

package com.suivenergies.app.web.rest;

import com.suivenergies.app.domain.TD014GenerateurECS;
import com.suivenergies.app.repository.TD014GenerateurECSRepository;
import com.suivenergies.app.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.suivenergies.app.domain.TD014GenerateurECS}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TD014GenerateurECSResource {

    private final Logger log = LoggerFactory.getLogger(TD014GenerateurECSResource.class);

    private static final String ENTITY_NAME = "tD014GenerateurECS";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TD014GenerateurECSRepository tD014GenerateurECSRepository;

    public TD014GenerateurECSResource(TD014GenerateurECSRepository tD014GenerateurECSRepository) {
        this.tD014GenerateurECSRepository = tD014GenerateurECSRepository;
    }

    /**
     * {@code POST  /td-014-generateur-ecs} : Create a new tD014GenerateurECS.
     *
     * @param tD014GenerateurECS the tD014GenerateurECS to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tD014GenerateurECS, or with status {@code 400 (Bad Request)} if the tD014GenerateurECS has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/td-014-generateur-ecs")
    public ResponseEntity<TD014GenerateurECS> createTD014GenerateurECS(@RequestBody TD014GenerateurECS tD014GenerateurECS) throws URISyntaxException {
        log.debug("REST request to save TD014GenerateurECS : {}", tD014GenerateurECS);
        if (tD014GenerateurECS.getId() != null) {
            throw new BadRequestAlertException("A new tD014GenerateurECS cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TD014GenerateurECS result = tD014GenerateurECSRepository.save(tD014GenerateurECS);
        return ResponseEntity.created(new URI("/api/td-014-generateur-ecs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /td-014-generateur-ecs} : Updates an existing tD014GenerateurECS.
     *
     * @param tD014GenerateurECS the tD014GenerateurECS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tD014GenerateurECS,
     * or with status {@code 400 (Bad Request)} if the tD014GenerateurECS is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tD014GenerateurECS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/td-014-generateur-ecs")
    public ResponseEntity<TD014GenerateurECS> updateTD014GenerateurECS(@RequestBody TD014GenerateurECS tD014GenerateurECS) throws URISyntaxException {
        log.debug("REST request to update TD014GenerateurECS : {}", tD014GenerateurECS);
        if (tD014GenerateurECS.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TD014GenerateurECS result = tD014GenerateurECSRepository.save(tD014GenerateurECS);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tD014GenerateurECS.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /td-014-generateur-ecs} : get all the tD014GenerateurECS.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tD014GenerateurECS in body.
     */
    @GetMapping("/td-014-generateur-ecs")
    public List<TD014GenerateurECS> getAllTD014GenerateurECS(@RequestParam(required = false) String filter) {
        if ("td013instalationecs-is-null".equals(filter)) {
            log.debug("REST request to get all TD014GenerateurECSs where td013InstalationECS is null");
            return StreamSupport
                .stream(tD014GenerateurECSRepository.findAll().spliterator(), false)
                .filter(tD014GenerateurECS -> tD014GenerateurECS.getTd013InstalationECS() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all TD014GenerateurECS");
        return tD014GenerateurECSRepository.findAll();
    }

    /**
     * {@code GET  /td-014-generateur-ecs/:id} : get the "id" tD014GenerateurECS.
     *
     * @param id the id of the tD014GenerateurECS to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tD014GenerateurECS, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/td-014-generateur-ecs/{id}")
    public ResponseEntity<TD014GenerateurECS> getTD014GenerateurECS(@PathVariable Long id) {
        log.debug("REST request to get TD014GenerateurECS : {}", id);
        Optional<TD014GenerateurECS> tD014GenerateurECS = tD014GenerateurECSRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tD014GenerateurECS);
    }

    /**
     * {@code DELETE  /td-014-generateur-ecs/:id} : delete the "id" tD014GenerateurECS.
     *
     * @param id the id of the tD014GenerateurECS to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/td-014-generateur-ecs/{id}")
    public ResponseEntity<Void> deleteTD014GenerateurECS(@PathVariable Long id) {
        log.debug("REST request to delete TD014GenerateurECS : {}", id);
        tD014GenerateurECSRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

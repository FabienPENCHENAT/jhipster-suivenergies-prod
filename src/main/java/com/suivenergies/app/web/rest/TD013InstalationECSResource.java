package com.suivenergies.app.web.rest;

import com.suivenergies.app.domain.TD013InstalationECS;
import com.suivenergies.app.repository.TD013InstalationECSRepository;
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
 * REST controller for managing {@link com.suivenergies.app.domain.TD013InstalationECS}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TD013InstalationECSResource {

    private final Logger log = LoggerFactory.getLogger(TD013InstalationECSResource.class);

    private static final String ENTITY_NAME = "tD013InstalationECS";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TD013InstalationECSRepository tD013InstalationECSRepository;

    public TD013InstalationECSResource(TD013InstalationECSRepository tD013InstalationECSRepository) {
        this.tD013InstalationECSRepository = tD013InstalationECSRepository;
    }

    /**
     * {@code POST  /td-013-instalation-ecs} : Create a new tD013InstalationECS.
     *
     * @param tD013InstalationECS the tD013InstalationECS to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tD013InstalationECS, or with status {@code 400 (Bad Request)} if the tD013InstalationECS has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/td-013-instalation-ecs")
    public ResponseEntity<TD013InstalationECS> createTD013InstalationECS(@RequestBody TD013InstalationECS tD013InstalationECS) throws URISyntaxException {
        log.debug("REST request to save TD013InstalationECS : {}", tD013InstalationECS);
        if (tD013InstalationECS.getId() != null) {
            throw new BadRequestAlertException("A new tD013InstalationECS cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TD013InstalationECS result = tD013InstalationECSRepository.save(tD013InstalationECS);
        return ResponseEntity.created(new URI("/api/td-013-instalation-ecs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /td-013-instalation-ecs} : Updates an existing tD013InstalationECS.
     *
     * @param tD013InstalationECS the tD013InstalationECS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tD013InstalationECS,
     * or with status {@code 400 (Bad Request)} if the tD013InstalationECS is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tD013InstalationECS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/td-013-instalation-ecs")
    public ResponseEntity<TD013InstalationECS> updateTD013InstalationECS(@RequestBody TD013InstalationECS tD013InstalationECS) throws URISyntaxException {
        log.debug("REST request to update TD013InstalationECS : {}", tD013InstalationECS);
        if (tD013InstalationECS.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TD013InstalationECS result = tD013InstalationECSRepository.save(tD013InstalationECS);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tD013InstalationECS.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /td-013-instalation-ecs} : get all the tD013InstalationECS.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tD013InstalationECS in body.
     */
    @GetMapping("/td-013-instalation-ecs")
    public List<TD013InstalationECS> getAllTD013InstalationECS(@RequestParam(required = false) String filter) {
        if ("td006batiment-is-null".equals(filter)) {
            log.debug("REST request to get all TD013InstalationECSs where td006Batiment is null");
            return StreamSupport
                .stream(tD013InstalationECSRepository.findAll().spliterator(), false)
                .filter(tD013InstalationECS -> tD013InstalationECS.getTd006Batiment() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all TD013InstalationECS");
        return tD013InstalationECSRepository.findAll();
    }

    /**
     * {@code GET  /td-013-instalation-ecs/:id} : get the "id" tD013InstalationECS.
     *
     * @param id the id of the tD013InstalationECS to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tD013InstalationECS, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/td-013-instalation-ecs/{id}")
    public ResponseEntity<TD013InstalationECS> getTD013InstalationECS(@PathVariable Long id) {
        log.debug("REST request to get TD013InstalationECS : {}", id);
        Optional<TD013InstalationECS> tD013InstalationECS = tD013InstalationECSRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tD013InstalationECS);
    }

    /**
     * {@code DELETE  /td-013-instalation-ecs/:id} : delete the "id" tD013InstalationECS.
     *
     * @param id the id of the tD013InstalationECS to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/td-013-instalation-ecs/{id}")
    public ResponseEntity<Void> deleteTD013InstalationECS(@PathVariable Long id) {
        log.debug("REST request to delete TD013InstalationECS : {}", id);
        tD013InstalationECSRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

package com.suivenergies.app.web.rest;

import com.suivenergies.app.domain.TD015ProductionEnergies;
import com.suivenergies.app.repository.TD015ProductionEnergiesRepository;
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
 * REST controller for managing {@link com.suivenergies.app.domain.TD015ProductionEnergies}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TD015ProductionEnergiesResource {

    private final Logger log = LoggerFactory.getLogger(TD015ProductionEnergiesResource.class);

    private static final String ENTITY_NAME = "tD015ProductionEnergies";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TD015ProductionEnergiesRepository tD015ProductionEnergiesRepository;

    public TD015ProductionEnergiesResource(TD015ProductionEnergiesRepository tD015ProductionEnergiesRepository) {
        this.tD015ProductionEnergiesRepository = tD015ProductionEnergiesRepository;
    }

    /**
     * {@code POST  /td-015-production-energies} : Create a new tD015ProductionEnergies.
     *
     * @param tD015ProductionEnergies the tD015ProductionEnergies to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tD015ProductionEnergies, or with status {@code 400 (Bad Request)} if the tD015ProductionEnergies has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/td-015-production-energies")
    public ResponseEntity<TD015ProductionEnergies> createTD015ProductionEnergies(@RequestBody TD015ProductionEnergies tD015ProductionEnergies) throws URISyntaxException {
        log.debug("REST request to save TD015ProductionEnergies : {}", tD015ProductionEnergies);
        if (tD015ProductionEnergies.getId() != null) {
            throw new BadRequestAlertException("A new tD015ProductionEnergies cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TD015ProductionEnergies result = tD015ProductionEnergiesRepository.save(tD015ProductionEnergies);
        return ResponseEntity.created(new URI("/api/td-015-production-energies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /td-015-production-energies} : Updates an existing tD015ProductionEnergies.
     *
     * @param tD015ProductionEnergies the tD015ProductionEnergies to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tD015ProductionEnergies,
     * or with status {@code 400 (Bad Request)} if the tD015ProductionEnergies is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tD015ProductionEnergies couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/td-015-production-energies")
    public ResponseEntity<TD015ProductionEnergies> updateTD015ProductionEnergies(@RequestBody TD015ProductionEnergies tD015ProductionEnergies) throws URISyntaxException {
        log.debug("REST request to update TD015ProductionEnergies : {}", tD015ProductionEnergies);
        if (tD015ProductionEnergies.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TD015ProductionEnergies result = tD015ProductionEnergiesRepository.save(tD015ProductionEnergies);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tD015ProductionEnergies.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /td-015-production-energies} : get all the tD015ProductionEnergies.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tD015ProductionEnergies in body.
     */
    @GetMapping("/td-015-production-energies")
    public List<TD015ProductionEnergies> getAllTD015ProductionEnergies(@RequestParam(required = false) String filter) {
        if ("td006batiment-is-null".equals(filter)) {
            log.debug("REST request to get all TD015ProductionEnergiess where td006Batiment is null");
            return StreamSupport
                .stream(tD015ProductionEnergiesRepository.findAll().spliterator(), false)
                .filter(tD015ProductionEnergies -> tD015ProductionEnergies.getTd006Batiment() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all TD015ProductionEnergies");
        return tD015ProductionEnergiesRepository.findAll();
    }

    /**
     * {@code GET  /td-015-production-energies/:id} : get the "id" tD015ProductionEnergies.
     *
     * @param id the id of the tD015ProductionEnergies to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tD015ProductionEnergies, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/td-015-production-energies/{id}")
    public ResponseEntity<TD015ProductionEnergies> getTD015ProductionEnergies(@PathVariable Long id) {
        log.debug("REST request to get TD015ProductionEnergies : {}", id);
        Optional<TD015ProductionEnergies> tD015ProductionEnergies = tD015ProductionEnergiesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tD015ProductionEnergies);
    }

    /**
     * {@code DELETE  /td-015-production-energies/:id} : delete the "id" tD015ProductionEnergies.
     *
     * @param id the id of the tD015ProductionEnergies to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/td-015-production-energies/{id}")
    public ResponseEntity<Void> deleteTD015ProductionEnergies(@PathVariable Long id) {
        log.debug("REST request to delete TD015ProductionEnergies : {}", id);
        tD015ProductionEnergiesRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

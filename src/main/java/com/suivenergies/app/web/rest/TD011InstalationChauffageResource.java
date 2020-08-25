package com.suivenergies.app.web.rest;

import com.suivenergies.app.domain.TD011InstalationChauffage;
import com.suivenergies.app.repository.TD011InstalationChauffageRepository;
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
 * REST controller for managing {@link com.suivenergies.app.domain.TD011InstalationChauffage}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TD011InstalationChauffageResource {

    private final Logger log = LoggerFactory.getLogger(TD011InstalationChauffageResource.class);

    private static final String ENTITY_NAME = "tD011InstalationChauffage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TD011InstalationChauffageRepository tD011InstalationChauffageRepository;

    public TD011InstalationChauffageResource(TD011InstalationChauffageRepository tD011InstalationChauffageRepository) {
        this.tD011InstalationChauffageRepository = tD011InstalationChauffageRepository;
    }

    /**
     * {@code POST  /td-011-instalation-chauffages} : Create a new tD011InstalationChauffage.
     *
     * @param tD011InstalationChauffage the tD011InstalationChauffage to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tD011InstalationChauffage, or with status {@code 400 (Bad Request)} if the tD011InstalationChauffage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/td-011-instalation-chauffages")
    public ResponseEntity<TD011InstalationChauffage> createTD011InstalationChauffage(@RequestBody TD011InstalationChauffage tD011InstalationChauffage) throws URISyntaxException {
        log.debug("REST request to save TD011InstalationChauffage : {}", tD011InstalationChauffage);
        if (tD011InstalationChauffage.getId() != null) {
            throw new BadRequestAlertException("A new tD011InstalationChauffage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TD011InstalationChauffage result = tD011InstalationChauffageRepository.save(tD011InstalationChauffage);
        return ResponseEntity.created(new URI("/api/td-011-instalation-chauffages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /td-011-instalation-chauffages} : Updates an existing tD011InstalationChauffage.
     *
     * @param tD011InstalationChauffage the tD011InstalationChauffage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tD011InstalationChauffage,
     * or with status {@code 400 (Bad Request)} if the tD011InstalationChauffage is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tD011InstalationChauffage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/td-011-instalation-chauffages")
    public ResponseEntity<TD011InstalationChauffage> updateTD011InstalationChauffage(@RequestBody TD011InstalationChauffage tD011InstalationChauffage) throws URISyntaxException {
        log.debug("REST request to update TD011InstalationChauffage : {}", tD011InstalationChauffage);
        if (tD011InstalationChauffage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TD011InstalationChauffage result = tD011InstalationChauffageRepository.save(tD011InstalationChauffage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tD011InstalationChauffage.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /td-011-instalation-chauffages} : get all the tD011InstalationChauffages.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tD011InstalationChauffages in body.
     */
    @GetMapping("/td-011-instalation-chauffages")
    public List<TD011InstalationChauffage> getAllTD011InstalationChauffages(@RequestParam(required = false) String filter) {
        if ("td006batiment-is-null".equals(filter)) {
            log.debug("REST request to get all TD011InstalationChauffages where td006Batiment is null");
            return StreamSupport
                .stream(tD011InstalationChauffageRepository.findAll().spliterator(), false)
                .filter(tD011InstalationChauffage -> tD011InstalationChauffage.getTd006Batiment() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all TD011InstalationChauffages");
        return tD011InstalationChauffageRepository.findAll();
    }

    /**
     * {@code GET  /td-011-instalation-chauffages/:id} : get the "id" tD011InstalationChauffage.
     *
     * @param id the id of the tD011InstalationChauffage to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tD011InstalationChauffage, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/td-011-instalation-chauffages/{id}")
    public ResponseEntity<TD011InstalationChauffage> getTD011InstalationChauffage(@PathVariable Long id) {
        log.debug("REST request to get TD011InstalationChauffage : {}", id);
        Optional<TD011InstalationChauffage> tD011InstalationChauffage = tD011InstalationChauffageRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tD011InstalationChauffage);
    }

    /**
     * {@code DELETE  /td-011-instalation-chauffages/:id} : delete the "id" tD011InstalationChauffage.
     *
     * @param id the id of the tD011InstalationChauffage to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/td-011-instalation-chauffages/{id}")
    public ResponseEntity<Void> deleteTD011InstalationChauffage(@PathVariable Long id) {
        log.debug("REST request to delete TD011InstalationChauffage : {}", id);
        tD011InstalationChauffageRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

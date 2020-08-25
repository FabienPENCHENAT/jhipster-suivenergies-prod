package com.suivenergies.app.web.rest;

import com.suivenergies.app.domain.TD002Consommations;
import com.suivenergies.app.repository.TD002ConsommationsRepository;
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
 * REST controller for managing {@link com.suivenergies.app.domain.TD002Consommations}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TD002ConsommationsResource {

    private final Logger log = LoggerFactory.getLogger(TD002ConsommationsResource.class);

    private static final String ENTITY_NAME = "tD002Consommations";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TD002ConsommationsRepository tD002ConsommationsRepository;

    public TD002ConsommationsResource(TD002ConsommationsRepository tD002ConsommationsRepository) {
        this.tD002ConsommationsRepository = tD002ConsommationsRepository;
    }

    /**
     * {@code POST  /td-002-consommations} : Create a new tD002Consommations.
     *
     * @param tD002Consommations the tD002Consommations to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tD002Consommations, or with status {@code 400 (Bad Request)} if the tD002Consommations has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/td-002-consommations")
    public ResponseEntity<TD002Consommations> createTD002Consommations(@RequestBody TD002Consommations tD002Consommations) throws URISyntaxException {
        log.debug("REST request to save TD002Consommations : {}", tD002Consommations);
        if (tD002Consommations.getId() != null) {
            throw new BadRequestAlertException("A new tD002Consommations cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TD002Consommations result = tD002ConsommationsRepository.save(tD002Consommations);
        return ResponseEntity.created(new URI("/api/td-002-consommations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /td-002-consommations} : Updates an existing tD002Consommations.
     *
     * @param tD002Consommations the tD002Consommations to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tD002Consommations,
     * or with status {@code 400 (Bad Request)} if the tD002Consommations is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tD002Consommations couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/td-002-consommations")
    public ResponseEntity<TD002Consommations> updateTD002Consommations(@RequestBody TD002Consommations tD002Consommations) throws URISyntaxException {
        log.debug("REST request to update TD002Consommations : {}", tD002Consommations);
        if (tD002Consommations.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TD002Consommations result = tD002ConsommationsRepository.save(tD002Consommations);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tD002Consommations.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /td-002-consommations} : get all the tD002Consommations.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tD002Consommations in body.
     */
    @GetMapping("/td-002-consommations")
    public List<TD002Consommations> getAllTD002Consommations(@RequestParam(required = false) String filter) {
        if ("td001dpe-is-null".equals(filter)) {
            log.debug("REST request to get all TD002Consommationss where td001DPE is null");
            return StreamSupport
                .stream(tD002ConsommationsRepository.findAll().spliterator(), false)
                .filter(tD002Consommations -> tD002Consommations.getTd001DPE() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all TD002Consommations");
        return tD002ConsommationsRepository.findAll();
    }

    /**
     * {@code GET  /td-002-consommations/:id} : get the "id" tD002Consommations.
     *
     * @param id the id of the tD002Consommations to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tD002Consommations, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/td-002-consommations/{id}")
    public ResponseEntity<TD002Consommations> getTD002Consommations(@PathVariable Long id) {
        log.debug("REST request to get TD002Consommations : {}", id);
        Optional<TD002Consommations> tD002Consommations = tD002ConsommationsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tD002Consommations);
    }

    /**
     * {@code DELETE  /td-002-consommations/:id} : delete the "id" tD002Consommations.
     *
     * @param id the id of the tD002Consommations to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/td-002-consommations/{id}")
    public ResponseEntity<Void> deleteTD002Consommations(@PathVariable Long id) {
        log.debug("REST request to delete TD002Consommations : {}", id);
        tD002ConsommationsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

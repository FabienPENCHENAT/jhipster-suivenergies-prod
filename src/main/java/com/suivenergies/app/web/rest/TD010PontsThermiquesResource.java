package com.suivenergies.app.web.rest;

import com.suivenergies.app.domain.TD010PontsThermiques;
import com.suivenergies.app.repository.TD010PontsThermiquesRepository;
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
 * REST controller for managing {@link com.suivenergies.app.domain.TD010PontsThermiques}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TD010PontsThermiquesResource {

    private final Logger log = LoggerFactory.getLogger(TD010PontsThermiquesResource.class);

    private static final String ENTITY_NAME = "tD010PontsThermiques";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TD010PontsThermiquesRepository tD010PontsThermiquesRepository;

    public TD010PontsThermiquesResource(TD010PontsThermiquesRepository tD010PontsThermiquesRepository) {
        this.tD010PontsThermiquesRepository = tD010PontsThermiquesRepository;
    }

    /**
     * {@code POST  /td-010-ponts-thermiques} : Create a new tD010PontsThermiques.
     *
     * @param tD010PontsThermiques the tD010PontsThermiques to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tD010PontsThermiques, or with status {@code 400 (Bad Request)} if the tD010PontsThermiques has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/td-010-ponts-thermiques")
    public ResponseEntity<TD010PontsThermiques> createTD010PontsThermiques(@RequestBody TD010PontsThermiques tD010PontsThermiques) throws URISyntaxException {
        log.debug("REST request to save TD010PontsThermiques : {}", tD010PontsThermiques);
        if (tD010PontsThermiques.getId() != null) {
            throw new BadRequestAlertException("A new tD010PontsThermiques cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TD010PontsThermiques result = tD010PontsThermiquesRepository.save(tD010PontsThermiques);
        return ResponseEntity.created(new URI("/api/td-010-ponts-thermiques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /td-010-ponts-thermiques} : Updates an existing tD010PontsThermiques.
     *
     * @param tD010PontsThermiques the tD010PontsThermiques to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tD010PontsThermiques,
     * or with status {@code 400 (Bad Request)} if the tD010PontsThermiques is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tD010PontsThermiques couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/td-010-ponts-thermiques")
    public ResponseEntity<TD010PontsThermiques> updateTD010PontsThermiques(@RequestBody TD010PontsThermiques tD010PontsThermiques) throws URISyntaxException {
        log.debug("REST request to update TD010PontsThermiques : {}", tD010PontsThermiques);
        if (tD010PontsThermiques.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TD010PontsThermiques result = tD010PontsThermiquesRepository.save(tD010PontsThermiques);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tD010PontsThermiques.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /td-010-ponts-thermiques} : get all the tD010PontsThermiques.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tD010PontsThermiques in body.
     */
    @GetMapping("/td-010-ponts-thermiques")
    public List<TD010PontsThermiques> getAllTD010PontsThermiques(@RequestParam(required = false) String filter) {
        if ("td006batiment-is-null".equals(filter)) {
            log.debug("REST request to get all TD010PontsThermiquess where td006Batiment is null");
            return StreamSupport
                .stream(tD010PontsThermiquesRepository.findAll().spliterator(), false)
                .filter(tD010PontsThermiques -> tD010PontsThermiques.getTd006Batiment() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all TD010PontsThermiques");
        return tD010PontsThermiquesRepository.findAll();
    }

    /**
     * {@code GET  /td-010-ponts-thermiques/:id} : get the "id" tD010PontsThermiques.
     *
     * @param id the id of the tD010PontsThermiques to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tD010PontsThermiques, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/td-010-ponts-thermiques/{id}")
    public ResponseEntity<TD010PontsThermiques> getTD010PontsThermiques(@PathVariable Long id) {
        log.debug("REST request to get TD010PontsThermiques : {}", id);
        Optional<TD010PontsThermiques> tD010PontsThermiques = tD010PontsThermiquesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tD010PontsThermiques);
    }

    /**
     * {@code DELETE  /td-010-ponts-thermiques/:id} : delete the "id" tD010PontsThermiques.
     *
     * @param id the id of the tD010PontsThermiques to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/td-010-ponts-thermiques/{id}")
    public ResponseEntity<Void> deleteTD010PontsThermiques(@PathVariable Long id) {
        log.debug("REST request to delete TD010PontsThermiques : {}", id);
        tD010PontsThermiquesRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

package com.suivenergies.app.web.rest;

import com.suivenergies.app.domain.TD006Batiment;
import com.suivenergies.app.repository.TD006BatimentRepository;
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
 * REST controller for managing {@link com.suivenergies.app.domain.TD006Batiment}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TD006BatimentResource {

    private final Logger log = LoggerFactory.getLogger(TD006BatimentResource.class);

    private static final String ENTITY_NAME = "tD006Batiment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TD006BatimentRepository tD006BatimentRepository;

    public TD006BatimentResource(TD006BatimentRepository tD006BatimentRepository) {
        this.tD006BatimentRepository = tD006BatimentRepository;
    }

    /**
     * {@code POST  /td-006-batiments} : Create a new tD006Batiment.
     *
     * @param tD006Batiment the tD006Batiment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tD006Batiment, or with status {@code 400 (Bad Request)} if the tD006Batiment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/td-006-batiments")
    public ResponseEntity<TD006Batiment> createTD006Batiment(@RequestBody TD006Batiment tD006Batiment) throws URISyntaxException {
        log.debug("REST request to save TD006Batiment : {}", tD006Batiment);
        if (tD006Batiment.getId() != null) {
            throw new BadRequestAlertException("A new tD006Batiment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TD006Batiment result = tD006BatimentRepository.save(tD006Batiment);
        return ResponseEntity.created(new URI("/api/td-006-batiments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /td-006-batiments} : Updates an existing tD006Batiment.
     *
     * @param tD006Batiment the tD006Batiment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tD006Batiment,
     * or with status {@code 400 (Bad Request)} if the tD006Batiment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tD006Batiment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/td-006-batiments")
    public ResponseEntity<TD006Batiment> updateTD006Batiment(@RequestBody TD006Batiment tD006Batiment) throws URISyntaxException {
        log.debug("REST request to update TD006Batiment : {}", tD006Batiment);
        if (tD006Batiment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TD006Batiment result = tD006BatimentRepository.save(tD006Batiment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tD006Batiment.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /td-006-batiments} : get all the tD006Batiments.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tD006Batiments in body.
     */
    @GetMapping("/td-006-batiments")
    public List<TD006Batiment> getAllTD006Batiments(@RequestParam(required = false) String filter) {
        if ("td001dpe-is-null".equals(filter)) {
            log.debug("REST request to get all TD006Batiments where td001DPE is null");
            return StreamSupport
                .stream(tD006BatimentRepository.findAll().spliterator(), false)
                .filter(tD006Batiment -> tD006Batiment.getTd001DPE() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all TD006Batiments");
        return tD006BatimentRepository.findAll();
    }

    /**
     * {@code GET  /td-006-batiments/:id} : get the "id" tD006Batiment.
     *
     * @param id the id of the tD006Batiment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tD006Batiment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/td-006-batiments/{id}")
    public ResponseEntity<TD006Batiment> getTD006Batiment(@PathVariable Long id) {
        log.debug("REST request to get TD006Batiment : {}", id);
        Optional<TD006Batiment> tD006Batiment = tD006BatimentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tD006Batiment);
    }

    /**
     * {@code DELETE  /td-006-batiments/:id} : delete the "id" tD006Batiment.
     *
     * @param id the id of the tD006Batiment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/td-006-batiments/{id}")
    public ResponseEntity<Void> deleteTD006Batiment(@PathVariable Long id) {
        log.debug("REST request to delete TD006Batiment : {}", id);
        tD006BatimentRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

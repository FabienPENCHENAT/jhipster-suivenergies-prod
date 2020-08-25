package com.suivenergies.app.web.rest;

import com.suivenergies.app.domain.TD001DPE;
import com.suivenergies.app.repository.TD001DPERepository;
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

/**
 * REST controller for managing {@link com.suivenergies.app.domain.TD001DPE}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TD001DPEResource {

    private final Logger log = LoggerFactory.getLogger(TD001DPEResource.class);

    private static final String ENTITY_NAME = "tD001DPE";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TD001DPERepository tD001DPERepository;

    public TD001DPEResource(TD001DPERepository tD001DPERepository) {
        this.tD001DPERepository = tD001DPERepository;
    }

    /**
     * {@code POST  /td-001-dpes} : Create a new tD001DPE.
     *
     * @param tD001DPE the tD001DPE to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tD001DPE, or with status {@code 400 (Bad Request)} if the tD001DPE has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/td-001-dpes")
    public ResponseEntity<TD001DPE> createTD001DPE(@RequestBody TD001DPE tD001DPE) throws URISyntaxException {
        log.debug("REST request to save TD001DPE : {}", tD001DPE);
        if (tD001DPE.getId() != null) {
            throw new BadRequestAlertException("A new tD001DPE cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TD001DPE result = tD001DPERepository.save(tD001DPE);
        return ResponseEntity.created(new URI("/api/td-001-dpes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /td-001-dpes} : Updates an existing tD001DPE.
     *
     * @param tD001DPE the tD001DPE to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tD001DPE,
     * or with status {@code 400 (Bad Request)} if the tD001DPE is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tD001DPE couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/td-001-dpes")
    public ResponseEntity<TD001DPE> updateTD001DPE(@RequestBody TD001DPE tD001DPE) throws URISyntaxException {
        log.debug("REST request to update TD001DPE : {}", tD001DPE);
        if (tD001DPE.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TD001DPE result = tD001DPERepository.save(tD001DPE);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tD001DPE.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /td-001-dpes} : get all the tD001DPES.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tD001DPES in body.
     */
    @GetMapping("/td-001-dpes")
    public List<TD001DPE> getAllTD001DPES() {
        log.debug("REST request to get all TD001DPES");
        return tD001DPERepository.findAll();
    }

    /**
     * {@code GET  /td-001-dpes/:id} : get the "id" tD001DPE.
     *
     * @param id the id of the tD001DPE to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tD001DPE, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/td-001-dpes/{id}")
    public ResponseEntity<TD001DPE> getTD001DPE(@PathVariable Long id) {
        log.debug("REST request to get TD001DPE : {}", id);
        Optional<TD001DPE> tD001DPE = tD001DPERepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tD001DPE);
    }

    /**
     * {@code DELETE  /td-001-dpes/:id} : delete the "id" tD001DPE.
     *
     * @param id the id of the tD001DPE to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/td-001-dpes/{id}")
    public ResponseEntity<Void> deleteTD001DPE(@PathVariable Long id) {
        log.debug("REST request to delete TD001DPE : {}", id);
        tD001DPERepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

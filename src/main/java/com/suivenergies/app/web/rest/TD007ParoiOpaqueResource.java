package com.suivenergies.app.web.rest;

import com.suivenergies.app.domain.TD007ParoiOpaque;
import com.suivenergies.app.repository.TD007ParoiOpaqueRepository;
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
 * REST controller for managing {@link com.suivenergies.app.domain.TD007ParoiOpaque}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TD007ParoiOpaqueResource {

    private final Logger log = LoggerFactory.getLogger(TD007ParoiOpaqueResource.class);

    private static final String ENTITY_NAME = "tD007ParoiOpaque";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TD007ParoiOpaqueRepository tD007ParoiOpaqueRepository;

    public TD007ParoiOpaqueResource(TD007ParoiOpaqueRepository tD007ParoiOpaqueRepository) {
        this.tD007ParoiOpaqueRepository = tD007ParoiOpaqueRepository;
    }

    /**
     * {@code POST  /td-007-paroi-opaques} : Create a new tD007ParoiOpaque.
     *
     * @param tD007ParoiOpaque the tD007ParoiOpaque to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tD007ParoiOpaque, or with status {@code 400 (Bad Request)} if the tD007ParoiOpaque has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/td-007-paroi-opaques")
    public ResponseEntity<TD007ParoiOpaque> createTD007ParoiOpaque(@RequestBody TD007ParoiOpaque tD007ParoiOpaque) throws URISyntaxException {
        log.debug("REST request to save TD007ParoiOpaque : {}", tD007ParoiOpaque);
        if (tD007ParoiOpaque.getId() != null) {
            throw new BadRequestAlertException("A new tD007ParoiOpaque cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TD007ParoiOpaque result = tD007ParoiOpaqueRepository.save(tD007ParoiOpaque);
        return ResponseEntity.created(new URI("/api/td-007-paroi-opaques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /td-007-paroi-opaques} : Updates an existing tD007ParoiOpaque.
     *
     * @param tD007ParoiOpaque the tD007ParoiOpaque to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tD007ParoiOpaque,
     * or with status {@code 400 (Bad Request)} if the tD007ParoiOpaque is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tD007ParoiOpaque couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/td-007-paroi-opaques")
    public ResponseEntity<TD007ParoiOpaque> updateTD007ParoiOpaque(@RequestBody TD007ParoiOpaque tD007ParoiOpaque) throws URISyntaxException {
        log.debug("REST request to update TD007ParoiOpaque : {}", tD007ParoiOpaque);
        if (tD007ParoiOpaque.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TD007ParoiOpaque result = tD007ParoiOpaqueRepository.save(tD007ParoiOpaque);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tD007ParoiOpaque.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /td-007-paroi-opaques} : get all the tD007ParoiOpaques.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tD007ParoiOpaques in body.
     */
    @GetMapping("/td-007-paroi-opaques")
    public List<TD007ParoiOpaque> getAllTD007ParoiOpaques(@RequestParam(required = false) String filter) {
        if ("td006batiment-is-null".equals(filter)) {
            log.debug("REST request to get all TD007ParoiOpaques where td006Batiment is null");
            return StreamSupport
                .stream(tD007ParoiOpaqueRepository.findAll().spliterator(), false)
                .filter(tD007ParoiOpaque -> tD007ParoiOpaque.getTd006Batiment() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all TD007ParoiOpaques");
        return tD007ParoiOpaqueRepository.findAll();
    }

    /**
     * {@code GET  /td-007-paroi-opaques/:id} : get the "id" tD007ParoiOpaque.
     *
     * @param id the id of the tD007ParoiOpaque to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tD007ParoiOpaque, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/td-007-paroi-opaques/{id}")
    public ResponseEntity<TD007ParoiOpaque> getTD007ParoiOpaque(@PathVariable Long id) {
        log.debug("REST request to get TD007ParoiOpaque : {}", id);
        Optional<TD007ParoiOpaque> tD007ParoiOpaque = tD007ParoiOpaqueRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tD007ParoiOpaque);
    }

    /**
     * {@code DELETE  /td-007-paroi-opaques/:id} : delete the "id" tD007ParoiOpaque.
     *
     * @param id the id of the tD007ParoiOpaque to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/td-007-paroi-opaques/{id}")
    public ResponseEntity<Void> deleteTD007ParoiOpaque(@PathVariable Long id) {
        log.debug("REST request to delete TD007ParoiOpaque : {}", id);
        tD007ParoiOpaqueRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

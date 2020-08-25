package com.suivenergies.app.web.rest;

import com.suivenergies.app.domain.TD008Baie;
import com.suivenergies.app.repository.TD008BaieRepository;
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
 * REST controller for managing {@link com.suivenergies.app.domain.TD008Baie}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TD008BaieResource {

    private final Logger log = LoggerFactory.getLogger(TD008BaieResource.class);

    private static final String ENTITY_NAME = "tD008Baie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TD008BaieRepository tD008BaieRepository;

    public TD008BaieResource(TD008BaieRepository tD008BaieRepository) {
        this.tD008BaieRepository = tD008BaieRepository;
    }

    /**
     * {@code POST  /td-008-baies} : Create a new tD008Baie.
     *
     * @param tD008Baie the tD008Baie to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tD008Baie, or with status {@code 400 (Bad Request)} if the tD008Baie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/td-008-baies")
    public ResponseEntity<TD008Baie> createTD008Baie(@RequestBody TD008Baie tD008Baie) throws URISyntaxException {
        log.debug("REST request to save TD008Baie : {}", tD008Baie);
        if (tD008Baie.getId() != null) {
            throw new BadRequestAlertException("A new tD008Baie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TD008Baie result = tD008BaieRepository.save(tD008Baie);
        return ResponseEntity.created(new URI("/api/td-008-baies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /td-008-baies} : Updates an existing tD008Baie.
     *
     * @param tD008Baie the tD008Baie to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tD008Baie,
     * or with status {@code 400 (Bad Request)} if the tD008Baie is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tD008Baie couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/td-008-baies")
    public ResponseEntity<TD008Baie> updateTD008Baie(@RequestBody TD008Baie tD008Baie) throws URISyntaxException {
        log.debug("REST request to update TD008Baie : {}", tD008Baie);
        if (tD008Baie.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TD008Baie result = tD008BaieRepository.save(tD008Baie);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tD008Baie.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /td-008-baies} : get all the tD008Baies.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tD008Baies in body.
     */
    @GetMapping("/td-008-baies")
    public List<TD008Baie> getAllTD008Baies(@RequestParam(required = false) String filter) {
        if ("td007paroiopaque-is-null".equals(filter)) {
            log.debug("REST request to get all TD008Baies where td007ParoiOpaque is null");
            return StreamSupport
                .stream(tD008BaieRepository.findAll().spliterator(), false)
                .filter(tD008Baie -> tD008Baie.getTd007ParoiOpaque() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all TD008Baies");
        return tD008BaieRepository.findAll();
    }

    /**
     * {@code GET  /td-008-baies/:id} : get the "id" tD008Baie.
     *
     * @param id the id of the tD008Baie to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tD008Baie, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/td-008-baies/{id}")
    public ResponseEntity<TD008Baie> getTD008Baie(@PathVariable Long id) {
        log.debug("REST request to get TD008Baie : {}", id);
        Optional<TD008Baie> tD008Baie = tD008BaieRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tD008Baie);
    }

    /**
     * {@code DELETE  /td-008-baies/:id} : delete the "id" tD008Baie.
     *
     * @param id the id of the tD008Baie to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/td-008-baies/{id}")
    public ResponseEntity<Void> deleteTD008Baie(@PathVariable Long id) {
        log.debug("REST request to delete TD008Baie : {}", id);
        tD008BaieRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

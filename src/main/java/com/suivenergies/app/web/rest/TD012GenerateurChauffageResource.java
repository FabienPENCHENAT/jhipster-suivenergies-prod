package com.suivenergies.app.web.rest;

import com.suivenergies.app.domain.TD012GenerateurChauffage;
import com.suivenergies.app.repository.TD012GenerateurChauffageRepository;
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
 * REST controller for managing {@link com.suivenergies.app.domain.TD012GenerateurChauffage}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TD012GenerateurChauffageResource {

    private final Logger log = LoggerFactory.getLogger(TD012GenerateurChauffageResource.class);

    private static final String ENTITY_NAME = "tD012GenerateurChauffage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TD012GenerateurChauffageRepository tD012GenerateurChauffageRepository;

    public TD012GenerateurChauffageResource(TD012GenerateurChauffageRepository tD012GenerateurChauffageRepository) {
        this.tD012GenerateurChauffageRepository = tD012GenerateurChauffageRepository;
    }

    /**
     * {@code POST  /td-012-generateur-chauffages} : Create a new tD012GenerateurChauffage.
     *
     * @param tD012GenerateurChauffage the tD012GenerateurChauffage to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tD012GenerateurChauffage, or with status {@code 400 (Bad Request)} if the tD012GenerateurChauffage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/td-012-generateur-chauffages")
    public ResponseEntity<TD012GenerateurChauffage> createTD012GenerateurChauffage(@RequestBody TD012GenerateurChauffage tD012GenerateurChauffage) throws URISyntaxException {
        log.debug("REST request to save TD012GenerateurChauffage : {}", tD012GenerateurChauffage);
        if (tD012GenerateurChauffage.getId() != null) {
            throw new BadRequestAlertException("A new tD012GenerateurChauffage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TD012GenerateurChauffage result = tD012GenerateurChauffageRepository.save(tD012GenerateurChauffage);
        return ResponseEntity.created(new URI("/api/td-012-generateur-chauffages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /td-012-generateur-chauffages} : Updates an existing tD012GenerateurChauffage.
     *
     * @param tD012GenerateurChauffage the tD012GenerateurChauffage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tD012GenerateurChauffage,
     * or with status {@code 400 (Bad Request)} if the tD012GenerateurChauffage is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tD012GenerateurChauffage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/td-012-generateur-chauffages")
    public ResponseEntity<TD012GenerateurChauffage> updateTD012GenerateurChauffage(@RequestBody TD012GenerateurChauffage tD012GenerateurChauffage) throws URISyntaxException {
        log.debug("REST request to update TD012GenerateurChauffage : {}", tD012GenerateurChauffage);
        if (tD012GenerateurChauffage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TD012GenerateurChauffage result = tD012GenerateurChauffageRepository.save(tD012GenerateurChauffage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tD012GenerateurChauffage.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /td-012-generateur-chauffages} : get all the tD012GenerateurChauffages.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tD012GenerateurChauffages in body.
     */
    @GetMapping("/td-012-generateur-chauffages")
    public List<TD012GenerateurChauffage> getAllTD012GenerateurChauffages(@RequestParam(required = false) String filter) {
        if ("td011instalationchauffage-is-null".equals(filter)) {
            log.debug("REST request to get all TD012GenerateurChauffages where td011InstalationChauffage is null");
            return StreamSupport
                .stream(tD012GenerateurChauffageRepository.findAll().spliterator(), false)
                .filter(tD012GenerateurChauffage -> tD012GenerateurChauffage.getTd011InstalationChauffage() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all TD012GenerateurChauffages");
        return tD012GenerateurChauffageRepository.findAll();
    }

    /**
     * {@code GET  /td-012-generateur-chauffages/:id} : get the "id" tD012GenerateurChauffage.
     *
     * @param id the id of the tD012GenerateurChauffage to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tD012GenerateurChauffage, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/td-012-generateur-chauffages/{id}")
    public ResponseEntity<TD012GenerateurChauffage> getTD012GenerateurChauffage(@PathVariable Long id) {
        log.debug("REST request to get TD012GenerateurChauffage : {}", id);
        Optional<TD012GenerateurChauffage> tD012GenerateurChauffage = tD012GenerateurChauffageRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tD012GenerateurChauffage);
    }

    /**
     * {@code DELETE  /td-012-generateur-chauffages/:id} : delete the "id" tD012GenerateurChauffage.
     *
     * @param id the id of the tD012GenerateurChauffage to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/td-012-generateur-chauffages/{id}")
    public ResponseEntity<Void> deleteTD012GenerateurChauffage(@PathVariable Long id) {
        log.debug("REST request to delete TD012GenerateurChauffage : {}", id);
        tD012GenerateurChauffageRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

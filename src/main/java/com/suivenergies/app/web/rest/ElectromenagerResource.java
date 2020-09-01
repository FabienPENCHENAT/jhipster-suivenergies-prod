package com.suivenergies.app.web.rest;

import com.suivenergies.app.domain.Client;
import com.suivenergies.app.domain.Confort;
import com.suivenergies.app.domain.Electromenager;
import com.suivenergies.app.repository.ConfortRepository;
import com.suivenergies.app.repository.ElectromenagerRepository;
import com.suivenergies.app.service.ClientService;
import com.suivenergies.app.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link com.suivenergies.app.domain.Electromenager}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ElectromenagerResource {
    private final Logger log = LoggerFactory.getLogger(ElectromenagerResource.class);

    private static final String ENTITY_NAME = "electromenager";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ElectromenagerRepository electromenagerRepository;

    public ElectromenagerResource(ElectromenagerRepository electromenagerRepository) {
        this.electromenagerRepository = electromenagerRepository;
    }

    /**
     * {@code POST  /electromenagers} : Create a new electromenager.
     *
     * @param electromenager the electromenager to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new electromenager, or with status {@code 400 (Bad Request)} if the electromenager has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/electromenagers")
    public ResponseEntity<Electromenager> createElectromenager(@RequestBody Electromenager electromenager) throws URISyntaxException {
        log.debug("REST request to save Electromenager : {}", electromenager);
        if (electromenager.getId() != null) {
            throw new BadRequestAlertException("A new electromenager cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Electromenager result = electromenagerRepository.save(electromenager);
        return ResponseEntity
            .created(new URI("/api/electromenagers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /electromenagers} : Updates an existing electromenager.
     *
     * @param electromenager the electromenager to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated electromenager,
     * or with status {@code 400 (Bad Request)} if the electromenager is not valid,
     * or with status {@code 500 (Internal Server Error)} if the electromenager couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/electromenagers")
    public ResponseEntity<Electromenager> updateElectromenager(@RequestBody Electromenager electromenager) throws URISyntaxException {
        log.debug("REST request to update Electromenager : {}", electromenager);
        if (electromenager.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Electromenager result = electromenagerRepository.save(electromenager);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, electromenager.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /electromenagers} : get all the electromenagers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of electromenagers in body.
     */
    @GetMapping("/electromenagers")
    public List<Electromenager> getAllElectromenagers() {
        log.debug("REST request to get all Electromenagers");
        return electromenagerRepository.findAll();
    }

    /**
     * {@code GET  /electromenagers/:id} : get the "id" electromenager.
     *
     * @param id the id of the electromenager to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the electromenager, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/electromenagers/{id}")
    public ResponseEntity<Electromenager> getElectromenager(@PathVariable Long id) {
        log.debug("REST request to get Electromenager : {}", id);
        Optional<Electromenager> electromenager = electromenagerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(electromenager);
    }

    /**
     * {@code DELETE  /electromenagers/:id} : delete the "id" electromenager.
     *
     * @param id the id of the electromenager to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/electromenagers/{id}")
    public ResponseEntity<Void> deleteElectromenager(@PathVariable Long id) {
        log.debug("REST request to delete Electromenager : {}", id);
        electromenagerRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

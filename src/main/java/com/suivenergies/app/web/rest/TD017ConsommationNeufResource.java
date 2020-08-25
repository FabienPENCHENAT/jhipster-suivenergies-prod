package com.suivenergies.app.web.rest;

import com.suivenergies.app.domain.TD017ConsommationNeuf;
import com.suivenergies.app.repository.TD017ConsommationNeufRepository;
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
 * REST controller for managing {@link com.suivenergies.app.domain.TD017ConsommationNeuf}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TD017ConsommationNeufResource {

    private final Logger log = LoggerFactory.getLogger(TD017ConsommationNeufResource.class);

    private static final String ENTITY_NAME = "tD017ConsommationNeuf";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TD017ConsommationNeufRepository tD017ConsommationNeufRepository;

    public TD017ConsommationNeufResource(TD017ConsommationNeufRepository tD017ConsommationNeufRepository) {
        this.tD017ConsommationNeufRepository = tD017ConsommationNeufRepository;
    }

    /**
     * {@code POST  /td-017-consommation-neufs} : Create a new tD017ConsommationNeuf.
     *
     * @param tD017ConsommationNeuf the tD017ConsommationNeuf to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tD017ConsommationNeuf, or with status {@code 400 (Bad Request)} if the tD017ConsommationNeuf has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/td-017-consommation-neufs")
    public ResponseEntity<TD017ConsommationNeuf> createTD017ConsommationNeuf(@RequestBody TD017ConsommationNeuf tD017ConsommationNeuf) throws URISyntaxException {
        log.debug("REST request to save TD017ConsommationNeuf : {}", tD017ConsommationNeuf);
        if (tD017ConsommationNeuf.getId() != null) {
            throw new BadRequestAlertException("A new tD017ConsommationNeuf cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TD017ConsommationNeuf result = tD017ConsommationNeufRepository.save(tD017ConsommationNeuf);
        return ResponseEntity.created(new URI("/api/td-017-consommation-neufs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /td-017-consommation-neufs} : Updates an existing tD017ConsommationNeuf.
     *
     * @param tD017ConsommationNeuf the tD017ConsommationNeuf to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tD017ConsommationNeuf,
     * or with status {@code 400 (Bad Request)} if the tD017ConsommationNeuf is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tD017ConsommationNeuf couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/td-017-consommation-neufs")
    public ResponseEntity<TD017ConsommationNeuf> updateTD017ConsommationNeuf(@RequestBody TD017ConsommationNeuf tD017ConsommationNeuf) throws URISyntaxException {
        log.debug("REST request to update TD017ConsommationNeuf : {}", tD017ConsommationNeuf);
        if (tD017ConsommationNeuf.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TD017ConsommationNeuf result = tD017ConsommationNeufRepository.save(tD017ConsommationNeuf);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tD017ConsommationNeuf.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /td-017-consommation-neufs} : get all the tD017ConsommationNeufs.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tD017ConsommationNeufs in body.
     */
    @GetMapping("/td-017-consommation-neufs")
    public List<TD017ConsommationNeuf> getAllTD017ConsommationNeufs(@RequestParam(required = false) String filter) {
        if ("td001dpe-is-null".equals(filter)) {
            log.debug("REST request to get all TD017ConsommationNeufs where td001DPE is null");
            return StreamSupport
                .stream(tD017ConsommationNeufRepository.findAll().spliterator(), false)
                .filter(tD017ConsommationNeuf -> tD017ConsommationNeuf.getTd001DPE() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all TD017ConsommationNeufs");
        return tD017ConsommationNeufRepository.findAll();
    }

    /**
     * {@code GET  /td-017-consommation-neufs/:id} : get the "id" tD017ConsommationNeuf.
     *
     * @param id the id of the tD017ConsommationNeuf to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tD017ConsommationNeuf, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/td-017-consommation-neufs/{id}")
    public ResponseEntity<TD017ConsommationNeuf> getTD017ConsommationNeuf(@PathVariable Long id) {
        log.debug("REST request to get TD017ConsommationNeuf : {}", id);
        Optional<TD017ConsommationNeuf> tD017ConsommationNeuf = tD017ConsommationNeufRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tD017ConsommationNeuf);
    }

    /**
     * {@code DELETE  /td-017-consommation-neufs/:id} : delete the "id" tD017ConsommationNeuf.
     *
     * @param id the id of the tD017ConsommationNeuf to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/td-017-consommation-neufs/{id}")
    public ResponseEntity<Void> deleteTD017ConsommationNeuf(@PathVariable Long id) {
        log.debug("REST request to delete TD017ConsommationNeuf : {}", id);
        tD017ConsommationNeufRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

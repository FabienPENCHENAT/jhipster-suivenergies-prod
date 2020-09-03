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
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link com.suivenergies.app.domain.Confort}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ConfortResource {
    private final Logger log = LoggerFactory.getLogger(ConfortResource.class);

    private static final String ENTITY_NAME = "confort";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConfortRepository confortRepository;

    private final ElectromenagerRepository electromenagerRepository;

    private final ClientService clientService;

    public ConfortResource(
        ConfortRepository confortRepository,
        final ClientService clientService,
        final ElectromenagerRepository electromenagerRepository
    ) {
        this.confortRepository = confortRepository;
        this.clientService = clientService;
        this.electromenagerRepository = electromenagerRepository;
    }

    @GetMapping("/confort")
    public Confort getConfort() {
        Client clientConnected = clientService.getClientConnected();

        log.debug("REST request to get ModeVie by client connected : {}", clientConnected.getId());
        Confort confort = confortRepository.findOneByClientId(clientConnected.getId());
        return confort;
    }

    @DeleteMapping("/confort/{id}")
    public ResponseEntity<Void> removeElectromenager(@PathVariable Long id) {
        Confort confort = getConfort();

        log.debug("REST request to remove electromenager from Confort : {}", confort.getId());
        if (id == null) {
            throw new BadRequestAlertException("Invalid id", "ELECTROMENAGER", "idnull");
        }

        Set<Electromenager> result = confort.getElectromenagers().stream().filter(e -> e.getId() != id).collect(Collectors.toSet());
        confort.setElectromenagers(result);
        confortRepository.save(confort);

        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, confort.getId().toString()))
            .build();
    }

    /**
     * {@code POST  /conforts} : Create a new confort.
     *
     * @param confort the confort to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new confort, or with status {@code 400 (Bad Request)} if the confort has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/conforts")
    public ResponseEntity<Confort> createConfort(@RequestBody Confort confort) throws URISyntaxException {
        log.debug("REST request to save Confort : {}", confort);
        if (confort.getId() != null) {
            throw new BadRequestAlertException("A new confort cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (confort.getClient() == null) {
			Client clientConnected = clientService.getClientConnected();
			confort.setClient(clientConnected);
		}
        Confort result = confortRepository.save(confort);
        return ResponseEntity
            .created(new URI("/api/conforts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /conforts} : Updates an existing confort.
     *
     * @param confort the confort to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated confort,
     * or with status {@code 400 (Bad Request)} if the confort is not valid,
     * or with status {@code 500 (Internal Server Error)} if the confort couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/conforts")
    public ResponseEntity<Confort> updateConfort(@RequestBody Confort confort) throws URISyntaxException {
        log.debug("REST request to update Confort : {}", confort);
        if (confort.getId() == null) {
            createConfort(confort);
        }
        if (confort.getClient() == null) {
			Client clientConnected = clientService.getClientConnected();
			confort.setClient(clientConnected);
		}
        Confort result = confortRepository.save(confort);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, confort.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /conforts} : get all the conforts.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of conforts in body.
     */
    @GetMapping("/conforts")
    public List<Confort> getAllConforts(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Conforts");
        return confortRepository.findAllWithEagerRelationships();
    }

    /**
     * {@code GET  /conforts/:id} : get the "id" confort.
     *
     * @param id the id of the confort to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the confort, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/conforts/{id}")
    public ResponseEntity<Confort> getConfort(@PathVariable Long id) {
        log.debug("REST request to get Confort : {}", id);
        Optional<Confort> confort = confortRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(confort);
    }

    /**
     * {@code DELETE  /conforts/:id} : delete the "id" confort.
     *
     * @param id the id of the confort to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/conforts/{id}")
    public ResponseEntity<Void> deleteConfort(@PathVariable Long id) {
        log.debug("REST request to delete Confort : {}", id);
        confortRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

package com.suivenergies.app.web.rest;

import com.suivenergies.app.domain.Client;
import com.suivenergies.app.domain.Facture;
import com.suivenergies.app.domain.FacturesByType;
import com.suivenergies.app.domain.enumeration.EnergiesFacture;
import com.suivenergies.app.repository.FactureRepository;
import com.suivenergies.app.service.ClientService;
import com.suivenergies.app.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link com.suivenergies.app.domain.Facture}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FactureResource {
    private final Logger log = LoggerFactory.getLogger(FactureResource.class);

    private static final String ENTITY_NAME = "facture";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FactureRepository factureRepository;

    private final ClientService clientService;

    public FactureResource(FactureRepository factureRepository, ClientService clientService) {
        this.factureRepository = factureRepository;
        this.clientService = clientService;
    }

    /**
     * {@code POST  /factures} : Create a new facture.
     *
     * @param facture the facture to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new facture, or with status {@code 400 (Bad Request)} if the facture has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/factures")
    public ResponseEntity<Facture> createFacture(@RequestBody Facture facture) throws URISyntaxException {
        log.debug("REST request to save Facture : {}", facture);
        if (facture.getId() != null) {
            throw new BadRequestAlertException("A new facture cannot already have an ID", ENTITY_NAME, "idexists");
        }

        if (facture != null && facture.getClient() == null) {
            facture.setClient(clientService.getClientConnected());
        }

        Facture result = factureRepository.save(facture);
        return ResponseEntity
            .created(new URI("/api/factures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /factures} : Updates an existing facture.
     *
     * @param facture the facture to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated facture,
     * or with status {@code 400 (Bad Request)} if the facture is not valid,
     * or with status {@code 500 (Internal Server Error)} if the facture couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/factures")
    public ResponseEntity<Facture> updateFacture(@RequestBody Facture facture) throws URISyntaxException {
        log.debug("REST request to update Facture : {}", facture);
        if (facture.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Facture result = factureRepository.save(facture);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, facture.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /factures} : get all the factures.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of factures in body.
     */
    @GetMapping("/factures")
    public List<Facture> getAllFactures() {
        log.debug("REST request to get all Factures");
        return factureRepository.findAllWithEagerRelationships();
    }

    /**
     * {@code GET  /factures/:id} : get the "id" facture.
     *
     * @param id the id of the facture to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the facture, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/factures/{id}")
    public ResponseEntity<Facture> getFacture(@PathVariable Long id) {
        log.debug("REST request to get Facture : {}", id);
        Optional<Facture> facture = factureRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(facture);
    }

    /**
     * {@code DELETE  /factures/:id} : delete the "id" facture.
     *
     * @param id the id of the facture to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/factures/{id}")
    public ResponseEntity<Void> deleteFacture(@PathVariable Long id) {
        log.debug("REST request to delete Facture : {}", id);
        factureRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code GET  /factures/client} : get by user connected.
     *
     * @param id the id of the infoDPE to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the infoDPE, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/factures/client")
    public ResponseEntity<List<FacturesByType>> getAllClientFactures() {
        Client clientConnected = clientService.getClientConnected();
        log.debug("REST request to get factures by client connected : {}", clientConnected.getId());
        Optional<List<Facture>> factures = factureRepository.findAllByClientId(clientConnected.getId());

        List<FacturesByType> result = new ArrayList<FacturesByType>();

        Map<EnergiesFacture, List<Facture>> facturesByType = new HashMap<>();
        for (Facture facture : factures.get()) {
            EnergiesFacture type = facture.getType();
            if (!facturesByType.containsKey(type)) {
                List<Facture> facturesInstance = new ArrayList<>();
                facturesInstance.add(facture);
                facturesByType.put(type, facturesInstance);
            } else {
                facturesByType.get(type).add(facture);
            }
        }

        for (Map.Entry tmp : facturesByType.entrySet()) {
            result.add(new FacturesByType((EnergiesFacture) tmp.getKey(), (ArrayList) tmp.getValue()));
        }

        return ResponseUtil.wrapOrNotFound(Optional.of(result));
    }
}

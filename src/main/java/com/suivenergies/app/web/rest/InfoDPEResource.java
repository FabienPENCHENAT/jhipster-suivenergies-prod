package com.suivenergies.app.web.rest;

import com.suivenergies.app.domain.Client;
import com.suivenergies.app.domain.InfoDPE;
import com.suivenergies.app.repository.InfoDPERepository;
import com.suivenergies.app.security.AuthoritiesConstants;
import com.suivenergies.app.service.ClientService;
import com.suivenergies.app.service.InfoDPEService;
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
import org.springframework.security.access.prepost.PreAuthorize;
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
 * REST controller for managing {@link com.suivenergies.app.domain.InfoDPE}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InfoDPEResource {
    private final Logger log = LoggerFactory.getLogger(InfoDPEResource.class);

    private static final String ENTITY_NAME = "infoDPE";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InfoDPERepository infoDPERepository;

    private final InfoDPEService infoDPEService;

    private final ClientService clientService;

    public InfoDPEResource(InfoDPERepository infoDPERepository, InfoDPEService infoDPEService, ClientService clientService) {
        this.infoDPERepository = infoDPERepository;
        this.infoDPEService = infoDPEService;
        this.clientService = clientService;
    }

    @PostMapping("/info-dpe/{numeroDPE}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<InfoDPE> downloadInfoDPE(@PathVariable String numeroDPE) throws URISyntaxException {
        log.debug("REST request to download DPE : {}", numeroDPE);

        if (numeroDPE == null || numeroDPE.isEmpty()) {
            throw new BadRequestAlertException("Un numéro est requis pour télécharger le DPE", "InfoDPE", "numero");
        } else {
            infoDPEService.downlodAndSaveDPE(numeroDPE);
        }
        return getInfoDPE();
    }

    /**
     * {@code POST  /info-dpes} : Create a new infoDPE.
     *
     * @param infoDPE the infoDPE to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new infoDPE, or with status {@code 400 (Bad Request)} if the infoDPE has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/info-dpes")
    public ResponseEntity<InfoDPE> createInfoDPE(@RequestBody InfoDPE infoDPE) throws URISyntaxException {
        log.debug("REST request to save InfoDPE : {}", infoDPE);
        if (infoDPE.getId() != null) {
            throw new BadRequestAlertException("A new infoDPE cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InfoDPE result = infoDPERepository.save(infoDPE);
        return ResponseEntity
            .created(new URI("/api/info-dpes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /info-dpes} : Updates an existing infoDPE.
     *
     * @param infoDPE the infoDPE to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated infoDPE,
     * or with status {@code 400 (Bad Request)} if the infoDPE is not valid,
     * or with status {@code 500 (Internal Server Error)} if the infoDPE couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/info-dpes")
    public ResponseEntity<InfoDPE> updateInfoDPE(@RequestBody InfoDPE infoDPE) throws URISyntaxException {
        log.debug("REST request to update InfoDPE : {}", infoDPE);
        if (infoDPE.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InfoDPE result = infoDPERepository.save(infoDPE);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, infoDPE.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /info-dpes} : get all the infoDPES.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of infoDPES in body.
     */
    @GetMapping("/info-dpes")
    public List<InfoDPE> getAllInfoDPES() {
        log.debug("REST request to get all InfoDPES");
        return infoDPERepository.findAll();
    }

    /**
     * {@code GET  /info-dpes/:id} : get the "id" infoDPE.
     *
     * @param id the id of the infoDPE to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the infoDPE, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/info-dpes/{id}")
    public ResponseEntity<InfoDPE> getInfoDPE(@PathVariable Long id) {
        log.debug("REST request to get InfoDPE : {}", id);
        Optional<InfoDPE> infoDPE = infoDPERepository.findById(id);
        return ResponseUtil.wrapOrNotFound(infoDPE);
    }

    /**
     * {@code GET  /info-dpes} : get by user connected.
     *
     * @param id the id of the infoDPE to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the infoDPE, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/info-dpes/last")
    public ResponseEntity<InfoDPE> getInfoDPE() {
        Client clientConnected = clientService.getClientConnected();

        log.debug("REST request to get InfoDPE by client connected : {}", clientConnected.getId());
        Optional<InfoDPE> infoDPE = infoDPERepository.findLastOneByClientId(clientConnected.getId());
        return ResponseUtil.wrapOrNotFound(infoDPE);
    }

    /**
     * {@code DELETE  /info-dpes/:id} : delete the "id" infoDPE.
     *
     * @param id the id of the infoDPE to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/info-dpes/{id}")
    public ResponseEntity<Void> deleteInfoDPE(@PathVariable Long id) {
        log.debug("REST request to delete InfoDPE : {}", id);
        infoDPERepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

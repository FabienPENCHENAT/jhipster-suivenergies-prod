package com.suivenergies.app.web.rest;

import com.suivenergies.app.domain.Client;
import com.suivenergies.app.domain.ModeVie;
import com.suivenergies.app.repository.ModeVieRepository;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link com.suivenergies.app.domain.ModeVie}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ModeVieResource {
	private final Logger log = LoggerFactory.getLogger(ModeVieResource.class);

	private static final String ENTITY_NAME = "modeVie";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final ModeVieRepository modeVieRepository;

	private final ClientService clientService;

	public ModeVieResource(ModeVieRepository modeVieRepository, ClientService clientService) {
		this.modeVieRepository = modeVieRepository;
		this.clientService = clientService;
	}

	@GetMapping("/mode-vie")
	public ResponseEntity<ModeVie> getModeDeVie() {
		Client clientConnected = clientService.getClientConnected();

		log.debug("REST request to get ModeVie by client connected : {}", clientConnected.getId());
		Optional<ModeVie> modeVie = modeVieRepository.findOneByClientId(clientConnected.getId());
		return ResponseUtil.wrapOrNotFound(modeVie);
	}

	/**
	 * {@code POST  /mode-vies} : Create a new modeVie.
	 *
	 * @param modeVie the modeVie to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new modeVie, or with status {@code 400 (Bad Request)} if the
	 *         modeVie has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/mode-vies")
	public ResponseEntity<ModeVie> createModeVie(@RequestBody ModeVie modeVie) throws URISyntaxException {
		log.debug("REST request to save ModeVie : {}", modeVie);
		if (modeVie.getClient() == null) {
			Client clientConnected = clientService.getClientConnected();
			modeVie.setClient(clientConnected);
		}
		if (modeVie.getId() != null) {
			throw new BadRequestAlertException("A new modeVie cannot already have an ID", ENTITY_NAME, "idexists");
		}
		ModeVie result = modeVieRepository.save(modeVie);
		return ResponseEntity
				.created(new URI("/api/mode-vies/" + result.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	/**
	 * {@code PUT  /mode-vies} : Updates an existing modeVie.
	 *
	 * @param modeVie the modeVie to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated modeVie, or with status {@code 400 (Bad Request)} if the
	 *         modeVie is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the modeVie couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/mode-vies")
	public ResponseEntity<ModeVie> updateModeVie(@RequestBody ModeVie modeVie) throws URISyntaxException {
		log.debug("REST request to update ModeVie : {}", modeVie);
		if (modeVie.getId() == null) {
			createModeVie(modeVie);
		}
		if (modeVie.getClient() == null) {
			Client clientConnected = clientService.getClientConnected();
			modeVie.setClient(clientConnected);
		}
		ModeVie result = modeVieRepository.save(modeVie);
		return ResponseEntity.ok().headers(
				HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, modeVie.getId().toString()))
				.body(result);
	}

	/**
	 * {@code GET  /mode-vies} : get all the modeVies.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of modeVies in body.
	 */
	@GetMapping("/mode-vies")
	public List<ModeVie> getAllModeVies() {
		log.debug("REST request to get all ModeVies");
		return modeVieRepository.findAll();
	}

	/**
	 * {@code GET  /mode-vies/:id} : get the "id" modeVie.
	 *
	 * @param id the id of the modeVie to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the modeVie, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/mode-vies/{id}")
	public ResponseEntity<ModeVie> getModeVie(@PathVariable Long id) {
		log.debug("REST request to get ModeVie : {}", id);
		Optional<ModeVie> modeVie = modeVieRepository.findById(id);
		return ResponseUtil.wrapOrNotFound(modeVie);
	}

	/**
	 * {@code DELETE  /mode-vies/:id} : delete the "id" modeVie.
	 *
	 * @param id the id of the modeVie to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/mode-vies/{id}")
	public ResponseEntity<Void> deleteModeVie(@PathVariable Long id) {
		log.debug("REST request to delete ModeVie : {}", id);
		modeVieRepository.deleteById(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
				.build();
	}
}

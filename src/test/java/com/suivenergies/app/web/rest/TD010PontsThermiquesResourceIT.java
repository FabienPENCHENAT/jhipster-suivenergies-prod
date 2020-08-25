package com.suivenergies.app.web.rest;

import com.suivenergies.app.SuivEnergiesApp;
import com.suivenergies.app.domain.TD010PontsThermiques;
import com.suivenergies.app.repository.TD010PontsThermiquesRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TD010PontsThermiquesResource} REST controller.
 */
@SpringBootTest(classes = SuivEnergiesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TD010PontsThermiquesResourceIT {

    private static final Long DEFAULT_LONGUEUR = 1L;
    private static final Long UPDATED_LONGUEUR = 2L;

    private static final Double DEFAULT_TV_013_VALEUR_PONT_THERMIQUE = 1D;
    private static final Double UPDATED_TV_013_VALEUR_PONT_THERMIQUE = 2D;

    @Autowired
    private TD010PontsThermiquesRepository tD010PontsThermiquesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTD010PontsThermiquesMockMvc;

    private TD010PontsThermiques tD010PontsThermiques;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD010PontsThermiques createEntity(EntityManager em) {
        TD010PontsThermiques tD010PontsThermiques = new TD010PontsThermiques()
            .longueur(DEFAULT_LONGUEUR)
            .tv013ValeurPontThermique(DEFAULT_TV_013_VALEUR_PONT_THERMIQUE);
        return tD010PontsThermiques;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD010PontsThermiques createUpdatedEntity(EntityManager em) {
        TD010PontsThermiques tD010PontsThermiques = new TD010PontsThermiques()
            .longueur(UPDATED_LONGUEUR)
            .tv013ValeurPontThermique(UPDATED_TV_013_VALEUR_PONT_THERMIQUE);
        return tD010PontsThermiques;
    }

    @BeforeEach
    public void initTest() {
        tD010PontsThermiques = createEntity(em);
    }

    @Test
    @Transactional
    public void createTD010PontsThermiques() throws Exception {
        int databaseSizeBeforeCreate = tD010PontsThermiquesRepository.findAll().size();
        // Create the TD010PontsThermiques
        restTD010PontsThermiquesMockMvc.perform(post("/api/td-010-ponts-thermiques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD010PontsThermiques)))
            .andExpect(status().isCreated());

        // Validate the TD010PontsThermiques in the database
        List<TD010PontsThermiques> tD010PontsThermiquesList = tD010PontsThermiquesRepository.findAll();
        assertThat(tD010PontsThermiquesList).hasSize(databaseSizeBeforeCreate + 1);
        TD010PontsThermiques testTD010PontsThermiques = tD010PontsThermiquesList.get(tD010PontsThermiquesList.size() - 1);
        assertThat(testTD010PontsThermiques.getLongueur()).isEqualTo(DEFAULT_LONGUEUR);
        assertThat(testTD010PontsThermiques.getTv013ValeurPontThermique()).isEqualTo(DEFAULT_TV_013_VALEUR_PONT_THERMIQUE);
    }

    @Test
    @Transactional
    public void createTD010PontsThermiquesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tD010PontsThermiquesRepository.findAll().size();

        // Create the TD010PontsThermiques with an existing ID
        tD010PontsThermiques.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTD010PontsThermiquesMockMvc.perform(post("/api/td-010-ponts-thermiques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD010PontsThermiques)))
            .andExpect(status().isBadRequest());

        // Validate the TD010PontsThermiques in the database
        List<TD010PontsThermiques> tD010PontsThermiquesList = tD010PontsThermiquesRepository.findAll();
        assertThat(tD010PontsThermiquesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTD010PontsThermiques() throws Exception {
        // Initialize the database
        tD010PontsThermiquesRepository.saveAndFlush(tD010PontsThermiques);

        // Get all the tD010PontsThermiquesList
        restTD010PontsThermiquesMockMvc.perform(get("/api/td-010-ponts-thermiques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tD010PontsThermiques.getId().intValue())))
            .andExpect(jsonPath("$.[*].longueur").value(hasItem(DEFAULT_LONGUEUR.intValue())))
            .andExpect(jsonPath("$.[*].tv013ValeurPontThermique").value(hasItem(DEFAULT_TV_013_VALEUR_PONT_THERMIQUE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getTD010PontsThermiques() throws Exception {
        // Initialize the database
        tD010PontsThermiquesRepository.saveAndFlush(tD010PontsThermiques);

        // Get the tD010PontsThermiques
        restTD010PontsThermiquesMockMvc.perform(get("/api/td-010-ponts-thermiques/{id}", tD010PontsThermiques.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tD010PontsThermiques.getId().intValue()))
            .andExpect(jsonPath("$.longueur").value(DEFAULT_LONGUEUR.intValue()))
            .andExpect(jsonPath("$.tv013ValeurPontThermique").value(DEFAULT_TV_013_VALEUR_PONT_THERMIQUE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTD010PontsThermiques() throws Exception {
        // Get the tD010PontsThermiques
        restTD010PontsThermiquesMockMvc.perform(get("/api/td-010-ponts-thermiques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTD010PontsThermiques() throws Exception {
        // Initialize the database
        tD010PontsThermiquesRepository.saveAndFlush(tD010PontsThermiques);

        int databaseSizeBeforeUpdate = tD010PontsThermiquesRepository.findAll().size();

        // Update the tD010PontsThermiques
        TD010PontsThermiques updatedTD010PontsThermiques = tD010PontsThermiquesRepository.findById(tD010PontsThermiques.getId()).get();
        // Disconnect from session so that the updates on updatedTD010PontsThermiques are not directly saved in db
        em.detach(updatedTD010PontsThermiques);
        updatedTD010PontsThermiques
            .longueur(UPDATED_LONGUEUR)
            .tv013ValeurPontThermique(UPDATED_TV_013_VALEUR_PONT_THERMIQUE);

        restTD010PontsThermiquesMockMvc.perform(put("/api/td-010-ponts-thermiques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTD010PontsThermiques)))
            .andExpect(status().isOk());

        // Validate the TD010PontsThermiques in the database
        List<TD010PontsThermiques> tD010PontsThermiquesList = tD010PontsThermiquesRepository.findAll();
        assertThat(tD010PontsThermiquesList).hasSize(databaseSizeBeforeUpdate);
        TD010PontsThermiques testTD010PontsThermiques = tD010PontsThermiquesList.get(tD010PontsThermiquesList.size() - 1);
        assertThat(testTD010PontsThermiques.getLongueur()).isEqualTo(UPDATED_LONGUEUR);
        assertThat(testTD010PontsThermiques.getTv013ValeurPontThermique()).isEqualTo(UPDATED_TV_013_VALEUR_PONT_THERMIQUE);
    }

    @Test
    @Transactional
    public void updateNonExistingTD010PontsThermiques() throws Exception {
        int databaseSizeBeforeUpdate = tD010PontsThermiquesRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTD010PontsThermiquesMockMvc.perform(put("/api/td-010-ponts-thermiques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD010PontsThermiques)))
            .andExpect(status().isBadRequest());

        // Validate the TD010PontsThermiques in the database
        List<TD010PontsThermiques> tD010PontsThermiquesList = tD010PontsThermiquesRepository.findAll();
        assertThat(tD010PontsThermiquesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTD010PontsThermiques() throws Exception {
        // Initialize the database
        tD010PontsThermiquesRepository.saveAndFlush(tD010PontsThermiques);

        int databaseSizeBeforeDelete = tD010PontsThermiquesRepository.findAll().size();

        // Delete the tD010PontsThermiques
        restTD010PontsThermiquesMockMvc.perform(delete("/api/td-010-ponts-thermiques/{id}", tD010PontsThermiques.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TD010PontsThermiques> tD010PontsThermiquesList = tD010PontsThermiquesRepository.findAll();
        assertThat(tD010PontsThermiquesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

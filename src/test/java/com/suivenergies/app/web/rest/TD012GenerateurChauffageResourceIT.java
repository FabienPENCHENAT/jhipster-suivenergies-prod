package com.suivenergies.app.web.rest;

import com.suivenergies.app.SuivEnergiesApp;
import com.suivenergies.app.domain.TD012GenerateurChauffage;
import com.suivenergies.app.repository.TD012GenerateurChauffageRepository;

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
 * Integration tests for the {@link TD012GenerateurChauffageResource} REST controller.
 */
@SpringBootTest(classes = SuivEnergiesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TD012GenerateurChauffageResourceIT {

    private static final String DEFAULT_SYSTEME_CHAUFFAGE_COGENERATION = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEME_CHAUFFAGE_COGENERATION = "BBBBBBBBBB";

    private static final String DEFAULT_TD_011_INSTALLATION_CHAUFFAGE = "AAAAAAAAAA";
    private static final String UPDATED_TD_011_INSTALLATION_CHAUFFAGE = "BBBBBBBBBB";

    private static final String DEFAULT_TR_004_TYPE_ENERGIE = "AAAAAAAAAA";
    private static final String UPDATED_TR_004_TYPE_ENERGIE = "BBBBBBBBBB";

    private static final Double DEFAULT_CONSOMMATION_CHAUFFAGE = 1D;
    private static final Double UPDATED_CONSOMMATION_CHAUFFAGE = 2D;

    @Autowired
    private TD012GenerateurChauffageRepository tD012GenerateurChauffageRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTD012GenerateurChauffageMockMvc;

    private TD012GenerateurChauffage tD012GenerateurChauffage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD012GenerateurChauffage createEntity(EntityManager em) {
        TD012GenerateurChauffage tD012GenerateurChauffage = new TD012GenerateurChauffage()
            .systemeChauffageCogeneration(DEFAULT_SYSTEME_CHAUFFAGE_COGENERATION)
            .td011InstallationChauffage(DEFAULT_TD_011_INSTALLATION_CHAUFFAGE)
            .tr004TypeEnergie(DEFAULT_TR_004_TYPE_ENERGIE)
            .consommationChauffage(DEFAULT_CONSOMMATION_CHAUFFAGE);
        return tD012GenerateurChauffage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD012GenerateurChauffage createUpdatedEntity(EntityManager em) {
        TD012GenerateurChauffage tD012GenerateurChauffage = new TD012GenerateurChauffage()
            .systemeChauffageCogeneration(UPDATED_SYSTEME_CHAUFFAGE_COGENERATION)
            .td011InstallationChauffage(UPDATED_TD_011_INSTALLATION_CHAUFFAGE)
            .tr004TypeEnergie(UPDATED_TR_004_TYPE_ENERGIE)
            .consommationChauffage(UPDATED_CONSOMMATION_CHAUFFAGE);
        return tD012GenerateurChauffage;
    }

    @BeforeEach
    public void initTest() {
        tD012GenerateurChauffage = createEntity(em);
    }

    @Test
    @Transactional
    public void createTD012GenerateurChauffage() throws Exception {
        int databaseSizeBeforeCreate = tD012GenerateurChauffageRepository.findAll().size();
        // Create the TD012GenerateurChauffage
        restTD012GenerateurChauffageMockMvc.perform(post("/api/td-012-generateur-chauffages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD012GenerateurChauffage)))
            .andExpect(status().isCreated());

        // Validate the TD012GenerateurChauffage in the database
        List<TD012GenerateurChauffage> tD012GenerateurChauffageList = tD012GenerateurChauffageRepository.findAll();
        assertThat(tD012GenerateurChauffageList).hasSize(databaseSizeBeforeCreate + 1);
        TD012GenerateurChauffage testTD012GenerateurChauffage = tD012GenerateurChauffageList.get(tD012GenerateurChauffageList.size() - 1);
        assertThat(testTD012GenerateurChauffage.getSystemeChauffageCogeneration()).isEqualTo(DEFAULT_SYSTEME_CHAUFFAGE_COGENERATION);
        assertThat(testTD012GenerateurChauffage.getTd011InstallationChauffage()).isEqualTo(DEFAULT_TD_011_INSTALLATION_CHAUFFAGE);
        assertThat(testTD012GenerateurChauffage.getTr004TypeEnergie()).isEqualTo(DEFAULT_TR_004_TYPE_ENERGIE);
        assertThat(testTD012GenerateurChauffage.getConsommationChauffage()).isEqualTo(DEFAULT_CONSOMMATION_CHAUFFAGE);
    }

    @Test
    @Transactional
    public void createTD012GenerateurChauffageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tD012GenerateurChauffageRepository.findAll().size();

        // Create the TD012GenerateurChauffage with an existing ID
        tD012GenerateurChauffage.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTD012GenerateurChauffageMockMvc.perform(post("/api/td-012-generateur-chauffages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD012GenerateurChauffage)))
            .andExpect(status().isBadRequest());

        // Validate the TD012GenerateurChauffage in the database
        List<TD012GenerateurChauffage> tD012GenerateurChauffageList = tD012GenerateurChauffageRepository.findAll();
        assertThat(tD012GenerateurChauffageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTD012GenerateurChauffages() throws Exception {
        // Initialize the database
        tD012GenerateurChauffageRepository.saveAndFlush(tD012GenerateurChauffage);

        // Get all the tD012GenerateurChauffageList
        restTD012GenerateurChauffageMockMvc.perform(get("/api/td-012-generateur-chauffages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tD012GenerateurChauffage.getId().intValue())))
            .andExpect(jsonPath("$.[*].systemeChauffageCogeneration").value(hasItem(DEFAULT_SYSTEME_CHAUFFAGE_COGENERATION)))
            .andExpect(jsonPath("$.[*].td011InstallationChauffage").value(hasItem(DEFAULT_TD_011_INSTALLATION_CHAUFFAGE)))
            .andExpect(jsonPath("$.[*].tr004TypeEnergie").value(hasItem(DEFAULT_TR_004_TYPE_ENERGIE)))
            .andExpect(jsonPath("$.[*].consommationChauffage").value(hasItem(DEFAULT_CONSOMMATION_CHAUFFAGE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getTD012GenerateurChauffage() throws Exception {
        // Initialize the database
        tD012GenerateurChauffageRepository.saveAndFlush(tD012GenerateurChauffage);

        // Get the tD012GenerateurChauffage
        restTD012GenerateurChauffageMockMvc.perform(get("/api/td-012-generateur-chauffages/{id}", tD012GenerateurChauffage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tD012GenerateurChauffage.getId().intValue()))
            .andExpect(jsonPath("$.systemeChauffageCogeneration").value(DEFAULT_SYSTEME_CHAUFFAGE_COGENERATION))
            .andExpect(jsonPath("$.td011InstallationChauffage").value(DEFAULT_TD_011_INSTALLATION_CHAUFFAGE))
            .andExpect(jsonPath("$.tr004TypeEnergie").value(DEFAULT_TR_004_TYPE_ENERGIE))
            .andExpect(jsonPath("$.consommationChauffage").value(DEFAULT_CONSOMMATION_CHAUFFAGE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTD012GenerateurChauffage() throws Exception {
        // Get the tD012GenerateurChauffage
        restTD012GenerateurChauffageMockMvc.perform(get("/api/td-012-generateur-chauffages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTD012GenerateurChauffage() throws Exception {
        // Initialize the database
        tD012GenerateurChauffageRepository.saveAndFlush(tD012GenerateurChauffage);

        int databaseSizeBeforeUpdate = tD012GenerateurChauffageRepository.findAll().size();

        // Update the tD012GenerateurChauffage
        TD012GenerateurChauffage updatedTD012GenerateurChauffage = tD012GenerateurChauffageRepository.findById(tD012GenerateurChauffage.getId()).get();
        // Disconnect from session so that the updates on updatedTD012GenerateurChauffage are not directly saved in db
        em.detach(updatedTD012GenerateurChauffage);
        updatedTD012GenerateurChauffage
            .systemeChauffageCogeneration(UPDATED_SYSTEME_CHAUFFAGE_COGENERATION)
            .td011InstallationChauffage(UPDATED_TD_011_INSTALLATION_CHAUFFAGE)
            .tr004TypeEnergie(UPDATED_TR_004_TYPE_ENERGIE)
            .consommationChauffage(UPDATED_CONSOMMATION_CHAUFFAGE);

        restTD012GenerateurChauffageMockMvc.perform(put("/api/td-012-generateur-chauffages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTD012GenerateurChauffage)))
            .andExpect(status().isOk());

        // Validate the TD012GenerateurChauffage in the database
        List<TD012GenerateurChauffage> tD012GenerateurChauffageList = tD012GenerateurChauffageRepository.findAll();
        assertThat(tD012GenerateurChauffageList).hasSize(databaseSizeBeforeUpdate);
        TD012GenerateurChauffage testTD012GenerateurChauffage = tD012GenerateurChauffageList.get(tD012GenerateurChauffageList.size() - 1);
        assertThat(testTD012GenerateurChauffage.getSystemeChauffageCogeneration()).isEqualTo(UPDATED_SYSTEME_CHAUFFAGE_COGENERATION);
        assertThat(testTD012GenerateurChauffage.getTd011InstallationChauffage()).isEqualTo(UPDATED_TD_011_INSTALLATION_CHAUFFAGE);
        assertThat(testTD012GenerateurChauffage.getTr004TypeEnergie()).isEqualTo(UPDATED_TR_004_TYPE_ENERGIE);
        assertThat(testTD012GenerateurChauffage.getConsommationChauffage()).isEqualTo(UPDATED_CONSOMMATION_CHAUFFAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingTD012GenerateurChauffage() throws Exception {
        int databaseSizeBeforeUpdate = tD012GenerateurChauffageRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTD012GenerateurChauffageMockMvc.perform(put("/api/td-012-generateur-chauffages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD012GenerateurChauffage)))
            .andExpect(status().isBadRequest());

        // Validate the TD012GenerateurChauffage in the database
        List<TD012GenerateurChauffage> tD012GenerateurChauffageList = tD012GenerateurChauffageRepository.findAll();
        assertThat(tD012GenerateurChauffageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTD012GenerateurChauffage() throws Exception {
        // Initialize the database
        tD012GenerateurChauffageRepository.saveAndFlush(tD012GenerateurChauffage);

        int databaseSizeBeforeDelete = tD012GenerateurChauffageRepository.findAll().size();

        // Delete the tD012GenerateurChauffage
        restTD012GenerateurChauffageMockMvc.perform(delete("/api/td-012-generateur-chauffages/{id}", tD012GenerateurChauffage.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TD012GenerateurChauffage> tD012GenerateurChauffageList = tD012GenerateurChauffageRepository.findAll();
        assertThat(tD012GenerateurChauffageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

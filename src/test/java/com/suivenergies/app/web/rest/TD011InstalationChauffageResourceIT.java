package com.suivenergies.app.web.rest;

import com.suivenergies.app.SuivEnergiesApp;
import com.suivenergies.app.domain.TD011InstalationChauffage;
import com.suivenergies.app.repository.TD011InstalationChauffageRepository;

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
 * Integration tests for the {@link TD011InstalationChauffageResource} REST controller.
 */
@SpringBootTest(classes = SuivEnergiesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TD011InstalationChauffageResourceIT {

    private static final String DEFAULT_TR_003_TYPE_INSTALLATION_CHAUFFAGE = "AAAAAAAAAA";
    private static final String UPDATED_TR_003_TYPE_INSTALLATION_CHAUFFAGE = "BBBBBBBBBB";

    private static final Double DEFAULT_SURFACE_CHAUFFEE = 1D;
    private static final Double UPDATED_SURFACE_CHAUFFEE = 2D;

    @Autowired
    private TD011InstalationChauffageRepository tD011InstalationChauffageRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTD011InstalationChauffageMockMvc;

    private TD011InstalationChauffage tD011InstalationChauffage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD011InstalationChauffage createEntity(EntityManager em) {
        TD011InstalationChauffage tD011InstalationChauffage = new TD011InstalationChauffage()
            .tr003TypeInstallationChauffage(DEFAULT_TR_003_TYPE_INSTALLATION_CHAUFFAGE)
            .surfaceChauffee(DEFAULT_SURFACE_CHAUFFEE);
        return tD011InstalationChauffage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD011InstalationChauffage createUpdatedEntity(EntityManager em) {
        TD011InstalationChauffage tD011InstalationChauffage = new TD011InstalationChauffage()
            .tr003TypeInstallationChauffage(UPDATED_TR_003_TYPE_INSTALLATION_CHAUFFAGE)
            .surfaceChauffee(UPDATED_SURFACE_CHAUFFEE);
        return tD011InstalationChauffage;
    }

    @BeforeEach
    public void initTest() {
        tD011InstalationChauffage = createEntity(em);
    }

    @Test
    @Transactional
    public void createTD011InstalationChauffage() throws Exception {
        int databaseSizeBeforeCreate = tD011InstalationChauffageRepository.findAll().size();
        // Create the TD011InstalationChauffage
        restTD011InstalationChauffageMockMvc.perform(post("/api/td-011-instalation-chauffages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD011InstalationChauffage)))
            .andExpect(status().isCreated());

        // Validate the TD011InstalationChauffage in the database
        List<TD011InstalationChauffage> tD011InstalationChauffageList = tD011InstalationChauffageRepository.findAll();
        assertThat(tD011InstalationChauffageList).hasSize(databaseSizeBeforeCreate + 1);
        TD011InstalationChauffage testTD011InstalationChauffage = tD011InstalationChauffageList.get(tD011InstalationChauffageList.size() - 1);
        assertThat(testTD011InstalationChauffage.getTr003TypeInstallationChauffage()).isEqualTo(DEFAULT_TR_003_TYPE_INSTALLATION_CHAUFFAGE);
        assertThat(testTD011InstalationChauffage.getSurfaceChauffee()).isEqualTo(DEFAULT_SURFACE_CHAUFFEE);
    }

    @Test
    @Transactional
    public void createTD011InstalationChauffageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tD011InstalationChauffageRepository.findAll().size();

        // Create the TD011InstalationChauffage with an existing ID
        tD011InstalationChauffage.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTD011InstalationChauffageMockMvc.perform(post("/api/td-011-instalation-chauffages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD011InstalationChauffage)))
            .andExpect(status().isBadRequest());

        // Validate the TD011InstalationChauffage in the database
        List<TD011InstalationChauffage> tD011InstalationChauffageList = tD011InstalationChauffageRepository.findAll();
        assertThat(tD011InstalationChauffageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTD011InstalationChauffages() throws Exception {
        // Initialize the database
        tD011InstalationChauffageRepository.saveAndFlush(tD011InstalationChauffage);

        // Get all the tD011InstalationChauffageList
        restTD011InstalationChauffageMockMvc.perform(get("/api/td-011-instalation-chauffages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tD011InstalationChauffage.getId().intValue())))
            .andExpect(jsonPath("$.[*].tr003TypeInstallationChauffage").value(hasItem(DEFAULT_TR_003_TYPE_INSTALLATION_CHAUFFAGE)))
            .andExpect(jsonPath("$.[*].surfaceChauffee").value(hasItem(DEFAULT_SURFACE_CHAUFFEE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getTD011InstalationChauffage() throws Exception {
        // Initialize the database
        tD011InstalationChauffageRepository.saveAndFlush(tD011InstalationChauffage);

        // Get the tD011InstalationChauffage
        restTD011InstalationChauffageMockMvc.perform(get("/api/td-011-instalation-chauffages/{id}", tD011InstalationChauffage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tD011InstalationChauffage.getId().intValue()))
            .andExpect(jsonPath("$.tr003TypeInstallationChauffage").value(DEFAULT_TR_003_TYPE_INSTALLATION_CHAUFFAGE))
            .andExpect(jsonPath("$.surfaceChauffee").value(DEFAULT_SURFACE_CHAUFFEE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTD011InstalationChauffage() throws Exception {
        // Get the tD011InstalationChauffage
        restTD011InstalationChauffageMockMvc.perform(get("/api/td-011-instalation-chauffages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTD011InstalationChauffage() throws Exception {
        // Initialize the database
        tD011InstalationChauffageRepository.saveAndFlush(tD011InstalationChauffage);

        int databaseSizeBeforeUpdate = tD011InstalationChauffageRepository.findAll().size();

        // Update the tD011InstalationChauffage
        TD011InstalationChauffage updatedTD011InstalationChauffage = tD011InstalationChauffageRepository.findById(tD011InstalationChauffage.getId()).get();
        // Disconnect from session so that the updates on updatedTD011InstalationChauffage are not directly saved in db
        em.detach(updatedTD011InstalationChauffage);
        updatedTD011InstalationChauffage
            .tr003TypeInstallationChauffage(UPDATED_TR_003_TYPE_INSTALLATION_CHAUFFAGE)
            .surfaceChauffee(UPDATED_SURFACE_CHAUFFEE);

        restTD011InstalationChauffageMockMvc.perform(put("/api/td-011-instalation-chauffages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTD011InstalationChauffage)))
            .andExpect(status().isOk());

        // Validate the TD011InstalationChauffage in the database
        List<TD011InstalationChauffage> tD011InstalationChauffageList = tD011InstalationChauffageRepository.findAll();
        assertThat(tD011InstalationChauffageList).hasSize(databaseSizeBeforeUpdate);
        TD011InstalationChauffage testTD011InstalationChauffage = tD011InstalationChauffageList.get(tD011InstalationChauffageList.size() - 1);
        assertThat(testTD011InstalationChauffage.getTr003TypeInstallationChauffage()).isEqualTo(UPDATED_TR_003_TYPE_INSTALLATION_CHAUFFAGE);
        assertThat(testTD011InstalationChauffage.getSurfaceChauffee()).isEqualTo(UPDATED_SURFACE_CHAUFFEE);
    }

    @Test
    @Transactional
    public void updateNonExistingTD011InstalationChauffage() throws Exception {
        int databaseSizeBeforeUpdate = tD011InstalationChauffageRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTD011InstalationChauffageMockMvc.perform(put("/api/td-011-instalation-chauffages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD011InstalationChauffage)))
            .andExpect(status().isBadRequest());

        // Validate the TD011InstalationChauffage in the database
        List<TD011InstalationChauffage> tD011InstalationChauffageList = tD011InstalationChauffageRepository.findAll();
        assertThat(tD011InstalationChauffageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTD011InstalationChauffage() throws Exception {
        // Initialize the database
        tD011InstalationChauffageRepository.saveAndFlush(tD011InstalationChauffage);

        int databaseSizeBeforeDelete = tD011InstalationChauffageRepository.findAll().size();

        // Delete the tD011InstalationChauffage
        restTD011InstalationChauffageMockMvc.perform(delete("/api/td-011-instalation-chauffages/{id}", tD011InstalationChauffage.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TD011InstalationChauffage> tD011InstalationChauffageList = tD011InstalationChauffageRepository.findAll();
        assertThat(tD011InstalationChauffageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

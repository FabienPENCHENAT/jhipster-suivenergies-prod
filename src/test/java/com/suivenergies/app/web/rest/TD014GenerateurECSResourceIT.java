package com.suivenergies.app.web.rest;

import com.suivenergies.app.SuivEnergiesApp;
import com.suivenergies.app.domain.TD014GenerateurECS;
import com.suivenergies.app.repository.TD014GenerateurECSRepository;

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
 * Integration tests for the {@link TD014GenerateurECSResource} REST controller.
 */
@SpringBootTest(classes = SuivEnergiesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TD014GenerateurECSResourceIT {

    private static final String DEFAULT_TD_013_INSTALLATION_ECS = "AAAAAAAAAA";
    private static final String UPDATED_TD_013_INSTALLATION_ECS = "BBBBBBBBBB";

    private static final String DEFAULT_TR_004_TYPE_ENERGIE = "AAAAAAAAAA";
    private static final String UPDATED_TR_004_TYPE_ENERGIE = "BBBBBBBBBB";

    private static final Double DEFAULT_VOLUME_STOCKAGE = 1D;
    private static final Double UPDATED_VOLUME_STOCKAGE = 2D;

    @Autowired
    private TD014GenerateurECSRepository tD014GenerateurECSRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTD014GenerateurECSMockMvc;

    private TD014GenerateurECS tD014GenerateurECS;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD014GenerateurECS createEntity(EntityManager em) {
        TD014GenerateurECS tD014GenerateurECS = new TD014GenerateurECS()
            .td013InstallationEcs(DEFAULT_TD_013_INSTALLATION_ECS)
            .tr004TypeEnergie(DEFAULT_TR_004_TYPE_ENERGIE)
            .volumeStockage(DEFAULT_VOLUME_STOCKAGE);
        return tD014GenerateurECS;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD014GenerateurECS createUpdatedEntity(EntityManager em) {
        TD014GenerateurECS tD014GenerateurECS = new TD014GenerateurECS()
            .td013InstallationEcs(UPDATED_TD_013_INSTALLATION_ECS)
            .tr004TypeEnergie(UPDATED_TR_004_TYPE_ENERGIE)
            .volumeStockage(UPDATED_VOLUME_STOCKAGE);
        return tD014GenerateurECS;
    }

    @BeforeEach
    public void initTest() {
        tD014GenerateurECS = createEntity(em);
    }

    @Test
    @Transactional
    public void createTD014GenerateurECS() throws Exception {
        int databaseSizeBeforeCreate = tD014GenerateurECSRepository.findAll().size();
        // Create the TD014GenerateurECS
        restTD014GenerateurECSMockMvc.perform(post("/api/td-014-generateur-ecs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD014GenerateurECS)))
            .andExpect(status().isCreated());

        // Validate the TD014GenerateurECS in the database
        List<TD014GenerateurECS> tD014GenerateurECSList = tD014GenerateurECSRepository.findAll();
        assertThat(tD014GenerateurECSList).hasSize(databaseSizeBeforeCreate + 1);
        TD014GenerateurECS testTD014GenerateurECS = tD014GenerateurECSList.get(tD014GenerateurECSList.size() - 1);
        assertThat(testTD014GenerateurECS.getTd013InstallationEcs()).isEqualTo(DEFAULT_TD_013_INSTALLATION_ECS);
        assertThat(testTD014GenerateurECS.getTr004TypeEnergie()).isEqualTo(DEFAULT_TR_004_TYPE_ENERGIE);
        assertThat(testTD014GenerateurECS.getVolumeStockage()).isEqualTo(DEFAULT_VOLUME_STOCKAGE);
    }

    @Test
    @Transactional
    public void createTD014GenerateurECSWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tD014GenerateurECSRepository.findAll().size();

        // Create the TD014GenerateurECS with an existing ID
        tD014GenerateurECS.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTD014GenerateurECSMockMvc.perform(post("/api/td-014-generateur-ecs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD014GenerateurECS)))
            .andExpect(status().isBadRequest());

        // Validate the TD014GenerateurECS in the database
        List<TD014GenerateurECS> tD014GenerateurECSList = tD014GenerateurECSRepository.findAll();
        assertThat(tD014GenerateurECSList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTD014GenerateurECS() throws Exception {
        // Initialize the database
        tD014GenerateurECSRepository.saveAndFlush(tD014GenerateurECS);

        // Get all the tD014GenerateurECSList
        restTD014GenerateurECSMockMvc.perform(get("/api/td-014-generateur-ecs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tD014GenerateurECS.getId().intValue())))
            .andExpect(jsonPath("$.[*].td013InstallationEcs").value(hasItem(DEFAULT_TD_013_INSTALLATION_ECS)))
            .andExpect(jsonPath("$.[*].tr004TypeEnergie").value(hasItem(DEFAULT_TR_004_TYPE_ENERGIE)))
            .andExpect(jsonPath("$.[*].volumeStockage").value(hasItem(DEFAULT_VOLUME_STOCKAGE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getTD014GenerateurECS() throws Exception {
        // Initialize the database
        tD014GenerateurECSRepository.saveAndFlush(tD014GenerateurECS);

        // Get the tD014GenerateurECS
        restTD014GenerateurECSMockMvc.perform(get("/api/td-014-generateur-ecs/{id}", tD014GenerateurECS.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tD014GenerateurECS.getId().intValue()))
            .andExpect(jsonPath("$.td013InstallationEcs").value(DEFAULT_TD_013_INSTALLATION_ECS))
            .andExpect(jsonPath("$.tr004TypeEnergie").value(DEFAULT_TR_004_TYPE_ENERGIE))
            .andExpect(jsonPath("$.volumeStockage").value(DEFAULT_VOLUME_STOCKAGE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTD014GenerateurECS() throws Exception {
        // Get the tD014GenerateurECS
        restTD014GenerateurECSMockMvc.perform(get("/api/td-014-generateur-ecs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTD014GenerateurECS() throws Exception {
        // Initialize the database
        tD014GenerateurECSRepository.saveAndFlush(tD014GenerateurECS);

        int databaseSizeBeforeUpdate = tD014GenerateurECSRepository.findAll().size();

        // Update the tD014GenerateurECS
        TD014GenerateurECS updatedTD014GenerateurECS = tD014GenerateurECSRepository.findById(tD014GenerateurECS.getId()).get();
        // Disconnect from session so that the updates on updatedTD014GenerateurECS are not directly saved in db
        em.detach(updatedTD014GenerateurECS);
        updatedTD014GenerateurECS
            .td013InstallationEcs(UPDATED_TD_013_INSTALLATION_ECS)
            .tr004TypeEnergie(UPDATED_TR_004_TYPE_ENERGIE)
            .volumeStockage(UPDATED_VOLUME_STOCKAGE);

        restTD014GenerateurECSMockMvc.perform(put("/api/td-014-generateur-ecs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTD014GenerateurECS)))
            .andExpect(status().isOk());

        // Validate the TD014GenerateurECS in the database
        List<TD014GenerateurECS> tD014GenerateurECSList = tD014GenerateurECSRepository.findAll();
        assertThat(tD014GenerateurECSList).hasSize(databaseSizeBeforeUpdate);
        TD014GenerateurECS testTD014GenerateurECS = tD014GenerateurECSList.get(tD014GenerateurECSList.size() - 1);
        assertThat(testTD014GenerateurECS.getTd013InstallationEcs()).isEqualTo(UPDATED_TD_013_INSTALLATION_ECS);
        assertThat(testTD014GenerateurECS.getTr004TypeEnergie()).isEqualTo(UPDATED_TR_004_TYPE_ENERGIE);
        assertThat(testTD014GenerateurECS.getVolumeStockage()).isEqualTo(UPDATED_VOLUME_STOCKAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingTD014GenerateurECS() throws Exception {
        int databaseSizeBeforeUpdate = tD014GenerateurECSRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTD014GenerateurECSMockMvc.perform(put("/api/td-014-generateur-ecs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD014GenerateurECS)))
            .andExpect(status().isBadRequest());

        // Validate the TD014GenerateurECS in the database
        List<TD014GenerateurECS> tD014GenerateurECSList = tD014GenerateurECSRepository.findAll();
        assertThat(tD014GenerateurECSList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTD014GenerateurECS() throws Exception {
        // Initialize the database
        tD014GenerateurECSRepository.saveAndFlush(tD014GenerateurECS);

        int databaseSizeBeforeDelete = tD014GenerateurECSRepository.findAll().size();

        // Delete the tD014GenerateurECS
        restTD014GenerateurECSMockMvc.perform(delete("/api/td-014-generateur-ecs/{id}", tD014GenerateurECS.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TD014GenerateurECS> tD014GenerateurECSList = tD014GenerateurECSRepository.findAll();
        assertThat(tD014GenerateurECSList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

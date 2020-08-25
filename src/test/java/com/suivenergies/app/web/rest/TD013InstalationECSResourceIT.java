package com.suivenergies.app.web.rest;

import com.suivenergies.app.SuivEnergiesApp;
import com.suivenergies.app.domain.TD013InstalationECS;
import com.suivenergies.app.repository.TD013InstalationECSRepository;

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
 * Integration tests for the {@link TD013InstalationECSResource} REST controller.
 */
@SpringBootTest(classes = SuivEnergiesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TD013InstalationECSResourceIT {

    private static final String DEFAULT_TR_005_TYPE_INSTALLATION_ECS = "AAAAAAAAAA";
    private static final String UPDATED_TR_005_TYPE_INSTALLATION_ECS = "BBBBBBBBBB";

    private static final Double DEFAULT_BECS = 1D;
    private static final Double UPDATED_BECS = 2D;

    private static final String DEFAULT_TV_039_FORMULE_BECS = "AAAAAAAAAA";
    private static final String UPDATED_TV_039_FORMULE_BECS = "BBBBBBBBBB";

    private static final Double DEFAULT_SURFACE_ALIMENTEE = 1D;
    private static final Double UPDATED_SURFACE_ALIMENTEE = 2D;

    @Autowired
    private TD013InstalationECSRepository tD013InstalationECSRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTD013InstalationECSMockMvc;

    private TD013InstalationECS tD013InstalationECS;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD013InstalationECS createEntity(EntityManager em) {
        TD013InstalationECS tD013InstalationECS = new TD013InstalationECS()
            .tr005TypeInstallationEcs(DEFAULT_TR_005_TYPE_INSTALLATION_ECS)
            .becs(DEFAULT_BECS)
            .tv039FormuleBecs(DEFAULT_TV_039_FORMULE_BECS)
            .surfaceAlimentee(DEFAULT_SURFACE_ALIMENTEE);
        return tD013InstalationECS;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD013InstalationECS createUpdatedEntity(EntityManager em) {
        TD013InstalationECS tD013InstalationECS = new TD013InstalationECS()
            .tr005TypeInstallationEcs(UPDATED_TR_005_TYPE_INSTALLATION_ECS)
            .becs(UPDATED_BECS)
            .tv039FormuleBecs(UPDATED_TV_039_FORMULE_BECS)
            .surfaceAlimentee(UPDATED_SURFACE_ALIMENTEE);
        return tD013InstalationECS;
    }

    @BeforeEach
    public void initTest() {
        tD013InstalationECS = createEntity(em);
    }

    @Test
    @Transactional
    public void createTD013InstalationECS() throws Exception {
        int databaseSizeBeforeCreate = tD013InstalationECSRepository.findAll().size();
        // Create the TD013InstalationECS
        restTD013InstalationECSMockMvc.perform(post("/api/td-013-instalation-ecs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD013InstalationECS)))
            .andExpect(status().isCreated());

        // Validate the TD013InstalationECS in the database
        List<TD013InstalationECS> tD013InstalationECSList = tD013InstalationECSRepository.findAll();
        assertThat(tD013InstalationECSList).hasSize(databaseSizeBeforeCreate + 1);
        TD013InstalationECS testTD013InstalationECS = tD013InstalationECSList.get(tD013InstalationECSList.size() - 1);
        assertThat(testTD013InstalationECS.getTr005TypeInstallationEcs()).isEqualTo(DEFAULT_TR_005_TYPE_INSTALLATION_ECS);
        assertThat(testTD013InstalationECS.getBecs()).isEqualTo(DEFAULT_BECS);
        assertThat(testTD013InstalationECS.getTv039FormuleBecs()).isEqualTo(DEFAULT_TV_039_FORMULE_BECS);
        assertThat(testTD013InstalationECS.getSurfaceAlimentee()).isEqualTo(DEFAULT_SURFACE_ALIMENTEE);
    }

    @Test
    @Transactional
    public void createTD013InstalationECSWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tD013InstalationECSRepository.findAll().size();

        // Create the TD013InstalationECS with an existing ID
        tD013InstalationECS.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTD013InstalationECSMockMvc.perform(post("/api/td-013-instalation-ecs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD013InstalationECS)))
            .andExpect(status().isBadRequest());

        // Validate the TD013InstalationECS in the database
        List<TD013InstalationECS> tD013InstalationECSList = tD013InstalationECSRepository.findAll();
        assertThat(tD013InstalationECSList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTD013InstalationECS() throws Exception {
        // Initialize the database
        tD013InstalationECSRepository.saveAndFlush(tD013InstalationECS);

        // Get all the tD013InstalationECSList
        restTD013InstalationECSMockMvc.perform(get("/api/td-013-instalation-ecs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tD013InstalationECS.getId().intValue())))
            .andExpect(jsonPath("$.[*].tr005TypeInstallationEcs").value(hasItem(DEFAULT_TR_005_TYPE_INSTALLATION_ECS)))
            .andExpect(jsonPath("$.[*].becs").value(hasItem(DEFAULT_BECS.doubleValue())))
            .andExpect(jsonPath("$.[*].tv039FormuleBecs").value(hasItem(DEFAULT_TV_039_FORMULE_BECS)))
            .andExpect(jsonPath("$.[*].surfaceAlimentee").value(hasItem(DEFAULT_SURFACE_ALIMENTEE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getTD013InstalationECS() throws Exception {
        // Initialize the database
        tD013InstalationECSRepository.saveAndFlush(tD013InstalationECS);

        // Get the tD013InstalationECS
        restTD013InstalationECSMockMvc.perform(get("/api/td-013-instalation-ecs/{id}", tD013InstalationECS.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tD013InstalationECS.getId().intValue()))
            .andExpect(jsonPath("$.tr005TypeInstallationEcs").value(DEFAULT_TR_005_TYPE_INSTALLATION_ECS))
            .andExpect(jsonPath("$.becs").value(DEFAULT_BECS.doubleValue()))
            .andExpect(jsonPath("$.tv039FormuleBecs").value(DEFAULT_TV_039_FORMULE_BECS))
            .andExpect(jsonPath("$.surfaceAlimentee").value(DEFAULT_SURFACE_ALIMENTEE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTD013InstalationECS() throws Exception {
        // Get the tD013InstalationECS
        restTD013InstalationECSMockMvc.perform(get("/api/td-013-instalation-ecs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTD013InstalationECS() throws Exception {
        // Initialize the database
        tD013InstalationECSRepository.saveAndFlush(tD013InstalationECS);

        int databaseSizeBeforeUpdate = tD013InstalationECSRepository.findAll().size();

        // Update the tD013InstalationECS
        TD013InstalationECS updatedTD013InstalationECS = tD013InstalationECSRepository.findById(tD013InstalationECS.getId()).get();
        // Disconnect from session so that the updates on updatedTD013InstalationECS are not directly saved in db
        em.detach(updatedTD013InstalationECS);
        updatedTD013InstalationECS
            .tr005TypeInstallationEcs(UPDATED_TR_005_TYPE_INSTALLATION_ECS)
            .becs(UPDATED_BECS)
            .tv039FormuleBecs(UPDATED_TV_039_FORMULE_BECS)
            .surfaceAlimentee(UPDATED_SURFACE_ALIMENTEE);

        restTD013InstalationECSMockMvc.perform(put("/api/td-013-instalation-ecs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTD013InstalationECS)))
            .andExpect(status().isOk());

        // Validate the TD013InstalationECS in the database
        List<TD013InstalationECS> tD013InstalationECSList = tD013InstalationECSRepository.findAll();
        assertThat(tD013InstalationECSList).hasSize(databaseSizeBeforeUpdate);
        TD013InstalationECS testTD013InstalationECS = tD013InstalationECSList.get(tD013InstalationECSList.size() - 1);
        assertThat(testTD013InstalationECS.getTr005TypeInstallationEcs()).isEqualTo(UPDATED_TR_005_TYPE_INSTALLATION_ECS);
        assertThat(testTD013InstalationECS.getBecs()).isEqualTo(UPDATED_BECS);
        assertThat(testTD013InstalationECS.getTv039FormuleBecs()).isEqualTo(UPDATED_TV_039_FORMULE_BECS);
        assertThat(testTD013InstalationECS.getSurfaceAlimentee()).isEqualTo(UPDATED_SURFACE_ALIMENTEE);
    }

    @Test
    @Transactional
    public void updateNonExistingTD013InstalationECS() throws Exception {
        int databaseSizeBeforeUpdate = tD013InstalationECSRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTD013InstalationECSMockMvc.perform(put("/api/td-013-instalation-ecs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD013InstalationECS)))
            .andExpect(status().isBadRequest());

        // Validate the TD013InstalationECS in the database
        List<TD013InstalationECS> tD013InstalationECSList = tD013InstalationECSRepository.findAll();
        assertThat(tD013InstalationECSList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTD013InstalationECS() throws Exception {
        // Initialize the database
        tD013InstalationECSRepository.saveAndFlush(tD013InstalationECS);

        int databaseSizeBeforeDelete = tD013InstalationECSRepository.findAll().size();

        // Delete the tD013InstalationECS
        restTD013InstalationECSMockMvc.perform(delete("/api/td-013-instalation-ecs/{id}", tD013InstalationECS.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TD013InstalationECS> tD013InstalationECSList = tD013InstalationECSRepository.findAll();
        assertThat(tD013InstalationECSList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

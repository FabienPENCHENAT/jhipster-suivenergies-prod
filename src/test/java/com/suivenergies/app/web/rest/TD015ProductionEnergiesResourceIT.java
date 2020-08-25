package com.suivenergies.app.web.rest;

import com.suivenergies.app.SuivEnergiesApp;
import com.suivenergies.app.domain.TD015ProductionEnergies;
import com.suivenergies.app.repository.TD015ProductionEnergiesRepository;

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
 * Integration tests for the {@link TD015ProductionEnergiesResource} REST controller.
 */
@SpringBootTest(classes = SuivEnergiesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TD015ProductionEnergiesResourceIT {

    private static final String DEFAULT_TR_004_TYPE_ENERGIE = "AAAAAAAAAA";
    private static final String UPDATED_TR_004_TYPE_ENERGIE = "BBBBBBBBBB";

    private static final Double DEFAULT_PRODUCTION_ELECTRICITE_CAPTEURS_PHOTOVOLTAIQUES = 1D;
    private static final Double UPDATED_PRODUCTION_ELECTRICITE_CAPTEURS_PHOTOVOLTAIQUES = 2D;

    private static final Boolean DEFAULT_PRODUCTIONLE_ECTRICITE_MICRO_EOLIENNE = false;
    private static final Boolean UPDATED_PRODUCTIONLE_ECTRICITE_MICRO_EOLIENNE = true;

    private static final Double DEFAULT_PRODUCTION_COGENERATION = 1D;
    private static final Double UPDATED_PRODUCTION_COGENERATION = 2D;

    @Autowired
    private TD015ProductionEnergiesRepository tD015ProductionEnergiesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTD015ProductionEnergiesMockMvc;

    private TD015ProductionEnergies tD015ProductionEnergies;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD015ProductionEnergies createEntity(EntityManager em) {
        TD015ProductionEnergies tD015ProductionEnergies = new TD015ProductionEnergies()
            .tr004TypeEnergie(DEFAULT_TR_004_TYPE_ENERGIE)
            .productionElectriciteCapteursPhotovoltaiques(DEFAULT_PRODUCTION_ELECTRICITE_CAPTEURS_PHOTOVOLTAIQUES)
            .productionleEctriciteMicroEolienne(DEFAULT_PRODUCTIONLE_ECTRICITE_MICRO_EOLIENNE)
            .productionCogeneration(DEFAULT_PRODUCTION_COGENERATION);
        return tD015ProductionEnergies;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD015ProductionEnergies createUpdatedEntity(EntityManager em) {
        TD015ProductionEnergies tD015ProductionEnergies = new TD015ProductionEnergies()
            .tr004TypeEnergie(UPDATED_TR_004_TYPE_ENERGIE)
            .productionElectriciteCapteursPhotovoltaiques(UPDATED_PRODUCTION_ELECTRICITE_CAPTEURS_PHOTOVOLTAIQUES)
            .productionleEctriciteMicroEolienne(UPDATED_PRODUCTIONLE_ECTRICITE_MICRO_EOLIENNE)
            .productionCogeneration(UPDATED_PRODUCTION_COGENERATION);
        return tD015ProductionEnergies;
    }

    @BeforeEach
    public void initTest() {
        tD015ProductionEnergies = createEntity(em);
    }

    @Test
    @Transactional
    public void createTD015ProductionEnergies() throws Exception {
        int databaseSizeBeforeCreate = tD015ProductionEnergiesRepository.findAll().size();
        // Create the TD015ProductionEnergies
        restTD015ProductionEnergiesMockMvc.perform(post("/api/td-015-production-energies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD015ProductionEnergies)))
            .andExpect(status().isCreated());

        // Validate the TD015ProductionEnergies in the database
        List<TD015ProductionEnergies> tD015ProductionEnergiesList = tD015ProductionEnergiesRepository.findAll();
        assertThat(tD015ProductionEnergiesList).hasSize(databaseSizeBeforeCreate + 1);
        TD015ProductionEnergies testTD015ProductionEnergies = tD015ProductionEnergiesList.get(tD015ProductionEnergiesList.size() - 1);
        assertThat(testTD015ProductionEnergies.getTr004TypeEnergie()).isEqualTo(DEFAULT_TR_004_TYPE_ENERGIE);
        assertThat(testTD015ProductionEnergies.getProductionElectriciteCapteursPhotovoltaiques()).isEqualTo(DEFAULT_PRODUCTION_ELECTRICITE_CAPTEURS_PHOTOVOLTAIQUES);
        assertThat(testTD015ProductionEnergies.isProductionleEctriciteMicroEolienne()).isEqualTo(DEFAULT_PRODUCTIONLE_ECTRICITE_MICRO_EOLIENNE);
        assertThat(testTD015ProductionEnergies.getProductionCogeneration()).isEqualTo(DEFAULT_PRODUCTION_COGENERATION);
    }

    @Test
    @Transactional
    public void createTD015ProductionEnergiesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tD015ProductionEnergiesRepository.findAll().size();

        // Create the TD015ProductionEnergies with an existing ID
        tD015ProductionEnergies.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTD015ProductionEnergiesMockMvc.perform(post("/api/td-015-production-energies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD015ProductionEnergies)))
            .andExpect(status().isBadRequest());

        // Validate the TD015ProductionEnergies in the database
        List<TD015ProductionEnergies> tD015ProductionEnergiesList = tD015ProductionEnergiesRepository.findAll();
        assertThat(tD015ProductionEnergiesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTD015ProductionEnergies() throws Exception {
        // Initialize the database
        tD015ProductionEnergiesRepository.saveAndFlush(tD015ProductionEnergies);

        // Get all the tD015ProductionEnergiesList
        restTD015ProductionEnergiesMockMvc.perform(get("/api/td-015-production-energies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tD015ProductionEnergies.getId().intValue())))
            .andExpect(jsonPath("$.[*].tr004TypeEnergie").value(hasItem(DEFAULT_TR_004_TYPE_ENERGIE)))
            .andExpect(jsonPath("$.[*].productionElectriciteCapteursPhotovoltaiques").value(hasItem(DEFAULT_PRODUCTION_ELECTRICITE_CAPTEURS_PHOTOVOLTAIQUES.doubleValue())))
            .andExpect(jsonPath("$.[*].productionleEctriciteMicroEolienne").value(hasItem(DEFAULT_PRODUCTIONLE_ECTRICITE_MICRO_EOLIENNE.booleanValue())))
            .andExpect(jsonPath("$.[*].productionCogeneration").value(hasItem(DEFAULT_PRODUCTION_COGENERATION.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getTD015ProductionEnergies() throws Exception {
        // Initialize the database
        tD015ProductionEnergiesRepository.saveAndFlush(tD015ProductionEnergies);

        // Get the tD015ProductionEnergies
        restTD015ProductionEnergiesMockMvc.perform(get("/api/td-015-production-energies/{id}", tD015ProductionEnergies.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tD015ProductionEnergies.getId().intValue()))
            .andExpect(jsonPath("$.tr004TypeEnergie").value(DEFAULT_TR_004_TYPE_ENERGIE))
            .andExpect(jsonPath("$.productionElectriciteCapteursPhotovoltaiques").value(DEFAULT_PRODUCTION_ELECTRICITE_CAPTEURS_PHOTOVOLTAIQUES.doubleValue()))
            .andExpect(jsonPath("$.productionleEctriciteMicroEolienne").value(DEFAULT_PRODUCTIONLE_ECTRICITE_MICRO_EOLIENNE.booleanValue()))
            .andExpect(jsonPath("$.productionCogeneration").value(DEFAULT_PRODUCTION_COGENERATION.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTD015ProductionEnergies() throws Exception {
        // Get the tD015ProductionEnergies
        restTD015ProductionEnergiesMockMvc.perform(get("/api/td-015-production-energies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTD015ProductionEnergies() throws Exception {
        // Initialize the database
        tD015ProductionEnergiesRepository.saveAndFlush(tD015ProductionEnergies);

        int databaseSizeBeforeUpdate = tD015ProductionEnergiesRepository.findAll().size();

        // Update the tD015ProductionEnergies
        TD015ProductionEnergies updatedTD015ProductionEnergies = tD015ProductionEnergiesRepository.findById(tD015ProductionEnergies.getId()).get();
        // Disconnect from session so that the updates on updatedTD015ProductionEnergies are not directly saved in db
        em.detach(updatedTD015ProductionEnergies);
        updatedTD015ProductionEnergies
            .tr004TypeEnergie(UPDATED_TR_004_TYPE_ENERGIE)
            .productionElectriciteCapteursPhotovoltaiques(UPDATED_PRODUCTION_ELECTRICITE_CAPTEURS_PHOTOVOLTAIQUES)
            .productionleEctriciteMicroEolienne(UPDATED_PRODUCTIONLE_ECTRICITE_MICRO_EOLIENNE)
            .productionCogeneration(UPDATED_PRODUCTION_COGENERATION);

        restTD015ProductionEnergiesMockMvc.perform(put("/api/td-015-production-energies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTD015ProductionEnergies)))
            .andExpect(status().isOk());

        // Validate the TD015ProductionEnergies in the database
        List<TD015ProductionEnergies> tD015ProductionEnergiesList = tD015ProductionEnergiesRepository.findAll();
        assertThat(tD015ProductionEnergiesList).hasSize(databaseSizeBeforeUpdate);
        TD015ProductionEnergies testTD015ProductionEnergies = tD015ProductionEnergiesList.get(tD015ProductionEnergiesList.size() - 1);
        assertThat(testTD015ProductionEnergies.getTr004TypeEnergie()).isEqualTo(UPDATED_TR_004_TYPE_ENERGIE);
        assertThat(testTD015ProductionEnergies.getProductionElectriciteCapteursPhotovoltaiques()).isEqualTo(UPDATED_PRODUCTION_ELECTRICITE_CAPTEURS_PHOTOVOLTAIQUES);
        assertThat(testTD015ProductionEnergies.isProductionleEctriciteMicroEolienne()).isEqualTo(UPDATED_PRODUCTIONLE_ECTRICITE_MICRO_EOLIENNE);
        assertThat(testTD015ProductionEnergies.getProductionCogeneration()).isEqualTo(UPDATED_PRODUCTION_COGENERATION);
    }

    @Test
    @Transactional
    public void updateNonExistingTD015ProductionEnergies() throws Exception {
        int databaseSizeBeforeUpdate = tD015ProductionEnergiesRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTD015ProductionEnergiesMockMvc.perform(put("/api/td-015-production-energies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD015ProductionEnergies)))
            .andExpect(status().isBadRequest());

        // Validate the TD015ProductionEnergies in the database
        List<TD015ProductionEnergies> tD015ProductionEnergiesList = tD015ProductionEnergiesRepository.findAll();
        assertThat(tD015ProductionEnergiesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTD015ProductionEnergies() throws Exception {
        // Initialize the database
        tD015ProductionEnergiesRepository.saveAndFlush(tD015ProductionEnergies);

        int databaseSizeBeforeDelete = tD015ProductionEnergiesRepository.findAll().size();

        // Delete the tD015ProductionEnergies
        restTD015ProductionEnergiesMockMvc.perform(delete("/api/td-015-production-energies/{id}", tD015ProductionEnergies.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TD015ProductionEnergies> tD015ProductionEnergiesList = tD015ProductionEnergiesRepository.findAll();
        assertThat(tD015ProductionEnergiesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

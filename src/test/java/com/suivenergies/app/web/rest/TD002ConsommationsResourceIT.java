package com.suivenergies.app.web.rest;

import com.suivenergies.app.SuivEnergiesApp;
import com.suivenergies.app.domain.TD002Consommations;
import com.suivenergies.app.repository.TD002ConsommationsRepository;

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
 * Integration tests for the {@link TD002ConsommationsResource} REST controller.
 */
@SpringBootTest(classes = SuivEnergiesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TD002ConsommationsResourceIT {

    private static final String DEFAULT_TR_006_TYPE_USAGE = "AAAAAAAAAA";
    private static final String UPDATED_TR_006_TYPE_USAGE = "BBBBBBBBBB";

    private static final String DEFAULT_TR_004_TYP_ENERGIE = "AAAAAAAAAA";
    private static final String UPDATED_TR_004_TYP_ENERGIE = "BBBBBBBBBB";

    private static final Double DEFAULT_TV_042_TARIF_ENERGIE = 1D;
    private static final Double UPDATED_TV_042_TARIF_ENERGIE = 2D;

    private static final Double DEFAULT_CONSOMMATION_ENERGIE_FINALE = 1D;
    private static final Double UPDATED_CONSOMMATION_ENERGIE_FINALE = 2D;

    private static final Double DEFAULT_CONSOMMATION_ENERGIE_PRIMAIRE = 1D;
    private static final Double UPDATED_CONSOMMATION_ENERGIE_PRIMAIRE = 2D;

    private static final Double DEFAULT_FRAIS_ANNUELS_ENERGIE = 1D;
    private static final Double UPDATED_FRAIS_ANNUELS_ENERGIE = 2D;

    private static final Boolean DEFAULT_EST_EFFACE = false;
    private static final Boolean UPDATED_EST_EFFACE = true;

    @Autowired
    private TD002ConsommationsRepository tD002ConsommationsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTD002ConsommationsMockMvc;

    private TD002Consommations tD002Consommations;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD002Consommations createEntity(EntityManager em) {
        TD002Consommations tD002Consommations = new TD002Consommations()
            .tr006TypeUsage(DEFAULT_TR_006_TYPE_USAGE)
            .tr004TypEnergie(DEFAULT_TR_004_TYP_ENERGIE)
            .tv042TarifEnergie(DEFAULT_TV_042_TARIF_ENERGIE)
            .consommationEnergieFinale(DEFAULT_CONSOMMATION_ENERGIE_FINALE)
            .consommationEnergiePrimaire(DEFAULT_CONSOMMATION_ENERGIE_PRIMAIRE)
            .fraisAnnuelsEnergie(DEFAULT_FRAIS_ANNUELS_ENERGIE)
            .estEfface(DEFAULT_EST_EFFACE);
        return tD002Consommations;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD002Consommations createUpdatedEntity(EntityManager em) {
        TD002Consommations tD002Consommations = new TD002Consommations()
            .tr006TypeUsage(UPDATED_TR_006_TYPE_USAGE)
            .tr004TypEnergie(UPDATED_TR_004_TYP_ENERGIE)
            .tv042TarifEnergie(UPDATED_TV_042_TARIF_ENERGIE)
            .consommationEnergieFinale(UPDATED_CONSOMMATION_ENERGIE_FINALE)
            .consommationEnergiePrimaire(UPDATED_CONSOMMATION_ENERGIE_PRIMAIRE)
            .fraisAnnuelsEnergie(UPDATED_FRAIS_ANNUELS_ENERGIE)
            .estEfface(UPDATED_EST_EFFACE);
        return tD002Consommations;
    }

    @BeforeEach
    public void initTest() {
        tD002Consommations = createEntity(em);
    }

    @Test
    @Transactional
    public void createTD002Consommations() throws Exception {
        int databaseSizeBeforeCreate = tD002ConsommationsRepository.findAll().size();
        // Create the TD002Consommations
        restTD002ConsommationsMockMvc.perform(post("/api/td-002-consommations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD002Consommations)))
            .andExpect(status().isCreated());

        // Validate the TD002Consommations in the database
        List<TD002Consommations> tD002ConsommationsList = tD002ConsommationsRepository.findAll();
        assertThat(tD002ConsommationsList).hasSize(databaseSizeBeforeCreate + 1);
        TD002Consommations testTD002Consommations = tD002ConsommationsList.get(tD002ConsommationsList.size() - 1);
        assertThat(testTD002Consommations.getTr006TypeUsage()).isEqualTo(DEFAULT_TR_006_TYPE_USAGE);
        assertThat(testTD002Consommations.getTr004TypEnergie()).isEqualTo(DEFAULT_TR_004_TYP_ENERGIE);
        assertThat(testTD002Consommations.getTv042TarifEnergie()).isEqualTo(DEFAULT_TV_042_TARIF_ENERGIE);
        assertThat(testTD002Consommations.getConsommationEnergieFinale()).isEqualTo(DEFAULT_CONSOMMATION_ENERGIE_FINALE);
        assertThat(testTD002Consommations.getConsommationEnergiePrimaire()).isEqualTo(DEFAULT_CONSOMMATION_ENERGIE_PRIMAIRE);
        assertThat(testTD002Consommations.getFraisAnnuelsEnergie()).isEqualTo(DEFAULT_FRAIS_ANNUELS_ENERGIE);
        assertThat(testTD002Consommations.isEstEfface()).isEqualTo(DEFAULT_EST_EFFACE);
    }

    @Test
    @Transactional
    public void createTD002ConsommationsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tD002ConsommationsRepository.findAll().size();

        // Create the TD002Consommations with an existing ID
        tD002Consommations.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTD002ConsommationsMockMvc.perform(post("/api/td-002-consommations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD002Consommations)))
            .andExpect(status().isBadRequest());

        // Validate the TD002Consommations in the database
        List<TD002Consommations> tD002ConsommationsList = tD002ConsommationsRepository.findAll();
        assertThat(tD002ConsommationsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTD002Consommations() throws Exception {
        // Initialize the database
        tD002ConsommationsRepository.saveAndFlush(tD002Consommations);

        // Get all the tD002ConsommationsList
        restTD002ConsommationsMockMvc.perform(get("/api/td-002-consommations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tD002Consommations.getId().intValue())))
            .andExpect(jsonPath("$.[*].tr006TypeUsage").value(hasItem(DEFAULT_TR_006_TYPE_USAGE)))
            .andExpect(jsonPath("$.[*].tr004TypEnergie").value(hasItem(DEFAULT_TR_004_TYP_ENERGIE)))
            .andExpect(jsonPath("$.[*].tv042TarifEnergie").value(hasItem(DEFAULT_TV_042_TARIF_ENERGIE.doubleValue())))
            .andExpect(jsonPath("$.[*].consommationEnergieFinale").value(hasItem(DEFAULT_CONSOMMATION_ENERGIE_FINALE.doubleValue())))
            .andExpect(jsonPath("$.[*].consommationEnergiePrimaire").value(hasItem(DEFAULT_CONSOMMATION_ENERGIE_PRIMAIRE.doubleValue())))
            .andExpect(jsonPath("$.[*].fraisAnnuelsEnergie").value(hasItem(DEFAULT_FRAIS_ANNUELS_ENERGIE.doubleValue())))
            .andExpect(jsonPath("$.[*].estEfface").value(hasItem(DEFAULT_EST_EFFACE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getTD002Consommations() throws Exception {
        // Initialize the database
        tD002ConsommationsRepository.saveAndFlush(tD002Consommations);

        // Get the tD002Consommations
        restTD002ConsommationsMockMvc.perform(get("/api/td-002-consommations/{id}", tD002Consommations.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tD002Consommations.getId().intValue()))
            .andExpect(jsonPath("$.tr006TypeUsage").value(DEFAULT_TR_006_TYPE_USAGE))
            .andExpect(jsonPath("$.tr004TypEnergie").value(DEFAULT_TR_004_TYP_ENERGIE))
            .andExpect(jsonPath("$.tv042TarifEnergie").value(DEFAULT_TV_042_TARIF_ENERGIE.doubleValue()))
            .andExpect(jsonPath("$.consommationEnergieFinale").value(DEFAULT_CONSOMMATION_ENERGIE_FINALE.doubleValue()))
            .andExpect(jsonPath("$.consommationEnergiePrimaire").value(DEFAULT_CONSOMMATION_ENERGIE_PRIMAIRE.doubleValue()))
            .andExpect(jsonPath("$.fraisAnnuelsEnergie").value(DEFAULT_FRAIS_ANNUELS_ENERGIE.doubleValue()))
            .andExpect(jsonPath("$.estEfface").value(DEFAULT_EST_EFFACE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTD002Consommations() throws Exception {
        // Get the tD002Consommations
        restTD002ConsommationsMockMvc.perform(get("/api/td-002-consommations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTD002Consommations() throws Exception {
        // Initialize the database
        tD002ConsommationsRepository.saveAndFlush(tD002Consommations);

        int databaseSizeBeforeUpdate = tD002ConsommationsRepository.findAll().size();

        // Update the tD002Consommations
        TD002Consommations updatedTD002Consommations = tD002ConsommationsRepository.findById(tD002Consommations.getId()).get();
        // Disconnect from session so that the updates on updatedTD002Consommations are not directly saved in db
        em.detach(updatedTD002Consommations);
        updatedTD002Consommations
            .tr006TypeUsage(UPDATED_TR_006_TYPE_USAGE)
            .tr004TypEnergie(UPDATED_TR_004_TYP_ENERGIE)
            .tv042TarifEnergie(UPDATED_TV_042_TARIF_ENERGIE)
            .consommationEnergieFinale(UPDATED_CONSOMMATION_ENERGIE_FINALE)
            .consommationEnergiePrimaire(UPDATED_CONSOMMATION_ENERGIE_PRIMAIRE)
            .fraisAnnuelsEnergie(UPDATED_FRAIS_ANNUELS_ENERGIE)
            .estEfface(UPDATED_EST_EFFACE);

        restTD002ConsommationsMockMvc.perform(put("/api/td-002-consommations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTD002Consommations)))
            .andExpect(status().isOk());

        // Validate the TD002Consommations in the database
        List<TD002Consommations> tD002ConsommationsList = tD002ConsommationsRepository.findAll();
        assertThat(tD002ConsommationsList).hasSize(databaseSizeBeforeUpdate);
        TD002Consommations testTD002Consommations = tD002ConsommationsList.get(tD002ConsommationsList.size() - 1);
        assertThat(testTD002Consommations.getTr006TypeUsage()).isEqualTo(UPDATED_TR_006_TYPE_USAGE);
        assertThat(testTD002Consommations.getTr004TypEnergie()).isEqualTo(UPDATED_TR_004_TYP_ENERGIE);
        assertThat(testTD002Consommations.getTv042TarifEnergie()).isEqualTo(UPDATED_TV_042_TARIF_ENERGIE);
        assertThat(testTD002Consommations.getConsommationEnergieFinale()).isEqualTo(UPDATED_CONSOMMATION_ENERGIE_FINALE);
        assertThat(testTD002Consommations.getConsommationEnergiePrimaire()).isEqualTo(UPDATED_CONSOMMATION_ENERGIE_PRIMAIRE);
        assertThat(testTD002Consommations.getFraisAnnuelsEnergie()).isEqualTo(UPDATED_FRAIS_ANNUELS_ENERGIE);
        assertThat(testTD002Consommations.isEstEfface()).isEqualTo(UPDATED_EST_EFFACE);
    }

    @Test
    @Transactional
    public void updateNonExistingTD002Consommations() throws Exception {
        int databaseSizeBeforeUpdate = tD002ConsommationsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTD002ConsommationsMockMvc.perform(put("/api/td-002-consommations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD002Consommations)))
            .andExpect(status().isBadRequest());

        // Validate the TD002Consommations in the database
        List<TD002Consommations> tD002ConsommationsList = tD002ConsommationsRepository.findAll();
        assertThat(tD002ConsommationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTD002Consommations() throws Exception {
        // Initialize the database
        tD002ConsommationsRepository.saveAndFlush(tD002Consommations);

        int databaseSizeBeforeDelete = tD002ConsommationsRepository.findAll().size();

        // Delete the tD002Consommations
        restTD002ConsommationsMockMvc.perform(delete("/api/td-002-consommations/{id}", tD002Consommations.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TD002Consommations> tD002ConsommationsList = tD002ConsommationsRepository.findAll();
        assertThat(tD002ConsommationsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

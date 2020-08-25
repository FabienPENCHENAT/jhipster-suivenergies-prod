package com.suivenergies.app.web.rest;

import com.suivenergies.app.SuivEnergiesApp;
import com.suivenergies.app.domain.TD006Batiment;
import com.suivenergies.app.repository.TD006BatimentRepository;

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
 * Integration tests for the {@link TD006BatimentResource} REST controller.
 */
@SpringBootTest(classes = SuivEnergiesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TD006BatimentResourceIT {

    private static final Double DEFAULT_BESOIN_CHAUFFAGE = 1D;
    private static final Double UPDATED_BESOIN_CHAUFFAGE = 2D;

    private static final Double DEFAULT_DEPERDITION_ENVELOPPE = 1D;
    private static final Double UPDATED_DEPERDITION_ENVELOPPE = 2D;

    private static final Double DEFAULT_DEPERDITION_RENOUVELLEMENT_AIR = 1D;
    private static final Double UPDATED_DEPERDITION_RENOUVELLEMENT_AIR = 2D;

    private static final Double DEFAULT_ALTITUDE = 1D;
    private static final Double UPDATED_ALTITUDE = 2D;

    private static final Double DEFAULT_NOMBRE_NIVEAU = 1D;
    private static final Double UPDATED_NOMBRE_NIVEAU = 2D;

    private static final Double DEFAULT_HSP_MOYENNE = 1D;
    private static final Double UPDATED_HSP_MOYENNE = 2D;

    @Autowired
    private TD006BatimentRepository tD006BatimentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTD006BatimentMockMvc;

    private TD006Batiment tD006Batiment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD006Batiment createEntity(EntityManager em) {
        TD006Batiment tD006Batiment = new TD006Batiment()
            .besoinChauffage(DEFAULT_BESOIN_CHAUFFAGE)
            .deperditionEnveloppe(DEFAULT_DEPERDITION_ENVELOPPE)
            .deperditionRenouvellementAir(DEFAULT_DEPERDITION_RENOUVELLEMENT_AIR)
            .altitude(DEFAULT_ALTITUDE)
            .nombreNiveau(DEFAULT_NOMBRE_NIVEAU)
            .hspMoyenne(DEFAULT_HSP_MOYENNE);
        return tD006Batiment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD006Batiment createUpdatedEntity(EntityManager em) {
        TD006Batiment tD006Batiment = new TD006Batiment()
            .besoinChauffage(UPDATED_BESOIN_CHAUFFAGE)
            .deperditionEnveloppe(UPDATED_DEPERDITION_ENVELOPPE)
            .deperditionRenouvellementAir(UPDATED_DEPERDITION_RENOUVELLEMENT_AIR)
            .altitude(UPDATED_ALTITUDE)
            .nombreNiveau(UPDATED_NOMBRE_NIVEAU)
            .hspMoyenne(UPDATED_HSP_MOYENNE);
        return tD006Batiment;
    }

    @BeforeEach
    public void initTest() {
        tD006Batiment = createEntity(em);
    }

    @Test
    @Transactional
    public void createTD006Batiment() throws Exception {
        int databaseSizeBeforeCreate = tD006BatimentRepository.findAll().size();
        // Create the TD006Batiment
        restTD006BatimentMockMvc.perform(post("/api/td-006-batiments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD006Batiment)))
            .andExpect(status().isCreated());

        // Validate the TD006Batiment in the database
        List<TD006Batiment> tD006BatimentList = tD006BatimentRepository.findAll();
        assertThat(tD006BatimentList).hasSize(databaseSizeBeforeCreate + 1);
        TD006Batiment testTD006Batiment = tD006BatimentList.get(tD006BatimentList.size() - 1);
        assertThat(testTD006Batiment.getBesoinChauffage()).isEqualTo(DEFAULT_BESOIN_CHAUFFAGE);
        assertThat(testTD006Batiment.getDeperditionEnveloppe()).isEqualTo(DEFAULT_DEPERDITION_ENVELOPPE);
        assertThat(testTD006Batiment.getDeperditionRenouvellementAir()).isEqualTo(DEFAULT_DEPERDITION_RENOUVELLEMENT_AIR);
        assertThat(testTD006Batiment.getAltitude()).isEqualTo(DEFAULT_ALTITUDE);
        assertThat(testTD006Batiment.getNombreNiveau()).isEqualTo(DEFAULT_NOMBRE_NIVEAU);
        assertThat(testTD006Batiment.getHspMoyenne()).isEqualTo(DEFAULT_HSP_MOYENNE);
    }

    @Test
    @Transactional
    public void createTD006BatimentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tD006BatimentRepository.findAll().size();

        // Create the TD006Batiment with an existing ID
        tD006Batiment.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTD006BatimentMockMvc.perform(post("/api/td-006-batiments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD006Batiment)))
            .andExpect(status().isBadRequest());

        // Validate the TD006Batiment in the database
        List<TD006Batiment> tD006BatimentList = tD006BatimentRepository.findAll();
        assertThat(tD006BatimentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTD006Batiments() throws Exception {
        // Initialize the database
        tD006BatimentRepository.saveAndFlush(tD006Batiment);

        // Get all the tD006BatimentList
        restTD006BatimentMockMvc.perform(get("/api/td-006-batiments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tD006Batiment.getId().intValue())))
            .andExpect(jsonPath("$.[*].besoinChauffage").value(hasItem(DEFAULT_BESOIN_CHAUFFAGE.doubleValue())))
            .andExpect(jsonPath("$.[*].deperditionEnveloppe").value(hasItem(DEFAULT_DEPERDITION_ENVELOPPE.doubleValue())))
            .andExpect(jsonPath("$.[*].deperditionRenouvellementAir").value(hasItem(DEFAULT_DEPERDITION_RENOUVELLEMENT_AIR.doubleValue())))
            .andExpect(jsonPath("$.[*].altitude").value(hasItem(DEFAULT_ALTITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].nombreNiveau").value(hasItem(DEFAULT_NOMBRE_NIVEAU.doubleValue())))
            .andExpect(jsonPath("$.[*].hspMoyenne").value(hasItem(DEFAULT_HSP_MOYENNE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getTD006Batiment() throws Exception {
        // Initialize the database
        tD006BatimentRepository.saveAndFlush(tD006Batiment);

        // Get the tD006Batiment
        restTD006BatimentMockMvc.perform(get("/api/td-006-batiments/{id}", tD006Batiment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tD006Batiment.getId().intValue()))
            .andExpect(jsonPath("$.besoinChauffage").value(DEFAULT_BESOIN_CHAUFFAGE.doubleValue()))
            .andExpect(jsonPath("$.deperditionEnveloppe").value(DEFAULT_DEPERDITION_ENVELOPPE.doubleValue()))
            .andExpect(jsonPath("$.deperditionRenouvellementAir").value(DEFAULT_DEPERDITION_RENOUVELLEMENT_AIR.doubleValue()))
            .andExpect(jsonPath("$.altitude").value(DEFAULT_ALTITUDE.doubleValue()))
            .andExpect(jsonPath("$.nombreNiveau").value(DEFAULT_NOMBRE_NIVEAU.doubleValue()))
            .andExpect(jsonPath("$.hspMoyenne").value(DEFAULT_HSP_MOYENNE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTD006Batiment() throws Exception {
        // Get the tD006Batiment
        restTD006BatimentMockMvc.perform(get("/api/td-006-batiments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTD006Batiment() throws Exception {
        // Initialize the database
        tD006BatimentRepository.saveAndFlush(tD006Batiment);

        int databaseSizeBeforeUpdate = tD006BatimentRepository.findAll().size();

        // Update the tD006Batiment
        TD006Batiment updatedTD006Batiment = tD006BatimentRepository.findById(tD006Batiment.getId()).get();
        // Disconnect from session so that the updates on updatedTD006Batiment are not directly saved in db
        em.detach(updatedTD006Batiment);
        updatedTD006Batiment
            .besoinChauffage(UPDATED_BESOIN_CHAUFFAGE)
            .deperditionEnveloppe(UPDATED_DEPERDITION_ENVELOPPE)
            .deperditionRenouvellementAir(UPDATED_DEPERDITION_RENOUVELLEMENT_AIR)
            .altitude(UPDATED_ALTITUDE)
            .nombreNiveau(UPDATED_NOMBRE_NIVEAU)
            .hspMoyenne(UPDATED_HSP_MOYENNE);

        restTD006BatimentMockMvc.perform(put("/api/td-006-batiments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTD006Batiment)))
            .andExpect(status().isOk());

        // Validate the TD006Batiment in the database
        List<TD006Batiment> tD006BatimentList = tD006BatimentRepository.findAll();
        assertThat(tD006BatimentList).hasSize(databaseSizeBeforeUpdate);
        TD006Batiment testTD006Batiment = tD006BatimentList.get(tD006BatimentList.size() - 1);
        assertThat(testTD006Batiment.getBesoinChauffage()).isEqualTo(UPDATED_BESOIN_CHAUFFAGE);
        assertThat(testTD006Batiment.getDeperditionEnveloppe()).isEqualTo(UPDATED_DEPERDITION_ENVELOPPE);
        assertThat(testTD006Batiment.getDeperditionRenouvellementAir()).isEqualTo(UPDATED_DEPERDITION_RENOUVELLEMENT_AIR);
        assertThat(testTD006Batiment.getAltitude()).isEqualTo(UPDATED_ALTITUDE);
        assertThat(testTD006Batiment.getNombreNiveau()).isEqualTo(UPDATED_NOMBRE_NIVEAU);
        assertThat(testTD006Batiment.getHspMoyenne()).isEqualTo(UPDATED_HSP_MOYENNE);
    }

    @Test
    @Transactional
    public void updateNonExistingTD006Batiment() throws Exception {
        int databaseSizeBeforeUpdate = tD006BatimentRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTD006BatimentMockMvc.perform(put("/api/td-006-batiments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD006Batiment)))
            .andExpect(status().isBadRequest());

        // Validate the TD006Batiment in the database
        List<TD006Batiment> tD006BatimentList = tD006BatimentRepository.findAll();
        assertThat(tD006BatimentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTD006Batiment() throws Exception {
        // Initialize the database
        tD006BatimentRepository.saveAndFlush(tD006Batiment);

        int databaseSizeBeforeDelete = tD006BatimentRepository.findAll().size();

        // Delete the tD006Batiment
        restTD006BatimentMockMvc.perform(delete("/api/td-006-batiments/{id}", tD006Batiment.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TD006Batiment> tD006BatimentList = tD006BatimentRepository.findAll();
        assertThat(tD006BatimentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

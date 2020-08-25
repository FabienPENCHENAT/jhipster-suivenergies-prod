package com.suivenergies.app.web.rest;

import com.suivenergies.app.SuivEnergiesApp;
import com.suivenergies.app.domain.TD007ParoiOpaque;
import com.suivenergies.app.repository.TD007ParoiOpaqueRepository;

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
 * Integration tests for the {@link TD007ParoiOpaqueResource} REST controller.
 */
@SpringBootTest(classes = SuivEnergiesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TD007ParoiOpaqueResourceIT {

    private static final String DEFAULT_TR_014_TYPE_PAROIS_OPAQUE = "AAAAAAAAAA";
    private static final String UPDATED_TR_014_TYPE_PAROIS_OPAQUE = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final Double DEFAULT_DEPERDITION_THERMIQUE = 1D;
    private static final Double UPDATED_DEPERDITION_THERMIQUE = 2D;

    private static final Double DEFAULT_COEFFICIENT_TRANSMISSION_THERMIQUE_PAROI = 1D;
    private static final Double UPDATED_COEFFICIENT_TRANSMISSION_THERMIQUE_PAROI = 2D;

    private static final Double DEFAULT_RESISTANCE_THERMIQUE_ISOLATION = 1D;
    private static final Double UPDATED_RESISTANCE_THERMIQUE_ISOLATION = 2D;

    private static final Double DEFAULT_EPAISSEUR_ISOLATION = 1D;
    private static final Double UPDATED_EPAISSEUR_ISOLATION = 2D;

    private static final Double DEFAULT_SURFACE_PAROI = 1D;
    private static final Double UPDATED_SURFACE_PAROI = 2D;

    @Autowired
    private TD007ParoiOpaqueRepository tD007ParoiOpaqueRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTD007ParoiOpaqueMockMvc;

    private TD007ParoiOpaque tD007ParoiOpaque;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD007ParoiOpaque createEntity(EntityManager em) {
        TD007ParoiOpaque tD007ParoiOpaque = new TD007ParoiOpaque()
            .tr014TypeParoisOpaque(DEFAULT_TR_014_TYPE_PAROIS_OPAQUE)
            .reference(DEFAULT_REFERENCE)
            .deperditionThermique(DEFAULT_DEPERDITION_THERMIQUE)
            .coefficientTransmissionThermiqueParoi(DEFAULT_COEFFICIENT_TRANSMISSION_THERMIQUE_PAROI)
            .resistanceThermiqueIsolation(DEFAULT_RESISTANCE_THERMIQUE_ISOLATION)
            .epaisseurIsolation(DEFAULT_EPAISSEUR_ISOLATION)
            .surfaceParoi(DEFAULT_SURFACE_PAROI);
        return tD007ParoiOpaque;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD007ParoiOpaque createUpdatedEntity(EntityManager em) {
        TD007ParoiOpaque tD007ParoiOpaque = new TD007ParoiOpaque()
            .tr014TypeParoisOpaque(UPDATED_TR_014_TYPE_PAROIS_OPAQUE)
            .reference(UPDATED_REFERENCE)
            .deperditionThermique(UPDATED_DEPERDITION_THERMIQUE)
            .coefficientTransmissionThermiqueParoi(UPDATED_COEFFICIENT_TRANSMISSION_THERMIQUE_PAROI)
            .resistanceThermiqueIsolation(UPDATED_RESISTANCE_THERMIQUE_ISOLATION)
            .epaisseurIsolation(UPDATED_EPAISSEUR_ISOLATION)
            .surfaceParoi(UPDATED_SURFACE_PAROI);
        return tD007ParoiOpaque;
    }

    @BeforeEach
    public void initTest() {
        tD007ParoiOpaque = createEntity(em);
    }

    @Test
    @Transactional
    public void createTD007ParoiOpaque() throws Exception {
        int databaseSizeBeforeCreate = tD007ParoiOpaqueRepository.findAll().size();
        // Create the TD007ParoiOpaque
        restTD007ParoiOpaqueMockMvc.perform(post("/api/td-007-paroi-opaques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD007ParoiOpaque)))
            .andExpect(status().isCreated());

        // Validate the TD007ParoiOpaque in the database
        List<TD007ParoiOpaque> tD007ParoiOpaqueList = tD007ParoiOpaqueRepository.findAll();
        assertThat(tD007ParoiOpaqueList).hasSize(databaseSizeBeforeCreate + 1);
        TD007ParoiOpaque testTD007ParoiOpaque = tD007ParoiOpaqueList.get(tD007ParoiOpaqueList.size() - 1);
        assertThat(testTD007ParoiOpaque.getTr014TypeParoisOpaque()).isEqualTo(DEFAULT_TR_014_TYPE_PAROIS_OPAQUE);
        assertThat(testTD007ParoiOpaque.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testTD007ParoiOpaque.getDeperditionThermique()).isEqualTo(DEFAULT_DEPERDITION_THERMIQUE);
        assertThat(testTD007ParoiOpaque.getCoefficientTransmissionThermiqueParoi()).isEqualTo(DEFAULT_COEFFICIENT_TRANSMISSION_THERMIQUE_PAROI);
        assertThat(testTD007ParoiOpaque.getResistanceThermiqueIsolation()).isEqualTo(DEFAULT_RESISTANCE_THERMIQUE_ISOLATION);
        assertThat(testTD007ParoiOpaque.getEpaisseurIsolation()).isEqualTo(DEFAULT_EPAISSEUR_ISOLATION);
        assertThat(testTD007ParoiOpaque.getSurfaceParoi()).isEqualTo(DEFAULT_SURFACE_PAROI);
    }

    @Test
    @Transactional
    public void createTD007ParoiOpaqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tD007ParoiOpaqueRepository.findAll().size();

        // Create the TD007ParoiOpaque with an existing ID
        tD007ParoiOpaque.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTD007ParoiOpaqueMockMvc.perform(post("/api/td-007-paroi-opaques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD007ParoiOpaque)))
            .andExpect(status().isBadRequest());

        // Validate the TD007ParoiOpaque in the database
        List<TD007ParoiOpaque> tD007ParoiOpaqueList = tD007ParoiOpaqueRepository.findAll();
        assertThat(tD007ParoiOpaqueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTD007ParoiOpaques() throws Exception {
        // Initialize the database
        tD007ParoiOpaqueRepository.saveAndFlush(tD007ParoiOpaque);

        // Get all the tD007ParoiOpaqueList
        restTD007ParoiOpaqueMockMvc.perform(get("/api/td-007-paroi-opaques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tD007ParoiOpaque.getId().intValue())))
            .andExpect(jsonPath("$.[*].tr014TypeParoisOpaque").value(hasItem(DEFAULT_TR_014_TYPE_PAROIS_OPAQUE)))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)))
            .andExpect(jsonPath("$.[*].deperditionThermique").value(hasItem(DEFAULT_DEPERDITION_THERMIQUE.doubleValue())))
            .andExpect(jsonPath("$.[*].coefficientTransmissionThermiqueParoi").value(hasItem(DEFAULT_COEFFICIENT_TRANSMISSION_THERMIQUE_PAROI.doubleValue())))
            .andExpect(jsonPath("$.[*].resistanceThermiqueIsolation").value(hasItem(DEFAULT_RESISTANCE_THERMIQUE_ISOLATION.doubleValue())))
            .andExpect(jsonPath("$.[*].epaisseurIsolation").value(hasItem(DEFAULT_EPAISSEUR_ISOLATION.doubleValue())))
            .andExpect(jsonPath("$.[*].surfaceParoi").value(hasItem(DEFAULT_SURFACE_PAROI.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getTD007ParoiOpaque() throws Exception {
        // Initialize the database
        tD007ParoiOpaqueRepository.saveAndFlush(tD007ParoiOpaque);

        // Get the tD007ParoiOpaque
        restTD007ParoiOpaqueMockMvc.perform(get("/api/td-007-paroi-opaques/{id}", tD007ParoiOpaque.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tD007ParoiOpaque.getId().intValue()))
            .andExpect(jsonPath("$.tr014TypeParoisOpaque").value(DEFAULT_TR_014_TYPE_PAROIS_OPAQUE))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE))
            .andExpect(jsonPath("$.deperditionThermique").value(DEFAULT_DEPERDITION_THERMIQUE.doubleValue()))
            .andExpect(jsonPath("$.coefficientTransmissionThermiqueParoi").value(DEFAULT_COEFFICIENT_TRANSMISSION_THERMIQUE_PAROI.doubleValue()))
            .andExpect(jsonPath("$.resistanceThermiqueIsolation").value(DEFAULT_RESISTANCE_THERMIQUE_ISOLATION.doubleValue()))
            .andExpect(jsonPath("$.epaisseurIsolation").value(DEFAULT_EPAISSEUR_ISOLATION.doubleValue()))
            .andExpect(jsonPath("$.surfaceParoi").value(DEFAULT_SURFACE_PAROI.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTD007ParoiOpaque() throws Exception {
        // Get the tD007ParoiOpaque
        restTD007ParoiOpaqueMockMvc.perform(get("/api/td-007-paroi-opaques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTD007ParoiOpaque() throws Exception {
        // Initialize the database
        tD007ParoiOpaqueRepository.saveAndFlush(tD007ParoiOpaque);

        int databaseSizeBeforeUpdate = tD007ParoiOpaqueRepository.findAll().size();

        // Update the tD007ParoiOpaque
        TD007ParoiOpaque updatedTD007ParoiOpaque = tD007ParoiOpaqueRepository.findById(tD007ParoiOpaque.getId()).get();
        // Disconnect from session so that the updates on updatedTD007ParoiOpaque are not directly saved in db
        em.detach(updatedTD007ParoiOpaque);
        updatedTD007ParoiOpaque
            .tr014TypeParoisOpaque(UPDATED_TR_014_TYPE_PAROIS_OPAQUE)
            .reference(UPDATED_REFERENCE)
            .deperditionThermique(UPDATED_DEPERDITION_THERMIQUE)
            .coefficientTransmissionThermiqueParoi(UPDATED_COEFFICIENT_TRANSMISSION_THERMIQUE_PAROI)
            .resistanceThermiqueIsolation(UPDATED_RESISTANCE_THERMIQUE_ISOLATION)
            .epaisseurIsolation(UPDATED_EPAISSEUR_ISOLATION)
            .surfaceParoi(UPDATED_SURFACE_PAROI);

        restTD007ParoiOpaqueMockMvc.perform(put("/api/td-007-paroi-opaques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTD007ParoiOpaque)))
            .andExpect(status().isOk());

        // Validate the TD007ParoiOpaque in the database
        List<TD007ParoiOpaque> tD007ParoiOpaqueList = tD007ParoiOpaqueRepository.findAll();
        assertThat(tD007ParoiOpaqueList).hasSize(databaseSizeBeforeUpdate);
        TD007ParoiOpaque testTD007ParoiOpaque = tD007ParoiOpaqueList.get(tD007ParoiOpaqueList.size() - 1);
        assertThat(testTD007ParoiOpaque.getTr014TypeParoisOpaque()).isEqualTo(UPDATED_TR_014_TYPE_PAROIS_OPAQUE);
        assertThat(testTD007ParoiOpaque.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testTD007ParoiOpaque.getDeperditionThermique()).isEqualTo(UPDATED_DEPERDITION_THERMIQUE);
        assertThat(testTD007ParoiOpaque.getCoefficientTransmissionThermiqueParoi()).isEqualTo(UPDATED_COEFFICIENT_TRANSMISSION_THERMIQUE_PAROI);
        assertThat(testTD007ParoiOpaque.getResistanceThermiqueIsolation()).isEqualTo(UPDATED_RESISTANCE_THERMIQUE_ISOLATION);
        assertThat(testTD007ParoiOpaque.getEpaisseurIsolation()).isEqualTo(UPDATED_EPAISSEUR_ISOLATION);
        assertThat(testTD007ParoiOpaque.getSurfaceParoi()).isEqualTo(UPDATED_SURFACE_PAROI);
    }

    @Test
    @Transactional
    public void updateNonExistingTD007ParoiOpaque() throws Exception {
        int databaseSizeBeforeUpdate = tD007ParoiOpaqueRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTD007ParoiOpaqueMockMvc.perform(put("/api/td-007-paroi-opaques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD007ParoiOpaque)))
            .andExpect(status().isBadRequest());

        // Validate the TD007ParoiOpaque in the database
        List<TD007ParoiOpaque> tD007ParoiOpaqueList = tD007ParoiOpaqueRepository.findAll();
        assertThat(tD007ParoiOpaqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTD007ParoiOpaque() throws Exception {
        // Initialize the database
        tD007ParoiOpaqueRepository.saveAndFlush(tD007ParoiOpaque);

        int databaseSizeBeforeDelete = tD007ParoiOpaqueRepository.findAll().size();

        // Delete the tD007ParoiOpaque
        restTD007ParoiOpaqueMockMvc.perform(delete("/api/td-007-paroi-opaques/{id}", tD007ParoiOpaque.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TD007ParoiOpaque> tD007ParoiOpaqueList = tD007ParoiOpaqueRepository.findAll();
        assertThat(tD007ParoiOpaqueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

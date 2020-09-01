package com.suivenergies.app.web.rest;

import com.suivenergies.app.SuivEnergiesApp;
import com.suivenergies.app.domain.Electromenager;
import com.suivenergies.app.repository.ElectromenagerRepository;

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
 * Integration tests for the {@link ElectromenagerResource} REST controller.
 */
@SpringBootTest(classes = SuivEnergiesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ElectromenagerResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_BY_DEFAULT = false;
    private static final Boolean UPDATED_BY_DEFAULT = true;

    private static final Double DEFAULT_CONSO_ANNUELLE = 1D;
    private static final Double UPDATED_CONSO_ANNUELLE = 2D;

    @Autowired
    private ElectromenagerRepository electromenagerRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restElectromenagerMockMvc;

    private Electromenager electromenager;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Electromenager createEntity(EntityManager em) {
        Electromenager electromenager = new Electromenager()
            .nom(DEFAULT_NOM)
            .note(DEFAULT_NOTE)
            .byDefault(DEFAULT_BY_DEFAULT)
            .consoAnnuelle(DEFAULT_CONSO_ANNUELLE);
        return electromenager;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Electromenager createUpdatedEntity(EntityManager em) {
        Electromenager electromenager = new Electromenager()
            .nom(UPDATED_NOM)
            .note(UPDATED_NOTE)
            .byDefault(UPDATED_BY_DEFAULT)
            .consoAnnuelle(UPDATED_CONSO_ANNUELLE);
        return electromenager;
    }

    @BeforeEach
    public void initTest() {
        electromenager = createEntity(em);
    }

    @Test
    @Transactional
    public void createElectromenager() throws Exception {
        int databaseSizeBeforeCreate = electromenagerRepository.findAll().size();
        // Create the Electromenager
        restElectromenagerMockMvc.perform(post("/api/electromenagers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(electromenager)))
            .andExpect(status().isCreated());

        // Validate the Electromenager in the database
        List<Electromenager> electromenagerList = electromenagerRepository.findAll();
        assertThat(electromenagerList).hasSize(databaseSizeBeforeCreate + 1);
        Electromenager testElectromenager = electromenagerList.get(electromenagerList.size() - 1);
        assertThat(testElectromenager.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testElectromenager.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testElectromenager.isByDefault()).isEqualTo(DEFAULT_BY_DEFAULT);
        assertThat(testElectromenager.getConsoAnnuelle()).isEqualTo(DEFAULT_CONSO_ANNUELLE);
    }

    @Test
    @Transactional
    public void createElectromenagerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = electromenagerRepository.findAll().size();

        // Create the Electromenager with an existing ID
        electromenager.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restElectromenagerMockMvc.perform(post("/api/electromenagers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(electromenager)))
            .andExpect(status().isBadRequest());

        // Validate the Electromenager in the database
        List<Electromenager> electromenagerList = electromenagerRepository.findAll();
        assertThat(electromenagerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllElectromenagers() throws Exception {
        // Initialize the database
        electromenagerRepository.saveAndFlush(electromenager);

        // Get all the electromenagerList
        restElectromenagerMockMvc.perform(get("/api/electromenagers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(electromenager.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
            .andExpect(jsonPath("$.[*].byDefault").value(hasItem(DEFAULT_BY_DEFAULT.booleanValue())))
            .andExpect(jsonPath("$.[*].consoAnnuelle").value(hasItem(DEFAULT_CONSO_ANNUELLE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getElectromenager() throws Exception {
        // Initialize the database
        electromenagerRepository.saveAndFlush(electromenager);

        // Get the electromenager
        restElectromenagerMockMvc.perform(get("/api/electromenagers/{id}", electromenager.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(electromenager.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE))
            .andExpect(jsonPath("$.byDefault").value(DEFAULT_BY_DEFAULT.booleanValue()))
            .andExpect(jsonPath("$.consoAnnuelle").value(DEFAULT_CONSO_ANNUELLE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingElectromenager() throws Exception {
        // Get the electromenager
        restElectromenagerMockMvc.perform(get("/api/electromenagers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateElectromenager() throws Exception {
        // Initialize the database
        electromenagerRepository.saveAndFlush(electromenager);

        int databaseSizeBeforeUpdate = electromenagerRepository.findAll().size();

        // Update the electromenager
        Electromenager updatedElectromenager = electromenagerRepository.findById(electromenager.getId()).get();
        // Disconnect from session so that the updates on updatedElectromenager are not directly saved in db
        em.detach(updatedElectromenager);
        updatedElectromenager
            .nom(UPDATED_NOM)
            .note(UPDATED_NOTE)
            .byDefault(UPDATED_BY_DEFAULT)
            .consoAnnuelle(UPDATED_CONSO_ANNUELLE);

        restElectromenagerMockMvc.perform(put("/api/electromenagers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedElectromenager)))
            .andExpect(status().isOk());

        // Validate the Electromenager in the database
        List<Electromenager> electromenagerList = electromenagerRepository.findAll();
        assertThat(electromenagerList).hasSize(databaseSizeBeforeUpdate);
        Electromenager testElectromenager = electromenagerList.get(electromenagerList.size() - 1);
        assertThat(testElectromenager.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testElectromenager.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testElectromenager.isByDefault()).isEqualTo(UPDATED_BY_DEFAULT);
        assertThat(testElectromenager.getConsoAnnuelle()).isEqualTo(UPDATED_CONSO_ANNUELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingElectromenager() throws Exception {
        int databaseSizeBeforeUpdate = electromenagerRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restElectromenagerMockMvc.perform(put("/api/electromenagers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(electromenager)))
            .andExpect(status().isBadRequest());

        // Validate the Electromenager in the database
        List<Electromenager> electromenagerList = electromenagerRepository.findAll();
        assertThat(electromenagerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteElectromenager() throws Exception {
        // Initialize the database
        electromenagerRepository.saveAndFlush(electromenager);

        int databaseSizeBeforeDelete = electromenagerRepository.findAll().size();

        // Delete the electromenager
        restElectromenagerMockMvc.perform(delete("/api/electromenagers/{id}", electromenager.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Electromenager> electromenagerList = electromenagerRepository.findAll();
        assertThat(electromenagerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

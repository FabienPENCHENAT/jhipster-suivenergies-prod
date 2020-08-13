package com.suivenergies.app.web.rest;

import com.suivenergies.app.SuivEnergiesApp;
import com.suivenergies.app.domain.ModeVie;
import com.suivenergies.app.repository.ModeVieRepository;

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
 * Integration tests for the {@link ModeVieResource} REST controller.
 */
@SpringBootTest(classes = SuivEnergiesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ModeVieResourceIT {

    private static final Integer DEFAULT_NB_PERSONNES = 1;
    private static final Integer UPDATED_NB_PERSONNES = 2;

    private static final Boolean DEFAULT_PRESENCE_MATIN_SEMAINE = false;
    private static final Boolean UPDATED_PRESENCE_MATIN_SEMAINE = true;

    private static final Boolean DEFAULT_PRESENCE_MATIN_WE = false;
    private static final Boolean UPDATED_PRESENCE_MATIN_WE = true;

    private static final Boolean DEFAULT_PRESENCE_AM_SEMAINE = false;
    private static final Boolean UPDATED_PRESENCE_AM_SEMAINE = true;

    private static final Boolean DEFAULT_PRESENCE_AMWE = false;
    private static final Boolean UPDATED_PRESENCE_AMWE = true;

    private static final Boolean DEFAULT_PRESENCE_SOIR_SEMAINE = false;
    private static final Boolean UPDATED_PRESENCE_SOIR_SEMAINE = true;

    private static final Boolean DEFAULT_PRESENCE_SOIR_WE = false;
    private static final Boolean UPDATED_PRESENCE_SOIR_WE = true;

    private static final Boolean DEFAULT_PRESENCE_NUIT_SEMAINE = false;
    private static final Boolean UPDATED_PRESENCE_NUIT_SEMAINE = true;

    private static final Boolean DEFAULT_PRESENCE_NUIT_WE = false;
    private static final Boolean UPDATED_PRESENCE_NUIT_WE = true;

    private static final Integer DEFAULT_SEMAINES_ABSENCE_HIVER = 1;
    private static final Integer UPDATED_SEMAINES_ABSENCE_HIVER = 2;

    private static final Integer DEFAULT_SEMAINES_ABSENCE_ETE = 1;
    private static final Integer UPDATED_SEMAINES_ABSENCE_ETE = 2;

    @Autowired
    private ModeVieRepository modeVieRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restModeVieMockMvc;

    private ModeVie modeVie;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ModeVie createEntity(EntityManager em) {
        ModeVie modeVie = new ModeVie()
            .nbPersonnes(DEFAULT_NB_PERSONNES)
            .presenceMatinSemaine(DEFAULT_PRESENCE_MATIN_SEMAINE)
            .presenceMatinWE(DEFAULT_PRESENCE_MATIN_WE)
            .presenceAMSemaine(DEFAULT_PRESENCE_AM_SEMAINE)
            .presenceAMWE(DEFAULT_PRESENCE_AMWE)
            .presenceSoirSemaine(DEFAULT_PRESENCE_SOIR_SEMAINE)
            .presenceSoirWE(DEFAULT_PRESENCE_SOIR_WE)
            .presenceNuitSemaine(DEFAULT_PRESENCE_NUIT_SEMAINE)
            .presenceNuitWE(DEFAULT_PRESENCE_NUIT_WE)
            .semainesAbsenceHiver(DEFAULT_SEMAINES_ABSENCE_HIVER)
            .semainesAbsenceEte(DEFAULT_SEMAINES_ABSENCE_ETE);
        return modeVie;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ModeVie createUpdatedEntity(EntityManager em) {
        ModeVie modeVie = new ModeVie()
            .nbPersonnes(UPDATED_NB_PERSONNES)
            .presenceMatinSemaine(UPDATED_PRESENCE_MATIN_SEMAINE)
            .presenceMatinWE(UPDATED_PRESENCE_MATIN_WE)
            .presenceAMSemaine(UPDATED_PRESENCE_AM_SEMAINE)
            .presenceAMWE(UPDATED_PRESENCE_AMWE)
            .presenceSoirSemaine(UPDATED_PRESENCE_SOIR_SEMAINE)
            .presenceSoirWE(UPDATED_PRESENCE_SOIR_WE)
            .presenceNuitSemaine(UPDATED_PRESENCE_NUIT_SEMAINE)
            .presenceNuitWE(UPDATED_PRESENCE_NUIT_WE)
            .semainesAbsenceHiver(UPDATED_SEMAINES_ABSENCE_HIVER)
            .semainesAbsenceEte(UPDATED_SEMAINES_ABSENCE_ETE);
        return modeVie;
    }

    @BeforeEach
    public void initTest() {
        modeVie = createEntity(em);
    }

    @Test
    @Transactional
    public void createModeVie() throws Exception {
        int databaseSizeBeforeCreate = modeVieRepository.findAll().size();
        // Create the ModeVie
        restModeVieMockMvc.perform(post("/api/mode-vies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modeVie)))
            .andExpect(status().isCreated());

        // Validate the ModeVie in the database
        List<ModeVie> modeVieList = modeVieRepository.findAll();
        assertThat(modeVieList).hasSize(databaseSizeBeforeCreate + 1);
        ModeVie testModeVie = modeVieList.get(modeVieList.size() - 1);
        assertThat(testModeVie.getNbPersonnes()).isEqualTo(DEFAULT_NB_PERSONNES);
        assertThat(testModeVie.isPresenceMatinSemaine()).isEqualTo(DEFAULT_PRESENCE_MATIN_SEMAINE);
        assertThat(testModeVie.isPresenceMatinWE()).isEqualTo(DEFAULT_PRESENCE_MATIN_WE);
        assertThat(testModeVie.isPresenceAMSemaine()).isEqualTo(DEFAULT_PRESENCE_AM_SEMAINE);
        assertThat(testModeVie.isPresenceAMWE()).isEqualTo(DEFAULT_PRESENCE_AMWE);
        assertThat(testModeVie.isPresenceSoirSemaine()).isEqualTo(DEFAULT_PRESENCE_SOIR_SEMAINE);
        assertThat(testModeVie.isPresenceSoirWE()).isEqualTo(DEFAULT_PRESENCE_SOIR_WE);
        assertThat(testModeVie.isPresenceNuitSemaine()).isEqualTo(DEFAULT_PRESENCE_NUIT_SEMAINE);
        assertThat(testModeVie.isPresenceNuitWE()).isEqualTo(DEFAULT_PRESENCE_NUIT_WE);
        assertThat(testModeVie.getSemainesAbsenceHiver()).isEqualTo(DEFAULT_SEMAINES_ABSENCE_HIVER);
        assertThat(testModeVie.getSemainesAbsenceEte()).isEqualTo(DEFAULT_SEMAINES_ABSENCE_ETE);
    }

    @Test
    @Transactional
    public void createModeVieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = modeVieRepository.findAll().size();

        // Create the ModeVie with an existing ID
        modeVie.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restModeVieMockMvc.perform(post("/api/mode-vies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modeVie)))
            .andExpect(status().isBadRequest());

        // Validate the ModeVie in the database
        List<ModeVie> modeVieList = modeVieRepository.findAll();
        assertThat(modeVieList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllModeVies() throws Exception {
        // Initialize the database
        modeVieRepository.saveAndFlush(modeVie);

        // Get all the modeVieList
        restModeVieMockMvc.perform(get("/api/mode-vies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modeVie.getId().intValue())))
            .andExpect(jsonPath("$.[*].nbPersonnes").value(hasItem(DEFAULT_NB_PERSONNES)))
            .andExpect(jsonPath("$.[*].presenceMatinSemaine").value(hasItem(DEFAULT_PRESENCE_MATIN_SEMAINE.booleanValue())))
            .andExpect(jsonPath("$.[*].presenceMatinWE").value(hasItem(DEFAULT_PRESENCE_MATIN_WE.booleanValue())))
            .andExpect(jsonPath("$.[*].presenceAMSemaine").value(hasItem(DEFAULT_PRESENCE_AM_SEMAINE.booleanValue())))
            .andExpect(jsonPath("$.[*].presenceAMWE").value(hasItem(DEFAULT_PRESENCE_AMWE.booleanValue())))
            .andExpect(jsonPath("$.[*].presenceSoirSemaine").value(hasItem(DEFAULT_PRESENCE_SOIR_SEMAINE.booleanValue())))
            .andExpect(jsonPath("$.[*].presenceSoirWE").value(hasItem(DEFAULT_PRESENCE_SOIR_WE.booleanValue())))
            .andExpect(jsonPath("$.[*].presenceNuitSemaine").value(hasItem(DEFAULT_PRESENCE_NUIT_SEMAINE.booleanValue())))
            .andExpect(jsonPath("$.[*].presenceNuitWE").value(hasItem(DEFAULT_PRESENCE_NUIT_WE.booleanValue())))
            .andExpect(jsonPath("$.[*].semainesAbsenceHiver").value(hasItem(DEFAULT_SEMAINES_ABSENCE_HIVER)))
            .andExpect(jsonPath("$.[*].semainesAbsenceEte").value(hasItem(DEFAULT_SEMAINES_ABSENCE_ETE)));
    }
    
    @Test
    @Transactional
    public void getModeVie() throws Exception {
        // Initialize the database
        modeVieRepository.saveAndFlush(modeVie);

        // Get the modeVie
        restModeVieMockMvc.perform(get("/api/mode-vies/{id}", modeVie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(modeVie.getId().intValue()))
            .andExpect(jsonPath("$.nbPersonnes").value(DEFAULT_NB_PERSONNES))
            .andExpect(jsonPath("$.presenceMatinSemaine").value(DEFAULT_PRESENCE_MATIN_SEMAINE.booleanValue()))
            .andExpect(jsonPath("$.presenceMatinWE").value(DEFAULT_PRESENCE_MATIN_WE.booleanValue()))
            .andExpect(jsonPath("$.presenceAMSemaine").value(DEFAULT_PRESENCE_AM_SEMAINE.booleanValue()))
            .andExpect(jsonPath("$.presenceAMWE").value(DEFAULT_PRESENCE_AMWE.booleanValue()))
            .andExpect(jsonPath("$.presenceSoirSemaine").value(DEFAULT_PRESENCE_SOIR_SEMAINE.booleanValue()))
            .andExpect(jsonPath("$.presenceSoirWE").value(DEFAULT_PRESENCE_SOIR_WE.booleanValue()))
            .andExpect(jsonPath("$.presenceNuitSemaine").value(DEFAULT_PRESENCE_NUIT_SEMAINE.booleanValue()))
            .andExpect(jsonPath("$.presenceNuitWE").value(DEFAULT_PRESENCE_NUIT_WE.booleanValue()))
            .andExpect(jsonPath("$.semainesAbsenceHiver").value(DEFAULT_SEMAINES_ABSENCE_HIVER))
            .andExpect(jsonPath("$.semainesAbsenceEte").value(DEFAULT_SEMAINES_ABSENCE_ETE));
    }
    @Test
    @Transactional
    public void getNonExistingModeVie() throws Exception {
        // Get the modeVie
        restModeVieMockMvc.perform(get("/api/mode-vies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateModeVie() throws Exception {
        // Initialize the database
        modeVieRepository.saveAndFlush(modeVie);

        int databaseSizeBeforeUpdate = modeVieRepository.findAll().size();

        // Update the modeVie
        ModeVie updatedModeVie = modeVieRepository.findById(modeVie.getId()).get();
        // Disconnect from session so that the updates on updatedModeVie are not directly saved in db
        em.detach(updatedModeVie);
        updatedModeVie
            .nbPersonnes(UPDATED_NB_PERSONNES)
            .presenceMatinSemaine(UPDATED_PRESENCE_MATIN_SEMAINE)
            .presenceMatinWE(UPDATED_PRESENCE_MATIN_WE)
            .presenceAMSemaine(UPDATED_PRESENCE_AM_SEMAINE)
            .presenceAMWE(UPDATED_PRESENCE_AMWE)
            .presenceSoirSemaine(UPDATED_PRESENCE_SOIR_SEMAINE)
            .presenceSoirWE(UPDATED_PRESENCE_SOIR_WE)
            .presenceNuitSemaine(UPDATED_PRESENCE_NUIT_SEMAINE)
            .presenceNuitWE(UPDATED_PRESENCE_NUIT_WE)
            .semainesAbsenceHiver(UPDATED_SEMAINES_ABSENCE_HIVER)
            .semainesAbsenceEte(UPDATED_SEMAINES_ABSENCE_ETE);

        restModeVieMockMvc.perform(put("/api/mode-vies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedModeVie)))
            .andExpect(status().isOk());

        // Validate the ModeVie in the database
        List<ModeVie> modeVieList = modeVieRepository.findAll();
        assertThat(modeVieList).hasSize(databaseSizeBeforeUpdate);
        ModeVie testModeVie = modeVieList.get(modeVieList.size() - 1);
        assertThat(testModeVie.getNbPersonnes()).isEqualTo(UPDATED_NB_PERSONNES);
        assertThat(testModeVie.isPresenceMatinSemaine()).isEqualTo(UPDATED_PRESENCE_MATIN_SEMAINE);
        assertThat(testModeVie.isPresenceMatinWE()).isEqualTo(UPDATED_PRESENCE_MATIN_WE);
        assertThat(testModeVie.isPresenceAMSemaine()).isEqualTo(UPDATED_PRESENCE_AM_SEMAINE);
        assertThat(testModeVie.isPresenceAMWE()).isEqualTo(UPDATED_PRESENCE_AMWE);
        assertThat(testModeVie.isPresenceSoirSemaine()).isEqualTo(UPDATED_PRESENCE_SOIR_SEMAINE);
        assertThat(testModeVie.isPresenceSoirWE()).isEqualTo(UPDATED_PRESENCE_SOIR_WE);
        assertThat(testModeVie.isPresenceNuitSemaine()).isEqualTo(UPDATED_PRESENCE_NUIT_SEMAINE);
        assertThat(testModeVie.isPresenceNuitWE()).isEqualTo(UPDATED_PRESENCE_NUIT_WE);
        assertThat(testModeVie.getSemainesAbsenceHiver()).isEqualTo(UPDATED_SEMAINES_ABSENCE_HIVER);
        assertThat(testModeVie.getSemainesAbsenceEte()).isEqualTo(UPDATED_SEMAINES_ABSENCE_ETE);
    }

    @Test
    @Transactional
    public void updateNonExistingModeVie() throws Exception {
        int databaseSizeBeforeUpdate = modeVieRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModeVieMockMvc.perform(put("/api/mode-vies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modeVie)))
            .andExpect(status().isBadRequest());

        // Validate the ModeVie in the database
        List<ModeVie> modeVieList = modeVieRepository.findAll();
        assertThat(modeVieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteModeVie() throws Exception {
        // Initialize the database
        modeVieRepository.saveAndFlush(modeVie);

        int databaseSizeBeforeDelete = modeVieRepository.findAll().size();

        // Delete the modeVie
        restModeVieMockMvc.perform(delete("/api/mode-vies/{id}", modeVie.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ModeVie> modeVieList = modeVieRepository.findAll();
        assertThat(modeVieList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

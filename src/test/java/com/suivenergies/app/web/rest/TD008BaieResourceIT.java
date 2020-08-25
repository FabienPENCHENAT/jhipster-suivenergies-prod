package com.suivenergies.app.web.rest;

import com.suivenergies.app.SuivEnergiesApp;
import com.suivenergies.app.domain.TD008Baie;
import com.suivenergies.app.repository.TD008BaieRepository;

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
 * Integration tests for the {@link TD008BaieResource} REST controller.
 */
@SpringBootTest(classes = SuivEnergiesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TD008BaieResourceIT {

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_TD_008_BAIE = "AAAAAAAAAA";
    private static final String UPDATED_TD_008_BAIE = "BBBBBBBBBB";

    private static final Double DEFAULT_DEPERDITION = 1D;
    private static final Double UPDATED_DEPERDITION = 2D;

    private static final Double DEFAULT_TV_009_COEFFICIENT_TRANSMISSION_THERMIQUE_VITRAGE = 1D;
    private static final Double UPDATED_TV_009_COEFFICIENT_TRANSMISSION_THERMIQUE_VITRAGE = 2D;

    private static final Double DEFAULT_TV_012_COEF_TRANSMISSION_THERMIQUE_BAIE_PROTECTION_SOLAIRE = 1D;
    private static final Double UPDATED_TV_012_COEF_TRANSMISSION_THERMIQUE_BAIE_PROTECTION_SOLAIRE = 2D;

    private static final Double DEFAULT_SURFACE = 1D;
    private static final Double UPDATED_SURFACE = 2D;

    private static final Double DEFAULT_PERIMETRE = 1D;
    private static final Double UPDATED_PERIMETRE = 2D;

    private static final Double DEFAULT_TV_013_VALEUR_PONT_THERMIQUE = 1D;
    private static final Double UPDATED_TV_013_VALEUR_PONT_THERMIQUE = 2D;

    @Autowired
    private TD008BaieRepository tD008BaieRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTD008BaieMockMvc;

    private TD008Baie tD008Baie;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD008Baie createEntity(EntityManager em) {
        TD008Baie tD008Baie = new TD008Baie()
            .reference(DEFAULT_REFERENCE)
            .td008Baie(DEFAULT_TD_008_BAIE)
            .deperdition(DEFAULT_DEPERDITION)
            .tv009CoefficientTransmissionThermiqueVitrage(DEFAULT_TV_009_COEFFICIENT_TRANSMISSION_THERMIQUE_VITRAGE)
            .tv012CoefTransmissionThermiqueBaieProtectionSolaire(DEFAULT_TV_012_COEF_TRANSMISSION_THERMIQUE_BAIE_PROTECTION_SOLAIRE)
            .surface(DEFAULT_SURFACE)
            .perimetre(DEFAULT_PERIMETRE)
            .tv013ValeurPontThermique(DEFAULT_TV_013_VALEUR_PONT_THERMIQUE);
        return tD008Baie;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD008Baie createUpdatedEntity(EntityManager em) {
        TD008Baie tD008Baie = new TD008Baie()
            .reference(UPDATED_REFERENCE)
            .td008Baie(UPDATED_TD_008_BAIE)
            .deperdition(UPDATED_DEPERDITION)
            .tv009CoefficientTransmissionThermiqueVitrage(UPDATED_TV_009_COEFFICIENT_TRANSMISSION_THERMIQUE_VITRAGE)
            .tv012CoefTransmissionThermiqueBaieProtectionSolaire(UPDATED_TV_012_COEF_TRANSMISSION_THERMIQUE_BAIE_PROTECTION_SOLAIRE)
            .surface(UPDATED_SURFACE)
            .perimetre(UPDATED_PERIMETRE)
            .tv013ValeurPontThermique(UPDATED_TV_013_VALEUR_PONT_THERMIQUE);
        return tD008Baie;
    }

    @BeforeEach
    public void initTest() {
        tD008Baie = createEntity(em);
    }

    @Test
    @Transactional
    public void createTD008Baie() throws Exception {
        int databaseSizeBeforeCreate = tD008BaieRepository.findAll().size();
        // Create the TD008Baie
        restTD008BaieMockMvc.perform(post("/api/td-008-baies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD008Baie)))
            .andExpect(status().isCreated());

        // Validate the TD008Baie in the database
        List<TD008Baie> tD008BaieList = tD008BaieRepository.findAll();
        assertThat(tD008BaieList).hasSize(databaseSizeBeforeCreate + 1);
        TD008Baie testTD008Baie = tD008BaieList.get(tD008BaieList.size() - 1);
        assertThat(testTD008Baie.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testTD008Baie.getTd008Baie()).isEqualTo(DEFAULT_TD_008_BAIE);
        assertThat(testTD008Baie.getDeperdition()).isEqualTo(DEFAULT_DEPERDITION);
        assertThat(testTD008Baie.getTv009CoefficientTransmissionThermiqueVitrage()).isEqualTo(DEFAULT_TV_009_COEFFICIENT_TRANSMISSION_THERMIQUE_VITRAGE);
        assertThat(testTD008Baie.getTv012CoefTransmissionThermiqueBaieProtectionSolaire()).isEqualTo(DEFAULT_TV_012_COEF_TRANSMISSION_THERMIQUE_BAIE_PROTECTION_SOLAIRE);
        assertThat(testTD008Baie.getSurface()).isEqualTo(DEFAULT_SURFACE);
        assertThat(testTD008Baie.getPerimetre()).isEqualTo(DEFAULT_PERIMETRE);
        assertThat(testTD008Baie.getTv013ValeurPontThermique()).isEqualTo(DEFAULT_TV_013_VALEUR_PONT_THERMIQUE);
    }

    @Test
    @Transactional
    public void createTD008BaieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tD008BaieRepository.findAll().size();

        // Create the TD008Baie with an existing ID
        tD008Baie.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTD008BaieMockMvc.perform(post("/api/td-008-baies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD008Baie)))
            .andExpect(status().isBadRequest());

        // Validate the TD008Baie in the database
        List<TD008Baie> tD008BaieList = tD008BaieRepository.findAll();
        assertThat(tD008BaieList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTD008Baies() throws Exception {
        // Initialize the database
        tD008BaieRepository.saveAndFlush(tD008Baie);

        // Get all the tD008BaieList
        restTD008BaieMockMvc.perform(get("/api/td-008-baies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tD008Baie.getId().intValue())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)))
            .andExpect(jsonPath("$.[*].td008Baie").value(hasItem(DEFAULT_TD_008_BAIE)))
            .andExpect(jsonPath("$.[*].deperdition").value(hasItem(DEFAULT_DEPERDITION.doubleValue())))
            .andExpect(jsonPath("$.[*].tv009CoefficientTransmissionThermiqueVitrage").value(hasItem(DEFAULT_TV_009_COEFFICIENT_TRANSMISSION_THERMIQUE_VITRAGE.doubleValue())))
            .andExpect(jsonPath("$.[*].tv012CoefTransmissionThermiqueBaieProtectionSolaire").value(hasItem(DEFAULT_TV_012_COEF_TRANSMISSION_THERMIQUE_BAIE_PROTECTION_SOLAIRE.doubleValue())))
            .andExpect(jsonPath("$.[*].surface").value(hasItem(DEFAULT_SURFACE.doubleValue())))
            .andExpect(jsonPath("$.[*].perimetre").value(hasItem(DEFAULT_PERIMETRE.doubleValue())))
            .andExpect(jsonPath("$.[*].tv013ValeurPontThermique").value(hasItem(DEFAULT_TV_013_VALEUR_PONT_THERMIQUE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getTD008Baie() throws Exception {
        // Initialize the database
        tD008BaieRepository.saveAndFlush(tD008Baie);

        // Get the tD008Baie
        restTD008BaieMockMvc.perform(get("/api/td-008-baies/{id}", tD008Baie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tD008Baie.getId().intValue()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE))
            .andExpect(jsonPath("$.td008Baie").value(DEFAULT_TD_008_BAIE))
            .andExpect(jsonPath("$.deperdition").value(DEFAULT_DEPERDITION.doubleValue()))
            .andExpect(jsonPath("$.tv009CoefficientTransmissionThermiqueVitrage").value(DEFAULT_TV_009_COEFFICIENT_TRANSMISSION_THERMIQUE_VITRAGE.doubleValue()))
            .andExpect(jsonPath("$.tv012CoefTransmissionThermiqueBaieProtectionSolaire").value(DEFAULT_TV_012_COEF_TRANSMISSION_THERMIQUE_BAIE_PROTECTION_SOLAIRE.doubleValue()))
            .andExpect(jsonPath("$.surface").value(DEFAULT_SURFACE.doubleValue()))
            .andExpect(jsonPath("$.perimetre").value(DEFAULT_PERIMETRE.doubleValue()))
            .andExpect(jsonPath("$.tv013ValeurPontThermique").value(DEFAULT_TV_013_VALEUR_PONT_THERMIQUE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTD008Baie() throws Exception {
        // Get the tD008Baie
        restTD008BaieMockMvc.perform(get("/api/td-008-baies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTD008Baie() throws Exception {
        // Initialize the database
        tD008BaieRepository.saveAndFlush(tD008Baie);

        int databaseSizeBeforeUpdate = tD008BaieRepository.findAll().size();

        // Update the tD008Baie
        TD008Baie updatedTD008Baie = tD008BaieRepository.findById(tD008Baie.getId()).get();
        // Disconnect from session so that the updates on updatedTD008Baie are not directly saved in db
        em.detach(updatedTD008Baie);
        updatedTD008Baie
            .reference(UPDATED_REFERENCE)
            .td008Baie(UPDATED_TD_008_BAIE)
            .deperdition(UPDATED_DEPERDITION)
            .tv009CoefficientTransmissionThermiqueVitrage(UPDATED_TV_009_COEFFICIENT_TRANSMISSION_THERMIQUE_VITRAGE)
            .tv012CoefTransmissionThermiqueBaieProtectionSolaire(UPDATED_TV_012_COEF_TRANSMISSION_THERMIQUE_BAIE_PROTECTION_SOLAIRE)
            .surface(UPDATED_SURFACE)
            .perimetre(UPDATED_PERIMETRE)
            .tv013ValeurPontThermique(UPDATED_TV_013_VALEUR_PONT_THERMIQUE);

        restTD008BaieMockMvc.perform(put("/api/td-008-baies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTD008Baie)))
            .andExpect(status().isOk());

        // Validate the TD008Baie in the database
        List<TD008Baie> tD008BaieList = tD008BaieRepository.findAll();
        assertThat(tD008BaieList).hasSize(databaseSizeBeforeUpdate);
        TD008Baie testTD008Baie = tD008BaieList.get(tD008BaieList.size() - 1);
        assertThat(testTD008Baie.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testTD008Baie.getTd008Baie()).isEqualTo(UPDATED_TD_008_BAIE);
        assertThat(testTD008Baie.getDeperdition()).isEqualTo(UPDATED_DEPERDITION);
        assertThat(testTD008Baie.getTv009CoefficientTransmissionThermiqueVitrage()).isEqualTo(UPDATED_TV_009_COEFFICIENT_TRANSMISSION_THERMIQUE_VITRAGE);
        assertThat(testTD008Baie.getTv012CoefTransmissionThermiqueBaieProtectionSolaire()).isEqualTo(UPDATED_TV_012_COEF_TRANSMISSION_THERMIQUE_BAIE_PROTECTION_SOLAIRE);
        assertThat(testTD008Baie.getSurface()).isEqualTo(UPDATED_SURFACE);
        assertThat(testTD008Baie.getPerimetre()).isEqualTo(UPDATED_PERIMETRE);
        assertThat(testTD008Baie.getTv013ValeurPontThermique()).isEqualTo(UPDATED_TV_013_VALEUR_PONT_THERMIQUE);
    }

    @Test
    @Transactional
    public void updateNonExistingTD008Baie() throws Exception {
        int databaseSizeBeforeUpdate = tD008BaieRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTD008BaieMockMvc.perform(put("/api/td-008-baies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD008Baie)))
            .andExpect(status().isBadRequest());

        // Validate the TD008Baie in the database
        List<TD008Baie> tD008BaieList = tD008BaieRepository.findAll();
        assertThat(tD008BaieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTD008Baie() throws Exception {
        // Initialize the database
        tD008BaieRepository.saveAndFlush(tD008Baie);

        int databaseSizeBeforeDelete = tD008BaieRepository.findAll().size();

        // Delete the tD008Baie
        restTD008BaieMockMvc.perform(delete("/api/td-008-baies/{id}", tD008Baie.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TD008Baie> tD008BaieList = tD008BaieRepository.findAll();
        assertThat(tD008BaieList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

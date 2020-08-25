package com.suivenergies.app.web.rest;

import com.suivenergies.app.SuivEnergiesApp;
import com.suivenergies.app.domain.TD017ConsommationNeuf;
import com.suivenergies.app.repository.TD017ConsommationNeufRepository;

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
 * Integration tests for the {@link TD017ConsommationNeufResource} REST controller.
 */
@SpringBootTest(classes = SuivEnergiesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TD017ConsommationNeufResourceIT {

    private static final String DEFAULT_TR_004_TYPE_ENERGIE = "AAAAAAAAAA";
    private static final String UPDATED_TR_004_TYPE_ENERGIE = "BBBBBBBBBB";

    private static final String DEFAULT_TR_006_TYPE_USAGE = "AAAAAAAAAA";
    private static final String UPDATED_TR_006_TYPE_USAGE = "BBBBBBBBBB";

    private static final String DEFAULT_TV_044_CONVERSION_KWH_ENERGIES_RELEVEES = "AAAAAAAAAA";
    private static final String UPDATED_TV_044_CONVERSION_KWH_ENERGIES_RELEVEES = "BBBBBBBBBB";

    private static final String DEFAULT_TV_045_CONVERSION_KWH_CO_2 = "AAAAAAAAAA";
    private static final String UPDATED_TV_045_CONVERSION_KWH_CO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_TV_046_EVALUATION_CONTENU_CO_2_RESEAUX = "AAAAAAAAAA";
    private static final String UPDATED_TV_046_EVALUATION_CONTENU_CO_2_RESEAUX = "BBBBBBBBBB";

    private static final Double DEFAULT_CONSOMMATION_ENERGIE_FINALE = 1D;
    private static final Double UPDATED_CONSOMMATION_ENERGIE_FINALE = 2D;

    private static final Double DEFAULT_CONSOMMATION_ENERGIE_PRIMAIRE = 1D;
    private static final Double UPDATED_CONSOMMATION_ENERGIE_PRIMAIRE = 2D;

    private static final Double DEFAULT_FRAIS_ANNUELS_ENERGIE = 1D;
    private static final Double UPDATED_FRAIS_ANNUELS_ENERGIE = 2D;

    @Autowired
    private TD017ConsommationNeufRepository tD017ConsommationNeufRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTD017ConsommationNeufMockMvc;

    private TD017ConsommationNeuf tD017ConsommationNeuf;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD017ConsommationNeuf createEntity(EntityManager em) {
        TD017ConsommationNeuf tD017ConsommationNeuf = new TD017ConsommationNeuf()
            .tr004TypeEnergie(DEFAULT_TR_004_TYPE_ENERGIE)
            .tr006TypeUsage(DEFAULT_TR_006_TYPE_USAGE)
            .tv044ConversionKwhEnergiesRelevees(DEFAULT_TV_044_CONVERSION_KWH_ENERGIES_RELEVEES)
            .tv045ConversionKwhCo2(DEFAULT_TV_045_CONVERSION_KWH_CO_2)
            .tv046EvaluationContenuCo2Reseaux(DEFAULT_TV_046_EVALUATION_CONTENU_CO_2_RESEAUX)
            .consommationEnergieFinale(DEFAULT_CONSOMMATION_ENERGIE_FINALE)
            .consommationEnergiePrimaire(DEFAULT_CONSOMMATION_ENERGIE_PRIMAIRE)
            .fraisAnnuelsEnergie(DEFAULT_FRAIS_ANNUELS_ENERGIE);
        return tD017ConsommationNeuf;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD017ConsommationNeuf createUpdatedEntity(EntityManager em) {
        TD017ConsommationNeuf tD017ConsommationNeuf = new TD017ConsommationNeuf()
            .tr004TypeEnergie(UPDATED_TR_004_TYPE_ENERGIE)
            .tr006TypeUsage(UPDATED_TR_006_TYPE_USAGE)
            .tv044ConversionKwhEnergiesRelevees(UPDATED_TV_044_CONVERSION_KWH_ENERGIES_RELEVEES)
            .tv045ConversionKwhCo2(UPDATED_TV_045_CONVERSION_KWH_CO_2)
            .tv046EvaluationContenuCo2Reseaux(UPDATED_TV_046_EVALUATION_CONTENU_CO_2_RESEAUX)
            .consommationEnergieFinale(UPDATED_CONSOMMATION_ENERGIE_FINALE)
            .consommationEnergiePrimaire(UPDATED_CONSOMMATION_ENERGIE_PRIMAIRE)
            .fraisAnnuelsEnergie(UPDATED_FRAIS_ANNUELS_ENERGIE);
        return tD017ConsommationNeuf;
    }

    @BeforeEach
    public void initTest() {
        tD017ConsommationNeuf = createEntity(em);
    }

    @Test
    @Transactional
    public void createTD017ConsommationNeuf() throws Exception {
        int databaseSizeBeforeCreate = tD017ConsommationNeufRepository.findAll().size();
        // Create the TD017ConsommationNeuf
        restTD017ConsommationNeufMockMvc.perform(post("/api/td-017-consommation-neufs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD017ConsommationNeuf)))
            .andExpect(status().isCreated());

        // Validate the TD017ConsommationNeuf in the database
        List<TD017ConsommationNeuf> tD017ConsommationNeufList = tD017ConsommationNeufRepository.findAll();
        assertThat(tD017ConsommationNeufList).hasSize(databaseSizeBeforeCreate + 1);
        TD017ConsommationNeuf testTD017ConsommationNeuf = tD017ConsommationNeufList.get(tD017ConsommationNeufList.size() - 1);
        assertThat(testTD017ConsommationNeuf.getTr004TypeEnergie()).isEqualTo(DEFAULT_TR_004_TYPE_ENERGIE);
        assertThat(testTD017ConsommationNeuf.getTr006TypeUsage()).isEqualTo(DEFAULT_TR_006_TYPE_USAGE);
        assertThat(testTD017ConsommationNeuf.getTv044ConversionKwhEnergiesRelevees()).isEqualTo(DEFAULT_TV_044_CONVERSION_KWH_ENERGIES_RELEVEES);
        assertThat(testTD017ConsommationNeuf.getTv045ConversionKwhCo2()).isEqualTo(DEFAULT_TV_045_CONVERSION_KWH_CO_2);
        assertThat(testTD017ConsommationNeuf.getTv046EvaluationContenuCo2Reseaux()).isEqualTo(DEFAULT_TV_046_EVALUATION_CONTENU_CO_2_RESEAUX);
        assertThat(testTD017ConsommationNeuf.getConsommationEnergieFinale()).isEqualTo(DEFAULT_CONSOMMATION_ENERGIE_FINALE);
        assertThat(testTD017ConsommationNeuf.getConsommationEnergiePrimaire()).isEqualTo(DEFAULT_CONSOMMATION_ENERGIE_PRIMAIRE);
        assertThat(testTD017ConsommationNeuf.getFraisAnnuelsEnergie()).isEqualTo(DEFAULT_FRAIS_ANNUELS_ENERGIE);
    }

    @Test
    @Transactional
    public void createTD017ConsommationNeufWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tD017ConsommationNeufRepository.findAll().size();

        // Create the TD017ConsommationNeuf with an existing ID
        tD017ConsommationNeuf.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTD017ConsommationNeufMockMvc.perform(post("/api/td-017-consommation-neufs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD017ConsommationNeuf)))
            .andExpect(status().isBadRequest());

        // Validate the TD017ConsommationNeuf in the database
        List<TD017ConsommationNeuf> tD017ConsommationNeufList = tD017ConsommationNeufRepository.findAll();
        assertThat(tD017ConsommationNeufList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTD017ConsommationNeufs() throws Exception {
        // Initialize the database
        tD017ConsommationNeufRepository.saveAndFlush(tD017ConsommationNeuf);

        // Get all the tD017ConsommationNeufList
        restTD017ConsommationNeufMockMvc.perform(get("/api/td-017-consommation-neufs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tD017ConsommationNeuf.getId().intValue())))
            .andExpect(jsonPath("$.[*].tr004TypeEnergie").value(hasItem(DEFAULT_TR_004_TYPE_ENERGIE)))
            .andExpect(jsonPath("$.[*].tr006TypeUsage").value(hasItem(DEFAULT_TR_006_TYPE_USAGE)))
            .andExpect(jsonPath("$.[*].tv044ConversionKwhEnergiesRelevees").value(hasItem(DEFAULT_TV_044_CONVERSION_KWH_ENERGIES_RELEVEES)))
            .andExpect(jsonPath("$.[*].tv045ConversionKwhCo2").value(hasItem(DEFAULT_TV_045_CONVERSION_KWH_CO_2)))
            .andExpect(jsonPath("$.[*].tv046EvaluationContenuCo2Reseaux").value(hasItem(DEFAULT_TV_046_EVALUATION_CONTENU_CO_2_RESEAUX)))
            .andExpect(jsonPath("$.[*].consommationEnergieFinale").value(hasItem(DEFAULT_CONSOMMATION_ENERGIE_FINALE.doubleValue())))
            .andExpect(jsonPath("$.[*].consommationEnergiePrimaire").value(hasItem(DEFAULT_CONSOMMATION_ENERGIE_PRIMAIRE.doubleValue())))
            .andExpect(jsonPath("$.[*].fraisAnnuelsEnergie").value(hasItem(DEFAULT_FRAIS_ANNUELS_ENERGIE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getTD017ConsommationNeuf() throws Exception {
        // Initialize the database
        tD017ConsommationNeufRepository.saveAndFlush(tD017ConsommationNeuf);

        // Get the tD017ConsommationNeuf
        restTD017ConsommationNeufMockMvc.perform(get("/api/td-017-consommation-neufs/{id}", tD017ConsommationNeuf.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tD017ConsommationNeuf.getId().intValue()))
            .andExpect(jsonPath("$.tr004TypeEnergie").value(DEFAULT_TR_004_TYPE_ENERGIE))
            .andExpect(jsonPath("$.tr006TypeUsage").value(DEFAULT_TR_006_TYPE_USAGE))
            .andExpect(jsonPath("$.tv044ConversionKwhEnergiesRelevees").value(DEFAULT_TV_044_CONVERSION_KWH_ENERGIES_RELEVEES))
            .andExpect(jsonPath("$.tv045ConversionKwhCo2").value(DEFAULT_TV_045_CONVERSION_KWH_CO_2))
            .andExpect(jsonPath("$.tv046EvaluationContenuCo2Reseaux").value(DEFAULT_TV_046_EVALUATION_CONTENU_CO_2_RESEAUX))
            .andExpect(jsonPath("$.consommationEnergieFinale").value(DEFAULT_CONSOMMATION_ENERGIE_FINALE.doubleValue()))
            .andExpect(jsonPath("$.consommationEnergiePrimaire").value(DEFAULT_CONSOMMATION_ENERGIE_PRIMAIRE.doubleValue()))
            .andExpect(jsonPath("$.fraisAnnuelsEnergie").value(DEFAULT_FRAIS_ANNUELS_ENERGIE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTD017ConsommationNeuf() throws Exception {
        // Get the tD017ConsommationNeuf
        restTD017ConsommationNeufMockMvc.perform(get("/api/td-017-consommation-neufs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTD017ConsommationNeuf() throws Exception {
        // Initialize the database
        tD017ConsommationNeufRepository.saveAndFlush(tD017ConsommationNeuf);

        int databaseSizeBeforeUpdate = tD017ConsommationNeufRepository.findAll().size();

        // Update the tD017ConsommationNeuf
        TD017ConsommationNeuf updatedTD017ConsommationNeuf = tD017ConsommationNeufRepository.findById(tD017ConsommationNeuf.getId()).get();
        // Disconnect from session so that the updates on updatedTD017ConsommationNeuf are not directly saved in db
        em.detach(updatedTD017ConsommationNeuf);
        updatedTD017ConsommationNeuf
            .tr004TypeEnergie(UPDATED_TR_004_TYPE_ENERGIE)
            .tr006TypeUsage(UPDATED_TR_006_TYPE_USAGE)
            .tv044ConversionKwhEnergiesRelevees(UPDATED_TV_044_CONVERSION_KWH_ENERGIES_RELEVEES)
            .tv045ConversionKwhCo2(UPDATED_TV_045_CONVERSION_KWH_CO_2)
            .tv046EvaluationContenuCo2Reseaux(UPDATED_TV_046_EVALUATION_CONTENU_CO_2_RESEAUX)
            .consommationEnergieFinale(UPDATED_CONSOMMATION_ENERGIE_FINALE)
            .consommationEnergiePrimaire(UPDATED_CONSOMMATION_ENERGIE_PRIMAIRE)
            .fraisAnnuelsEnergie(UPDATED_FRAIS_ANNUELS_ENERGIE);

        restTD017ConsommationNeufMockMvc.perform(put("/api/td-017-consommation-neufs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTD017ConsommationNeuf)))
            .andExpect(status().isOk());

        // Validate the TD017ConsommationNeuf in the database
        List<TD017ConsommationNeuf> tD017ConsommationNeufList = tD017ConsommationNeufRepository.findAll();
        assertThat(tD017ConsommationNeufList).hasSize(databaseSizeBeforeUpdate);
        TD017ConsommationNeuf testTD017ConsommationNeuf = tD017ConsommationNeufList.get(tD017ConsommationNeufList.size() - 1);
        assertThat(testTD017ConsommationNeuf.getTr004TypeEnergie()).isEqualTo(UPDATED_TR_004_TYPE_ENERGIE);
        assertThat(testTD017ConsommationNeuf.getTr006TypeUsage()).isEqualTo(UPDATED_TR_006_TYPE_USAGE);
        assertThat(testTD017ConsommationNeuf.getTv044ConversionKwhEnergiesRelevees()).isEqualTo(UPDATED_TV_044_CONVERSION_KWH_ENERGIES_RELEVEES);
        assertThat(testTD017ConsommationNeuf.getTv045ConversionKwhCo2()).isEqualTo(UPDATED_TV_045_CONVERSION_KWH_CO_2);
        assertThat(testTD017ConsommationNeuf.getTv046EvaluationContenuCo2Reseaux()).isEqualTo(UPDATED_TV_046_EVALUATION_CONTENU_CO_2_RESEAUX);
        assertThat(testTD017ConsommationNeuf.getConsommationEnergieFinale()).isEqualTo(UPDATED_CONSOMMATION_ENERGIE_FINALE);
        assertThat(testTD017ConsommationNeuf.getConsommationEnergiePrimaire()).isEqualTo(UPDATED_CONSOMMATION_ENERGIE_PRIMAIRE);
        assertThat(testTD017ConsommationNeuf.getFraisAnnuelsEnergie()).isEqualTo(UPDATED_FRAIS_ANNUELS_ENERGIE);
    }

    @Test
    @Transactional
    public void updateNonExistingTD017ConsommationNeuf() throws Exception {
        int databaseSizeBeforeUpdate = tD017ConsommationNeufRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTD017ConsommationNeufMockMvc.perform(put("/api/td-017-consommation-neufs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD017ConsommationNeuf)))
            .andExpect(status().isBadRequest());

        // Validate the TD017ConsommationNeuf in the database
        List<TD017ConsommationNeuf> tD017ConsommationNeufList = tD017ConsommationNeufRepository.findAll();
        assertThat(tD017ConsommationNeufList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTD017ConsommationNeuf() throws Exception {
        // Initialize the database
        tD017ConsommationNeufRepository.saveAndFlush(tD017ConsommationNeuf);

        int databaseSizeBeforeDelete = tD017ConsommationNeufRepository.findAll().size();

        // Delete the tD017ConsommationNeuf
        restTD017ConsommationNeufMockMvc.perform(delete("/api/td-017-consommation-neufs/{id}", tD017ConsommationNeuf.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TD017ConsommationNeuf> tD017ConsommationNeufList = tD017ConsommationNeufRepository.findAll();
        assertThat(tD017ConsommationNeufList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

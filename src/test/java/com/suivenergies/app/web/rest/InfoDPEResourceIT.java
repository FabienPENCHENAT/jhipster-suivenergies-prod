package com.suivenergies.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.suivenergies.app.SuivEnergiesApp;
import com.suivenergies.app.domain.InfoDPE;
import com.suivenergies.app.repository.InfoDPERepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link InfoDPEResource} REST controller.
 */
@SpringBootTest(classes = SuivEnergiesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InfoDPEResourceIT {
    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_BATIMENT = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_BATIMENT = "BBBBBBBBBB";

    private static final Long DEFAULT_ANNEE_CONSTRUCTION = 1L;
    private static final Long UPDATED_ANNEE_CONSTRUCTION = 2L;

    private static final Long DEFAULT_SURFACE_HABITABLE = 1L;
    private static final Long UPDATED_SURFACE_HABITABLE = 2L;

    private static final String DEFAULT_ENERGIE_CHAUFFAGE = "AAAAAAAAAA";
    private static final String UPDATED_ENERGIE_CHAUFFAGE = "BBBBBBBBBB";

    private static final String DEFAULT_ENERGIE_EAU_CHAUDE = "AAAAAAAAAA";
    private static final String UPDATED_ENERGIE_EAU_CHAUDE = "BBBBBBBBBB";

    private static final Long DEFAULT_ENERGIE_PHOTOVOLTAIQUE = 1L;
    private static final Long UPDATED_ENERGIE_PHOTOVOLTAIQUE = 2L;

    private static final LocalDate DEFAULT_DATE_DPE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DPE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CLASSE_CONSO_ENERGIE = "AAAAAAAAAA";
    private static final String UPDATED_CLASSE_CONSO_ENERGIE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_DPE_JSON = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_DPE_JSON = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_DPE_JSON_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DPE_JSON_CONTENT_TYPE = "image/png";

    @Autowired
    private InfoDPERepository infoDPERepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInfoDPEMockMvc;

    private InfoDPE infoDPE;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InfoDPE createEntity(EntityManager em) {
        InfoDPE infoDPE = new InfoDPE()
            .numero(DEFAULT_NUMERO)
            .adresse(DEFAULT_ADRESSE)
            .typeBatiment(DEFAULT_TYPE_BATIMENT)
            .anneeConstruction(DEFAULT_ANNEE_CONSTRUCTION)
            .surfaceHabitable(DEFAULT_SURFACE_HABITABLE)
            .energieChauffage(DEFAULT_ENERGIE_CHAUFFAGE)
            .energieEauChaude(DEFAULT_ENERGIE_EAU_CHAUDE)
            .energiePhotovoltaique(DEFAULT_ENERGIE_PHOTOVOLTAIQUE)
            .dateDPE(DEFAULT_DATE_DPE)
            .classeConsoEnergie(DEFAULT_CLASSE_CONSO_ENERGIE)
            .dpeJson(DEFAULT_DPE_JSON)
            .dpeJsonContentType(DEFAULT_DPE_JSON_CONTENT_TYPE);
        return infoDPE;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InfoDPE createUpdatedEntity(EntityManager em) {
        InfoDPE infoDPE = new InfoDPE()
            .numero(UPDATED_NUMERO)
            .adresse(UPDATED_ADRESSE)
            .typeBatiment(UPDATED_TYPE_BATIMENT)
            .anneeConstruction(UPDATED_ANNEE_CONSTRUCTION)
            .surfaceHabitable(UPDATED_SURFACE_HABITABLE)
            .energieChauffage(UPDATED_ENERGIE_CHAUFFAGE)
            .energieEauChaude(UPDATED_ENERGIE_EAU_CHAUDE)
            .energiePhotovoltaique(UPDATED_ENERGIE_PHOTOVOLTAIQUE)
            .dateDPE(UPDATED_DATE_DPE)
            .classeConsoEnergie(UPDATED_CLASSE_CONSO_ENERGIE)
            .dpeJson(UPDATED_DPE_JSON)
            .dpeJsonContentType(UPDATED_DPE_JSON_CONTENT_TYPE);
        return infoDPE;
    }

    @BeforeEach
    public void initTest() {
        infoDPE = createEntity(em);
    }

    @Test
    @Transactional
    public void createInfoDPE() throws Exception {
        int databaseSizeBeforeCreate = infoDPERepository.findAll().size();
        // Create the InfoDPE
        restInfoDPEMockMvc
            .perform(post("/api/info-dpes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(infoDPE)))
            .andExpect(status().isCreated());

        // Validate the InfoDPE in the database
        List<InfoDPE> infoDPEList = infoDPERepository.findAll();
        assertThat(infoDPEList).hasSize(databaseSizeBeforeCreate + 1);
        InfoDPE testInfoDPE = infoDPEList.get(infoDPEList.size() - 1);
        assertThat(testInfoDPE.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testInfoDPE.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testInfoDPE.getTypeBatiment()).isEqualTo(DEFAULT_TYPE_BATIMENT);
        assertThat(testInfoDPE.getAnneeConstruction()).isEqualTo(DEFAULT_ANNEE_CONSTRUCTION);
        assertThat(testInfoDPE.getSurfaceHabitable()).isEqualTo(DEFAULT_SURFACE_HABITABLE);
        assertThat(testInfoDPE.getEnergieChauffage()).isEqualTo(DEFAULT_ENERGIE_CHAUFFAGE);
        assertThat(testInfoDPE.getEnergieEauChaude()).isEqualTo(DEFAULT_ENERGIE_EAU_CHAUDE);
        assertThat(testInfoDPE.getEnergiePhotovoltaique()).isEqualTo(DEFAULT_ENERGIE_PHOTOVOLTAIQUE);
        assertThat(testInfoDPE.getDateDPE()).isEqualTo(DEFAULT_DATE_DPE);
        assertThat(testInfoDPE.getClasseConsoEnergie()).isEqualTo(DEFAULT_CLASSE_CONSO_ENERGIE);
        assertThat(testInfoDPE.getDpeJson()).isEqualTo(DEFAULT_DPE_JSON);
        assertThat(testInfoDPE.getDpeJsonContentType()).isEqualTo(DEFAULT_DPE_JSON_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createInfoDPEWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = infoDPERepository.findAll().size();

        // Create the InfoDPE with an existing ID
        infoDPE.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInfoDPEMockMvc
            .perform(post("/api/info-dpes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(infoDPE)))
            .andExpect(status().isBadRequest());

        // Validate the InfoDPE in the database
        List<InfoDPE> infoDPEList = infoDPERepository.findAll();
        assertThat(infoDPEList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllInfoDPES() throws Exception {
        // Initialize the database
        infoDPERepository.saveAndFlush(infoDPE);

        // Get all the infoDPEList
        restInfoDPEMockMvc
            .perform(get("/api/info-dpes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(infoDPE.getId().intValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].typeBatiment").value(hasItem(DEFAULT_TYPE_BATIMENT)))
            .andExpect(jsonPath("$.[*].anneeConstruction").value(hasItem(DEFAULT_ANNEE_CONSTRUCTION.intValue())))
            .andExpect(jsonPath("$.[*].surfaceHabitable").value(hasItem(DEFAULT_SURFACE_HABITABLE.intValue())))
            .andExpect(jsonPath("$.[*].energieChauffage").value(hasItem(DEFAULT_ENERGIE_CHAUFFAGE)))
            .andExpect(jsonPath("$.[*].energieEauChaude").value(hasItem(DEFAULT_ENERGIE_EAU_CHAUDE)))
            .andExpect(jsonPath("$.[*].energiePhotovoltaique").value(hasItem(DEFAULT_ENERGIE_PHOTOVOLTAIQUE.intValue())))
            .andExpect(jsonPath("$.[*].dateDPE").value(hasItem(DEFAULT_DATE_DPE.toString())))
            .andExpect(jsonPath("$.[*].classeConsoEnergie").value(hasItem(DEFAULT_CLASSE_CONSO_ENERGIE)))
            .andExpect(jsonPath("$.[*].dpeJsonContentType").value(hasItem(DEFAULT_DPE_JSON_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].dpeJson").value(hasItem(Base64Utils.encodeToString(DEFAULT_DPE_JSON))));
    }

    @Test
    @Transactional
    public void getInfoDPE() throws Exception {
        // Initialize the database
        infoDPERepository.saveAndFlush(infoDPE);

        // Get the infoDPE
        restInfoDPEMockMvc
            .perform(get("/api/info-dpes/{id}", infoDPE.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(infoDPE.getId().intValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.typeBatiment").value(DEFAULT_TYPE_BATIMENT))
            .andExpect(jsonPath("$.anneeConstruction").value(DEFAULT_ANNEE_CONSTRUCTION.intValue()))
            .andExpect(jsonPath("$.surfaceHabitable").value(DEFAULT_SURFACE_HABITABLE.intValue()))
            .andExpect(jsonPath("$.energieChauffage").value(DEFAULT_ENERGIE_CHAUFFAGE))
            .andExpect(jsonPath("$.energieEauChaude").value(DEFAULT_ENERGIE_EAU_CHAUDE))
            .andExpect(jsonPath("$.energiePhotovoltaique").value(DEFAULT_ENERGIE_PHOTOVOLTAIQUE.intValue()))
            .andExpect(jsonPath("$.dateDPE").value(DEFAULT_DATE_DPE.toString()))
            .andExpect(jsonPath("$.classeConsoEnergie").value(DEFAULT_CLASSE_CONSO_ENERGIE))
            .andExpect(jsonPath("$.dpeJsonContentType").value(DEFAULT_DPE_JSON_CONTENT_TYPE))
            .andExpect(jsonPath("$.dpeJson").value(Base64Utils.encodeToString(DEFAULT_DPE_JSON)));
    }

    @Test
    @Transactional
    public void getNonExistingInfoDPE() throws Exception {
        // Get the infoDPE
        restInfoDPEMockMvc.perform(get("/api/info-dpes/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInfoDPE() throws Exception {
        // Initialize the database
        infoDPERepository.saveAndFlush(infoDPE);

        int databaseSizeBeforeUpdate = infoDPERepository.findAll().size();

        // Update the infoDPE
        InfoDPE updatedInfoDPE = infoDPERepository.findById(infoDPE.getId()).get();
        // Disconnect from session so that the updates on updatedInfoDPE are not directly saved in db
        em.detach(updatedInfoDPE);
        updatedInfoDPE
            .numero(UPDATED_NUMERO)
            .adresse(UPDATED_ADRESSE)
            .typeBatiment(UPDATED_TYPE_BATIMENT)
            .anneeConstruction(UPDATED_ANNEE_CONSTRUCTION)
            .surfaceHabitable(UPDATED_SURFACE_HABITABLE)
            .energieChauffage(UPDATED_ENERGIE_CHAUFFAGE)
            .energieEauChaude(UPDATED_ENERGIE_EAU_CHAUDE)
            .energiePhotovoltaique(UPDATED_ENERGIE_PHOTOVOLTAIQUE)
            .dateDPE(UPDATED_DATE_DPE)
            .classeConsoEnergie(UPDATED_CLASSE_CONSO_ENERGIE)
            .dpeJson(UPDATED_DPE_JSON)
            .dpeJsonContentType(UPDATED_DPE_JSON_CONTENT_TYPE);

        restInfoDPEMockMvc
            .perform(
                put("/api/info-dpes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(updatedInfoDPE))
            )
            .andExpect(status().isOk());

        // Validate the InfoDPE in the database
        List<InfoDPE> infoDPEList = infoDPERepository.findAll();
        assertThat(infoDPEList).hasSize(databaseSizeBeforeUpdate);
        InfoDPE testInfoDPE = infoDPEList.get(infoDPEList.size() - 1);
        assertThat(testInfoDPE.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testInfoDPE.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testInfoDPE.getTypeBatiment()).isEqualTo(UPDATED_TYPE_BATIMENT);
        assertThat(testInfoDPE.getAnneeConstruction()).isEqualTo(UPDATED_ANNEE_CONSTRUCTION);
        assertThat(testInfoDPE.getSurfaceHabitable()).isEqualTo(UPDATED_SURFACE_HABITABLE);
        assertThat(testInfoDPE.getEnergieChauffage()).isEqualTo(UPDATED_ENERGIE_CHAUFFAGE);
        assertThat(testInfoDPE.getEnergieEauChaude()).isEqualTo(UPDATED_ENERGIE_EAU_CHAUDE);
        assertThat(testInfoDPE.getEnergiePhotovoltaique()).isEqualTo(UPDATED_ENERGIE_PHOTOVOLTAIQUE);
        assertThat(testInfoDPE.getDateDPE()).isEqualTo(UPDATED_DATE_DPE);
        assertThat(testInfoDPE.getClasseConsoEnergie()).isEqualTo(UPDATED_CLASSE_CONSO_ENERGIE);
        assertThat(testInfoDPE.getDpeJson()).isEqualTo(UPDATED_DPE_JSON);
        assertThat(testInfoDPE.getDpeJsonContentType()).isEqualTo(UPDATED_DPE_JSON_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingInfoDPE() throws Exception {
        int databaseSizeBeforeUpdate = infoDPERepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInfoDPEMockMvc
            .perform(put("/api/info-dpes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(infoDPE)))
            .andExpect(status().isBadRequest());

        // Validate the InfoDPE in the database
        List<InfoDPE> infoDPEList = infoDPERepository.findAll();
        assertThat(infoDPEList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInfoDPE() throws Exception {
        // Initialize the database
        infoDPERepository.saveAndFlush(infoDPE);

        int databaseSizeBeforeDelete = infoDPERepository.findAll().size();

        // Delete the infoDPE
        restInfoDPEMockMvc
            .perform(delete("/api/info-dpes/{id}", infoDPE.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InfoDPE> infoDPEList = infoDPERepository.findAll();
        assertThat(infoDPEList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

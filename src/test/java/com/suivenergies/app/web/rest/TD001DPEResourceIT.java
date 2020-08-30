package com.suivenergies.app.web.rest;

import com.suivenergies.app.SuivEnergiesApp;
import com.suivenergies.app.domain.TD001DPE;
import com.suivenergies.app.repository.TD001DPERepository;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TD001DPEResource} REST controller.
 */
@SpringBootTest(classes = SuivEnergiesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TD001DPEResourceIT {

    private static final String DEFAULT_NUMERO_DPE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_DPE = "BBBBBBBBBB";

    private static final String DEFAULT_TR_001_MODELE_DPE = "AAAAAAAAAA";
    private static final String UPDATED_TR_001_MODELE_DPE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_METHODE_DPE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_METHODE_DPE = "BBBBBBBBBB";

    private static final Double DEFAULT_CONSOMMATION_ENERGIE = 1D;
    private static final Double UPDATED_CONSOMMATION_ENERGIE = 2D;

    private static final String DEFAULT_CLASSE_CONSOMMATION_ENERGIE = "AAAAAAAAAA";
    private static final String UPDATED_CLASSE_CONSOMMATION_ENERGIE = "BBBBBBBBBB";

    private static final Double DEFAULT_ESTIMATION_GES = 1D;
    private static final Double UPDATED_ESTIMATION_GES = 2D;

    private static final String DEFAULT_CLASSE_ESTIMATION_GES = "AAAAAAAAAA";
    private static final String UPDATED_CLASSE_ESTIMATION_GES = "BBBBBBBBBB";

    private static final String DEFAULT_TR_002_TYPE_BATIMENT = "AAAAAAAAAA";
    private static final String UPDATED_TR_002_TYPE_BATIMENT = "BBBBBBBBBB";

    private static final Long DEFAULT_ANNEE_CONSTRUCTION = 1L;
    private static final Long UPDATED_ANNEE_CONSTRUCTION = 2L;

    private static final Double DEFAULT_SURFACE_HABITABLE = 1D;
    private static final Double UPDATED_SURFACE_HABITABLE = 2D;

    private static final String DEFAULT_TV_016_DEPARTEMENT = "AAAAAAAAAA";
    private static final String UPDATED_TV_016_DEPARTEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_COMMUNE = "AAAAAAAAAA";
    private static final String UPDATED_COMMUNE = "BBBBBBBBBB";

    private static final String DEFAULT_ARRONDISSEMENT = "AAAAAAAAAA";
    private static final String UPDATED_ARRONDISSEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_VOIE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_VOIE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_RUE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_RUE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_RUE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_RUE = "BBBBBBBBBB";

    private static final String DEFAULT_BATIMENT = "AAAAAAAAAA";
    private static final String UPDATED_BATIMENT = "BBBBBBBBBB";

    private static final String DEFAULT_ESCALIER = "AAAAAAAAAA";
    private static final String UPDATED_ESCALIER = "BBBBBBBBBB";

    private static final String DEFAULT_ETAGE = "AAAAAAAAAA";
    private static final String UPDATED_ETAGE = "BBBBBBBBBB";

    private static final String DEFAULT_PORTE = "AAAAAAAAAA";
    private static final String UPDATED_PORTE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_POSTAL = "AAAAAAAAAA";
    private static final String UPDATED_CODE_POSTAL = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_INSEE_COMMUNE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_INSEE_COMMUNE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_INSEE_COMMUNE_ACTUALISE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_INSEE_COMMUNE_ACTUALISE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_INSEE_COMMUNE_CORRIGE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_INSEE_COMMUNE_CORRIGE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_LOT = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_LOT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_RECEPTION_DPE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RECEPTION_DPE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private TD001DPERepository tD001DPERepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTD001DPEMockMvc;

    private TD001DPE tD001DPE;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD001DPE createEntity(EntityManager em) {
        TD001DPE tD001DPE = new TD001DPE()
            .numeroDpe(DEFAULT_NUMERO_DPE)
            .tr001ModeleDpe(DEFAULT_TR_001_MODELE_DPE)
            .nomMethodeDpe(DEFAULT_NOM_METHODE_DPE)
            .consommationEnergie(DEFAULT_CONSOMMATION_ENERGIE)
            .classeConsommationEnergie(DEFAULT_CLASSE_CONSOMMATION_ENERGIE)
            .estimationGes(DEFAULT_ESTIMATION_GES)
            .classeEstimationGes(DEFAULT_CLASSE_ESTIMATION_GES)
            .tr002TypeBatiment(DEFAULT_TR_002_TYPE_BATIMENT)
            .anneeConstruction(DEFAULT_ANNEE_CONSTRUCTION)
            .surfaceHabitable(DEFAULT_SURFACE_HABITABLE)
            .tv016Departement(DEFAULT_TV_016_DEPARTEMENT)
            .commune(DEFAULT_COMMUNE)
            .arrondissement(DEFAULT_ARRONDISSEMENT)
            .typeVoie(DEFAULT_TYPE_VOIE)
            .nomRue(DEFAULT_NOM_RUE)
            .numeroRue(DEFAULT_NUMERO_RUE)
            .batiment(DEFAULT_BATIMENT)
            .escalier(DEFAULT_ESCALIER)
            .etage(DEFAULT_ETAGE)
            .porte(DEFAULT_PORTE)
            .codePostal(DEFAULT_CODE_POSTAL)
            .codeInseeCommune(DEFAULT_CODE_INSEE_COMMUNE)
            .codeInseeCommuneActualise(DEFAULT_CODE_INSEE_COMMUNE_ACTUALISE)
            .codeInseeCommuneCorrige(DEFAULT_CODE_INSEE_COMMUNE_CORRIGE)
            .numeroLot(DEFAULT_NUMERO_LOT)
            .dateReceptionDpe(DEFAULT_DATE_RECEPTION_DPE);
        return tD001DPE;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TD001DPE createUpdatedEntity(EntityManager em) {
        TD001DPE tD001DPE = new TD001DPE()
            .numeroDpe(UPDATED_NUMERO_DPE)
            .tr001ModeleDpe(UPDATED_TR_001_MODELE_DPE)
            .nomMethodeDpe(UPDATED_NOM_METHODE_DPE)
            .consommationEnergie(UPDATED_CONSOMMATION_ENERGIE)
            .classeConsommationEnergie(UPDATED_CLASSE_CONSOMMATION_ENERGIE)
            .estimationGes(UPDATED_ESTIMATION_GES)
            .classeEstimationGes(UPDATED_CLASSE_ESTIMATION_GES)
            .tr002TypeBatiment(UPDATED_TR_002_TYPE_BATIMENT)
            .anneeConstruction(UPDATED_ANNEE_CONSTRUCTION)
            .surfaceHabitable(UPDATED_SURFACE_HABITABLE)
            .tv016Departement(UPDATED_TV_016_DEPARTEMENT)
            .commune(UPDATED_COMMUNE)
            .arrondissement(UPDATED_ARRONDISSEMENT)
            .typeVoie(UPDATED_TYPE_VOIE)
            .nomRue(UPDATED_NOM_RUE)
            .numeroRue(UPDATED_NUMERO_RUE)
            .batiment(UPDATED_BATIMENT)
            .escalier(UPDATED_ESCALIER)
            .etage(UPDATED_ETAGE)
            .porte(UPDATED_PORTE)
            .codePostal(UPDATED_CODE_POSTAL)
            .codeInseeCommune(UPDATED_CODE_INSEE_COMMUNE)
            .codeInseeCommuneActualise(UPDATED_CODE_INSEE_COMMUNE_ACTUALISE)
            .codeInseeCommuneCorrige(UPDATED_CODE_INSEE_COMMUNE_CORRIGE)
            .numeroLot(UPDATED_NUMERO_LOT)
            .dateReceptionDpe(UPDATED_DATE_RECEPTION_DPE);
        return tD001DPE;
    }

    @BeforeEach
    public void initTest() {
        tD001DPE = createEntity(em);
    }

    @Test
    @Transactional
    public void createTD001DPE() throws Exception {
        int databaseSizeBeforeCreate = tD001DPERepository.findAll().size();
        // Create the TD001DPE
        restTD001DPEMockMvc.perform(post("/api/td-001-dpes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD001DPE)))
            .andExpect(status().isCreated());

        // Validate the TD001DPE in the database
        List<TD001DPE> tD001DPEList = tD001DPERepository.findAll();
        assertThat(tD001DPEList).hasSize(databaseSizeBeforeCreate + 1);
        TD001DPE testTD001DPE = tD001DPEList.get(tD001DPEList.size() - 1);
        assertThat(testTD001DPE.getNumeroDpe()).isEqualTo(DEFAULT_NUMERO_DPE);
        assertThat(testTD001DPE.getTr001ModeleDpe()).isEqualTo(DEFAULT_TR_001_MODELE_DPE);
        assertThat(testTD001DPE.getNomMethodeDpe()).isEqualTo(DEFAULT_NOM_METHODE_DPE);
        assertThat(testTD001DPE.getConsommationEnergie()).isEqualTo(DEFAULT_CONSOMMATION_ENERGIE);
        assertThat(testTD001DPE.getClasseConsommationEnergie()).isEqualTo(DEFAULT_CLASSE_CONSOMMATION_ENERGIE);
        assertThat(testTD001DPE.getEstimationGes()).isEqualTo(DEFAULT_ESTIMATION_GES);
        assertThat(testTD001DPE.getClasseEstimationGes()).isEqualTo(DEFAULT_CLASSE_ESTIMATION_GES);
        assertThat(testTD001DPE.getTr002TypeBatiment()).isEqualTo(DEFAULT_TR_002_TYPE_BATIMENT);
        assertThat(testTD001DPE.getAnneeConstruction()).isEqualTo(DEFAULT_ANNEE_CONSTRUCTION);
        assertThat(testTD001DPE.getSurfaceHabitable()).isEqualTo(DEFAULT_SURFACE_HABITABLE);
        assertThat(testTD001DPE.getTv016Departement()).isEqualTo(DEFAULT_TV_016_DEPARTEMENT);
        assertThat(testTD001DPE.getCommune()).isEqualTo(DEFAULT_COMMUNE);
        assertThat(testTD001DPE.getArrondissement()).isEqualTo(DEFAULT_ARRONDISSEMENT);
        assertThat(testTD001DPE.getTypeVoie()).isEqualTo(DEFAULT_TYPE_VOIE);
        assertThat(testTD001DPE.getNomRue()).isEqualTo(DEFAULT_NOM_RUE);
        assertThat(testTD001DPE.getNumeroRue()).isEqualTo(DEFAULT_NUMERO_RUE);
        assertThat(testTD001DPE.getBatiment()).isEqualTo(DEFAULT_BATIMENT);
        assertThat(testTD001DPE.getEscalier()).isEqualTo(DEFAULT_ESCALIER);
        assertThat(testTD001DPE.getEtage()).isEqualTo(DEFAULT_ETAGE);
        assertThat(testTD001DPE.getPorte()).isEqualTo(DEFAULT_PORTE);
        assertThat(testTD001DPE.getCodePostal()).isEqualTo(DEFAULT_CODE_POSTAL);
        assertThat(testTD001DPE.getCodeInseeCommune()).isEqualTo(DEFAULT_CODE_INSEE_COMMUNE);
        assertThat(testTD001DPE.getCodeInseeCommuneActualise()).isEqualTo(DEFAULT_CODE_INSEE_COMMUNE_ACTUALISE);
        assertThat(testTD001DPE.getCodeInseeCommuneCorrige()).isEqualTo(DEFAULT_CODE_INSEE_COMMUNE_CORRIGE);
        assertThat(testTD001DPE.getNumeroLot()).isEqualTo(DEFAULT_NUMERO_LOT);
        assertThat(testTD001DPE.getDateReceptionDpe()).isEqualTo(DEFAULT_DATE_RECEPTION_DPE);
    }

    @Test
    @Transactional
    public void createTD001DPEWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tD001DPERepository.findAll().size();

        // Create the TD001DPE with an existing ID
        tD001DPE.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTD001DPEMockMvc.perform(post("/api/td-001-dpes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD001DPE)))
            .andExpect(status().isBadRequest());

        // Validate the TD001DPE in the database
        List<TD001DPE> tD001DPEList = tD001DPERepository.findAll();
        assertThat(tD001DPEList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTD001DPES() throws Exception {
        // Initialize the database
        tD001DPERepository.saveAndFlush(tD001DPE);

        // Get all the tD001DPEList
        restTD001DPEMockMvc.perform(get("/api/td-001-dpes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tD001DPE.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroDpe").value(hasItem(DEFAULT_NUMERO_DPE)))
            .andExpect(jsonPath("$.[*].tr001ModeleDpe").value(hasItem(DEFAULT_TR_001_MODELE_DPE)))
            .andExpect(jsonPath("$.[*].nomMethodeDpe").value(hasItem(DEFAULT_NOM_METHODE_DPE)))
            .andExpect(jsonPath("$.[*].consommationEnergie").value(hasItem(DEFAULT_CONSOMMATION_ENERGIE.doubleValue())))
            .andExpect(jsonPath("$.[*].classeConsommationEnergie").value(hasItem(DEFAULT_CLASSE_CONSOMMATION_ENERGIE)))
            .andExpect(jsonPath("$.[*].estimationGes").value(hasItem(DEFAULT_ESTIMATION_GES.doubleValue())))
            .andExpect(jsonPath("$.[*].classeEstimationGes").value(hasItem(DEFAULT_CLASSE_ESTIMATION_GES)))
            .andExpect(jsonPath("$.[*].tr002TypeBatiment").value(hasItem(DEFAULT_TR_002_TYPE_BATIMENT)))
            .andExpect(jsonPath("$.[*].anneeConstruction").value(hasItem(DEFAULT_ANNEE_CONSTRUCTION.intValue())))
            .andExpect(jsonPath("$.[*].surfaceHabitable").value(hasItem(DEFAULT_SURFACE_HABITABLE.doubleValue())))
            .andExpect(jsonPath("$.[*].tv016Departement").value(hasItem(DEFAULT_TV_016_DEPARTEMENT)))
            .andExpect(jsonPath("$.[*].commune").value(hasItem(DEFAULT_COMMUNE)))
            .andExpect(jsonPath("$.[*].arrondissement").value(hasItem(DEFAULT_ARRONDISSEMENT)))
            .andExpect(jsonPath("$.[*].typeVoie").value(hasItem(DEFAULT_TYPE_VOIE)))
            .andExpect(jsonPath("$.[*].nomRue").value(hasItem(DEFAULT_NOM_RUE)))
            .andExpect(jsonPath("$.[*].numeroRue").value(hasItem(DEFAULT_NUMERO_RUE)))
            .andExpect(jsonPath("$.[*].batiment").value(hasItem(DEFAULT_BATIMENT)))
            .andExpect(jsonPath("$.[*].escalier").value(hasItem(DEFAULT_ESCALIER)))
            .andExpect(jsonPath("$.[*].etage").value(hasItem(DEFAULT_ETAGE)))
            .andExpect(jsonPath("$.[*].porte").value(hasItem(DEFAULT_PORTE)))
            .andExpect(jsonPath("$.[*].codePostal").value(hasItem(DEFAULT_CODE_POSTAL)))
            .andExpect(jsonPath("$.[*].codeInseeCommune").value(hasItem(DEFAULT_CODE_INSEE_COMMUNE)))
            .andExpect(jsonPath("$.[*].codeInseeCommuneActualise").value(hasItem(DEFAULT_CODE_INSEE_COMMUNE_ACTUALISE)))
            .andExpect(jsonPath("$.[*].codeInseeCommuneCorrige").value(hasItem(DEFAULT_CODE_INSEE_COMMUNE_CORRIGE)))
            .andExpect(jsonPath("$.[*].numeroLot").value(hasItem(DEFAULT_NUMERO_LOT)))
            .andExpect(jsonPath("$.[*].dateReceptionDpe").value(hasItem(DEFAULT_DATE_RECEPTION_DPE.toString())));
    }
    
    @Test
    @Transactional
    public void getTD001DPE() throws Exception {
        // Initialize the database
        tD001DPERepository.saveAndFlush(tD001DPE);

        // Get the tD001DPE
        restTD001DPEMockMvc.perform(get("/api/td-001-dpes/{id}", tD001DPE.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tD001DPE.getId().intValue()))
            .andExpect(jsonPath("$.numeroDpe").value(DEFAULT_NUMERO_DPE))
            .andExpect(jsonPath("$.tr001ModeleDpe").value(DEFAULT_TR_001_MODELE_DPE))
            .andExpect(jsonPath("$.nomMethodeDpe").value(DEFAULT_NOM_METHODE_DPE))
            .andExpect(jsonPath("$.consommationEnergie").value(DEFAULT_CONSOMMATION_ENERGIE.doubleValue()))
            .andExpect(jsonPath("$.classeConsommationEnergie").value(DEFAULT_CLASSE_CONSOMMATION_ENERGIE))
            .andExpect(jsonPath("$.estimationGes").value(DEFAULT_ESTIMATION_GES.doubleValue()))
            .andExpect(jsonPath("$.classeEstimationGes").value(DEFAULT_CLASSE_ESTIMATION_GES))
            .andExpect(jsonPath("$.tr002TypeBatiment").value(DEFAULT_TR_002_TYPE_BATIMENT))
            .andExpect(jsonPath("$.anneeConstruction").value(DEFAULT_ANNEE_CONSTRUCTION.intValue()))
            .andExpect(jsonPath("$.surfaceHabitable").value(DEFAULT_SURFACE_HABITABLE.doubleValue()))
            .andExpect(jsonPath("$.tv016Departement").value(DEFAULT_TV_016_DEPARTEMENT))
            .andExpect(jsonPath("$.commune").value(DEFAULT_COMMUNE))
            .andExpect(jsonPath("$.arrondissement").value(DEFAULT_ARRONDISSEMENT))
            .andExpect(jsonPath("$.typeVoie").value(DEFAULT_TYPE_VOIE))
            .andExpect(jsonPath("$.nomRue").value(DEFAULT_NOM_RUE))
            .andExpect(jsonPath("$.numeroRue").value(DEFAULT_NUMERO_RUE))
            .andExpect(jsonPath("$.batiment").value(DEFAULT_BATIMENT))
            .andExpect(jsonPath("$.escalier").value(DEFAULT_ESCALIER))
            .andExpect(jsonPath("$.etage").value(DEFAULT_ETAGE))
            .andExpect(jsonPath("$.porte").value(DEFAULT_PORTE))
            .andExpect(jsonPath("$.codePostal").value(DEFAULT_CODE_POSTAL))
            .andExpect(jsonPath("$.codeInseeCommune").value(DEFAULT_CODE_INSEE_COMMUNE))
            .andExpect(jsonPath("$.codeInseeCommuneActualise").value(DEFAULT_CODE_INSEE_COMMUNE_ACTUALISE))
            .andExpect(jsonPath("$.codeInseeCommuneCorrige").value(DEFAULT_CODE_INSEE_COMMUNE_CORRIGE))
            .andExpect(jsonPath("$.numeroLot").value(DEFAULT_NUMERO_LOT))
            .andExpect(jsonPath("$.dateReceptionDpe").value(DEFAULT_DATE_RECEPTION_DPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTD001DPE() throws Exception {
        // Get the tD001DPE
        restTD001DPEMockMvc.perform(get("/api/td-001-dpes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTD001DPE() throws Exception {
        // Initialize the database
        tD001DPERepository.saveAndFlush(tD001DPE);

        int databaseSizeBeforeUpdate = tD001DPERepository.findAll().size();

        // Update the tD001DPE
        TD001DPE updatedTD001DPE = tD001DPERepository.findById(tD001DPE.getId()).get();
        // Disconnect from session so that the updates on updatedTD001DPE are not directly saved in db
        em.detach(updatedTD001DPE);
        updatedTD001DPE
            .numeroDpe(UPDATED_NUMERO_DPE)
            .tr001ModeleDpe(UPDATED_TR_001_MODELE_DPE)
            .nomMethodeDpe(UPDATED_NOM_METHODE_DPE)
            .consommationEnergie(UPDATED_CONSOMMATION_ENERGIE)
            .classeConsommationEnergie(UPDATED_CLASSE_CONSOMMATION_ENERGIE)
            .estimationGes(UPDATED_ESTIMATION_GES)
            .classeEstimationGes(UPDATED_CLASSE_ESTIMATION_GES)
            .tr002TypeBatiment(UPDATED_TR_002_TYPE_BATIMENT)
            .anneeConstruction(UPDATED_ANNEE_CONSTRUCTION)
            .surfaceHabitable(UPDATED_SURFACE_HABITABLE)
            .tv016Departement(UPDATED_TV_016_DEPARTEMENT)
            .commune(UPDATED_COMMUNE)
            .arrondissement(UPDATED_ARRONDISSEMENT)
            .typeVoie(UPDATED_TYPE_VOIE)
            .nomRue(UPDATED_NOM_RUE)
            .numeroRue(UPDATED_NUMERO_RUE)
            .batiment(UPDATED_BATIMENT)
            .escalier(UPDATED_ESCALIER)
            .etage(UPDATED_ETAGE)
            .porte(UPDATED_PORTE)
            .codePostal(UPDATED_CODE_POSTAL)
            .codeInseeCommune(UPDATED_CODE_INSEE_COMMUNE)
            .codeInseeCommuneActualise(UPDATED_CODE_INSEE_COMMUNE_ACTUALISE)
            .codeInseeCommuneCorrige(UPDATED_CODE_INSEE_COMMUNE_CORRIGE)
            .numeroLot(UPDATED_NUMERO_LOT)
            .dateReceptionDpe(UPDATED_DATE_RECEPTION_DPE);

        restTD001DPEMockMvc.perform(put("/api/td-001-dpes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTD001DPE)))
            .andExpect(status().isOk());

        // Validate the TD001DPE in the database
        List<TD001DPE> tD001DPEList = tD001DPERepository.findAll();
        assertThat(tD001DPEList).hasSize(databaseSizeBeforeUpdate);
        TD001DPE testTD001DPE = tD001DPEList.get(tD001DPEList.size() - 1);
        assertThat(testTD001DPE.getNumeroDpe()).isEqualTo(UPDATED_NUMERO_DPE);
        assertThat(testTD001DPE.getTr001ModeleDpe()).isEqualTo(UPDATED_TR_001_MODELE_DPE);
        assertThat(testTD001DPE.getNomMethodeDpe()).isEqualTo(UPDATED_NOM_METHODE_DPE);
        assertThat(testTD001DPE.getConsommationEnergie()).isEqualTo(UPDATED_CONSOMMATION_ENERGIE);
        assertThat(testTD001DPE.getClasseConsommationEnergie()).isEqualTo(UPDATED_CLASSE_CONSOMMATION_ENERGIE);
        assertThat(testTD001DPE.getEstimationGes()).isEqualTo(UPDATED_ESTIMATION_GES);
        assertThat(testTD001DPE.getClasseEstimationGes()).isEqualTo(UPDATED_CLASSE_ESTIMATION_GES);
        assertThat(testTD001DPE.getTr002TypeBatiment()).isEqualTo(UPDATED_TR_002_TYPE_BATIMENT);
        assertThat(testTD001DPE.getAnneeConstruction()).isEqualTo(UPDATED_ANNEE_CONSTRUCTION);
        assertThat(testTD001DPE.getSurfaceHabitable()).isEqualTo(UPDATED_SURFACE_HABITABLE);
        assertThat(testTD001DPE.getTv016Departement()).isEqualTo(UPDATED_TV_016_DEPARTEMENT);
        assertThat(testTD001DPE.getCommune()).isEqualTo(UPDATED_COMMUNE);
        assertThat(testTD001DPE.getArrondissement()).isEqualTo(UPDATED_ARRONDISSEMENT);
        assertThat(testTD001DPE.getTypeVoie()).isEqualTo(UPDATED_TYPE_VOIE);
        assertThat(testTD001DPE.getNomRue()).isEqualTo(UPDATED_NOM_RUE);
        assertThat(testTD001DPE.getNumeroRue()).isEqualTo(UPDATED_NUMERO_RUE);
        assertThat(testTD001DPE.getBatiment()).isEqualTo(UPDATED_BATIMENT);
        assertThat(testTD001DPE.getEscalier()).isEqualTo(UPDATED_ESCALIER);
        assertThat(testTD001DPE.getEtage()).isEqualTo(UPDATED_ETAGE);
        assertThat(testTD001DPE.getPorte()).isEqualTo(UPDATED_PORTE);
        assertThat(testTD001DPE.getCodePostal()).isEqualTo(UPDATED_CODE_POSTAL);
        assertThat(testTD001DPE.getCodeInseeCommune()).isEqualTo(UPDATED_CODE_INSEE_COMMUNE);
        assertThat(testTD001DPE.getCodeInseeCommuneActualise()).isEqualTo(UPDATED_CODE_INSEE_COMMUNE_ACTUALISE);
        assertThat(testTD001DPE.getCodeInseeCommuneCorrige()).isEqualTo(UPDATED_CODE_INSEE_COMMUNE_CORRIGE);
        assertThat(testTD001DPE.getNumeroLot()).isEqualTo(UPDATED_NUMERO_LOT);
        assertThat(testTD001DPE.getDateReceptionDpe()).isEqualTo(UPDATED_DATE_RECEPTION_DPE);
    }

    @Test
    @Transactional
    public void updateNonExistingTD001DPE() throws Exception {
        int databaseSizeBeforeUpdate = tD001DPERepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTD001DPEMockMvc.perform(put("/api/td-001-dpes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tD001DPE)))
            .andExpect(status().isBadRequest());

        // Validate the TD001DPE in the database
        List<TD001DPE> tD001DPEList = tD001DPERepository.findAll();
        assertThat(tD001DPEList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTD001DPE() throws Exception {
        // Initialize the database
        tD001DPERepository.saveAndFlush(tD001DPE);

        int databaseSizeBeforeDelete = tD001DPERepository.findAll().size();

        // Delete the tD001DPE
        restTD001DPEMockMvc.perform(delete("/api/td-001-dpes/{id}", tD001DPE.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TD001DPE> tD001DPEList = tD001DPERepository.findAll();
        assertThat(tD001DPEList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.suivenergies.app.web.rest;

import com.suivenergies.app.SuivEnergiesApp;
import com.suivenergies.app.domain.Confort;
import com.suivenergies.app.repository.ConfortRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ConfortResource} REST controller.
 */
@SpringBootTest(classes = SuivEnergiesApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class ConfortResourceIT {

    private static final Boolean DEFAULT_INSTALLATION_ELECTRIQUE = false;
    private static final Boolean UPDATED_INSTALLATION_ELECTRIQUE = true;

    private static final Boolean DEFAULT_INSTALLATION_GAZ = false;
    private static final Boolean UPDATED_INSTALLATION_GAZ = true;

    private static final Boolean DEFAULT_INSTALLATION_HUMIDITE = false;
    private static final Boolean UPDATED_INSTALLATION_HUMIDITE = true;

    private static final Boolean DEFAULT_INSTALLATION_PORTES_FENETRES = false;
    private static final Boolean UPDATED_INSTALLATION_PORTES_FENETRES = true;

    private static final Boolean DEFAULT_CHAUFFAGE_HIVER = false;
    private static final Boolean UPDATED_CHAUFFAGE_HIVER = true;

    private static final Double DEFAULT_SURFACE_CHAUFFEE = 1D;
    private static final Double UPDATED_SURFACE_CHAUFFEE = 2D;

    private static final Double DEFAULT_TEMPERATURE_HIVER_SEJOUR = 1D;
    private static final Double UPDATED_TEMPERATURE_HIVER_SEJOUR = 2D;

    private static final Double DEFAULT_TEMPERATURE_HIVER_CHAMBRES = 1D;
    private static final Double UPDATED_TEMPERATURE_HIVER_CHAMBRES = 2D;

    private static final Boolean DEFAULT_CLIM_ETE = false;
    private static final Boolean UPDATED_CLIM_ETE = true;

    private static final Double DEFAULT_TEMPERATURE_ETE_SEJOUR = 1D;
    private static final Double UPDATED_TEMPERATURE_ETE_SEJOUR = 2D;

    private static final Double DEFAULT_TEMPERATURE_ETE_CHAMBRES = 1D;
    private static final Double UPDATED_TEMPERATURE_ETE_CHAMBRES = 2D;

    @Autowired
    private ConfortRepository confortRepository;

    @Mock
    private ConfortRepository confortRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restConfortMockMvc;

    private Confort confort;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Confort createEntity(EntityManager em) {
        Confort confort = new Confort()
            .installationElectrique(DEFAULT_INSTALLATION_ELECTRIQUE)
            .installationGaz(DEFAULT_INSTALLATION_GAZ)
            .installationHumidite(DEFAULT_INSTALLATION_HUMIDITE)
            .installationPortesFenetres(DEFAULT_INSTALLATION_PORTES_FENETRES)
            .chauffageHiver(DEFAULT_CHAUFFAGE_HIVER)
            .surfaceChauffee(DEFAULT_SURFACE_CHAUFFEE)
            .temperatureHiverSejour(DEFAULT_TEMPERATURE_HIVER_SEJOUR)
            .temperatureHiverChambres(DEFAULT_TEMPERATURE_HIVER_CHAMBRES)
            .climEte(DEFAULT_CLIM_ETE)
            .temperatureEteSejour(DEFAULT_TEMPERATURE_ETE_SEJOUR)
            .temperatureEteChambres(DEFAULT_TEMPERATURE_ETE_CHAMBRES);
        return confort;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Confort createUpdatedEntity(EntityManager em) {
        Confort confort = new Confort()
            .installationElectrique(UPDATED_INSTALLATION_ELECTRIQUE)
            .installationGaz(UPDATED_INSTALLATION_GAZ)
            .installationHumidite(UPDATED_INSTALLATION_HUMIDITE)
            .installationPortesFenetres(UPDATED_INSTALLATION_PORTES_FENETRES)
            .chauffageHiver(UPDATED_CHAUFFAGE_HIVER)
            .surfaceChauffee(UPDATED_SURFACE_CHAUFFEE)
            .temperatureHiverSejour(UPDATED_TEMPERATURE_HIVER_SEJOUR)
            .temperatureHiverChambres(UPDATED_TEMPERATURE_HIVER_CHAMBRES)
            .climEte(UPDATED_CLIM_ETE)
            .temperatureEteSejour(UPDATED_TEMPERATURE_ETE_SEJOUR)
            .temperatureEteChambres(UPDATED_TEMPERATURE_ETE_CHAMBRES);
        return confort;
    }

    @BeforeEach
    public void initTest() {
        confort = createEntity(em);
    }

    @Test
    @Transactional
    public void createConfort() throws Exception {
        int databaseSizeBeforeCreate = confortRepository.findAll().size();
        // Create the Confort
        restConfortMockMvc.perform(post("/api/conforts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(confort)))
            .andExpect(status().isCreated());

        // Validate the Confort in the database
        List<Confort> confortList = confortRepository.findAll();
        assertThat(confortList).hasSize(databaseSizeBeforeCreate + 1);
        Confort testConfort = confortList.get(confortList.size() - 1);
        assertThat(testConfort.isInstallationElectrique()).isEqualTo(DEFAULT_INSTALLATION_ELECTRIQUE);
        assertThat(testConfort.isInstallationGaz()).isEqualTo(DEFAULT_INSTALLATION_GAZ);
        assertThat(testConfort.isInstallationHumidite()).isEqualTo(DEFAULT_INSTALLATION_HUMIDITE);
        assertThat(testConfort.isInstallationPortesFenetres()).isEqualTo(DEFAULT_INSTALLATION_PORTES_FENETRES);
        assertThat(testConfort.isChauffageHiver()).isEqualTo(DEFAULT_CHAUFFAGE_HIVER);
        assertThat(testConfort.getSurfaceChauffee()).isEqualTo(DEFAULT_SURFACE_CHAUFFEE);
        assertThat(testConfort.getTemperatureHiverSejour()).isEqualTo(DEFAULT_TEMPERATURE_HIVER_SEJOUR);
        assertThat(testConfort.getTemperatureHiverChambres()).isEqualTo(DEFAULT_TEMPERATURE_HIVER_CHAMBRES);
        assertThat(testConfort.isClimEte()).isEqualTo(DEFAULT_CLIM_ETE);
        assertThat(testConfort.getTemperatureEteSejour()).isEqualTo(DEFAULT_TEMPERATURE_ETE_SEJOUR);
        assertThat(testConfort.getTemperatureEteChambres()).isEqualTo(DEFAULT_TEMPERATURE_ETE_CHAMBRES);
    }

    @Test
    @Transactional
    public void createConfortWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = confortRepository.findAll().size();

        // Create the Confort with an existing ID
        confort.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConfortMockMvc.perform(post("/api/conforts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(confort)))
            .andExpect(status().isBadRequest());

        // Validate the Confort in the database
        List<Confort> confortList = confortRepository.findAll();
        assertThat(confortList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllConforts() throws Exception {
        // Initialize the database
        confortRepository.saveAndFlush(confort);

        // Get all the confortList
        restConfortMockMvc.perform(get("/api/conforts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(confort.getId().intValue())))
            .andExpect(jsonPath("$.[*].installationElectrique").value(hasItem(DEFAULT_INSTALLATION_ELECTRIQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].installationGaz").value(hasItem(DEFAULT_INSTALLATION_GAZ.booleanValue())))
            .andExpect(jsonPath("$.[*].installationHumidite").value(hasItem(DEFAULT_INSTALLATION_HUMIDITE.booleanValue())))
            .andExpect(jsonPath("$.[*].installationPortesFenetres").value(hasItem(DEFAULT_INSTALLATION_PORTES_FENETRES.booleanValue())))
            .andExpect(jsonPath("$.[*].chauffageHiver").value(hasItem(DEFAULT_CHAUFFAGE_HIVER.booleanValue())))
            .andExpect(jsonPath("$.[*].surfaceChauffee").value(hasItem(DEFAULT_SURFACE_CHAUFFEE.doubleValue())))
            .andExpect(jsonPath("$.[*].temperatureHiverSejour").value(hasItem(DEFAULT_TEMPERATURE_HIVER_SEJOUR.doubleValue())))
            .andExpect(jsonPath("$.[*].temperatureHiverChambres").value(hasItem(DEFAULT_TEMPERATURE_HIVER_CHAMBRES.doubleValue())))
            .andExpect(jsonPath("$.[*].climEte").value(hasItem(DEFAULT_CLIM_ETE.booleanValue())))
            .andExpect(jsonPath("$.[*].temperatureEteSejour").value(hasItem(DEFAULT_TEMPERATURE_ETE_SEJOUR.doubleValue())))
            .andExpect(jsonPath("$.[*].temperatureEteChambres").value(hasItem(DEFAULT_TEMPERATURE_ETE_CHAMBRES.doubleValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllConfortsWithEagerRelationshipsIsEnabled() throws Exception {
        when(confortRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restConfortMockMvc.perform(get("/api/conforts?eagerload=true"))
            .andExpect(status().isOk());

        verify(confortRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllConfortsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(confortRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restConfortMockMvc.perform(get("/api/conforts?eagerload=true"))
            .andExpect(status().isOk());

        verify(confortRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getConfort() throws Exception {
        // Initialize the database
        confortRepository.saveAndFlush(confort);

        // Get the confort
        restConfortMockMvc.perform(get("/api/conforts/{id}", confort.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(confort.getId().intValue()))
            .andExpect(jsonPath("$.installationElectrique").value(DEFAULT_INSTALLATION_ELECTRIQUE.booleanValue()))
            .andExpect(jsonPath("$.installationGaz").value(DEFAULT_INSTALLATION_GAZ.booleanValue()))
            .andExpect(jsonPath("$.installationHumidite").value(DEFAULT_INSTALLATION_HUMIDITE.booleanValue()))
            .andExpect(jsonPath("$.installationPortesFenetres").value(DEFAULT_INSTALLATION_PORTES_FENETRES.booleanValue()))
            .andExpect(jsonPath("$.chauffageHiver").value(DEFAULT_CHAUFFAGE_HIVER.booleanValue()))
            .andExpect(jsonPath("$.surfaceChauffee").value(DEFAULT_SURFACE_CHAUFFEE.doubleValue()))
            .andExpect(jsonPath("$.temperatureHiverSejour").value(DEFAULT_TEMPERATURE_HIVER_SEJOUR.doubleValue()))
            .andExpect(jsonPath("$.temperatureHiverChambres").value(DEFAULT_TEMPERATURE_HIVER_CHAMBRES.doubleValue()))
            .andExpect(jsonPath("$.climEte").value(DEFAULT_CLIM_ETE.booleanValue()))
            .andExpect(jsonPath("$.temperatureEteSejour").value(DEFAULT_TEMPERATURE_ETE_SEJOUR.doubleValue()))
            .andExpect(jsonPath("$.temperatureEteChambres").value(DEFAULT_TEMPERATURE_ETE_CHAMBRES.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingConfort() throws Exception {
        // Get the confort
        restConfortMockMvc.perform(get("/api/conforts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConfort() throws Exception {
        // Initialize the database
        confortRepository.saveAndFlush(confort);

        int databaseSizeBeforeUpdate = confortRepository.findAll().size();

        // Update the confort
        Confort updatedConfort = confortRepository.findById(confort.getId()).get();
        // Disconnect from session so that the updates on updatedConfort are not directly saved in db
        em.detach(updatedConfort);
        updatedConfort
            .installationElectrique(UPDATED_INSTALLATION_ELECTRIQUE)
            .installationGaz(UPDATED_INSTALLATION_GAZ)
            .installationHumidite(UPDATED_INSTALLATION_HUMIDITE)
            .installationPortesFenetres(UPDATED_INSTALLATION_PORTES_FENETRES)
            .chauffageHiver(UPDATED_CHAUFFAGE_HIVER)
            .surfaceChauffee(UPDATED_SURFACE_CHAUFFEE)
            .temperatureHiverSejour(UPDATED_TEMPERATURE_HIVER_SEJOUR)
            .temperatureHiverChambres(UPDATED_TEMPERATURE_HIVER_CHAMBRES)
            .climEte(UPDATED_CLIM_ETE)
            .temperatureEteSejour(UPDATED_TEMPERATURE_ETE_SEJOUR)
            .temperatureEteChambres(UPDATED_TEMPERATURE_ETE_CHAMBRES);

        restConfortMockMvc.perform(put("/api/conforts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedConfort)))
            .andExpect(status().isOk());

        // Validate the Confort in the database
        List<Confort> confortList = confortRepository.findAll();
        assertThat(confortList).hasSize(databaseSizeBeforeUpdate);
        Confort testConfort = confortList.get(confortList.size() - 1);
        assertThat(testConfort.isInstallationElectrique()).isEqualTo(UPDATED_INSTALLATION_ELECTRIQUE);
        assertThat(testConfort.isInstallationGaz()).isEqualTo(UPDATED_INSTALLATION_GAZ);
        assertThat(testConfort.isInstallationHumidite()).isEqualTo(UPDATED_INSTALLATION_HUMIDITE);
        assertThat(testConfort.isInstallationPortesFenetres()).isEqualTo(UPDATED_INSTALLATION_PORTES_FENETRES);
        assertThat(testConfort.isChauffageHiver()).isEqualTo(UPDATED_CHAUFFAGE_HIVER);
        assertThat(testConfort.getSurfaceChauffee()).isEqualTo(UPDATED_SURFACE_CHAUFFEE);
        assertThat(testConfort.getTemperatureHiverSejour()).isEqualTo(UPDATED_TEMPERATURE_HIVER_SEJOUR);
        assertThat(testConfort.getTemperatureHiverChambres()).isEqualTo(UPDATED_TEMPERATURE_HIVER_CHAMBRES);
        assertThat(testConfort.isClimEte()).isEqualTo(UPDATED_CLIM_ETE);
        assertThat(testConfort.getTemperatureEteSejour()).isEqualTo(UPDATED_TEMPERATURE_ETE_SEJOUR);
        assertThat(testConfort.getTemperatureEteChambres()).isEqualTo(UPDATED_TEMPERATURE_ETE_CHAMBRES);
    }

    @Test
    @Transactional
    public void updateNonExistingConfort() throws Exception {
        int databaseSizeBeforeUpdate = confortRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConfortMockMvc.perform(put("/api/conforts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(confort)))
            .andExpect(status().isBadRequest());

        // Validate the Confort in the database
        List<Confort> confortList = confortRepository.findAll();
        assertThat(confortList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConfort() throws Exception {
        // Initialize the database
        confortRepository.saveAndFlush(confort);

        int databaseSizeBeforeDelete = confortRepository.findAll().size();

        // Delete the confort
        restConfortMockMvc.perform(delete("/api/conforts/{id}", confort.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Confort> confortList = confortRepository.findAll();
        assertThat(confortList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

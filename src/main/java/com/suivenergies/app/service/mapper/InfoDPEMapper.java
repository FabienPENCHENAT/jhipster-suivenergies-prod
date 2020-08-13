package com.suivenergies.app.service.mapper;

import com.suivenergies.app.domain.InfoDPE;
import com.suivenergies.app.service.dto.api.Dpe;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class InfoDPEMapper {

    public InfoDPE dpeToInfoDPE(Dpe dpe, String dpeJson) {
        InfoDPE infoDpe = new InfoDPE();
        infoDpe.setNumero(dpe.getId());
        infoDpe.setAdresse(dpe.getResultLabel());
        infoDpe.setTypeBatiment(dpe.getTr002TypeBatimentDescription());
        infoDpe.setAnneeConstruction(dpe.getAnneeConstruction());
        infoDpe.setSurfaceHabitable(dpe.getSurfaceThermiqueLot());
        //		infoDpe.setEnergieChauffage(energieChauffage);
        //		infoDpe.setEnergieEauChaude(energieEauChaude);
        //		infoDpe.setEnergiePhotovoltaique(dpe.get);
        infoDpe.setDateDPE(stringToLocalDate(dpe.getDateEtablissementDpe()));
        infoDpe.setClasseConsoEnergie(dpe.getClasseConsommationEnergie());
        //		infoDpe.setClient(dpe.getC);
        infoDpe.setDpeJson(dpeJson.getBytes());
        return infoDpe;
    }

    /**
     * Convert string to localDate.
     *
     * @param date
     * @return
     */
    private LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        return LocalDate.parse(date, formatter);
    }
}

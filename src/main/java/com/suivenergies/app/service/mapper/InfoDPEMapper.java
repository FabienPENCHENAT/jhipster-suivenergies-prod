package com.suivenergies.app.service.mapper;

import com.suivenergies.app.domain.InfoDPE;
import com.suivenergies.app.domain.TD001DPE;
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

    public InfoDPE dpeToInfoDPE(TD001DPE td001dpe) {
        InfoDPE infoDpe = new InfoDPE();
        infoDpe.setNumero(td001dpe.getNumeroDpe());
        StringBuilder adresse = new StringBuilder();
        adresse.append(td001dpe.getNumeroRue());
        adresse.append(" ");
        adresse.append(td001dpe.getTypeVoie());
        adresse.append(" ");
        adresse.append(td001dpe.getNomRue());
        adresse.append(" ");
        adresse.append(td001dpe.getCodePostal());
        adresse.append(" ");
        adresse.append(td001dpe.getCommune());
        infoDpe.setAdresse(adresse.toString());
        infoDpe.setTypeBatiment(td001dpe.getTr002TypeBatiment());
        infoDpe.setAnneeConstruction(td001dpe.getAnneeConstruction());
        infoDpe.setSurfaceHabitable(Math.round(td001dpe.getSurfaceHabitable()));
        infoDpe.setDateDPE(td001dpe.getDateReceptionDpe());
        infoDpe.setClasseConsoEnergie(td001dpe.getClasseConsommationEnergie());

        //		infoDpe.setEnergieChauffage(energieChauffage);
        //		infoDpe.setEnergieEauChaude(energieEauChaude);
        //		infoDpe.setEnergiePhotovoltaique(dpe.get);

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

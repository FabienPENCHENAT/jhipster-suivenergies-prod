package com.suivenergies.app.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suivenergies.app.domain.Client;
import com.suivenergies.app.domain.InfoDPE;
import com.suivenergies.app.repository.InfoDPERepository;
import com.suivenergies.app.service.dto.api.Dpe;
import com.suivenergies.app.service.dto.api.DpeApi;
import com.suivenergies.app.service.mapper.InfoDPEMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing Dpe.
 *
 * @author Fabien P.
 *
 */
@Service
@Transactional
public class InfoDPEService {
    private final Logger log = LoggerFactory.getLogger(InfoDPEService.class);

    private static final String API_DPE = "https://koumoul.com/s/data-fair/api/v1/datasets/dpe-france/lines";
    private static final String SEPARATOR = "?";
    private static final String NUMERO_DPE = "q=";

    private static ObjectMapper mapper = new ObjectMapper();

    private InfoDPEMapper infoDpeMapper;

    private final InfoDPERepository infoDPERepository;

    private final ClientService clientService;

    public InfoDPEService(InfoDPEMapper infoDpeMapper, InfoDPERepository infoDPERepository, ClientService clientService) {
        this.infoDpeMapper = infoDpeMapper;
        this.infoDPERepository = infoDPERepository;
        this.clientService = clientService;
    }

    /**
     * Download DPE by DPE Number and save in DB with user connected.
     *
     * @param numeroDPE
     */
    public void downlodAndSaveDPE(String numeroDPE) {
        downlodAndSaveDPE(numeroDPE, null);
    }

    /**
     * Download DPE by DPE Number and save in DB with new user.
     *
     * @param numeroDPE
     * @param client just created
     */
    public void downlodAndSaveDPE(String numeroDPE, Client client) {
        // call api get dpe info
        InfoDPE infoDpe = downloadDPE(numeroDPE);

        // Get client associated to the user connected
        if (client != null) {
            infoDpe.setClient(client);
        } else {
            infoDpe.setClient(clientService.getClientConnected());
        }

        // save info dpe in db
        infoDPERepository.save(infoDpe);
    }

    private InfoDPE downloadDPE(String numeroDPE) {
        String dpeJson = null;
        try {
            // Call api with number
            dpeJson = call(numeroDPE);
            //Map result api with InfoDPE
            return infoDpeMapper.dpeToInfoDPE(convertJsonToDpeApi(dpeJson), dpeJson);
        } catch (IOException e) {
            log.error("An error occurred during the call to the DPE API.", e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get data by callinf URL.
     *
     * @param urlString
     * @throws IOException@
     */
    private String call(String numeroDPE) throws IOException {
        URL url = new URL(API_DPE + SEPARATOR + NUMERO_DPE + numeroDPE);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        log.debug("CALL FOOTBALL API (GET) - " + API_DPE);

        int status = con.getResponseCode();
        log.debug("Status - " + status);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        return content.toString();
    }

    /**
     * Conversion Json to Dpe Object.
     *
     * @param depJson
     * @return
     */
    private Dpe convertJsonToDpeApi(String depJson) {
        DpeApi dpeApi = null;

        try {
            dpeApi = mapper.readValue(depJson, DpeApi.class);
        } catch (JsonParseException e) {
            log.error("An error occurred during JSON parsing", e.getMessage());
            e.printStackTrace();
        } catch (JsonMappingException e) {
            log.error("An error occurred during JSON mapping", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.error("An technical error occurred", e.getMessage());
            e.printStackTrace();
        }

        return dpeApi.getDpes().get(0);
    }
}

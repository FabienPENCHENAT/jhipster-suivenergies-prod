package com.suivenergies.app.service;

import com.suivenergies.app.domain.Client;
import com.suivenergies.app.domain.User;
import com.suivenergies.app.repository.ClientRepository;
import com.suivenergies.app.repository.UserRepository;
import com.suivenergies.app.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 *
 * @author Fabien P.
 *
 */
@Service
@Transactional
public class ClientService {
    private final Logger log = LoggerFactory.getLogger(ClientService.class);

    private final UserRepository userRepository;

    private final ClientRepository clientRepository;

    public ClientService(UserRepository userRepository, ClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }

    /**
     *
     * Get client connected by user connected by SecurityUtils.
     *
     * @return Client
     */
    public Client getClientConnected() {
        User userConnected = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get()).get();
        return clientRepository.findOneByUsersId(userConnected.getId()).get();
    }
}

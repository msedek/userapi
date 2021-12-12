package com.globallogic.userapi.services;

import com.globallogic.userapi.entities.User;
import com.globallogic.userapi.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion de repositorio para datos de contacto del usuario.
 */
@Service
@Transactional
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository userRepository;

    @Override
    public void createPhone(User.UserPhone phone) {
        userRepository.save(phone);
    }
}

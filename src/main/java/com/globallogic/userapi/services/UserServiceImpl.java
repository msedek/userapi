package com.globallogic.userapi.services;

import com.globallogic.userapi.entities.User;
import com.globallogic.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository  userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}

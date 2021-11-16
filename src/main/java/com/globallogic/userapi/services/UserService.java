package com.globallogic.userapi.services;

import com.globallogic.userapi.entities.User;
import com.globallogic.userapi.entities.UserPhone;

public interface UserService {
    User createUser(User user);
    void createPhone(UserPhone phone);
}

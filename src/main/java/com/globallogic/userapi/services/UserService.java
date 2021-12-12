package com.globallogic.userapi.services;

import com.globallogic.userapi.entities.User;

public interface UserService {
    /**
     * UserService
     * @param user usuario validado, activado y tokenizado.
     * @return user creado en la base de datos.
     */
    User createUser(User user);
}

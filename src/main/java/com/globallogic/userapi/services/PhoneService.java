package com.globallogic.userapi.services;

import com.globallogic.userapi.entities.User;

public interface PhoneService {

    /**
     * createPhone
     * Almacena datos de contacto del usuario
     * @param phone datos de contacto del usuario
     */
    void createPhone(User.UserPhone phone);
}

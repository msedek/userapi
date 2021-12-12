package com.globallogic.userapi.services;

import com.globallogic.userapi.customexceptions.AuditDataServiceException;
import com.globallogic.userapi.entities.Constants;
import com.globallogic.userapi.entities.User;
import com.globallogic.userapi.entities.UtilsMethodProvider;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserActivationServiceImp implements UserActivationService {

    protected static final Logger logger = LoggerFactory.getLogger(UserActivationServiceImp.class);

    /**
     * createRequest Logica de negocio
     * @param body request recibido via REST para ser procesado y validado.
     * @return user actaivado y tokenizazo o error en caso de no validar.
     */
    @Override
    public User createRequest(String body) throws AuditDataServiceException {

        if (body == null || body.isEmpty())
            throw new AuditDataServiceException(HttpStatus.BAD_REQUEST, Constants.NO_BODY);

        User user = new Gson().fromJson(body, User.class);
        logger.info("Request: {} ", user);

        if (user.getEmail() == null)
            throw new AuditDataServiceException(HttpStatus.BAD_REQUEST, Constants.INVALID_MAIL_NULL);

        if (!user.getEmail().matches(Constants.MAIL_REGEX))
            throw new AuditDataServiceException(HttpStatus.BAD_REQUEST, Constants.INVALID_MAIL);

        if (user.getPassword() == null)
            throw new AuditDataServiceException(HttpStatus.BAD_REQUEST, Constants.INVALID_PASSWORD_NULL);

        if (!user.getPassword().matches(Constants.PASSWORD_REGEX))
            throw new AuditDataServiceException(HttpStatus.BAD_REQUEST, Constants.INVALID_PASSWORD);

        user.setActive(Constants.ACTIVATE_USER);
        user.setToken(UtilsMethodProvider.tokenGeneration(user.getEmail()));

        return user;
    }
}

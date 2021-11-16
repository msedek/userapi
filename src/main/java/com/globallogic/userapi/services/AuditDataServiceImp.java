package com.globallogic.userapi.services;

import com.globallogic.userapi.CustoExceptions.AuditDataServiceException;
import com.globallogic.userapi.entities.Constants;
import com.globallogic.userapi.entities.User;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuditDataServiceImp implements AuditDataService {

    protected static final Logger logger = LoggerFactory.getLogger(AuditDataServiceImp.class);

    @Override
    public User auditRequest(String body) throws AuditDataServiceException {

        try {

            if (body == null || body.isEmpty())
                throw new AuditDataServiceException(HttpStatus.BAD_REQUEST, Constants.NO_BODY);

            User user = new Gson().fromJson(body, User.class);
            logger.info(user.toString());

            if (user.isNullField())
                throw new AuditDataServiceException(HttpStatus.BAD_REQUEST, Constants.MISSING_INFO);

            if (!user.getEmail().matches(Constants.MAIL_REGEX))
                throw new AuditDataServiceException(HttpStatus.BAD_REQUEST, Constants.INVALID_MAIL);

            if (!user.getPassword().matches(Constants.PASSWORD_REGEX))
                throw new AuditDataServiceException(HttpStatus.BAD_REQUEST, Constants.INVALID_PASSWORD);

            return user;

        } catch (Exception e) {
            logger.error(e.toString(), e);
            throw e;
        }
    }
}

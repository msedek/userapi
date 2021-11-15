package com.globallogic.userapi.services;

import com.globallogic.userapi.entities.AuditDataResponse;
import com.globallogic.userapi.entities.Constants;
import com.globallogic.userapi.entities.ErrorResponse;
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
    public AuditDataResponse auditRequest(String body) {

        try {

            if (body == null || body.isEmpty()) {
                logger.warn(Constants.NO_BODY);
                return new AuditDataResponse(HttpStatus.BAD_REQUEST, new Gson().toJson(new ErrorResponse(Constants.NO_BODY)), null);
            }

            User user = new Gson().fromJson(body, User.class);
            logger.info(user.toString());

            if (user.isNullField()) {
                logger.warn(Constants.MISSING_INFO);
                return new AuditDataResponse(HttpStatus.BAD_REQUEST, new Gson().toJson(new ErrorResponse(Constants.MISSING_INFO)), null);
            }

            if (!user.getEmail().matches(Constants.MAIL_REGEX)) {
                logger.warn(Constants.INVALID_MAIL);
                return new AuditDataResponse(HttpStatus.BAD_REQUEST, new Gson().toJson(new ErrorResponse(Constants.INVALID_MAIL)), null);
            }

            if (!user.getPassword().matches(Constants.PASSWORD_REGEX)) {
                logger.warn(Constants.INVALID_PASSWORD);
                return new AuditDataResponse(HttpStatus.BAD_REQUEST, new Gson().toJson(new ErrorResponse(Constants.INVALID_PASSWORD)), null);
            }

            return new AuditDataResponse(null, null, user);

        } catch (Exception e) {
            logger.error(e.toString(), e);

            return new AuditDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, new Gson().toJson(new ErrorResponse(e.toString())), null);
        }
    }
}

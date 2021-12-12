package com.globallogic.userapi.controllers;

import com.globallogic.userapi.customexceptions.AuditDataServiceException;
import com.globallogic.userapi.entities.Constants;
import com.globallogic.userapi.entities.CreateUserResponse;
import com.globallogic.userapi.entities.ErrorResponse;
import com.globallogic.userapi.entities.User;
import com.globallogic.userapi.services.PhoneService;
import com.globallogic.userapi.services.UserActivationService;
import com.globallogic.userapi.services.UserService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador creacion de usuario
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PhoneService phoneService;

    @Autowired
    private UserActivationService userActivationService;

    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * Endpoint para creacion de un nuevo usuario.
     * @param  httpEntity objeto de entrada servicio REST que contiene el body con el JSON del request.
     * @return ResponseEntity.
     */
    @PostMapping("/createuser")
    public ResponseEntity<String> createUser(HttpEntity<String> httpEntity) {

        try {

            User user = userService.createUser(userActivationService.createRequest(httpEntity.getBody()));

            if(user.getPhones() != null)
                user.getPhones().forEach(phoneService::createPhone);

            return responseBuilder(CreateUserResponse.builder()
                    .id(user.getId())
                    .created(user.getCreated())
                    .modified(user.getModified())
                    .lastLogin(user.getLastLogin())
                    .token(user.getToken())
                    .isActive(user.isActive())
                    .build());

        } catch (AuditDataServiceException e) {
            return responseBuilder(e.getStatus(), new ErrorResponse(e.getMessage()));
        }  catch(DataIntegrityViolationException e) {
            logger.error(e.toString(), e);

            if(e.toString().toUpperCase().contains(Constants.EMAIL_EXCEPTION_KEYWORD))
                return responseBuilder(HttpStatus.CONFLICT,new ErrorResponse(Constants.REGISTERED_EMAIL));

            return responseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(e.toString()));
        } catch (Exception e) {
            logger.error(e.toString(), e);
            return responseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(e.toString()));
        }
    }

    /**
     * Client response
     * @param  status status HTTP.
     * @param  body, JSON con la data.
     * @return http ResponseEntity para el cliente.
     */
    private ResponseEntity<String> responseBuilder(HttpStatus status, ErrorResponse body) {
        logger.info("Response: {}", body);
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new Gson().toJson(body));
    }

    /**
     * Client response
     * @param  body, response object.
     * @return http ResponseEntity para el cliente.
     */
    private ResponseEntity<String> responseBuilder(CreateUserResponse body) {
        logger.info("Response: {}", body);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new Gson().toJson(body));
    }
}

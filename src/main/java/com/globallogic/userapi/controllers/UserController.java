package com.globallogic.userapi.controllers;

import com.globallogic.userapi.CustoExceptions.AuditDataServiceException;
import com.globallogic.userapi.entities.*;
import com.globallogic.userapi.services.AuditDataService;
import com.globallogic.userapi.services.UserService;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuditDataService auditDataService;

    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/createuser")
    public ResponseEntity createUser(HttpEntity<String> httpEntity) {

        try {

            User user = auditDataService.auditRequest(httpEntity.getBody());

            user.setActive(true);
            user.setToken(Jwts
                    .builder()
                    .setSubject(user.getEmail())
                    .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                    .compact());

            User createdUser = userService.createUser(user);

            for (UserPhone phone: user.getPhones()) {
                logger.info(phone.toString());
                phone.setUser(user);
                userService.createPhone(phone);
            }

            return responseBuilder(HttpStatus.CREATED, new Gson().toJson(new CreateUserResponse(
                    createdUser.getId(),
                    createdUser.getCreated(),
                    createdUser.getModified(),
                    createdUser.getLastLogin(),
                    createdUser.getToken(),
                    createdUser.isActive())));

        } catch (AuditDataServiceException e) {
            return responseBuilder(e.getStatus(), new Gson().toJson(new ErrorResponse(e.getMessage())));
        }  catch(DataIntegrityViolationException e) {
            logger.error(e.toString(), e);

            if(e.toString().toUpperCase().contains(Constants.EMAIL_EXCEPTION_KEYWORD))
                return responseBuilder(HttpStatus.CONFLICT, new Gson().toJson(new ErrorResponse(Constants.REGISTERED_EMAIL)));

            return responseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, new Gson().toJson(new ErrorResponse(e.toString())));
        } catch (Exception e) {
            logger.error(e.toString(), e);

            return responseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, new Gson().toJson(new ErrorResponse(e.toString())));
        }
    }

    private ResponseEntity responseBuilder(HttpStatus status, String body) {
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }
}

package com.globallogic.userapi.controllers;

import com.globallogic.userapi.entities.*;
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

    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/createuser")
    public ResponseEntity createUser(HttpEntity<String> httpEntity) {

        try {

            String body = httpEntity.getBody();

            if (body == null || body.isEmpty()) {
                logger.warn(Constants.NO_BODY);
                return responseBuilder(HttpStatus.BAD_REQUEST, new Gson().toJson(new ErrorResponse(Constants.NO_BODY)));
            }

            User user = new Gson().fromJson(body, User.class);
            logger.info(user.toString());

            if(user.isNullField()) {
                logger.warn(Constants.MISSING_INFO);
                return responseBuilder(HttpStatus.BAD_REQUEST, new Gson().toJson(new ErrorResponse(Constants.MISSING_INFO)));
            }

            if(!user.getEmail().matches(Constants.MAIL_REGEX)) {
                logger.warn(Constants.INVALID_MAIL);
                return responseBuilder(HttpStatus.BAD_REQUEST, new Gson().toJson(new ErrorResponse(Constants.INVALID_MAIL)));
            }

            if(!user.getPassword().matches(Constants.PASSWORD_REGEX)) {
                logger.warn(Constants.INVALID_PASSWORD);
                return responseBuilder(HttpStatus.BAD_REQUEST, new Gson().toJson(new ErrorResponse(Constants.INVALID_PASSWORD)));
            }

            user.setActive(true);
            user.setToken(Jwts.builder().setSubject(user.getEmail()).signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256)).compact());

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

        } catch(DataIntegrityViolationException e) {
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

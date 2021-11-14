package com.globallogic.userapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globallogic.userapi.entities.User;
import com.globallogic.userapi.entities.UserPhone;
import com.globallogic.userapi.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerIntTest {

    protected static final Logger logger = LoggerFactory.getLogger(UserControllerIntTest.class);

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void nullBodyResponse() throws Exception {

        //The test is performed by not sending the body.
        RequestBuilder request = MockMvcRequestBuilders
                .post("/createuser")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult  result = mockMvc.perform(request).andReturn();
        assertEquals(400, result.getResponse().getStatus());
        logger.info(String.format("Response status nullBodyResponse %d Expected status %d", result.getResponse().getStatus(), 400));
    }

    @Test
    void badFieldNameResponse() throws Exception {

        //The test is performed by changing the field "name" to "surname" (fields "email" and "password" can also be changed to perform the test).
        String body = "{ \"surname\": \"Julio Gonzalez\", \"email\": \"julio@test.cl\", \"password\": \"K45g\", \"phones\": [ { \"number\": \"87650009\", \"citycode\": \"7\", \"contrycode\": \"25\" } ] }";

        RequestBuilder request = MockMvcRequestBuilders
                .post("/createuser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body);

        MvcResult  result = mockMvc.perform(request).andReturn();
        assertEquals(400, result.getResponse().getStatus());
        logger.info(String.format("Response status badFieldNameResponse %d Expected status %d", result.getResponse().getStatus(), 400));
    }

    @Test
    void badEmailResponse() throws Exception {

        //The test is performed by sending domain ".com" in the email and not ".cl" as per specifications.
        User user = new User("Miguel Sedek", "miguelsedek@gmail.com",
                "H33zxc", Arrays.asList(new UserPhone("1234567",
                "34","12")));

        RequestBuilder request = MockMvcRequestBuilders
                .post("/createuser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user));

        MvcResult  result = mockMvc.perform(request).andReturn();
        assertEquals(400, result.getResponse().getStatus());
        logger.info(String.format("Response status badEmailResponse %d Expected status %d", result.getResponse().getStatus(), 400));
    }

    @Test
    void badPasswordResponse() throws Exception {

        //The test is performed by sending an invalid password structure as per specifications (in this case not sending an uppercase letter)
        // it can also be tested by not sending any lowercase letter or either by sending no numbers or more than 2.
        User user = new User("Miguel Sedek", "miguelsedek@gmail.cl",
                "33zxc", Arrays.asList(new UserPhone("1234567",
                "34","12")));

        RequestBuilder request = MockMvcRequestBuilders
                .post("/createuser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user));

        MvcResult  result = mockMvc.perform(request).andReturn();
        assertEquals(400, result.getResponse().getStatus());
        logger.info(String.format("Response status badPasswordResponse %d Expected status %d", result.getResponse().getStatus(), 400));
    }
}
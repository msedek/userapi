package com.globallogic.userapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globallogic.userapi.entities.User;
import com.globallogic.userapi.services.PhoneService;
import com.globallogic.userapi.services.UserActivationService;
import com.globallogic.userapi.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
@ComponentScan("com.globallogic.userapi.services")
class UserControllerIntTest {

    protected static final Logger logger = LoggerFactory.getLogger(UserControllerIntTest.class);

    @MockBean
    private UserService userService;
    @MockBean
    private PhoneService phoneService;

    @Autowired
    private UserActivationService userActivationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * The test is performed by not sending the body.
     */
    @Test
    void nullBodyResponse() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .post("/createuser")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult  result = mockMvc.perform(request).andReturn();
        assertEquals(400, result.getResponse().getStatus());
        logger.info(String.format("Response status nullBodyResponse %d Expected status %d", result.getResponse().getStatus(), 400));
    }

    /**
     * The test is performed by not sending domain ".something".
     */
    @Test
    void badEmailResponse() throws Exception {

        User user =  User.builder()
                .name("Miguel Sedek")
                .email("miguelsedek@gmail")
                .password("H33zxc")
                .phones(Collections.singletonList(User.UserPhone.builder()
                        .number("1234567")
                        .cityCode("34")
                        .countryCode("12")
                        .build()))
                .build();

        RequestBuilder request = MockMvcRequestBuilders
                .post("/createuser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user));

        MvcResult  result = mockMvc.perform(request).andReturn();
        assertEquals(400, result.getResponse().getStatus());
        logger.info(String.format("Response status badEmailResponse %d Expected status %d", result.getResponse().getStatus(), 400));
    }

    /**
     * The test is performed by sending an invalid password structure as per specifications (in this case not sending an uppercase letter)
     * it can also be tested by not sending any lowercase letter or either by sending no numbers or more than 2.
     */
    @Test
    void badPasswordResponse() throws Exception {

        User user =  User.builder()
                .name("Miguel Sedek")
                .email("miguelsedek@gmail.com")
                .password("33zxc")
                .phones(Collections.singletonList(User.UserPhone.builder()
                        .number("1234567")
                        .cityCode("34")
                        .countryCode("12")
                        .build()))
                .build();

        RequestBuilder request = MockMvcRequestBuilders
                .post("/createuser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user));

        MvcResult  result = mockMvc.perform(request).andReturn();
        assertEquals(400, result.getResponse().getStatus());
        logger.info(String.format("Response status badPasswordResponse %d Expected status %d", result.getResponse().getStatus(), 400));
    }
}
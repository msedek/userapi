package com.globallogic.userapi.repository;

import com.globallogic.userapi.entities.Constants;
import com.globallogic.userapi.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;

@DataJpaTest
class UserRepositoryIntTest {

    protected static final Logger logger = LoggerFactory.getLogger(UserRepositoryIntTest.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    /**
     * The test is performed by creating user and phones in the database
     */
    @Test
    void createUserTest() {

        User user = User.builder()
                .name("Miguel Sedek")
                .email("miguelsedek@gmail.com")
                .password("H33zxc")
                .phones(Collections.singletonList(User.UserPhone.builder()
                        .number("1234567")
                        .countryCode("43")
                        .cityCode("12")
                        .build()))
                .isActive(Constants.ACTIVATE_USER)
                .token(Jwts.builder().setSubject("miguelsedek@gmail.cl").signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256)).compact())
                .build();

        userRepository.save(user);

        Assertions.assertThat(user.getId()).isNotNull();
        logger.info("User {}", user);

        user.getPhones().forEach(userPhone -> {
            logger.info("Phone data {}", userPhone);
            user.getPhones().forEach(phoneRepository::save);
            Assertions.assertThat(userPhone.getId()).isPositive();
            logger.info(String.format("Phone creation success, id %d", userPhone.getId()));
        });
    }
}

package com.globallogic.userapi.repository;

import com.globallogic.userapi.entities.User;
import com.globallogic.userapi.entities.UserPhone;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.assertj.core.api.Assertions;

import java.util.Arrays;

@DataJpaTest
public class UserRepositoryTest {

    protected static final Logger logger = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Test
    void createUserTest() throws Exception {

        //The test is performed by sending the correct json structure as per specification to create an user row.
        User user = new User("Miguel Sedek", "miguelsedek@gmail.cl",
                "H33zxc", Arrays.asList(new UserPhone("1234567",
                "34","12")), true);

        user.setActive(true);
        user.setToken(Jwts.builder().setSubject(user.getEmail()).signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256)).compact());

        userRepository.save(user);

        Assertions.assertThat(user.getId()).isNotNull();
        logger.info(String.format("User creation success, id %s and token %s", user.getId(), user.getToken()));

        for (UserPhone phone: user.getPhones()) {
            logger.info(phone.toString());
            phone.setUser(user);
            phoneRepository.save(phone);

            Assertions.assertThat(phone.getId()).isGreaterThan(0);
            logger.info(String.format("Phone creation success, id %d", phone.getId()));
        }
    }
}

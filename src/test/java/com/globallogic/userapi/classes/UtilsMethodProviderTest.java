package com.globallogic.userapi.classes;

import com.globallogic.userapi.entities.Constants;
import com.globallogic.userapi.entities.UtilsMethodProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@ComponentScan("com.globallogic.entities")
class UtilsMethodProviderTest {

    protected static final Logger logger = LoggerFactory.getLogger(UtilsMethodProviderTest.class);

    private UtilsMethodProvider utilsMethodProvider;

    /**
     * The test is performed by generating a JWT.
     */
    @Test
    void tokenGeneration() {
        String jwt = UtilsMethodProvider.tokenGeneration("miguelsedek@gmail.com");
        assertTrue(jwt.matches(Constants.VALID_JWT_PATTERN));
        logger.info("Token is a valid JWT {}", jwt);
    }
}
package ua.pp.sola.stackoverfloo;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
//@EnableOAuth2Sso
public class MainApiApp {

private static final Logger logger = Logger.getLogger(MainApiApp.class.getName());

    public static void main(String[] args) {
        logger.debug("Debug log level ready!");
        logger.info("Start logging!");
        SpringApplication.run(MainApiApp.class, args);
    }






}

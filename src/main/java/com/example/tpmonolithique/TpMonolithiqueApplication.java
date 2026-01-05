package com.example.tpmonolithique;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Monolithic Spring Boot application.
 * This serves as the entry point for the application and enables auto-configuration,
 * component scanning, and other Spring Boot features.
 */

@SpringBootApplication
public class TpMonolithiqueApplication {
    private static final Logger logger = LoggerFactory.getLogger(TpMonolithiqueApplication.class);

    public static void main(String[] args) {
        logger.info("Starting TpMonolithiqueApplication...");
        SpringApplication.run(TpMonolithiqueApplication.class, args);
    }
}

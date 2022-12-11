package com.example.Tischmesse;

import com.example.Tischmesse.model.User;
import com.example.Tischmesse.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.Set;

@Component
public class InitialUsersAdder implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitialUsersAdder.class);

    private final UserRepository userRepo;
    @Value("${tischmesse.administrator_password:#{null}}")
    private String administartorPW;

    public InitialUsersAdder(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) {
        addInitialUsers();
    }

    public void addInitialUsers() {
        if (userRepo.findAll().isEmpty()) {
            logger.info("Adding initial user 'administrator'");
            var encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

            String adminPW = administartorPW;
            if (administartorPW == null) {
                adminPW = generatePassword();
                System.out.println("Password for 'administrator': " + adminPW);
            }
            var admin = new User("administrator", encoder.encode(adminPW), Set.of("ADMINISTRATOR"), Collections.emptyList());
            userRepo.save(admin);
        }
    }

    private String generatePassword() {
        var random = new SecureRandom();
        return new BigInteger(128, random).toString(32);
    }
}

package com.dpiotr.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by dpiotr on 20.11.17.
 */
public class PasswordUtilities {
    public static String getHashFor(String text) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(text);
    }

    public static boolean validatePassword(String providedPassword, String hashedPassword) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.matches(providedPassword, hashedPassword);
    }

}

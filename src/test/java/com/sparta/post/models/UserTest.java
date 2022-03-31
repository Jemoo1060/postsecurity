package com.sparta.post.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    @Test
    @DisplayName("정상 케이스")
    void userTest(){

        String usernamePattern = "^[A-Za-z[0-9]]{2,50}$";
        String passwordPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{3,100}";

        String username1 = "ASD";
        String username2 = "asd123";

        String password1 = "qweqwe!@1";
        String password2 = "qweqwe1!";

        assertTrue(Pattern.matches(usernamePattern,username1));
        assertTrue(Pattern.matches(usernamePattern,username2));

        assertTrue(Pattern.matches(passwordPattern,password1));
        assertTrue(Pattern.matches(passwordPattern,password2));

        assertFalse(password1.contains(username1));



    }





}
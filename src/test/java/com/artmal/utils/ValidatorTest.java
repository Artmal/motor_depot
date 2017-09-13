package com.artmal.utils;

import static org.junit.Assert.assertFalse;

public class ValidatorTest {
    @org.junit.Test
    public void validateEmail() throws Exception {
        String[] wrongEmails = {
                "plainaddress",
                "#@%^%#$@#$@#.com",
                "@example.com",
                "email.example.com",
                "email@example@example.com",
                ".email@example.com",
                "email.@example.com",
                "email..email@example.com",
                "あいうえお@example.com",
                "email@example.com (Joe Smith)",
                "email@example",
                "email@-example.com",
                "email@111.222.333.44444",
                "email@example..com",
                "Abc..123@example.com",
        };


        for (String wrongEmail : wrongEmails) {
            assertFalse(Validator.validateEmail(wrongEmail));
        }
    }

    @org.junit.Test
    public void validateTown() throws Exception {
        String[] wrongTownNames = {
                "харьков",
                "kharkov",
                "kHarkov",
                "хАрьКоВ",
        };

        for (String wrongTownName : wrongTownNames) {
            assertFalse(Validator.validateTown(wrongTownName));
        }

    }

    @org.junit.Test
    public void validateFullName() throws Exception {
        String[] wrongFullNames = {
                "aRTEM maLCHENKO",
                "ARTEM MALCHENKO",
                "artem Malchenko",
                "art213 malche2132",
        };

        for (String wrongFullName : wrongFullNames) {
            assertFalse(Validator.validateFullName(wrongFullName));
        }
    }

    @org.junit.Test
    public void validatePhoneNumber() throws Exception {
        String[] wrongPhoneNumbers = {
                "+sad21312",
                "sdad123123",
                "123123dsa",
        };

        for (String wrongPhoneNumber : wrongPhoneNumbers) {
            assertFalse(Validator.validatePhoneNumber(wrongPhoneNumber));
        }
    }

    @org.junit.Test
    public void validateAge() throws Exception {
        String[] wrongAges = {
                "1a",
                "1234",
                "0",
                "-12"
        };

        for (String wrongAge : wrongAges) {
            assertFalse(Validator.validateAge(wrongAge));
        }


    }

}
package com.artmal.utils.validation;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(DataProviderRunner.class)
public class RegistrationValidatorTest {
    @DataProvider
    public static Object[] invalidEmailsData() {
        return new Object[]
                {
                        "plainaddress", "#@%^%#$@#$@#.com",
                        "@example.com", "email.example.com",
                        "email@example@example.com", ".email@example.com",
                        "email.@example.com", "email..email@example.com",
                        "あいうえお@example.com", "email@example.com (Joe Smith)",
                        "email@example", "email@-example.com",
                        "email@111.222.333.44444", "email@example..com",
                        "Abc..123@example.com"
                };
    }

    @DataProvider
    public static Object[] validEmailsData() {
        return new Object[]
                {
                        "email@example.com", "firstname.lastname@example.com",
                        "email@subdomain.example.com", "1234567890@example.com",
                        "email@example-one.com", "_______@example.com",
                        "email@example.name", "email@example.museum",
                        "email@example.co.jp"
                };
    }

    @DataProvider
    public static Object[] invalidFullNamesData() {
        return new Object[]
                {
                        "aRTEM maLCHENKO", "ARTEM MALCHENKO",
                        "artem Malchenko", "art213 malche2132",
                };
    }

    @DataProvider
    public static Object[] validFullNamesData() {
        return new Object[]
                {
                        "Ivan Ivanov", "Иван Иванов"
                };
    }

    @DataProvider
    public static Object[] invalidPhoneNumbersData() {
        return new Object[]
                {
                        "+sad21312", "sdad123123",
                        "123123dsa"
                };
    }

    @DataProvider
    public static Object[] validPhoneNumbersData() {
        return new Object[]
                {
                        "+380661279543", "+1 (617) 634-6218",
                };
    }

    @DataProvider
    public static Object[] invalidAgesData() {
        return new Object[]
                {
                        "1a", "1234",
                        "0", "-12"
                };
    }

    @DataProvider
    public static Object[] validAgesData() {
        return new Object[]
                {
                        "12", "101",
                };
    }


    @Test
    @UseDataProvider("invalidEmailsData")
    public void invalidateEmail(final String input) throws Exception {
        assertFalse(RegistrationValidator.validateEmail(input));
    }

    @Test
    @UseDataProvider("validEmailsData")
    public void validateEmail(final String input) throws Exception {
        assertTrue(RegistrationValidator.validateEmail(input));
    }

    @Test
    @UseDataProvider("invalidFullNamesData")
    public void invalidateFullName(final String input) throws Exception {
        assertFalse(RegistrationValidator.validateFullName(input));
    }

    @Test
    @UseDataProvider("validFullNamesData")
    public void validateFullName(final String input) throws Exception {
        assertTrue(RegistrationValidator.validateFullName(input));
    }

    @Test
    @UseDataProvider("invalidPhoneNumbersData")
    public void invalidatePhoneNumber(final String input) throws Exception {
        assertFalse(RegistrationValidator.validatePhoneNumber(input));
    }

    @Test
    @UseDataProvider("validPhoneNumbersData")
    public void validatePhoneNumber(final String input) throws Exception {
        assertTrue(RegistrationValidator.validatePhoneNumber(input));
    }

    @Test
    @UseDataProvider("invalidAgesData")
    public void validateInvalidAge(final String input) throws Exception {
        assertFalse(RegistrationValidator.validateAge(input));
    }

    @Test
    @UseDataProvider("validAgesData")
    public void validateValidAge(final String input) throws Exception {
        assertTrue(RegistrationValidator.validateAge(input));
    }
}

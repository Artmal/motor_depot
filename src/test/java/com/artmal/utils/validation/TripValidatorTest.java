package com.artmal.utils.validation;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(DataProviderRunner.class)
public class TripValidatorTest {
    @DataProvider
    public static Object[] invalidTownNamesData() {
        return new Object[]
                {
                        "харьков", "kharkov",
                        "kHarkov", "хАрьКоВ"
                };
    }

    @DataProvider
    public static Object[] validTownNamesData() {
        return new Object[]
                {
                        "Kharkov", "Харьков",
                };
    }

    @DataProvider
    public static Object[] invalidTimeData() {
        return new Object[]
                {
                        "1000-09-12 12:00:00", "2017-09-12 12:3:15",
                        "2017:09:12 12-00-00", "12-12-12 12:00:00",
                        "2017-00-12 12:00:00", "2017-12-00 12:00:00"
                };
    }

    @DataProvider
    public static Object[] validTimeData() {
        return new Object[]
                {
                        "2017-09-12 12:00:00", "2017-09-12 02:00:15",
                };
    }

    @DataProvider
    public static Object[] invalidPaymentData() {
        return new Object[]
                {
                        "-12", "0",
                        "012"
                };
    }

    @DataProvider
    public static Object[] validPaymentData() {
        return new Object[]
                {
                        "1", "12",
                        "123", "1234"
                };
    }


    @Test
    @UseDataProvider("invalidTownNamesData")
    public void invalidateTown(final String input) throws Exception {
        assertFalse(TripValidator.validateTown(input));
    }

    @Test
    @UseDataProvider("validTownNamesData")
    public void validateTown(final String input) throws Exception {
        assertTrue(TripValidator.validateTown(input));
    }

    @Test
    @UseDataProvider("invalidTimeData")
    public void invalidateTime(final String input) throws Exception {
        assertFalse(TripValidator.validateTime(input));
    }

    @Test
    @UseDataProvider("validTimeData")
    public void validTime(final String input) throws Exception {
        assertTrue(TripValidator.validateTime(input));
    }

    @Test
    @UseDataProvider("invalidPaymentData")
    public void invalidatePayment(final String input) throws Exception {
        assertFalse(TripValidator.validatePayment(input));
    }

    @Test
    @UseDataProvider("validPaymentData")
    public void validatePayment(final String input) throws Exception {
        assertTrue(TripValidator.validatePayment(input));
    }
}
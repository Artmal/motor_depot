package com.artmal.utils.validation;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(DataProviderRunner.class)
public class CarAddValidatorTest {
    @DataProvider
    public static Object[] invalidNumberOfSeatsData() {
        return new Object[]
                {
                        "-1", "0",
                        "123"
                };
    }

    @DataProvider
    public static Object[] validNumberOfSeatsData() {
        return new Object[]
                {
                        "2", "12",
                };
    }

    @DataProvider
    public static Object[] invalidCarColorData() {
        return new Object[]
                {
                        "белый", "white",
                        "rgb#123"
                };
    }

    @DataProvider
    public static Object[] validCarColorData() {
        return new Object[]
                {
                        "Белый", "White",
                };
    }

    @Test
    @UseDataProvider("invalidNumberOfSeatsData")
    public void invalidateNumberOfSeats(final String input) throws Exception {
        assertFalse(CarAddValidator.validateNumberOfSeats(input));

    }

    @Test
    @UseDataProvider("validNumberOfSeatsData")
    public void validateNumberOfSeats(final String input) throws Exception {
        assertTrue(CarAddValidator.validateNumberOfSeats(input));
    }

    @Test
    @UseDataProvider("invalidCarColorData")
    public void invalidateCarColor(final String input) throws Exception {
        assertFalse(CarAddValidator.validateCarColor(input));
    }

    @Test
    @UseDataProvider("validCarColorData")
    public void validateCarColor(final String input) throws Exception {
        assertTrue(CarAddValidator.validateCarColor(input));
    }
}
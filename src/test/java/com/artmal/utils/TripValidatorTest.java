package com.artmal.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class TripValidatorTest {
    @Test
    public void validateTown() throws Exception {
        String[] wrongTownNames = {
                "харьков",
                "kharkov",
                "kHarkov",
                "хАрьКоВ",
        };

        for (String wrongTownName : wrongTownNames) {
            assertFalse(TripValidator.validateTown(wrongTownName));
        }
    }
}
package com.artmal.utils;

import com.artmal.model.enums.TripStatus;

import java.util.NoSuchElementException;

public class TripUtils {
    public static TripStatus intToStatus(int statusId) {
        switch(statusId) {
            case 1: return TripStatus.Open;
            case 2: return TripStatus.In_progress;
            case 3: return TripStatus.Closed;
            case 4: return TripStatus.Canceled;
        }

        throw new NoSuchElementException();
    }
}

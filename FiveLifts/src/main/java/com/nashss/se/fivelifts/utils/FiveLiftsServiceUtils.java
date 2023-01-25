package com.nashss.se.fivelifts.utils;

import org.apache.commons.lang3.RandomStringUtils;

public final class FiveLiftsServiceUtils {
    private FiveLiftsServiceUtils() {}

    public static String generatePlaylistId() {
        return RandomStringUtils.randomAlphanumeric(5);
    }
}

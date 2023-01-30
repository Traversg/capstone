package com.nashss.se.fivelifts.utils;

import org.apache.commons.lang3.RandomStringUtils;
/**
 * Various utilities to deal to use within FiveLiftsService.
 */
public final class FiveLiftsServiceUtils {
    private FiveLiftsServiceUtils() {}

    /**
     * Generates a random user Id.
     * @return User Id
     */
    public static String generateUserId() {
        return RandomStringUtils.randomAlphanumeric(5);
    }
}

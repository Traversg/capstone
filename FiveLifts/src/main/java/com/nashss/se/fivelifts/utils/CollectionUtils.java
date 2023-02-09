package com.nashss.se.fivelifts.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.nashss.se.fivelifts.utils.NullUtils.ifNotNull;

/**
 * Various helpful utilities for converting collections.
 */
public class CollectionUtils {
    private CollectionUtils() {};

    /**
     * If the parameter is not null, create a new List with the collections contents.
     * If the parameter is null, return null.
     * @param collectionToWrap The collection to copy into a List.
     * @param <E> The type of element in the collection.
     * @return A new Set or null.
     */
    public static <E> List<E> copyToList(Collection<E> collectionToWrap) {
        return ifNotNull(collectionToWrap, () -> new ArrayList<>(collectionToWrap));
    }
}

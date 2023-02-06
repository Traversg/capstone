package com.nashss.se.fivelifts.utils;

/**
 * Determines weight increase for each lift
 * when a user is ready to increase their lift weight.
 */
public class Increments {
    private static final int DEADLIFT = 10;
    private static final int SQUAT = 5;
    private static final int BENCH_PRESS = 5;
    private static final int OVERHEAD_PRESS = 5;
    private static final int BARBELL_ROW = 5;

    private Increments() {}

    /**
     * Weight increase for deadlift.
     * @return increase
     */
    public static int deadlift() {
        return DEADLIFT;
    }

    /**
     * Weight increase for squat.
     * @return increase
     */
    public static int squat() {
        return SQUAT;
    }

    /**
     * Weight increase for bench press.
     * @return increase
     */
    public static int benchPress() {
        return BENCH_PRESS;
    }

    /**
     * Weight increase for overhead press.
     * @return increase
     */
    public static int overheadPress() {
        return OVERHEAD_PRESS;
    }

    /**
     * Weight increase for barbell row.
     * @return increase
     */
    public static int barbellRow() {
        return BARBELL_ROW;
    }
}

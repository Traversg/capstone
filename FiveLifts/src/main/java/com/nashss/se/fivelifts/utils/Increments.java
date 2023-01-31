package com.nashss.se.fivelifts.utils;

public class Increments {
    private static final int DEADLIFT = 10;
    private static final int SQUAT = 5;
    private static final int BENCH_PRESS = 5;
    private static final int OVERHEAD_PRESS = 5;
    private static final int BARBELL_ROW = 5;

    private Increments() {}

    public static int deadlift() {
        return DEADLIFT;
    }

    public static int squat() {
        return SQUAT;
    }

    public static int benchPress() {
        return BENCH_PRESS;
    }

    public static int overheadPress() {
        return OVERHEAD_PRESS;
    }

    public static int barbellRow() {
        return BARBELL_ROW;
    }
}

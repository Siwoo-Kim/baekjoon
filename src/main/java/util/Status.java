package util;

public @interface Status {

    enum Result {
        SUCCESS, FAIL, REWORK, REVISIT
    }

    Result result() default Result.SUCCESS;
}

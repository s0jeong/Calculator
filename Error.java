package calculator;

// Enum to represent different error types
public enum Error {
    SHORT("001", "too short arguments"),
    MANY("002", "too many arguments"),
    DIVIDED("003", "divided by zero"),
    INVALID_NUM("004", "invalid number"),
    INVALID_OP("005", "invalid operator");

    // Fields to store error code and message
    private final String code;
    private final String message;

    // Constructor to initialize error code and message
    Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // Getter method to retrieve the error message
    public String get() {
        return message;
    }
}

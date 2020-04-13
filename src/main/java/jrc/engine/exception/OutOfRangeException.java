package jrc.engine.exception;

public class OutOfRangeException extends RuntimeException {
    
    private static String format(String paramName, int value, int min, int max) {
        return String.format("Parameter \"%s\" (value: %s) must be in range [%s, %s)",
                paramName,
                value,
                min,
                max);
    }
    
    public OutOfRangeException(String paramName, int value, int min, int max) {
        super(format(paramName, value, min, max));
    }
    
    public OutOfRangeException(String paramName, int value, int max) {
        super(format(paramName, value, 0, max));
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}

package com.jackpot.base.exception;

/**
 * @Author: Hanjt
 * @Date: 2018/8/7 14:15
 * @Description: 缓存异常
 */
public class CacheException extends RuntimeException {

    private static final long serialVersionUID = -6978689622008112206L;

    /**
     * Creates a new ArgumentException.
     */
    public CacheException() {
        super();
    }

    /**
     * Constructs a new ArgumentException.
     *
     * @param message the reason for the exception
     */
    public CacheException(String message) {
        super(message);
    }

    /**
     * Constructs a new ArgumentException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public CacheException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new ArgumentException.
     *
     * @param message the reason for the exception
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }

}

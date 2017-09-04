package com.giants3.hd.data.exception;

/**
 * Created by david on 2016/1/2.
 */
public class RemoteDataException extends Exception {

    public RemoteDataException() {
        super();
    }

    public RemoteDataException(final String message) {
        super(message);
    }

    public RemoteDataException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RemoteDataException(final Throwable cause) {
        super(cause);
    }
}
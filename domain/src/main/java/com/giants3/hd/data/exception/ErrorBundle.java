package com.giants3.hd.data.exception;

/**
 * Created by david on 2015/12/25.
 */
public interface ErrorBundle {
    Exception getException();

    String getErrorMessage();
}

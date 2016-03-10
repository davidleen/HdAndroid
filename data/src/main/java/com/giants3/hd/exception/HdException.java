package com.giants3.hd.exception;


public class HdException extends  Exception {


    public static final int FAIL_ASYNC_CLIENT = 1000;
    public static final int FAIL_READ_RESPONSE_IO = 1001;
    public static final int FAIL_SCALE_IMAGE=1002;






    public String message;
    public int errorCode;

    protected HdException(String s, Throwable cause) {
        super(s,cause);

    }

    public HdException() {
        super();
    }

    public static HdException create(int errorCode,Throwable cause)
    {
        HdException hdException= new HdException("", cause);
        hdException.errorCode=errorCode;
        hdException.message=cause.getLocalizedMessage();
        return hdException;
    }

    public static HdException create(String message
    )
    {
        HdException hdException= new HdException(message,null);
        hdException.errorCode=-1;
        hdException.message=message;


        return hdException;
    }

    public static HdException create( Throwable cause)
    {
       return create(-1,cause);
    }
}

package com.denger.naomitian.utils.util;


import com.denger.naomitian.utils.Reference;

;



public class NoStackTraceThrowable extends RuntimeException {

    public NoStackTraceThrowable(final String msg) {
        super(msg);
        this.setStackTrace(new StackTraceElement[0]);
    }

    @Override
    public String toString() {
        return "" + Reference.Version;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}

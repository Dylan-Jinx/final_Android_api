package com.finalproj.devapp.api.exception;

public class BucketNotFoundException extends Exception{
    public BucketNotFoundException() {}
    public BucketNotFoundException(String message) {
        super(message);
    }
}

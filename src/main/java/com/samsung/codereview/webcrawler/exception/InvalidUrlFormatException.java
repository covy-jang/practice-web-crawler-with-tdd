package com.samsung.codereview.webcrawler.exception;

public class InvalidUrlFormatException extends RuntimeException {
    public InvalidUrlFormatException() {
    }

    public InvalidUrlFormatException(String message) {
        super(message);
    }
}

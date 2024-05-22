package com.AnimeCatalog.exception;

public class FileExistsException extends RuntimeException {

    public FileExistsException(String message) {
        super(message);
    }

}

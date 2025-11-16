package com.nataliaschwindt.conversor.excepcion;

public class ConversorException extends Exception {
    public ConversorException(String message) {
        super(message);
    }

    public ConversorException(String message, Throwable cause) {
        super(message, cause);
    }
}
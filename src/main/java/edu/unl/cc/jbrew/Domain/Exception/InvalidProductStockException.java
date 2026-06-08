package edu.unl.cc.jbrew.Domain.Exception;

public class InvalidProductStockException extends RuntimeException {
    public InvalidProductStockException(String message) {

        super(message);
    }
}

package org.example.Exceptions;

public class CodigoNaoExisteException extends Exception {
    public CodigoNaoExisteException (String msg) {
        super(msg);
    }
    public CodigoNaoExisteException() {
        super();
    }
}
package org.example.Exceptions;

public class TipoNaoExisteException extends Exception {
    public TipoNaoExisteException(String msg) {
        super(msg);
    }
    public TipoNaoExisteException() {
        super();
    }
}
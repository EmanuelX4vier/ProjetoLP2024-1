package com.ufpb.dcx.emanuel.xavier.Exceptions;

public class TipoNaoExisteException extends Exception {
    public TipoNaoExisteException(String msg) {
        super(msg);
    }
    public TipoNaoExisteException() {
        super();
    }
}
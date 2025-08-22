package com.ufpb.dcx.emanuel.xavier.Exceptions;

public class CodigoNaoExisteException extends Exception {
    public CodigoNaoExisteException (String msg) {
        super(msg);
    }
    public CodigoNaoExisteException() {
        super();
    }
}
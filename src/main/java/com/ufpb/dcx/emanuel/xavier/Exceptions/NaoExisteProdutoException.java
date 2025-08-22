package com.ufpb.dcx.emanuel.xavier.Exceptions;

public class NaoExisteProdutoException extends Exception {
    public NaoExisteProdutoException(String msg) {
        super(msg);
    }
    public NaoExisteProdutoException() {
        super();
    }
}
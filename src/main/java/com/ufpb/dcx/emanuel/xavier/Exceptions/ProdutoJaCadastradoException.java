package com.ufpb.dcx.emanuel.xavier.Exceptions;

public class ProdutoJaCadastradoException extends Exception {
    public ProdutoJaCadastradoException (String msg) {
        super(msg);
    }
    public ProdutoJaCadastradoException() {
        super();
    }
}
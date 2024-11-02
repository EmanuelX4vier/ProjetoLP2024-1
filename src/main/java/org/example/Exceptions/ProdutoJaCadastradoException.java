package org.example.Exceptions;

public class ProdutoJaCadastradoException extends Exception {
    public ProdutoJaCadastradoException (String msg) {
        super(msg);
    }
    public ProdutoJaCadastradoException() {
        super();
    }
}
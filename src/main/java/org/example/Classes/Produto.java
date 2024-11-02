package org.example.Classes;

import java.util.Objects;

public class Produto implements Comparable<Produto> {
    private String nome;
    private String tipo;
    private double valor;
    private String codigo;
    private int quantidade;

    public Produto (String nome, String tipo, double valor, String codigo, int quantidade) {
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.codigo = codigo;
        this.quantidade = quantidade;
    }

    public Produto() {
        this("", "", 0.0, "", 0);
    }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return this.tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return this.valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCodigo() {
        return this.codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return this.quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String toString() {
        return String.format("%nProduto:%nNome: %s%nTipo: %s%nCódigo: %s%nValor: R$ %.2f%nQuantidade em estoque: %d%n",
                this.nome, this.tipo, this.codigo, this.valor, this.quantidade);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Double.compare(valor, produto.valor) == 0 && quantidade == produto.quantidade && Objects.equals(nome, produto.nome) && Objects.equals(tipo, produto.tipo) && Objects.equals(codigo, produto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, tipo, valor, codigo, quantidade);
    }


    @Override
    public int compareTo(Produto o) {
        return 0;
    }
}
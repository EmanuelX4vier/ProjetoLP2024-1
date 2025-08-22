package com.ufpb.dcx.emanuel.xavier.Classes;

import java.util.Objects;

public class Produto implements Comparable<Produto> {
    private String nome;
    private String tipo;
    private double valor;
    private String codigo;
    private int quantidade;

    // Construtor completo
    public Produto(String nome, String tipo, double valor, String codigo, int quantidade) {
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.codigo = codigo;
        this.quantidade = quantidade;
    }

    // Construtor vazio
    public Produto() {
        this("", "", 0.0, "", 0);
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Representação em texto
    @Override
    public String toString() {
        return "Produto:" +
                "\nNome: " + nome +
                "\nTipo: " + tipo +
                "\nCódigo: " + codigo +
                String.format("\nValor: R$ %.2f", valor) +
                "\nQuantidade em estoque: " + quantidade + "\n";
    }

    // Igualdade entre objetos
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // mesmo objeto
        if (obj == null || getClass() != obj.getClass()) return false; // nulo ou classe diferente

        Produto produto = (Produto) obj;
        return Double.compare(produto.valor, valor) == 0 &&
                quantidade == produto.quantidade &&
                Objects.equals(nome, produto.nome) &&
                Objects.equals(tipo, produto.tipo) &&
                Objects.equals(codigo, produto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, tipo, valor, codigo, quantidade);
    }

    // Comparação básica (pode ser por nome)
    @Override
    public int compareTo(Produto outro) {
        return this.nome.compareToIgnoreCase(outro.getNome());
    }
}

package org.example.Classes;

import org.example.Exceptions.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaDeEstoqueEquipe implements SistemaDeEstoque {

    private List<Produto> estoque = new ArrayList<>();
    private GravadorDeDados gravadorDeProdutos;

    public SistemaDeEstoqueEquipe() {
        this.estoque = new ArrayList<>();
        this.gravadorDeProdutos = new GravadorDeDados();
    }

    //Parte que trata do cadastro.
    public boolean verificarSeEstaCadastrado(String nome) {
        for (Produto p : this.estoque) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public void cadastrarProduto(String nome, String tipo, double valor, String codigo, int quantidade) throws ProdutoJaCadastradoException{
        if (verificarSeEstaCadastrado(nome)) {
            throw new ProdutoJaCadastradoException("Este produto já foi cadastrado no estoque.");
        }
        Produto produto = new Produto(nome, tipo, valor, codigo, quantidade);
        this.estoque.add(produto);
    }

    public void cadastrarProduto(Produto produto) throws ProdutoJaCadastradoException{
        if (verificarSeEstaCadastrado(produto.getNome())) {
            throw new ProdutoJaCadastradoException("Este produto já foi cadastrado no estoque.");
        }
        this.estoque.add(produto);
    }

    //Parte com que trata dos nomes.
    public String procurarProdutoPorNome(String nome) throws ProdutoNaoCadastradoException {
        for (Produto p : this.estoque) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p.toString();
            }
        }
        throw new ProdutoNaoCadastradoException("Este produto ainda não foi cadastrado:\n" + nome);
    }

    public void alterarNomeDoProduto(String nome, String novoNome) throws ProdutoJaCadastradoException, ProdutoNaoCadastradoException {
        if (verificarSeEstaCadastrado(novoNome)) {
            throw new ProdutoJaCadastradoException("Já existe um produto com este nome no estoque:\n" + nome);
        }
        if (verificarSeEstaCadastrado(nome)) {
            for (Produto p : this.estoque) {
                if (p.getNome().equalsIgnoreCase(nome)) {
                    p.setNome(novoNome);
                }
            }
        } else {
            throw new ProdutoNaoCadastradoException("Este produto ainda não foi cadastrado:\n" + nome);
        }
    }

    //Parte que trata dos tipo.
    public boolean verificaSeTipoExiste(String tipo) {
        for (Produto p : this.estoque) {
            if (p.getTipo().equalsIgnoreCase(tipo)) {
                return true;
            }
        }
        return false;
    }

    public List<Produto> procurarProdutoPorTipo(String tipo) throws TipoNaoExisteException {
        List<Produto> produtos = new ArrayList<>();
        for (Produto p : this.estoque) {
            if (p.getTipo().equalsIgnoreCase(tipo)) {
                produtos.add(p);
            }
        }
        if (produtos.size() != 0) {
            return produtos;
        }
        throw new TipoNaoExisteException("Este tipo não existe no estoque:\n " + tipo);
    }

    public void alterarTipoDoProduto(String nome, String novoTipo) throws ProdutoNaoCadastradoException {
        if (verificarSeEstaCadastrado(nome)) {
            for (Produto p : this.estoque) {
                if (p.getNome().equalsIgnoreCase(nome)) {
                    p.setTipo(novoTipo);
                }
            }
        } else {
            throw new ProdutoNaoCadastradoException("Este produto ainda não foi cadastrado:\n" + nome);
        }
    }

    //Parte que trata dos valores.
    public void alterarValorDoProduto(String nome, double novoValor) throws ProdutoNaoCadastradoException {
        if (verificarSeEstaCadastrado(nome)) {
            for (Produto p : this.estoque) {
                if (p.getNome().equalsIgnoreCase(nome)) {
                    p.setValor(novoValor);
                }
            }
        } else {
            throw new ProdutoNaoCadastradoException("Este produto ainda não foi cadastrado:\n" + nome);
        }
    }

    public Produto produtoComMaiorValor() throws NaoExisteProdutoException {
        if (estoque.isEmpty()) {
            throw new NaoExisteProdutoException("Estoque vazio.");
        }
        Produto maiorValor = estoque.getFirst();
        for (Produto p : estoque) {
            if (p.getValor() > maiorValor.getValor()) {
                maiorValor = p;
            }
        }
        if (maiorValor.getValor() != 0) {
            return maiorValor;
        }
        throw new NaoExisteProdutoException("Estoque vazio.");
    }

    public Produto produtoComMenorValor() throws NaoExisteProdutoException {
        if (estoque.isEmpty()) {
            throw new NaoExisteProdutoException("Estoque vazio.");
        }
        Produto menorValor = estoque.getFirst();
        for (Produto p : estoque) {
            if (p.getValor() < menorValor.getValor()) {
                menorValor = p;
            }
        }
        if (menorValor.getValor() != 0) {
            return menorValor;
        }
        throw new NaoExisteProdutoException("Estoque vazio.");
    }

    //Parte que trata dos códigos.
    public List<Produto> procurarProdutoPorCodigo(String codigo) throws CodigoNaoExisteException {
        List<Produto> produtos = new ArrayList<>();
        for (Produto p : this.estoque) {
            if (p.getCodigo().equals(codigo)) {
                produtos.add(p);
            }
        }
        if (produtos.size() > 0) {
            return produtos;
        }
        throw new CodigoNaoExisteException("Este código não existe.");
    }

    public void alterarCodigoDoProduto(String nome, String novoCodigo) throws ProdutoNaoCadastradoException {
        if (verificarSeEstaCadastrado(nome)) {
            for (Produto produto : estoque) {
                if (produto.getNome().equalsIgnoreCase(nome)) {
                    produto.setCodigo(novoCodigo);
                }
            }
        } else {
            throw new ProdutoNaoCadastradoException("Este produto ainda não foi cadastrado:\n" + nome);
        }
    }

    //Parte que trata das quantidades.
    public int quantidadeDoProduto(String nome, int novaQuantidade) throws ProdutoNaoCadastradoException {
        for (Produto p : this.estoque) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p.getQuantidade();
            }
        }
        throw new ProdutoNaoCadastradoException("Este produto ainda não foi cadastrado:\n" + nome);
    }

    public Produto produtoComMaiorQuantidade() throws NaoExisteProdutoException {
        if (estoque.isEmpty()) {
            throw new NaoExisteProdutoException("Estoque vazio.");
        }
        Produto maiorQuantidade = estoque.getFirst();
        for (Produto p : estoque) {
            if (p.getQuantidade() > maiorQuantidade.getQuantidade()) {
                maiorQuantidade = p;
            }
        }
        if (maiorQuantidade.getQuantidade() != 0) {
            return maiorQuantidade;
        }
        throw new NaoExisteProdutoException("Estoque vazio.");
    }

    public Produto produtoComMenorQuantidade() throws NaoExisteProdutoException {
        if (estoque.isEmpty()) {
            throw new NaoExisteProdutoException("Estoque vazio.");
        }
        Produto menorQuantidade = estoque.getFirst();
        for (Produto p : estoque) {
            if (p.getQuantidade() < menorQuantidade.getQuantidade()) {
                menorQuantidade = p;
            }
        }
        if (menorQuantidade.getQuantidade() != 0) {
            return menorQuantidade;
        }
        throw new NaoExisteProdutoException("Estoque vazio.");
    }

    public void alterarQuantidadeDoProduto(String nome, int novaQuantidade) throws ProdutoNaoCadastradoException {
        if (verificarSeEstaCadastrado(nome)) {
            for (Produto produto : estoque) {
                if (produto.getNome().equalsIgnoreCase(nome)) {
                    produto.setQuantidade(novaQuantidade);
                }
            }
        } else {
            throw new ProdutoNaoCadastradoException("Este produto ainda não foi cadastrado:\n" + nome);
        }
    }

    public void adicionarQuantidadeDoProduto(String nome, int valorInserido) throws ProdutoNaoCadastradoException {
        if (verificarSeEstaCadastrado(nome)) {
            for (Produto produto : estoque) {
                if (produto.getNome().equalsIgnoreCase(nome)) {
                    produto.setQuantidade(produto.getQuantidade() + valorInserido);
                }
            }
        } else {
            throw new ProdutoNaoCadastradoException("Este produto ainda não foi cadastrado:\n" + nome);
        }
    }

    public void removerQuantidadeDoProduto(String nome, int valorInserido) throws ProdutoNaoCadastradoException {
        if (verificarSeEstaCadastrado(nome)) {
            for (Produto produto : estoque) {
                if (produto.getNome().equalsIgnoreCase(nome)) {
                    int novaQuantidade = produto.getQuantidade() - valorInserido;
                    if (novaQuantidade >= 0) {
                        produto.setQuantidade(novaQuantidade);
                    }
                }
            }
        } else {
            throw new ProdutoNaoCadastradoException("Este produto ainda não foi cadastrado:\n" + nome);
        }
    }

    //Relatório geral.
    public List<Produto> relatorioGeral() throws NaoExisteProdutoException {
        if (estoque.size() != 0) {
            return this.estoque;
        }
        throw new NaoExisteProdutoException("Estoque Vazio.");
    }

    //Parte que trata salvamento, retorno e exclusão de dados.
    public void salvarDados() throws IOException{
        this.gravadorDeProdutos.gravaProdutos(this.estoque);
    }

    public void recuperaDados() throws IOException{
        this.estoque = this.gravadorDeProdutos.recuperaDados();
    }

    public void setEstoque(List<Produto> estoque) {
        this.estoque = estoque;
    }
}
package org.example.Classes;

import org.example.Exceptions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SistemaDeEstoqueEquipe implements SistemaDeEstoque {

    private List<Produto> estoque;
    private GravadorDeDados gravadorDeProdutos;

    public SistemaDeEstoqueEquipe() {
        this.estoque = new ArrayList<>();
        this.gravadorDeProdutos = new GravadorDeDados();
    }

    // ==============================
    // Métodos auxiliares
    // ==============================
    private Produto buscarProduto(String nome) {
        for (Produto p : estoque) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean verificarSeEstaCadastrado(String nome) {
        return buscarProduto(nome) != null;
    }

    @Override
    public boolean verificarSeTipoExiste(String tipo) {
        for (Produto p : estoque) {
            if (p.getTipo().equalsIgnoreCase(tipo)) {
                return true;
            }
        }
        return false;
    }

    // ==============================
    // Cadastro
    // ==============================
    @Override
    public void cadastrarProduto(String nome, String tipo, double valor, String codigo, int quantidade)
            throws ProdutoJaCadastradoException {
        if (verificarSeEstaCadastrado(nome)) {
            throw new ProdutoJaCadastradoException("Este produto já foi cadastrado no estoque.");
        }
        Produto produto = new Produto(nome, tipo, valor, codigo, quantidade);
        estoque.add(produto);
    }

    @Override
    public void cadastrarProduto(Produto produto) throws ProdutoJaCadastradoException {
        if (verificarSeEstaCadastrado(produto.getNome())) {
            throw new ProdutoJaCadastradoException("Este produto já foi cadastrado no estoque.");
        }
        estoque.add(produto);
    }

    // ==============================
    // Nome
    // ==============================
    @Override
    public String procurarProdutoPorNome(String nome) throws ProdutoNaoCadastradoException {
        Produto p = buscarProduto(nome);
        if (p != null) {
            return p.toString();
        }
        throw new ProdutoNaoCadastradoException("Este produto ainda não foi cadastrado:\n" + nome);
    }

    @Override
    public void alterarNomeDoProduto(String nome, String novoNome)
            throws ProdutoJaCadastradoException, ProdutoNaoCadastradoException {
        if (verificarSeEstaCadastrado(novoNome)) {
            throw new ProdutoJaCadastradoException("Já existe um produto com este nome no estoque:\n" + novoNome);
        }
        Produto p = buscarProduto(nome);
        if (p != null) {
            p.setNome(novoNome);
        } else {
            throw new ProdutoNaoCadastradoException("Este produto ainda não foi cadastrado:\n" + nome);
        }
    }

    // ==============================
    // Tipo
    // ==============================
    @Override
    public List<Produto> procurarProdutoPorTipo(String tipo) throws TipoNaoExisteException {
        List<Produto> produtos = new ArrayList<>();
        for (Produto p : estoque) {
            if (p.getTipo().equalsIgnoreCase(tipo)) {
                produtos.add(p);
            }
        }
        if (!produtos.isEmpty()) {
            return produtos;
        }
        throw new TipoNaoExisteException("Este tipo não existe no estoque:\n" + tipo);
    }

    @Override
    public void alterarTipoDoProduto(String nome, String novoTipo) throws ProdutoNaoCadastradoException {
        Produto p = buscarProduto(nome);
        if (p != null) {
            p.setTipo(novoTipo);
        } else {
            throw new ProdutoNaoCadastradoException("Este produto ainda não foi cadastrado:\n" + nome);
        }
    }

    // ==============================
    // Valor
    // ==============================
    @Override
    public void alterarValorDoProduto(String nome, double novoValor) throws ProdutoNaoCadastradoException {
        Produto p = buscarProduto(nome);
        if (p != null) {
            p.setValor(novoValor);
        } else {
            throw new ProdutoNaoCadastradoException("Este produto ainda não foi cadastrado:\n" + nome);
        }
    }

    @Override
    public Produto produtoComMaiorValor() throws NaoExisteProdutoException {
        if (estoque.isEmpty()) throw new NaoExisteProdutoException("Estoque vazio.");

        Produto maior = estoque.get(0);
        for (Produto p : estoque) {
            if (p.getValor() > maior.getValor()) {
                maior = p;
            }
        }
        return maior;
    }

    @Override
    public Produto produtoComMenorValor() throws NaoExisteProdutoException {
        if (estoque.isEmpty()) throw new NaoExisteProdutoException("Estoque vazio.");

        Produto menor = estoque.get(0);
        for (Produto p : estoque) {
            if (p.getValor() < menor.getValor()) {
                menor = p;
            }
        }
        return menor;
    }

    // ==============================
    // Código
    // ==============================
    @Override
    public List<Produto> procurarProdutoPorCodigo(String codigo) throws CodigoNaoExisteException {
        List<Produto> produtos = new ArrayList<>();
        for (Produto p : estoque) {
            if (p.getCodigo().equals(codigo)) {
                produtos.add(p);
            }
        }
        if (!produtos.isEmpty()) {
            return produtos;
        }
        throw new CodigoNaoExisteException("Este código não existe.");
    }

    @Override
    public void alterarCodigoDoProduto(String nome, String novoCodigo) throws ProdutoNaoCadastradoException {
        Produto p = buscarProduto(nome);
        if (p != null) {
            p.setCodigo(novoCodigo);
        } else {
            throw new ProdutoNaoCadastradoException("Este produto ainda não foi cadastrado:\n" + nome);
        }
    }

    // ==============================
    // Quantidade
    // ==============================
    @Override
    public int quantidadeDoProduto(String nome) throws ProdutoNaoCadastradoException {
        Produto p = buscarProduto(nome);
        if (p != null) {
            return p.getQuantidade();
        }
        throw new ProdutoNaoCadastradoException("Este produto ainda não foi cadastrado:\n" + nome);
    }

    @Override
    public Produto produtoComMaiorQuantidade() throws NaoExisteProdutoException {
        if (estoque.isEmpty()) throw new NaoExisteProdutoException("Estoque vazio.");

        Produto maior = estoque.get(0);
        for (Produto p : estoque) {
            if (p.getQuantidade() > maior.getQuantidade()) {
                maior = p;
            }
        }
        return maior;
    }

    @Override
    public Produto produtoComMenorQuantidade() throws NaoExisteProdutoException {
        if (estoque.isEmpty()) throw new NaoExisteProdutoException("Estoque vazio.");

        Produto menor = estoque.get(0);
        for (Produto p : estoque) {
            if (p.getQuantidade() < menor.getQuantidade()) {
                menor = p;
            }
        }
        return menor;
    }

    @Override
    public void alterarQuantidadeDoProduto(String nome, int novaQuantidade) throws ProdutoNaoCadastradoException {
        Produto p = buscarProduto(nome);
        if (p != null) {
            p.setQuantidade(novaQuantidade);
        } else {
            throw new ProdutoNaoCadastradoException("Este produto ainda não foi cadastrado:\n" + nome);
        }
    }

    @Override
    public void adicionarQuantidadeDoProduto(String nome, int valorInserido) throws ProdutoNaoCadastradoException {
        Produto p = buscarProduto(nome);
        if (p != null) {
            p.setQuantidade(p.getQuantidade() + valorInserido);
        } else {
            throw new ProdutoNaoCadastradoException("Este produto ainda não foi cadastrado:\n" + nome);
        }
    }

    @Override
    public void removerQuantidadeDoProduto(String nome, int valorRemovido) throws ProdutoNaoCadastradoException {
        Produto p = buscarProduto(nome);
        if (p != null) {
            int novaQtd = p.getQuantidade() - valorRemovido;
            if (novaQtd >= 0) {
                p.setQuantidade(novaQtd);
            }
        } else {
            throw new ProdutoNaoCadastradoException("Este produto ainda não foi cadastrado:\n" + nome);
        }
    }

    // ==============================
    // Relatório
    // ==============================
    @Override
    public List<Produto> relatorioGeral() throws NaoExisteProdutoException {
        if (estoque.isEmpty()) {
            throw new NaoExisteProdutoException("Estoque Vazio.");
        }
        return estoque;
    }

    // ==============================
    // Persistência
    // ==============================
    @Override
    public void salvarDados() throws IOException {
        gravadorDeProdutos.gravaProdutos(estoque);
    }

    @Override
    public void recuperaDados() throws IOException {
        estoque = gravadorDeProdutos.recuperaDados();
    }

    @Override
    public void setEstoque(List<Produto> estoque) {
        this.estoque = estoque;
    }
}

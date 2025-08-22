package org.example.Classes;

import org.example.Exceptions.*;

import java.io.IOException;
import java.util.List;

public interface SistemaDeEstoque {

    // ==============================
    // Cadastro
    // ==============================
    void cadastrarProduto(String nome, String tipo, double valor, String codigo, int quantidade)
            throws ProdutoJaCadastradoException;

    void cadastrarProduto(Produto produto) throws ProdutoJaCadastradoException;

    boolean verificarSeEstaCadastrado(String nome);

    // ==============================
    // Nome
    // ==============================
    String procurarProdutoPorNome(String nome) throws ProdutoNaoCadastradoException;

    void alterarNomeDoProduto(String nome, String novoNome)
            throws ProdutoJaCadastradoException, ProdutoNaoCadastradoException;

    // ==============================
    // Tipo
    // ==============================
    boolean verificarSeTipoExiste(String tipo);

    List<Produto> procurarProdutoPorTipo(String tipo) throws TipoNaoExisteException;

    void alterarTipoDoProduto(String nome, String novoTipo) throws ProdutoNaoCadastradoException;

    // ==============================
    // Valor
    // ==============================
    void alterarValorDoProduto(String nome, double novoValor) throws ProdutoNaoCadastradoException;

    Produto produtoComMenorValor() throws NaoExisteProdutoException;

    Produto produtoComMaiorValor() throws NaoExisteProdutoException;

    // ==============================
    // Código
    // ==============================
    List<Produto> procurarProdutoPorCodigo(String codigo) throws CodigoNaoExisteException;

    void alterarCodigoDoProduto(String nome, String novoCodigo) throws ProdutoNaoCadastradoException;

    // ==============================
    // Quantidade
    // ==============================
    int quantidadeDoProduto(String nome) throws ProdutoNaoCadastradoException;

    Produto produtoComMenorQuantidade() throws NaoExisteProdutoException;

    Produto produtoComMaiorQuantidade() throws NaoExisteProdutoException;

    void alterarQuantidadeDoProduto(String nome, int novaQuantidade) throws ProdutoNaoCadastradoException;

    void adicionarQuantidadeDoProduto(String nome, int valorInserido) throws ProdutoNaoCadastradoException;

    void removerQuantidadeDoProduto(String nome, int valorInserido) throws ProdutoNaoCadastradoException;

    // ==============================
    // Relatório
    // ==============================
    List<Produto> relatorioGeral() throws NaoExisteProdutoException;

    // ==============================
    // Persistência
    // ==============================
    void salvarDados() throws IOException;

    void recuperaDados() throws IOException;

    void setEstoque(List<Produto> produtos);
}

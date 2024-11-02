package org.example.Classes;

import org.example.Exceptions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface SistemaDeEstoque {
    //Criação do estoque.
    List<Produto> estoque = new ArrayList<>();

    //Parte do cadastro.
    void cadastrarProduto(String nome, String tipo, double valor, String codigo, int quantidade) throws ProdutoJaCadastradoException;
    void cadastrarProduto(Produto produto) throws ProdutoJaCadastradoException;
    boolean verificarSeEstaCadastrado(String nome);

    //Parte que trata dos nomes.
    String procurarProdutoPorNome(String nome) throws ProdutoNaoCadastradoException;
    void alterarNomeDoProduto (String nome, String novoNome) throws ProdutoJaCadastradoException, ProdutoNaoCadastradoException;

    //Parte que trata dos tipos.
    boolean verificaSeTipoExiste(String tipo) throws TipoNaoExisteException;
    List<Produto> procurarProdutoPorTipo(String tipo) throws TipoNaoExisteException;
    void alterarTipoDoProduto (String nome, String novoTipo) throws ProdutoNaoCadastradoException;

    //Parte que trata dos valores.
    void alterarValorDoProduto (String nome, double novoValor) throws ProdutoNaoCadastradoException;
    Produto produtoComMenorValor() throws NaoExisteProdutoException;
    Produto produtoComMaiorValor() throws NaoExisteProdutoException;

    //Parte que trata dos códigos.
    List<Produto> procurarProdutoPorCodigo(String codigo) throws CodigoNaoExisteException;
    void alterarCodigoDoProduto(String nome, String novoTipo) throws ProdutoNaoCadastradoException;

    //Parte que trata das quantidades.
    int quantidadeDoProduto(String nome, int novaQuantidade) throws ProdutoNaoCadastradoException;
    Produto produtoComMenorQuantidade() throws NaoExisteProdutoException;
    Produto produtoComMaiorQuantidade() throws NaoExisteProdutoException;
    void alterarQuantidadeDoProduto(String nome, int novaQuantidade) throws ProdutoNaoCadastradoException;
    void adicionarQuantidadeDoProduto(String nome, int valorInserido) throws ProdutoNaoCadastradoException;
    void removerQuantidadeDoProduto(String nome, int valorInserido) throws ProdutoNaoCadastradoException;

    //Relatorio geral.
    List<Produto> relatorioGeral() throws NaoExisteProdutoException;

    //Parte que trata salvamento, retorno e exclusão de dados.
    void salvarDados() throws IOException;
    void recuperaDados() throws IOException;
    void setEstoque(List<Produto> produtos);
}
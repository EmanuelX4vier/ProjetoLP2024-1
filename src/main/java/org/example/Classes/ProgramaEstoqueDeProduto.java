package org.example.Classes;

import org.example.Exceptions.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProgramaEstoqueDeProduto {
    public static void main(String[] args) {
        //Carrega dados do estoque.
        SistemaDeEstoque sistema = new SistemaDeEstoqueEquipe();
        try {
            sistema.recuperaDados();
            JOptionPane.showMessageDialog(null, "Dados do sistema carregados com sucessos!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Falha interna, dados não recuperados.");
            e.getStackTrace();
        }
        //Inicia o menu.
        boolean continuar = true;
        while (continuar) {
            String opcao = JOptionPane.showInputDialog("""
                    Digite a opção desejada:
                    1 - Cadastrar produto.
                    2 - Verifica se um produto está cadastrado.
                    3 - Procurar um produto.
                    4 - Altera os dados de um produto.
                    5 - Adicionar quantidade de um produto.
                    6 - Remover quantidade de um produto.
                    7 - Relatórios.
                    8 - Salvar dados.
                    9 - Excluir dados.
                    0 - Encerrar.""");
            switch (opcao) {
                //Cadastrar produto.
                case "1":
                    String nome = JOptionPane.showInputDialog(null, "Digite o nome do produto:");
                    if(nome.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Campo de nome vazio.");
                    }else{
                        String tipo = JOptionPane.showInputDialog(null, "Digite o tipo do produto:");
                        if(tipo.isEmpty()){
                            JOptionPane.showMessageDialog(null, "Campo de nome vazio.");
                        }else{
                            try {
                                double valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor deste produto:"));
                                String codigo = JOptionPane.showInputDialog(null, "Digite o código desse produto");
                                if(codigo.isEmpty()){
                                    JOptionPane.showMessageDialog(null, "Campo vazio");
                                }else{
                                    try{
                                        int quantidade = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a quantidade de produtos a serem cadastrados."));
                                        Produto produto = new Produto(nome, tipo, valor, codigo, quantidade);
                                        sistema.cadastrarProduto(produto);
                                        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso.");
                                    } catch (ProdutoJaCadastradoException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                    } catch (NumberFormatException e) {
                                        JOptionPane.showMessageDialog(null, "Valor não reconhecido.");
                                    }
                                }
                            }catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Valor não reconhecido.");
                            }
                        }
                    }
                    break;
                //Verificar se produto está cadastrado.
                case "2":
                    String nomeCase2 = JOptionPane.showInputDialog(null, "Digite o nome do produto:");
                    if (sistema.verificarSeEstaCadastrado(nomeCase2)) {
                        JOptionPane.showMessageDialog(null, "O produto está cadastrado.");
                    } else if (nomeCase2.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campo de nome vazio.");
                    } else {
                        JOptionPane.showMessageDialog(null, "O produto não está cadastrado.");
                    }
                    break;
                //Pesquisar produto por:
                case "3":
                    boolean contagemCase3 = true;
                    while (contagemCase3) {
                        String opcao3 = JOptionPane.showInputDialog(null, """
                                Digite de qual forma de pesquisa:
                                1 - Por nome.
                                2 - Por tipo.
                                3 - Por código.
                                4 - Sair.""");
                        switch (opcao3) {
                            //Nome.
                            case "1":
                                String nomeCase3 = JOptionPane.showInputDialog(null, "Digite o nome do produto:");
                                if(nomeCase3.isEmpty()){
                                    JOptionPane.showMessageDialog(null, "Campo de nome vazio.");
                                }else{
                                    try {
                                        JOptionPane.showMessageDialog(null, sistema.procurarProdutoPorNome(nomeCase3));
                                    } catch (ProdutoNaoCadastradoException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                    }
                                }

                                break;
                            //Tipo.
                            case "2":
                                String tipoCase3 = JOptionPane.showInputDialog(null, "Digite o tipo do produto.");
                                if(tipoCase3.isEmpty()){
                                    JOptionPane.showMessageDialog(null, "Campo de nome vazio.");
                                }else{
                                    try {
                                        List<Produto> listaProcurarPorTipo = new ArrayList<>(sistema.procurarProdutoPorTipo(tipoCase3));
                                        JOptionPane.showMessageDialog(null, listaProcurarPorTipo);
                                    } catch (TipoNaoExisteException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                    }
                                }
                                break;
                            //Código.
                            case "3":
                                try {
                                    String codigoCase3 = JOptionPane.showInputDialog("Digite o código do seu produto");
                                    JOptionPane.showMessageDialog(null, sistema.procurarProdutoPorCodigo(codigoCase3).toString());
                                } catch (CodigoNaoExisteException e) {
                                    JOptionPane.showMessageDialog(null, "Código inexistente.");
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Valor não reconhecido.");
                                }
                                break;
                            //Encerra o while.
                            case "4":
                                contagemCase3 = false;
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Inválido.");
                                break;
                        }
                        break;
                    }
                    break;
                //Alteração de dados por:
                case "4":
                    String nomeDoProdutoCase4 = JOptionPane.showInputDialog(null, "Digite o nome do produto:");
                    if(nomeDoProdutoCase4.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Campo de nome vazio.");
                    }else{
                        //Inicia menu da opção.
                        boolean contagemCase4 = true;
                        while (contagemCase4) {
                            String opcao4 = JOptionPane.showInputDialog(null, """
                                Digite qual informação alterar:
                                1 - Nome.
                                2 - Tipo.
                                3 - Valor.
                                4 - Código.
                                5 - Quantidade.
                                6 - Sair.""");
                            switch (opcao4) {
                                //Nome.
                                case "1":
                                    String novoNome = JOptionPane.showInputDialog("Digite o novo nome do produto.");
                                    if(novoNome.isEmpty()){
                                        JOptionPane.showMessageDialog(null, "Campo de nome vazio.");
                                    }else{
                                        try {
                                            sistema.alterarNomeDoProduto(nomeDoProdutoCase4, novoNome);
                                            JOptionPane.showMessageDialog(null, "Nome alterado com sucesso.");
                                        } catch (ProdutoJaCadastradoException | ProdutoNaoCadastradoException e) {
                                            JOptionPane.showMessageDialog(null, e.getMessage());
                                        }
                                    }
                                    break;
                                //Tipo.
                                case "2":
                                    String novoTipo = JOptionPane.showInputDialog(null, "Digite o novo tipo do produto.");
                                    if(novoTipo.isEmpty()){
                                        JOptionPane.showMessageDialog(null, "Campo de nome vazio.");
                                    }else{
                                        try {
                                            sistema.alterarTipoDoProduto(nomeDoProdutoCase4, novoTipo);
                                            JOptionPane.showMessageDialog(null, "Tipo alterado com sucesso.");
                                        } catch (ProdutoNaoCadastradoException e) {
                                            JOptionPane.showMessageDialog(null, e.getMessage());
                                        }
                                    }
                                    break;
                                //Valor.
                                case "3":
                                    try {
                                        double novoValor = Double.parseDouble(JOptionPane.showInputDialog("Digite o novo valor do produto."));
                                        sistema.alterarValorDoProduto(nomeDoProdutoCase4, novoValor);
                                        JOptionPane.showMessageDialog(null, "Valor alterado com sucesso.");
                                    } catch (ProdutoNaoCadastradoException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                    } catch (NumberFormatException e) {
                                        JOptionPane.showMessageDialog(null, "Valor não reconhecido.");
                                    }
                                    break;
                                //Código.
                                case "4":
                                    try {
                                        String novoCodigo = JOptionPane.showInputDialog("Digite o novo código do produto.");
                                        sistema.alterarCodigoDoProduto(nomeDoProdutoCase4, novoCodigo);
                                        JOptionPane.showMessageDialog(null, "Codigo alterado com sucesso.");
                                    } catch (ProdutoNaoCadastradoException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                    } catch (NumberFormatException e) {
                                        JOptionPane.showMessageDialog(null, "Valor não reconhecido.");
                                    }
                                    break;
                                //Quantidade.
                                case "5":
                                    try {
                                        int novaQuantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a nova quantidade do produto:"));
                                        sistema.alterarQuantidadeDoProduto(nomeDoProdutoCase4, novaQuantidade);
                                        JOptionPane.showMessageDialog(null, "Nova quantidade alterada com sucesso.");
                                    } catch (ProdutoNaoCadastradoException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                    } catch (NumberFormatException e) {
                                        JOptionPane.showMessageDialog(null, "Valor não reconhecido.");
                                    }
                                    break;
                                //Encerra o while.
                                case "6":
                                    contagemCase4 = false;
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Inválido.");
                                    break;
                            }
                            break;
                        }
                    }
                    break;
                //Adicionar quantidade.
                case "5":
                    String nomeCase5 = JOptionPane.showInputDialog(null, "Digite o nome do produto que você queira adicionar quantidade:");
                    if(nomeCase5.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Campo de nome vazio.");
                    }else{
                        try {
                            int quantCase5 = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a quantidade que você quer adicionar:"));
                            try {
                                sistema.adicionarQuantidadeDoProduto(nomeCase5, quantCase5);
                                JOptionPane.showMessageDialog(null, "Quantidade adicionada com sucesso.");
                            } catch (ProdutoNaoCadastradoException e) {
                                JOptionPane.showMessageDialog(null, e.getMessage());
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Valor não reconhecido");
                        }
                    }
                    break;
                //Remover quantidade.
                case "6":
                    String nomeCase6 = JOptionPane.showInputDialog(null, "Digite o nome do produto que você deseja subtrair quantidade:");
                    if(nomeCase6.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Campo de nome vazio.");
                    }else{
                        try {
                            int quantCase6 = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite q quantidade a ser subtraída:"));
                            try {
                                sistema.removerQuantidadeDoProduto(nomeCase6, quantCase6);
                                JOptionPane.showMessageDialog(null, "Quantidade removida com sucesso.");
                            } catch (ProdutoNaoCadastradoException e) {
                                JOptionPane.showMessageDialog(null, e.getMessage());
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Valor não reconhecido.");
                        }
                    }
                    break;
                //Relatórios.
                case "7":
                    //Inicia menu da opção.
                    boolean contagem7 = true;
                    while (contagem7) {
                        String opcao7 = JOptionPane.showInputDialog(null, """
                                Digite o relatório desejado:
                                1 - Produto com maior quantidade em estoque.
                                2 - Produto com menor quantidade em estoque.
                                3 - Produto com maior valor em estoque.
                                4 - Produto com menor valor em estoque.
                                5 - Relatório geral do estoque.
                                6 - Sair.""");
                        switch (opcao7) {
                            //Maior quantidade.
                            case "1":
                                try {
                                    JOptionPane.showMessageDialog(null, sistema.produtoComMaiorQuantidade());
                                } catch (NaoExisteProdutoException e) {
                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                }
                                break;
                            //Menor quantidade.
                            case "2":
                                try {
                                    JOptionPane.showMessageDialog(null, sistema.produtoComMenorQuantidade());
                                } catch (NaoExisteProdutoException e) {
                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                }
                                break;
                            //Maior valor.
                            case "3":
                                try {
                                    JOptionPane.showMessageDialog(null, sistema.produtoComMaiorValor());
                                } catch (NaoExisteProdutoException e) {
                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                }
                                break;
                            //Menor valor.
                            case "4":
                                try {
                                    JOptionPane.showMessageDialog(null, sistema.produtoComMenorValor());
                                } catch (NaoExisteProdutoException e) {
                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                }
                                break;
                            //Geral.
                            case "5":
                                try {
                                    List<Produto> relatorioGeralImprimir = new ArrayList<>(sistema.relatorioGeral());
                                    JOptionPane.showMessageDialog(null, relatorioGeralImprimir);
                                } catch (NaoExisteProdutoException e) {
                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                }
                                break;
                            case "6":
                                contagem7 = false;
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Inválido.");
                                break;
                        }
                    }
                    break;
                //Salvar.
                case "8":
                    try {
                        sistema.salvarDados();
                        JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Erro, dados não salvos.");
                        e.printStackTrace();
                    }
                    break;
                //Remover.
                case "9":
                    //vai criar uma nova lista vazia e salvar ela
                    sistema.setEstoque(new ArrayList<>());
                    try {
                        sistema.salvarDados();
                        JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso.");
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Erro, dados não salvos.");
                        e.printStackTrace();
                    }
                    break;
                //Encerrar o while.
                case "0":
                    continuar = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Número inválido, tente novamente.");
                    break;
            }
        }
    }
}
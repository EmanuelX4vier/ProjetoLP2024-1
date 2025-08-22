package com.ufpb.dcx.emanuel.xavier.Classes;

import com.ufpb.dcx.emanuel.xavier.Exceptions.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProgramaDeEstoque {
    public static void main(String[] args) {
        // =====================================
        // Inicializa o sistema de estoque
        // =====================================
        InterfaceSistemaDeEstoque sistema = new SistemaDeEstoque();

        // =====================================
        // Tenta recuperar dados salvos anteriormente
        // =====================================
        try {
            sistema.recuperaDados();
            JOptionPane.showMessageDialog(null, "Dados do sistema carregados com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Falha interna, dados não recuperados.");
            e.printStackTrace();
        }

        // =====================================
        // Loop principal do menu
        // =====================================
        boolean continuar = true;
        while (continuar) {
            String opcao = JOptionPane.showInputDialog("""
                    Digite a opção desejada:
                    1 - Cadastrar produto
                    2 - Verificar se produto está cadastrado
                    3 - Procurar produto
                    4 - Alterar informações do produto
                    5 - Relatório geral
                    6 - Salvar dados
                    7 - Recuperar dados
                    0 - Encerrar
                    """);

            switch (opcao) {
                // =====================================
                // Cadastrar produto
                // =====================================
                case "1": {
                    String nome = JOptionPane.showInputDialog("Digite o nome do produto:");
                    if (nome.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campo de nome vazio.");
                        break;
                    }

                    String tipo = JOptionPane.showInputDialog("Digite o tipo do produto:");
                    if (tipo.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campo de tipo vazio.");
                        break;
                    }

                    String codigo = JOptionPane.showInputDialog("Digite o código do produto:");
                    if (codigo.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campo de código vazio.");
                        break;
                    }

                    try {
                        double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do produto:"));
                        int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade:"));

                        try {
                            sistema.cadastrarProduto(nome, tipo, valor, codigo, quantidade);
                            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                        } catch (ProdutoJaCadastradoException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Valor ou quantidade inválida.");
                    }
                    break;
                }

                // =====================================
                // Verificar se produto está cadastrado
                // =====================================
                case "2": {
                    String nomeCase2 = JOptionPane.showInputDialog("Digite o nome do produto:");
                    if (nomeCase2.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campo de nome vazio.");
                    } else if (sistema.verificarSeEstaCadastrado(nomeCase2)) {
                        JOptionPane.showMessageDialog(null, "O produto está cadastrado.");
                    } else {
                        JOptionPane.showMessageDialog(null, "O produto não está cadastrado.");
                    }
                    break;
                }

                // =====================================
                // Procurar produto por nome, tipo ou código
                // =====================================
                case "3": {
                    boolean contagemCase3 = true;
                    while (contagemCase3) {
                        String opcao3 = JOptionPane.showInputDialog("""
                                Digite de qual forma de pesquisa:
                                1 - Por nome
                                2 - Por tipo
                                3 - Por código
                                4 - Sair
                                """);

                        switch (opcao3) {
                            // Pesquisar por nome
                            case "1": {
                                String nomeCase3 = JOptionPane.showInputDialog("Digite o nome do produto:");
                                if (nomeCase3.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Campo de nome vazio.");
                                } else {
                                    try {
                                        JOptionPane.showMessageDialog(null, sistema.procurarProdutoPorNome(nomeCase3));
                                    } catch (ProdutoNaoCadastradoException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                    }
                                }
                                break;
                            }

                            // Pesquisar por tipo
                            case "2": {
                                String tipoCase3 = JOptionPane.showInputDialog("Digite o tipo do produto:");
                                if (tipoCase3.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Campo de tipo vazio.");
                                } else {
                                    try {
                                        if (sistema.verificarSeTipoExiste(tipoCase3)) {
                                            List<Produto> listaProcurarPorTipo = new ArrayList<>(sistema.procurarProdutoPorTipo(tipoCase3));
                                            JOptionPane.showMessageDialog(null, listaProcurarPorTipo);
                                        }
                                    } catch (TipoNaoExisteException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                    }
                                }
                                break;
                            }

                            // Pesquisar por código
                            case "3": {
                                String codigoCase3 = JOptionPane.showInputDialog("Digite o código do produto:");
                                try {
                                    List<Produto> listaProcurarPorCodigo = new ArrayList<>(sistema.procurarProdutoPorCodigo(codigoCase3));
                                    JOptionPane.showMessageDialog(null, listaProcurarPorCodigo);
                                } catch (CodigoNaoExisteException e) {
                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                }
                                break;
                            }

                            // Sair da pesquisa
                            case "4":
                                contagemCase3 = false;
                                break;

                            default:
                                JOptionPane.showMessageDialog(null, "Opção inválida.");
                        }
                    }
                    break;
                }

                // =====================================
                // Alterar dados do produto
                // =====================================
                case "4": {
                    String nomeDoProdutoCase4 = JOptionPane.showInputDialog("Digite o nome do produto:");
                    if (nomeDoProdutoCase4.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campo de nome vazio.");
                        break;
                    }

                    boolean contagemCase4 = true;
                    while (contagemCase4) {
                        String opcao4 = JOptionPane.showInputDialog("""
                                Digite qual informação alterar:
                                1 - Nome
                                2 - Tipo
                                3 - Valor
                                4 - Código
                                5 - Quantidade
                                6 - Sair
                                """);

                        try {
                            switch (opcao4) {
                                case "1":
                                    String novoNome = JOptionPane.showInputDialog("Digite o novo nome do produto:");
                                    sistema.alterarNomeDoProduto(nomeDoProdutoCase4, novoNome);
                                    break;

                                case "2":
                                    String novoTipo = JOptionPane.showInputDialog("Digite o novo tipo do produto:");
                                    sistema.alterarTipoDoProduto(nomeDoProdutoCase4, novoTipo);
                                    break;

                                case "3":
                                    double novoValor = Double.parseDouble(JOptionPane.showInputDialog("Digite o novo valor do produto:"));
                                    sistema.alterarValorDoProduto(nomeDoProdutoCase4, novoValor);
                                    break;

                                case "4":
                                    String novoCodigo = JOptionPane.showInputDialog("Digite o novo código do produto:");
                                    sistema.alterarCodigoDoProduto(nomeDoProdutoCase4, novoCodigo);
                                    break;

                                case "5":
                                    int novaQuantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a nova quantidade:"));
                                    sistema.alterarQuantidadeDoProduto(nomeDoProdutoCase4, novaQuantidade);
                                    break;

                                case "6":
                                    contagemCase4 = false;
                                    break;

                                default:
                                    JOptionPane.showMessageDialog(null, "Opção inválida.");
                            }
                            JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!");
                        } catch (ProdutoJaCadastradoException | ProdutoNaoCadastradoException | NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                    break;
                }

                // =====================================
                // Relatório geral
                // =====================================
                case "5":
                    try {
                        List<Produto> relatorioGeral = sistema.relatorioGeral();
                        JOptionPane.showMessageDialog(null, relatorioGeral);
                    } catch (NaoExisteProdutoException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;

                // =====================================
                // Salvar dados
                // =====================================
                case "6":
                    try {
                        sistema.salvarDados();
                        JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Erro ao salvar dados: " + e.getMessage());
                    }
                    break;

                // =====================================
                // Recuperar dados
                // =====================================
                case "7":
                    try {
                        sistema.recuperaDados();
                        JOptionPane.showMessageDialog(null, "Dados recuperados com sucesso!");
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Erro ao recuperar dados: " + e.getMessage());
                    }
                    break;

                // =====================================
                // Encerrar o programa
                // =====================================
                case "0":
                    JOptionPane.showMessageDialog(null, "Saindo do sistema...");
                    continuar = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida, tente novamente.");
            }
        }
    }
}

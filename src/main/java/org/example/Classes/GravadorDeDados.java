package org.example.Classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GravadorDeDados {

    public static final String DADOS_ESTOQUE = "estoque.txt";

    // Salva produtos em formato texto
    public void gravaProdutos(List<Produto> produtos) throws IOException {
        List<String> textoDosProdutos = new ArrayList<>();
        for(Produto produto : produtos) {
            String textoProduto = produto.getNome() + ";" +
                    produto.getTipo() + ";" +
                    produto.getValor() + ";" +
                    produto.getCodigo() + ";" +
                    produto.getQuantidade();
            textoDosProdutos.add(textoProduto);
        }
        this.gravaTexto(textoDosProdutos);
    }

    // Lê produtos do arquivo e converte para objetos Produto
    public List<Produto> recuperaDados() throws IOException {
        List<Produto> produtosRecuperados = new ArrayList<>();
        List<String> dadosProdutos;
        try {
            dadosProdutos = recuperaTexto();
        } catch (FileNotFoundException e) {
            // Se não existir, retorna lista vazia
            return produtosRecuperados;
        }

        for (String linhaLida : dadosProdutos) {
            String[] dadosProduto = linhaLida.split(";");
            if(dadosProduto.length == 5) { // garante que todos os campos existam
                String nome = dadosProduto[0];
                String tipo = dadosProduto[1];
                double valor = Double.parseDouble(dadosProduto[2]);
                String codigo = dadosProduto[3];
                int quantidade = Integer.parseInt(dadosProduto[4]);

                Produto produto = new Produto(nome, tipo, valor, codigo, quantidade);
                produtosRecuperados.add(produto);
            }
        }
        return produtosRecuperados;
    }

    // Escreve texto no arquivo
    public void gravaTexto(List<String> texto) throws IOException {
        try (BufferedWriter gravador = new BufferedWriter(new FileWriter(DADOS_ESTOQUE))) {
            for (String s : texto) {
                gravador.write(s);
                gravador.newLine();
            }
        }
    }

    // Lê todas as linhas do arquivo
    public List<String> recuperaTexto() throws IOException {
        List<String> textoLido = new ArrayList<>();
        try (BufferedReader leitor = new BufferedReader(new FileReader(DADOS_ESTOQUE))) {
            String texto;
            while ((texto = leitor.readLine()) != null) {
                textoLido.add(texto);
            }
        }
        return textoLido;
    }
}

package org.example.Classes;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GravadorDeDados {

    public static final String DADOS_ESTOQUE = "estoque.txt";

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

    public List<Produto> recuperaDados() throws IOException {
        List<String> dadosProdutos = recuperaTexto();
        List<Produto> produtosRecuperados = new ArrayList<>();
        for (String linhaLida : dadosProdutos) {
            String[] dadosProduto = linhaLida.split(";");
            String nome = dadosProduto[0];
            String tipo = dadosProduto[1];
            double valor = Double.parseDouble(dadosProduto[2]);
            String codigo = (dadosProduto[3]);
            int quantidade = Integer.parseInt(dadosProduto[4]);

            Produto produto = new Produto(nome, tipo, valor, codigo, quantidade);
            produtosRecuperados.add(produto);
        }
        return produtosRecuperados;
    }

    public void gravaTexto(List<String> texto) throws IOException {
        BufferedWriter gravador = null;
        try{
            gravador = new BufferedWriter(new FileWriter(DADOS_ESTOQUE));
            for (String s: texto) {
                gravador.write(s+"\n");
            }
        }finally {
            if(gravador != null) {
                gravador.close();
            }
        }
    }

    public List<String> recuperaTexto() throws IOException {
        BufferedReader leitor = null;
        List<String> textoLido = new ArrayList<>();
        try {
            leitor = new BufferedReader(new FileReader(DADOS_ESTOQUE));
            String texto = null;
            do {
                texto = leitor.readLine();
                if(texto != null) {
                    textoLido.add(texto);
                }
            } while (texto != null);
        }finally {
            if(leitor != null) {
                leitor.close();
            }
        }
        return textoLido;
    }
}
package br.com.screenmatch.models;

import br.com.screenmatch.excecao.ErroDeConversaoDeAnoException;
import com.google.gson.annotations.SerializedName;

public class Titulo implements Comparable<Titulo> {
    @SerializedName("Title") // referencia o title do JSON para titulo do meu programa
    private String nome;
    private int anoLancamento;
    private boolean incluidoNoPlano;
    private double somaDasAvaliacoes;
    private int totalAvaliacoes;
    private int duracaoEmMinutos;

    public Titulo(String nome, int anoLancamento) {
        this.nome = nome;
        this.anoLancamento = anoLancamento;
    }

    public Titulo (TituloOMDB meuTituloOmbd) {
        this.nome = meuTituloOmbd.title();


        if (meuTituloOmbd.year().length() > 4) {
            throw new ErroDeConversaoDeAnoException("Não consegui converter o ano porque tem mais de 04 caracteres.");
        }
        this.anoLancamento = Integer.valueOf(meuTituloOmbd.year());
        this.duracaoEmMinutos = Integer.valueOf(meuTituloOmbd.runtime().substring(0, 2));
    }

    public String getNome() {
        return nome;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public boolean isIncluidoNoPlano() {
        return incluidoNoPlano;
    }

    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public int getTotalAvaliacoes() {
        return totalAvaliacoes;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public void setIncluidoNoPlano(boolean incluidoNoPlano) {
        this.incluidoNoPlano = incluidoNoPlano;
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public void exibeFichaTecnica() {
        System.out.println("Nome do filme: " + nome);
        System.out.println("Ano de lançamento: " + anoLancamento);
    }

    public void avalia(double nota) {
        somaDasAvaliacoes += nota;
        totalAvaliacoes++;
    }

    public double obterMedia() {
        return somaDasAvaliacoes / totalAvaliacoes;
    }

    @Override
    public int compareTo(Titulo outroTitulo) {
        return this.getNome().compareTo(outroTitulo.getNome()); // ai nao precisa fazer um monte de if comparando kkk
    }

    @Override
    public String toString() {
        return "(nome = " + nome +
                ", anoLancamento = " + anoLancamento + ", " +
                "duração = " + duracaoEmMinutos + ")";
    }
}

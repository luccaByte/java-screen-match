package br.com.screenmatch.listas;

import br.com.screenmatch.models.Filme;
import br.com.screenmatch.models.Serie;
import br.com.screenmatch.models.Titulo;

import java.util.*;

public class Listas {
    public static void main(String[] args) {
        Filme filme1 = new Filme("Deadpool x Wolverine", 2024);
        filme1.setDuracaoEmMinutos(180);
        filme1.avalia(9);
        Filme filme2 = new Filme("Dogville", 2003);
        filme2.setDuracaoEmMinutos(200);
        filme2.avalia(6);
        Serie serie = new Serie("Lost", 2000);
        serie.exibeFichaTecnica();

       //  ArrayList<Titulo> listas = new ArrayList<>();
        List<Titulo> listas = new LinkedList<>();
        listas.add(filme1);
        listas.add(filme2);
        listas.add(serie);

        for(Titulo item : listas) {
            System.out.println(item.getNome());

            // nao é tao elegante, o ideal é evitar
            if (item instanceof Filme filme && filme.getClassificacao() > 2) {
                System.out.println("Classificação: " + filme.getClassificacao());
            }
        }

        ArrayList<String> buscaPorArtista = new ArrayList<>();
        buscaPorArtista.add("Adam Sandler");
        buscaPorArtista.add("Frieren");
        buscaPorArtista.add("Fern");

        System.out.println(buscaPorArtista);

        Collections.sort(buscaPorArtista);
        System.out.println("Depois da ordenação: " + buscaPorArtista);

        Collections.sort(listas);
        System.out.println("Lista de titulos ordenadas: " + listas);

        listas.sort(Comparator.comparing(Titulo::getAnoLancamento));
        System.out.println("Ordenando por ano: " + listas);
    }
}

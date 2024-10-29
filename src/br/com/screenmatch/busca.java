package br.com.screenmatch;

import br.com.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.screenmatch.models.Titulo;
import br.com.screenmatch.models.TituloOMDB;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class busca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();
        String busca = "";

        // aqui configura o GSON para usar uma política de nomenclatura específica para os campos ao serializar e deserializar no json
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (!busca.equalsIgnoreCase("sair")) {

            System.out.println("Digite um filme para busca: ");
            busca = sc.nextLine();

            if (busca.equalsIgnoreCase("sair")) {
                break;
            }

            String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=59fc3cd2";
            System.out.println(endereco);

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                System.out.println(json);

                TituloOMDB meuTituloOmdb = gson.fromJson(json, TituloOMDB.class);
                System.out.println(meuTituloOmdb);

                Titulo meutitulo = new Titulo(meuTituloOmdb);
                System.out.println("Titulo convertido: " + meutitulo);

                titulos.add(meutitulo);

            } catch (NumberFormatException e) {
                System.out.println("Aconteceu um erro: ");
                System.err.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Algum erro de argumento na busca, verifique o endereço");
            } catch (ErroDeConversaoDeAnoException e) {
                System.out.println(e.getMessage());
            }
        }

        FileWriter arquivoJson = new FileWriter("filmes.json");
        arquivoJson.write(gson.toJson(titulos));
        arquivoJson.close();

        System.out.println(titulos);
        System.out.println("Programa finalizou corretamente");
        sc.close();
    }
}

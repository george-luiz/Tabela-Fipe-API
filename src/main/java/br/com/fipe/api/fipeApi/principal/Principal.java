package br.com.fipe.api.fipeApi.principal;

import br.com.fipe.api.fipeApi.model.Automoveis;
import br.com.fipe.api.fipeApi.model.DadosAutomoveis;
import br.com.fipe.api.fipeApi.model.DadosModelos;
import br.com.fipe.api.fipeApi.model.Veiculo;
import br.com.fipe.api.fipeApi.services.ConsumoApi;
import br.com.fipe.api.fipeApi.services.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private static final String URLAPI = "https://parallelum.com.br/fipe/api/v1/";
    private static final String MARCAS = "/marcas/";
    private static final String MODELOS = "/modelos/";
    private static final String ANOS = "/anos/";
    Scanner scanner = new Scanner(System.in);
    ConsumoApi consumoApi = new ConsumoApi();

    ConverteDados converteDados = new ConverteDados();

    public void exibeMenu() {

        System.out.println("Qual o tipo de automovel que deseja pesquisar ?");
        System.out.println(Automoveis.carros);
        System.out.println(Automoveis.motos);
        System.out.println(Automoveis.caminhoes);
        var automovel = scanner.nextLine();
        var urlCompleta = "";

        if(automovel.toLowerCase().contains("carr")) {
            urlCompleta = URLAPI + "carros" + MARCAS;
        } else if ( automovel.toLowerCase().contains("mot")) {
            urlCompleta = URLAPI + "motos" + MARCAS;
        } else if (automovel.toLowerCase().contains("caminh")) {
            urlCompleta = URLAPI + "caminhoes" + MARCAS;
        }
        else {
            System.out.println("o tipo do automovel não foi reconhecido");
            System.out.println("tipos validos");
            System.out.println(Automoveis.carros);
            System.out.println(Automoveis.motos);
            System.out.println(Automoveis.caminhoes);
        }

        String json = consumoApi.obterDados(urlCompleta);
        var dadosAutomoveis = converteDados.obterList(json, DadosAutomoveis.class);
        dadosAutomoveis.stream()
                .forEach(System.out::println);

        System.out.println("Informe um código da marca para consulta? ");
        String cogidoMarca = scanner.nextLine();

        String urlModelos = urlCompleta + cogidoMarca + MODELOS;
        json = consumoApi.obterDados(urlModelos);
        var dadosModelos = converteDados.obterDados(json, DadosModelos.class);
        dadosModelos.modelos().stream()
                .forEach(System.out::println);

        System.out.println("Digite o trecho do nome do veiculo para consulta:");
        String marca = scanner.nextLine();

        dadosModelos.modelos().stream()
                .filter(n -> n.nome().toLowerCase().contains(marca.toLowerCase()))
                .forEach(System.out::println);

        System.out.println("Digite um código do modelo para consultar valores:");
        int codigoModelo = scanner.nextInt();

        String urlCodigoModelo = urlModelos + codigoModelo + ANOS;
        json = consumoApi.obterDados(urlCodigoModelo);

        List<DadosAutomoveis> anos = converteDados.obterList(json, DadosAutomoveis.class);
//        anos.stream().forEach(System.out::println);

        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            String urlCodigoAno = urlCodigoModelo + anos.get(i).codigo();
            json = consumoApi.obterDados(urlCodigoAno);
            Veiculo veiculo = converteDados.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }

        veiculos.stream().forEach(System.out::println);
    }
}

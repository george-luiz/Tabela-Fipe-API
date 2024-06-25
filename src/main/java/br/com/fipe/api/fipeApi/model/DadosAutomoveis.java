package br.com.fipe.api.fipeApi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutomoveis(
        @JsonAlias("codigo") String codigo,
        @JsonAlias("nome") String nome
) {
}

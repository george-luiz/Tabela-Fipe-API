package br.com.fipe.api.fipeApi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModelos(
        @JsonAlias("modelos") List<DadosAutomoveis> modelos
) {
}

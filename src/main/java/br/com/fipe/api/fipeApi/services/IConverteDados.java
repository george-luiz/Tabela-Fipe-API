package br.com.fipe.api.fipeApi.services;

import java.util.List;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe); //<T> T: Irá retornar um tipo generico

    <T> List<T> obterList(String json, Class<T> classe);
}

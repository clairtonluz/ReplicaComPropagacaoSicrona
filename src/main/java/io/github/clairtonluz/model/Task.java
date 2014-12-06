package io.github.clairtonluz.model;

import java.util.Properties;

/**
 * Created by clairton on 11/15/14.
 */
public interface Task<T> {
    T execute();
    String getTipo();
    boolean isSucesso();
    void setArquivoBase(Properties arquivoBase);
}
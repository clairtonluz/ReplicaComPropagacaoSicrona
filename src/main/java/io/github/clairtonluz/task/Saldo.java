package io.github.clairtonluz.task;

import io.github.clairtonluz.model.Task;

import java.io.Serializable;
import java.util.Properties;


/**
 * Created by clairton on 11/15/14.
 */
public class Saldo implements Task<Double>, Serializable {

    private static final long serialVersionUID = 2272L;

    private String conta;
    private Properties arquivoBase;
    private boolean sucesso;

    public Saldo(String conta) {
        super();
        this.conta = conta;
    }

    @Override
    public Double execute() {
        String valor = arquivoBase.getProperty(conta);
        double saldo = 0;
        if(valor != null && !valor.isEmpty()){
            saldo = Double.parseDouble(valor);
        }
        setSucesso(true);
        System.out.printf("Saldo de %.2f na conta do(a) %s%n", saldo, conta);
        return saldo;
    }

    @Override
    public String getTipo() {
        return this.getClass().getSimpleName();
    }


    @Override
    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public Properties getArquivoBase() {
        return arquivoBase;
    }

    public void setArquivoBase(Properties arquivoBase) {
        this.arquivoBase = arquivoBase;
    }

    @Override
    public String getConta() {
        return conta;
    }
}

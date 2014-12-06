package io.github.clairtonluz.task;

import io.github.clairtonluz.model.Task;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Properties;

/**
 * Created by clairton on 11/15/14.
 */
public class Sacar implements Task<Double>, Serializable {
    private static final long serialVersionUID = 227L;

    private Properties arquivoBase;

    private boolean sucesso;
    private String conta;
    private double valor;

    public Sacar(String conta, double valor) {
        super();
        this.conta = conta;
        this.valor = valor;

    }

    @Override
    public Double execute() {
        double novoSaldo = valor;
        String saldo = arquivoBase.getProperty(conta);
        if (saldo != null && !saldo.isEmpty()) {
            novoSaldo -= Double.parseDouble(saldo);
        }
        arquivoBase.setProperty(conta, String.valueOf(novoSaldo));
        arquivoBase.setProperty("ultimaAlteracao" + conta, LocalDateTime.now().toString());
        setSucesso(true);
        System.out.printf("Saque de %.2f na conta do(a) %s%n", valor, conta);
        return novoSaldo;
    }

    @Override
    public String getTipo() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean isSucesso() {
        return sucesso;
    }

    @Override
    public void setArquivoBase(Properties arquivoBase) {
        this.arquivoBase = arquivoBase;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    @Override
    public String getConta() {
        return conta;
    }
}

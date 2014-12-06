package io.github.clairtonluz;

import io.github.clairtonluz.task.Depositar;
import io.github.clairtonluz.task.Sacar;
import io.github.clairtonluz.task.Saldo;

/**
 * Created by clairton on 12/5/14.
 */
public class Cliente {

    private final String conta;

    public Cliente(String conta) {
        this.conta = conta;
    }

    public Depositar depositar(double valor) throws java.rmi.RemoteException {
        synchronized (this) {
            Depositar depositar = new Depositar(conta, valor);
            return depositar;
        }
    }

    public Sacar sacar(double valor) throws java.rmi.RemoteException {
        synchronized (this) {
            Sacar sacar = new Sacar(conta, valor);
            return sacar;
        }
    }

    public Saldo saldo() throws java.rmi.RemoteException {
        Saldo saldo = new Saldo(conta);
        return saldo;
    }
}

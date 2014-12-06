package io.github.clairtonluz;

import io.github.clairtonluz.constants.Replica;
import io.github.clairtonluz.model.Compute;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by clairton on 12/5/14.
 */
public class Main {

    public static void main(String... args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", Replica.PRIMARIA.getPort());
            Compute banco = (Compute) registry.lookup(Replica.PRIMARIA.name());

            Cliente clairton = new Cliente("Clairton");
            Cliente rafael = new Cliente("Rafael");
            Cliente caio = new Cliente("Caio");
            Cliente felipe = new Cliente("Felipe");

            System.out.println("D SALDO = " + banco.executeTask(clairton.depositar(100)));
            System.out.println("D SALDO = " + banco.executeTask(rafael.depositar(100)));
            System.out.println("D SALDO = " + banco.executeTask(caio.depositar(100)));
            System.out.println("D SALDO = " + banco.executeTask(felipe.depositar(100)));
            System.out.println("S SALDO = " + banco.executeTask(clairton.sacar(100)));
            System.out.println("S SALDO = " + banco.executeTask(caio.sacar(100)));
            System.out.println("SALDO = " + banco.executeTask(caio.saldo()));
            System.out.println("SALDO = " + banco.executeTask(clairton.saldo()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package io.github.clairtonluz;

import io.github.clairtonluz.servico.ReplicaPrimaria;
import io.github.clairtonluz.servico.ReplicaSecundaria;
import io.github.clairtonluz.servico.Server;

/**
 * Created by clairton on 12/5/14.
 */
public class MainServer {
    public static void main(String... args) {
        Server replicaPrimaria = new ReplicaPrimaria();
        Server replicaSecundaria = new ReplicaSecundaria();

        replicaPrimaria.start();
        replicaSecundaria.start();
    }
}

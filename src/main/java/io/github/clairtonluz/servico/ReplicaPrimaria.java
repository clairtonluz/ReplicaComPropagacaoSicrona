package io.github.clairtonluz.servico;

import io.github.clairtonluz.constants.Replica;
import io.github.clairtonluz.model.Compute;
import io.github.clairtonluz.model.Task;
import io.github.clairtonluz.util.PropertiesUtil;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by clairton on 12/5/14.
 */
public class ReplicaPrimaria extends Server {

    public ReplicaPrimaria() {
        super(Replica.PRIMARIA);
    }

    @Override
    public <T> T executeTask(Task<T> t) throws RemoteException {
        T result = super.executeTask(t);

        if(t.getTipo().equalsIgnoreCase("Depositar") || t.getTipo().equalsIgnoreCase("Sacar")) {
            executarEmReplicaSecundaria(t);
        }

        return result;
    }

    private <T> T executarEmReplicaSecundaria(Task<T> t) throws RemoteException {
        T retorno = null;
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", Replica.SECUNDARIA.getPort());
            Compute secundaria = (Compute) registry.lookup(Replica.SECUNDARIA.name());

            retorno = secundaria.executeTask(t);
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        return retorno;
    }

}

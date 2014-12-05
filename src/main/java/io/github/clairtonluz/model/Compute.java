package io.github.clairtonluz.model;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by clairton on 11/15/14.
 */
public interface Compute extends Remote {
    <T> T executeTask(Task<T> t) throws RemoteException;
}

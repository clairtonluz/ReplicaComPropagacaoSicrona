package io.github.clairtonluz.model;

import io.github.clairtonluz.util.PropertiesUtil;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;

/**
 * Created by clairton on 12/5/14.
 */
public class Replica implements Compute {
    private static final String EXTENSION = ".properties";
    private static final String HOME = System.getProperty("user.home");

    private Properties arquivoBase;
    private File log;
    private String name;
    private int port;

    public Replica(String name, int port) {
        this.name = name;
        this.port = port;
        this.arquivoBase = PropertiesUtil.INSTANCE.load(String.format("%s/%s", HOME, name, EXTENSION));
        this.log = new File(String.format("%s/%s", HOME, name, EXTENSION));
        if (!log.exists()) {
            criarAquivoLog();
        }
    }

    public void start(){

    }

    private void criarAquivoLog() {
        try {
            log.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getArquivoBase() {
        return arquivoBase;
    }

    public File getLog() {
        return log;
    }

    public String getName() {
        return name;
    }

    @Override
    public <T> T executeTask(Task<T> t) throws RemoteException {
        return null;
    }
}

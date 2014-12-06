package io.github.clairtonluz.servico;

import io.github.clairtonluz.constants.Replica;
import io.github.clairtonluz.model.Compute;
import io.github.clairtonluz.model.Task;
import io.github.clairtonluz.util.FileUtil;
import io.github.clairtonluz.util.PropertiesUtil;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * Created by clairton on 12/5/14.
 */
public abstract class Server implements Compute {
    private static final String HOME = System.getProperty("user.home");
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    protected final String arquivoBasePath;

    protected Properties arquivoBase;
    private File log;
    private Replica replica;

    public Server(Replica replica) {
        this.replica = replica;
        arquivoBasePath = String.format("%s/%s.properties", HOME, replica.name());

        this.arquivoBase = PropertiesUtil.INSTANCE.load(arquivoBasePath);
        this.log = new File(String.format("%s/%s.log", HOME, replica.name()));

        if (!log.exists()) {
            criarAquivoLog();
        }
    }

    public void start(){
        registrarRMI();
        try {
            Compute stub = (Compute) UnicastRemoteObject
                    .exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry(replica.getPort());
            registry.rebind(replica.name(), stub);
            System.out.printf("Server %s:%d iniciado%n", replica.name(), replica.getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registrarRMI() {
        try {
            LocateRegistry.createRegistry(replica.getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected <T> void log(Task<T> t) {
        StringBuilder sb = new StringBuilder();
        sb.append(LocalDateTime.now().format(FORMATTER));
        sb.append(t.isSucesso() ? " [SUCESSO] " : " [FAIL] ");
        sb.append("TIPO: ");
        sb.append(t.getTipo().toUpperCase());
        sb.append(" USUARIO: ");
        sb.append(t.getConta());

        sb.append(System.getProperty("line.separator"));
        FileUtil.INSTANCE.write(log, sb.toString());
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

    @Override
    public <T> T executeTask(Task<T> t) throws RemoteException {
        t.setArquivoBase(arquivoBase);
        T result = t.execute();
        PropertiesUtil.INSTANCE.write(arquivoBase, arquivoBasePath);
        log(t);
        return result;
    }
}

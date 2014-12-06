package io.github.clairtonluz.util;

import java.io.*;
import java.util.Properties;

/**
 * Created by clairton on 12/5/14.
 */
public enum PropertiesUtil {

    INSTANCE;

    public Properties load(String filePath){
        Properties prop = new Properties();
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (InputStream input = new FileInputStream(file)) {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public void write(Properties prop, String filePath) {
        try (OutputStream output = new FileOutputStream(filePath)) {
            prop.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

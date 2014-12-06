package io.github.clairtonluz.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * Created by clairton on 12/5/14.
 */
public enum FileUtil {

    INSTANCE;

    public String read(File file){
        StringBuilder sb = new StringBuilder();
        try {
            Files.lines(file.toPath())
                    .forEach(s -> sb.append(s).append(System.getProperty("line.separator")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void write(File file, String text){
        try {
            Files.write(file.toPath(), text.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void override(File file, String text){
        try {
            Files.write(file.toPath(), text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

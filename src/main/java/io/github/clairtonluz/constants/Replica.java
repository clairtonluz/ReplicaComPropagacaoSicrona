package io.github.clairtonluz.constants;

import java.io.File;
import java.io.IOException;

/**
 * Created by clairton on 11/15/14.
 */
public enum Replica {

    PRIMARIA, SECUNDARIA;

    private static final String HOME = System.getProperty("user.home");

    Replica() {
    }

    public int getPort() {
        return ordinal() + 1099;
    }

}

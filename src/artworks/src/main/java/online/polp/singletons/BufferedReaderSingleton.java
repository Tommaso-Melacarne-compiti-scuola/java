package online.polp.singletons;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BufferedReaderSingleton {
    private static final BufferedReader INSTANCE =  new BufferedReader(new InputStreamReader(System.in));

    private BufferedReaderSingleton() {
    }

    public static BufferedReader getInstance() {
        return INSTANCE;
    }
}

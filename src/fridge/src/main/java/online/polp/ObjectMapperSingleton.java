package online.polp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperSingleton {
    private static final ObjectMapper INSTANCE = new ObjectMapper();

    static {
        INSTANCE.registerModule(new JavaTimeModule());
    }

    private ObjectMapperSingleton() {
    }

    public static ObjectMapper getInstance() {
        return INSTANCE;
    }
}

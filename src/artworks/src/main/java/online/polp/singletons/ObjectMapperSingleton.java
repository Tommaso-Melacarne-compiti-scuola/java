package online.polp.singletons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperSingleton {
    private static final ObjectMapper INSTANCE = new ObjectMapper();

    static {
        INSTANCE.registerModule(new JavaTimeModule());
        INSTANCE.enable(SerializationFeature.INDENT_OUTPUT);
    }

    private ObjectMapperSingleton() {
    }

    public static ObjectMapper getInstance() {
        return INSTANCE;
    }
}

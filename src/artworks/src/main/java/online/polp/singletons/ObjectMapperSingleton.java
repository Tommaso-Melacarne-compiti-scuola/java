package online.polp.singletons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import online.polp.Painting;
import online.polp.Sculpture;

public class ObjectMapperSingleton {
    private static final ObjectMapper INSTANCE = new ObjectMapper();

    static {
        INSTANCE.registerModule(new JavaTimeModule());
        INSTANCE.enable(SerializationFeature.INDENT_OUTPUT);

        INSTANCE.registerSubtypes(new NamedType(Sculpture.class, "sculpture"));
        INSTANCE.registerSubtypes(new NamedType(Painting.class, "painting"));
    }

    private ObjectMapperSingleton() {
    }

    public static ObjectMapper getInstance() {
        return INSTANCE;
    }
}

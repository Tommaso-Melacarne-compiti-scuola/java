package online.polp.pojos;

import java.util.List;

public record Biblioteca(
        Scaffale scaffale,
        List<Lettore> codaLettori
) {
}
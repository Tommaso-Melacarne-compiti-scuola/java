package online.polp;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Dictionary {
    /**
     * A dictionary that maps Italian words to their English translation.
     */
    Map<String, String> dictionary = new HashMap<>();

    public Dictionary() {
    }

    public void addWord(String italianWord, String englishWord) {
        dictionary.put(italianWord, englishWord);
    }

    public String translate(String italianWord) {
        String englishWord = dictionary.get(italianWord);

        if (englishWord == null) {
            return italianWord + " not found in the dictionary";
        }

        return englishWord;
    }
}

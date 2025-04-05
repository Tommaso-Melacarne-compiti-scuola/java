package online.polp.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public static <T extends Number> List<List<T>> chunkList(List<T> list, int chunkSize) {
        int size = list.size();
        List<List<T>> chunks = new ArrayList<>(size / chunkSize + 1);

        for (int i = 0; i < size; i += chunkSize) {
            int end = Math.min(i + chunkSize, size);
            chunks.add(list.subList(i, end));
        }

        return chunks;
    }
}

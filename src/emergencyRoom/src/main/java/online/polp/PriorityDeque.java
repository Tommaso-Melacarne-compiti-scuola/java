package online.polp;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PriorityDeque<T> {
    private final Map<Priority, Deque<T>> map = new HashMap<>();

    public PriorityDeque() {
        for (Priority priority : Priority.values()) {
            map.put(priority, new ArrayDeque<>());
        }
    }

    /**
     * Adds an element to the deque with the given priority.
     *
     * @param priority the priority of the element
     * @param element  the element to add
     */
    public void add(Priority priority, T element) {
        map.get(priority).add(element);
    }

    /**
     * Removes the given element from the deque.
     *
     * @param element the element to remove
     */
    public void remove(T element) {
        map.values().forEach(deque -> deque.remove(element));
    }

    /**
     * Removes the element with the highest priority from the deque.
     *
     * @return the element with the highest priority, or {@code null} if the deque is empty
     */
    public T pop() {
        for (Priority priority : Priority.values()) {
            if (!map.get(priority).isEmpty()) {
                return map.get(priority).poll();
            }
        }

        return null;
    }

    public Map<Priority, Integer> sizeForEachPriority() {
        return map
            .entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    entry -> entry.getValue().size()
                )
            );
    }

}

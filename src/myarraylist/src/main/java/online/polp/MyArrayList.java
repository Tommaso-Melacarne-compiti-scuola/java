package online.polp;

import java.util.Arrays;

public class MyArrayList<T> {
    private T[] elements;
    private int logicalLength;

    /**
     * Creates a new instance of MyArrayList.
     */

    public MyArrayList() {
        this(1);
    }

    /**
     * Creates a new instance of MyArrayList with the specified initial capacity.
     *
     * @param initialCapacity The initial capacity of the list.
     */
    @SuppressWarnings("unchecked")
    public MyArrayList(int initialCapacity) {
        elements = (T[]) new Object[initialCapacity];
        logicalLength = 0;
    }


    /**
     * Adds an element to the end of the list.
     *
     * @param element The element to add.
     */
    public void add(T element) {
        if (logicalLength + 1 > elements.length) {
            allocateMoreMemory();
        }

        elements[logicalLength] = element;
        logicalLength++;
    }


    /**
     * Adds an element to the list at the specified index.
     *
     * @param index   The index at which to add the element.
     * @param element The element to add.
     */
    public void add(int index, T element) {
        if (index < 0 || index > logicalLength) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        if (logicalLength + 1 > elements.length) {
            allocateMoreMemory();
        }

        for (int i = logicalLength; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;
        logicalLength++;
    }


    /**
     * Gets the element at the specified index.
     *
     * @param index The index of the element to get.
     * @return The element at the specified index.
     */
    public T get(int index) {
        return elements[index];
    }


    /**
     * Removes the element at the specified index.
     *
     * @param index The index of the element to remove.
     * @return True if an element was removed, false otherwise.
     */
    public boolean remove(int index) {
        if (index < 0 || index >= logicalLength) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        for (int i = index; i < logicalLength - 1; i++) {
            elements[i] = elements[i + 1];
        }

        logicalLength--;

        return true;
    }

    /**
     * Removes the element at the specified index matching the given predicate.
     *
     * @param predicate The predicate to match.
     * @return True if an element was removed, false otherwise.
     */
    public boolean removeIf(MyPredicate<T> predicate) {
        boolean removed = false;

        for (int i = 0; i < logicalLength; i++) {
            if (predicate.test(elements[i])) {
                removeAt(i);
                removed = true;
            }
        }

        return removed;
    }

    /**
     * Removes the element at the specified index.
     *
     * @param index The index of the element to remove.
     */
    public void removeAt(int index) {
        for (int i = index; i < logicalLength - 1; i++) {
            elements[i] = elements[i + 1];
        }

        logicalLength--;
    }


    /**
     * Returns true if the list contains the specified element.
     *
     * @param element The element to check for.
     * @return True if the list contains the element, false otherwise.
     */
    public boolean contains(T element) {
        for (int i = 0; i < logicalLength; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }

        return false;
    }


    /**
     * Sets an element at the specified index.
     *
     * @param index   The index at which to set the element.
     * @param element The element to set.
     */
    public void set(int index, T element) {
        elements[index] = element;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return The number of elements in the list.
     */
    public int size() {
        return logicalLength;
    }

    /**
     * Returns true if the list is empty.
     *
     * @return True if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return logicalLength == 0;
    }

    /**
     * Returns the first index of the specified element.
     *
     * @param element The element to find.
     * @return The index of the element, or -1 if the element is not found.
     */
    public int indexOf(T element) {
        for (int i = 0; i < logicalLength; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }


    /**
     * Internal method to allocate more memory for the list.
     */
    private void allocateMoreMemory() {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity * 2;

        @SuppressWarnings("unchecked") T[] newElements = (T[]) new Object[newCapacity];

        //noinspection ManualArrayCopy
        for (int i = 0; i < logicalLength; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;
    }

    @Override
    public String toString() {
        return "MyArrayList " + Arrays.toString(elements);
    }
}

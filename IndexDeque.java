// Winston Osei-Bonsu
// CS 0445 Fall 2020
// This class is a subclass of the MyDeque class and implements Indexable interface.
// The Indexable interface implements methods that allow for values in the deque to be
// retrieved or manipulated.

public class IndexDeque<T> extends MyDeque<T> implements Indexable<T>  {

    public IndexDeque(int size) {
        super(size);
    }

    public T getFront(int i) {
        // Edge Case: Checks if the specified index is out of bounds.
        if(size < i + 1)
            throw new IndexOutOfBoundsException("Illegal Index " + i);

        // Normal Case.
        // Avoids checking for edge case via modulus.
        int curIndex = + (front + i) % data.length;
        return data[curIndex];
    }

    public T getBack(int i) {
        // Edge Case: Checks if the specified index is out of bounds.
        if(size < i + 1)
            throw new IndexOutOfBoundsException("Illegal Index " + i);

        int curIndex = back - i;

        // Checks Edge case: Checks if the array has reached its physical front.
        if (curIndex < 0) {
            return data[curIndex + data.length];
        }
        else {
            return data[curIndex];
        }
    }

    public void setFront(int i, T item) {
        // Edge Case: Checks if the specified index is out of bounds.
        if(size < i + 1)
            throw new IndexOutOfBoundsException("Illegal Index " + i);

        // Normal Case.
        // Avoids checking for edge case via modulus.
        int curIndex = (front + i) % data.length;
        data[curIndex] = item;
    }

    public void setBack(int i, T item) {
        // Edge Case: Checks if the specified index is out of bounds.
        if(size < i + 1)
            throw new IndexOutOfBoundsException("Illegal Index " + i);

        // Checks Edge case: Checks if the array has reached its physical front.
        int curIndex = back - i;
        if (curIndex < 0) {
            data[curIndex + data.length] = item;
        }
        else {
            data[curIndex] = item;
        }
    }

    public int size() {
        return super.size();
    }
}

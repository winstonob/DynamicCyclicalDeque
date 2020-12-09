// Winston Osei-Bonsu
// CS 0445 Fall 2020
// This class is a subclass of the IndexDeque class and implements IndexableAddRemove interface.
// The IndexableAddRemove interface implements methods that allow for values in the deque to be
// removed and added. However, this feature requires values in the array to be shifted
// upon removal and insertion.

public class IndexAddRemoveDeque<T> extends IndexDeque<T> implements IndexableAddRemove<T> {
    public IndexAddRemoveDeque(int size) {
        super(size);
    }

    public void addToFront(int i, T item) {
        // Edge Case: Checks if the specified index is out of bounds.
        if(size < i) {
            throw new IndexOutOfBoundsException("Illegal Index " + i);
        }

        // Edge Case: Checks if the is full.
        if(isFull()) {
            doubleCapacity();
        }

        // Determines index to be replaced.
        int replaceIndex = (front + i) % data.length;

        // Required variables for shifting.
        int prevIndex = front;
        // Edge Case: Checks if the array has reached its physical front.
        if (frontReachedStart()) {
            front = data.length - 1;
        }
        else {
            front--;
        }

        // Shifts data.
        for(int k = 0; k <= i; k++) {
            // Normal Case
            // Avoids checking for edge case via modulus
            data[(front + k) % data.length] = data[prevIndex];
            prevIndex = (prevIndex + 1) % data.length;
        }

        //Replacing and Resizing
        data[replaceIndex - 1] = item;
        size++;
    }

    public void addToBack(int i, T item) {
        // Edge Case: Checks if the specified index is out of bounds.
        if(size < i) {
            throw new IndexOutOfBoundsException("Illegal Index " + i);
        }

        // Edge Case: Checks if the is full.
        if(isFull()) {
            doubleCapacity();
        }

        // Determines index to be replaced.
        int replaceIndex;
        // Edge Case: Checks if the array has reached its physical back.
        if (back - i < 0) {
            replaceIndex = back - i + data.length;
        }
        else {
            replaceIndex = back - i;
        }

        // Required variables for shifting.
        int prevIndex = back;
        back = (back + 1) % data.length;

        // Shifts Data.
        for(int k = 0; k <= i; k++) {
            if(back - k < 0)
                data[back - k + data.length] = data[prevIndex];
            else
                data[back - k] = data[prevIndex];

            if(prevIndex - 1 < 0)
                prevIndex = data.length - 1;
            else
                prevIndex--;

        }

        // Replacing and Resizing
        data[replaceIndex + 1] = item;
        size++;
    }

    public T removeFront(int i) {
        // Edge Case: Checks if the specified index is out of bounds.
        if(size < i + 1) {
            throw new IndexOutOfBoundsException("Illegal Index " + i);
        }

        // Determines the index to be removed and stores it.
        int removedIndex = (front + i) % data.length;
        T temp = data[removedIndex];

        // Required variables for shifting.
        int previousIndex;
        int curIndex;

        // Shifts Data.
        for(int k = 0; k < i; k++) {
            if (removedIndex - k < 0)
                curIndex = removedIndex - k + data.length;
            else
                curIndex = removedIndex - k;

            if (curIndex - 1 < 0)
                previousIndex = curIndex - 1 + data.length;
            else
                previousIndex = curIndex - 1;

            data[curIndex] = data[previousIndex];
        }

        //Erases index and update array properties.
        data[front] = null;
        front = (front + 1) % i;
        size--;

        return temp;
    }

    public T removeBack(int i) {
        // Edge Case: Checks if the specified index is out of bounds.
        if(size < i + 1) {
            throw new IndexOutOfBoundsException("Illegal Index " + i);
        }

        // Determines the index to be removed and stores it.
        // Required variables for shifting.
        int removedIndex;
        if(back - i < 0) {
            removedIndex = back - i + data.length;
        }
        else {
            removedIndex = back - i;
        }
        T temp = data[removedIndex];

        // Shifts Data.
        for(int k = 0; k < i; k++) {
            data[(removedIndex + k) % data.length] = data[(removedIndex + k + 1) % data.length];
        }

        // Erases index and update array properties.
        data[back] = null;
        if (back == 0) {
            back = data.length - 1;
        }
        else {
            back--;
        }
        size--;

        return temp;
    }

}

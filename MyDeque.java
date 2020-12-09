// Winston Osei-Bonsu
// CS 0445 Fall 2020
// This class implements the DequeInterface to create a
// Generic Double-Ended Queue. This structure is cyclical in
// nature. If an item is added to the logical front/back of the deque and
// the physical front/back of the data array is reached, the item and its corresponding index
// will wrap around to the other end of the array.
// This structure is dynamic. If the array reaches max capacity, the array will be reconstructed with
// double the previous size.

public class MyDeque<T> implements DequeInterface<T>{

    protected T[] data;
    protected int front;
    protected int back;
    protected int size;

    @SuppressWarnings("unchecked")
    public MyDeque(int size) {
        data = (T[]) new Object[size];
        this.size = 0;
        front = -1;
        back = -1;
    }

    @SuppressWarnings("unchecked")
    public MyDeque(MyDeque<T> old) {
        front = old.front;
        back = old.back;
        size = old.size;

        //Deep copying data.
        //The reference of the new array and old array are not shared, but their data is.
        data = (T[]) new Object[old.data.length];
        for(int i = 0; i < size; i++) {
            data[i] = old.data[i];
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Contents: ");
        for(int i = 0; i < size; i++)
            str.append(data[(front + i) % data.length]).append(" ");

        return str.toString();
    }

    public void addToFront(T newEntry) {
        // Checks Edge case: Checks if the array is empty.
        if(isEmpty()) {
            front = 0;
            back = 0;
            data[front] = newEntry;
            size++;
        }
        // Checks Edge case: Checks if the array is full.
        else if (isFull()) {
            doubleCapacity();
            front = data.length - 1;
            data[front] = newEntry;
            size++;
        }
        // Checks Edge case: Checks if the array has reached its physical front.
        else if(frontReachedStart()) {
            front = data.length - 1;
            data[front] = newEntry;
            size++;
        }
        // Normal Case.
        else {
            front--;
            data[front] = newEntry;
            size++;
        }
    }

    public void addToBack(T newEntry) {
        //Checks Edge Case: Checks if the array is empty.
        if(isEmpty()) {
            front = 0;
            back = 0;
            data[back] = newEntry;
            size++;
        }
        //Checks Edge Case: Checks if the array is full.
        else if (isFull()) {
            doubleCapacity();
            back++;
            data[back] = newEntry;
            size++;
        }
        // Normal Case.
        // Avoids checking for edge case via modulus.
        else {
            back = (back + 1) % data.length;
            data[back] = newEntry;
            size++;
        }
    }

    public T removeFront() {
        // Edge Case: Checks if array is empty.
        if (isEmpty()) {
            return null;
        }
        // Normal Case.
        // Avoids checking for edge case via modulus.
        else {
            T temp = data[front];
            data[front] = null;
            front = (front + 1) % data.length;
            size--;

            return temp;
        }
    }

    public T removeBack() {
        // Edge Case: Checks when array is empty.
        if (isEmpty()) {
            return null;
        }
        // Edge Case: Checks when the array has reached its physical front.
        else if (backReachedStart()) { T temp = data[back];
            data[back] = null;
            back = data.length - 1;
            size--;

            return temp;
        }
        // Normal Case.
        else {
            T temp = data[back];
            data[back] = null;
            back--;
            size--;

            return temp;
        }
    }

    public T getFront() {
        // Edge Case: Checks when array is empty.
        if(isEmpty()) {
            return null;
        }
        // Normal Case.
        else {
            return data[front];
        }
    }

    public T getBack() {
        // Edge Case: Checks when array is empty.
        if(isEmpty()) {
            return null;
        }
        // Normal Case.
        else {
            return data[back];
        }
    }

    public boolean equals(MyDeque<T> rhs) {
        //Compares array size.
        if (rhs.size != size) {
            return false;
        }
        //Compares array values.
        for (int i = 0; i < size; i++) {
            if(!rhs.data[(rhs.front + i) % rhs.data.length].equals(data[(front + i) % data.length]))
                return false;
        }

        return true;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    protected boolean frontReachedStart() {
        return front == 0;
    }

    protected boolean backReachedStart() {
        return back == 0;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    @SuppressWarnings("unchecked")
    public void clear() {
        data = (T[]) new Object[data.length];
        size = 0;
        front = -1;
        back = -1;
    }

    @SuppressWarnings("unchecked")
    protected void doubleCapacity() {
        T[] temp = (T[]) new Object[size * 2];

        for (int i = 0; i < size; i++)
            temp[i] = data[(front + i) % size];

        data = temp;
        front = 0;
        back = size - 1;
    }
}

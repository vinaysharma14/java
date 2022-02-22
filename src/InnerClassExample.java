import java.util.Iterator;

class Array {
    private static final int SIZE = 30;
    private final int[] array = new int[SIZE];

    Array() {
        for (int i = 0; i < SIZE; i++) {
            array[i] = i;
        }
    }

    public void printArray() {
        ArrayIterator iterator = this.new ArrayIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private class ArrayIterator implements Iterator<Integer> {
        private int iterator = 0;

        public boolean hasNext() {
            return iterator < Array.SIZE;
        }

        public Integer next() {
            int currentElement = array[iterator];

            if (this.hasNext()) {
                this.iterator++;
            }

            return currentElement;
        }
    }
}

class InnerClassExample {
    public static void main() {
        Array array = new Array();
        array.printArray();
    }
}
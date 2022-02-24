import java.util.function.Function;

class DataStructure {
    private final static int SIZE = 15;
    private int[] arrayOfInts = new int[SIZE];

    public static int getSize() {
        return SIZE;
    }

    public int[] getArrayOfInts() {
        return arrayOfInts;
    }

    public DataStructure() {
        for (int i = 0; i < SIZE; i++) {
            arrayOfInts[i] = i;
        }
    }

    public void printEven() {
        DataStructureIterator iterator = this.new EvenIterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        System.out.println();
    }

    interface DataStructureIterator extends java.util.Iterator<Integer> {
    }

    private class EvenIterator implements DataStructureIterator {
        private int nextIndex = 0;

        public boolean hasNext() {
            return (nextIndex <= SIZE - 1);
        }

        public Integer next() {
            int retValue = arrayOfInts[nextIndex];
            nextIndex += 2;

            return retValue;
        }
    }

    public DataStructureIterator getEvenIterator() {
        return new EvenIterator();
    }

    public void print(DataStructureIterator iterator) {
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        System.out.println();
    }

    public void print(Function<Integer, Boolean> iterator) {
        for (int i = 0; i < SIZE; i++) {
            if (iterator.apply(i)) {
                System.out.print(arrayOfInts[i] + " ");
            }
        }

        System.out.println();
    }

    public static Boolean isEvenIndex(Integer index) {
        if (index % 2 == 0) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    public static Boolean isOddIndex(Integer index) {
        if (index % 2 != 0) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}

public class MethodReferenceExercise {
    public static void main() {
        DataStructure ds = new DataStructure();
        ds.printEven();

        // instance of EvenIterator
        ds.print(ds.getEvenIterator());

        // an anonymous class to print odd index elements
        ds.print(new DataStructure.DataStructureIterator() {
            private int nextIndex = 1;

            public boolean hasNext() {
                return nextIndex <= DataStructure.getSize() - 1;
            }

            public Integer next() {
                int retValue = ds.getArrayOfInts()[nextIndex];
                nextIndex += 2;

                return retValue;
            }
        });

        // lambda expressions to print even and odd index elements
        ds.print((Integer index) -> index % 2 == 0);
        ds.print((Integer index) -> index % 2 != 0);

        // lambda expressions which can be converted into method references
        ds.print((Integer index) -> DataStructure.isEvenIndex((index)));
        ds.print((Integer index) -> DataStructure.isOddIndex((index)));

        // method references used
        ds.print(DataStructure::isEvenIndex);
        ds.print(DataStructure::isOddIndex);
    }
}

import java.util.Arrays;

interface VehicleInterfaceA {
    void changeSpeed(int speed);
}

interface VehicleInterfaceB {
    int getSpeed();
}

// this way we can implement multiple interfaces
class Vehicle implements VehicleInterfaceA, VehicleInterfaceB {
    private int speed = 10;

    public Vehicle() {
        this.speed = 100;
    }

    // constructor overloading
    public Vehicle(int speed) {
        this.speed = speed;
    }

    public void changeSpeed(int newSpeed) {
        speed += newSpeed;
    }

    public int getSpeed() {
        return speed;
    }
}

class Bicycle extends Vehicle {
    private final boolean isGeared = true;

    public Bicycle(int speed) {
        super(speed);
    }
}

class BasicOOPS {
    public static void main() {
        int integer = 'a'; // why
        char character = 0; // why

        String foo = "foo";
        System.out.println(foo.equals("FOO".toLowerCase()));

        Bicycle myCycle = new Bicycle(9);
        myCycle.changeSpeed(100);

        System.out.println(myCycle.getSpeed());
        System.out.println(myCycle instanceof Vehicle);

        int[] intCollection = {0, 1, 2};
        int[] emptyIntCollection = new int[10];
        emptyIntCollection[0] = 0;

        char[] emptyCharCollection = new char[2];
        emptyCharCollection[0] = 'a';

        System.out.println(intCollection.length);
        System.out.println(emptyIntCollection.length);
        System.out.println(emptyCharCollection.length);

        String[] allCoffee = {
                "Affogato", "Americano", "Cappuccino", "Corretto", "Cortado",
                "Doppio", "Espresso", "Frappucino", "Freddo", "Lungo", "Macchiato",
                "Marocchino", "Ristretto"
        };

        String[] allCoffeeCopy = {
                "Affogato", "Americano", "Cappuccino", "Corretto", "Cortado",
                "Doppio", "Espresso", "Frappucino", "Freddo", "Lungo", "Macchiato",
                "Marocchino", "Ristretto"
        };

        System.out.println(Arrays.toString(allCoffee));
        System.out.println(Arrays.equals(allCoffee, allCoffeeCopy));

        String[] myCoffee = Arrays.copyOfRange(allCoffee, 1, 4);

        for (String coffee : myCoffee) {
            System.out.println(coffee);
        }
    }
}
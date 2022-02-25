import java.util.Arrays;

enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

enum Planet {
    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    EARTH(5.976e+24, 6.37814e6),
    MARS(6.421e+23, 3.3972e6),
    JUPITER(1.9e+27, 7.1492e7),
    SATURN(5.688e+26, 6.0268e7),
    URANUS(8.686e+25, 2.5559e7),
    NEPTUNE(1.024e+26, 2.4746e7);

    private final double mass;
    private final double radius;
    public static final double G = 6.67300E-11;

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }

    public double getMass() {
        return this.mass;
    }

    public double getRadius() {
        return this.radius;
    }

    public double getSurfaceGravity() {
        return (G * mass) / (radius * radius);
    }

    public double getSurfaceWeight(double otherMass) {
        return otherMass * this.getSurfaceGravity();
    }
}

class EnumTest {
    public Day day;

    EnumTest(Day day) {
        this.day = day;
    }

    public void tellItLikeItIs() {
        switch (day) {
            case MONDAY: {
                System.out.println("Mondays are bad!");
                break;
            }

            case FRIDAY: {
                System.out.println("Fridays are better.");
                break;
            }

            case SATURDAY:
            case SUNDAY: {
                System.out.println("Weekends are best!");
                break;
            }

            default: {
                System.out.println("Midweek days are so-so.");
                break;
            }
        }
    }
}

public class EnumsExample {
    public static void main(String[] args) {
        // just called the methods on instances
        // instead of declaring an object first
        new EnumTest(Day.MONDAY).tellItLikeItIs();
        new EnumTest(Day.WEDNESDAY).tellItLikeItIs();
        new EnumTest(Day.FRIDAY).tellItLikeItIs();
        new EnumTest(Day.SATURDAY).tellItLikeItIs();

        // values() is an out-of-the box method on enums
        System.out.println(Arrays.toString(Day.values()));

        if (args.length == 0) {
            System.err.println("Usage: java Planet <earth_weight>");
            System.exit(-1);
        }

        double earthWeight = Double.parseDouble(args[0]);
        double mass = earthWeight / Planet.EARTH.getSurfaceGravity();

        for (Planet p : Planet.values()) {
            System.out.printf("Your weight on %s is %f%n", p, p.getSurfaceWeight(mass));
        }
    }
}

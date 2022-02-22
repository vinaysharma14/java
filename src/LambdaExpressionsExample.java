import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

class Person {
    // enum is static and final implicitly
    enum Sex {
        MALE,
        FEMALE,
    }

    private Sex gender;
    private String name;
    private LocalDate birthday;
    private String emailAddress;

    public Person(Sex gender, String name, LocalDate birthday, String emailAddress) {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.emailAddress = emailAddress;
    }

    public Sex getGender() {
        return this.gender;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public int getAge() {
        return this.birthday.until(LocalDate.now()).getYears();
    }

    public void printPerson() {
        System.out.println(this.name + ", " + this.getAge());
    }

    public static int compareAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }

    public static List<Person> createRoster() {
        List<Person> roster = new ArrayList<Person>();

        roster.add(new Person(Sex.MALE, "Fred", LocalDate.of(1980, 6, 20), "fred@example.com"));
        roster.add(new Person(Sex.FEMALE, "Jane", LocalDate.of(1990, 7, 15), "jane@example.com"));
        roster.add(new Person(Sex.MALE, "George", LocalDate.of(1991, 8, 13), "george@example.com"));
        roster.add(new Person(Sex.MALE, "Bob", LocalDate.of(2000, 9, 12), "bob@example.com"));

        return roster;
    }
}

public class LambdaExpressionsExample {
    interface Tester {
        public boolean test(Person person);
    }

    // approach 1: a very brittle and restrictive method
    private static void printPersonsOlderThan(List<Person> roster, int age) {
        System.out.println("===== printPersonsOlderThan() =====");

        for (Person person : roster) {
            if (person.getAge() >= age) {
                person.printPerson();
            }
        }
    }

    // approach 2: slightly generic but still brittle and strongly coupled with just age
    private static void printPersonWithinAgeRange(List<Person> roster, int lowerAge, int upperAge) {
        System.out.println("\n===== printPersonWithinAgeRange() =====");

        for (Person person : roster) {
            if (lowerAge <= person.getAge() && person.getAge() <= upperAge) {
                person.printPerson();
            }
        }
    }

    // approach 3: less brittle but extra code required for writing class and interface
    public static void printPersonWithTester(List<Person> roster, Tester tester) {
        System.out.println("\n===== printPersonWithTester() =====");

        for (Person person : roster) {
            if (tester.test(person)) {
                person.printPerson();
            }
        }
    }

    // approach 4: uses inbuilt generic types Predicate and Consumer
    public static void printPersonWithGenerics(List<Person> roster, Predicate<Person> tester, Consumer<Person> operation) {
        System.out.println("\n===== printPersonWithGenerics() =====");

        for (Person person : roster) {
            if (tester.test(person)) {
                operation.accept(person);
            }
        }
    }

    // approach 5: uses inbuilt generic types
    public static void printPersonWithMoreGenerics(
            List<Person> roster,
            Predicate<Person> tester,
            Function<Person, String> mapper,
            Consumer<String> printer
    ) {
        System.out.println("\n===== printPersonWithMoreGenerics() =====");

        for (Person person : roster) {
            if (tester.test(person)) {
                String data = mapper.apply(person);
                printer.accept(data);
            }
        }
    }

    // approach 6: entirely generic method where X is input type and Y is output type
    public static <X, Y> void printPersonWithAllGenerics(
            List<X> source,
            Predicate<X> tester,
            Function<X, Y> mapper,
            Consumer<Y> printer
    ) {
        System.out.println("\n===== printPersonWithAllGenerics() =====");

        for (X s : source) {
            if (tester.test(s)) {
                Y data = mapper.apply(s);
                printer.accept(data);
            }
        }
    }

    public static void main() {
        List<Person> roster = Person.createRoster();

        printPersonsOlderThan(roster, 30);
        printPersonWithinAgeRange(roster, 30, 45);

        class TesterClass implements Tester {
            public boolean test(Person person) {
                return 30 <= person.getAge()
                        && person.getAge() <= 45
                        && person.getGender() == Person.Sex.FEMALE;
            }
        }

        // local class
        printPersonWithTester(roster, new TesterClass());

        // anonymous class
        printPersonWithTester(
                roster,
                new Tester() {
                    public boolean test(Person person) {
                        return 30 <= person.getAge()
                                && person.getAge() <= 45
                                && person.getGender() == Person.Sex.FEMALE;
                    }
                }
        );

        // lambda expression
        printPersonWithTester(
                roster,
                person -> 30 <= person.getAge()
                        && person.getAge() <= 45
                        && person.getGender() == Person.Sex.FEMALE
        );

        // lambda expressions
        printPersonWithGenerics(
                roster,
                person -> 30 <= person.getAge()
                        && person.getAge() <= 45
                        && person.getGender() == Person.Sex.FEMALE,
                person -> person.printPerson()
        );

        // lambda expressions
        printPersonWithMoreGenerics(
                roster,
                person -> 30 <= person.getAge()
                        && person.getAge() <= 45
                        && person.getGender() == Person.Sex.FEMALE,
                person -> person.getEmailAddress(),
                data -> System.out.println(data)
        );

        // lambda expressions
        printPersonWithAllGenerics(
                roster,
                person -> 30 <= person.getAge()
                        && person.getAge() <= 45
                        && person.getGender() == Person.Sex.FEMALE,
                person -> person.getEmailAddress(),
                data -> System.out.println(data)
        );

        System.out.println("\n===== steam() =====");
        roster
                .stream()
                .filter(person -> 30 <= person.getAge()
                        && person.getAge() <= 45
                        && person.getGender() == Person.Sex.MALE
                )
                .map(person -> person.getEmailAddress())
                .forEach(emailAddress -> System.out.println(emailAddress));
        ;
    }
}

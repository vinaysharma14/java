import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Supplier;
import java.util.function.BiFunction;

public class MethodReferenceExample {
    private static <T> T biFunction(T a, T b, BiFunction<T, T, T> merger) {
        return merger.apply(a, b);
    }

    private String appendStrings(String a, String b) {
        return a + b;
    }

    private static String appendStringsStaticMethod(String a, String b) {
        return a + b;
    }

    private static void printPersonArray(Person[] array) {
        for (Person person : array) {
            System.out.println("Person: " + person.getName() + " " + person.getAge());
        }
    }

    private static <T, S extends Collection<T>, D extends Collection<T>> D transferElements(
            S sourceCollection,
            Supplier<D> destinationCollectionFactory
    ) {
        D destinationCollection = destinationCollectionFactory.get();

        for (T t : sourceCollection) {
            destinationCollection.add(t);
        }

        return destinationCollection;
    }

    public static void main() {
        List<Person> roster = Person.createRoster();
        Person[] rosterAsArray = roster.toArray(new Person[roster.size()]);

        class PersonAgeComparator implements Comparator<Person> {
            public int compare(Person personA, Person personB) {
                return personA.getBirthday().compareTo(personB.getBirthday());
            }
        }

        // arrays.sort method expects a comparator
        Arrays.sort(rosterAsArray, new PersonAgeComparator());

        // as comparator is a functional interface we can directly use lambda expression instead
        Arrays.sort(rosterAsArray, (Person personA, Person personB) -> personA.getBirthday().compareTo(personB.getBirthday()));

        // we can reuse the static compareAge method on the parent class itself as well
        Arrays.sort(rosterAsArray, (Person personA, Person personB) -> Person.compareAge(personA, personB));

        // as the above lambda expression was doing nothing at all by itself and just calling
        // an existing method, it's better to directly refer to the existing method instead
        Arrays.sort(rosterAsArray, Person::compareAge);

        printPersonArray(rosterAsArray);

        String s1 = "Hello ", s2 = "World!";
        MethodReferenceExample methodReferenceExample = new MethodReferenceExample();

        System.out.println(s1.concat(s2)); // inbuilt String function
        System.out.println(biFunction(s1, s2, String::concat)); // arbitrary object instance method reference
        System.out.println(biFunction(s1, s2, (String a, String b) -> a + b)); // lambda expression
        System.out.println(biFunction(s1, s2, methodReferenceExample::appendStrings)); // instance method reference
        System.out.println(biFunction(s1, s2, MethodReferenceExample::appendStringsStaticMethod)); // static method reference

        String[] stringArray = {"Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda"};

        Arrays.sort(stringArray); // normal sort
        Arrays.sort(stringArray, (String a, String b) -> a.compareTo(b)); // sort with a lambda expression
        Arrays.sort(stringArray, String::compareTo); // equivalent to above with reference to instance method of String

        System.out.println(Arrays.toString(stringArray));

        HashSet<Person> rosterSetWithLambda = transferElements(roster, () -> new HashSet()); // lambda expression
        // or new HashSet<> or new HashSet<Person>

        HashSet<Person> rosterSetWithRef = transferElements(roster, HashSet::new); // constructor reference
        // or HashSet<>::new or HashSet<Person>::new

    }
}

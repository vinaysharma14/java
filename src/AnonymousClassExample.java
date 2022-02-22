class HelloWorld {
    interface Greeting {
        public void greet();

        public void greetWithName(String name);
    }

    public void sayHello() {
        // this is a local class declaration here
        class EnglishGreeting implements Greeting {
            private final String greeting = "Hi";

            public void greet() {
                System.out.println(greeting);
            }

            public void greetWithName(String name) {
                System.out.println(greeting + " " + name + "!");
            }
        }

        Greeting englishGreeting = new EnglishGreeting();

        // this is an anonymous class declaration here, and it always needs either an interface
        // or a super class. it can't have a constructor as this class doesn't have a name
        Greeting frenchGreeting = new Greeting() {
            private final String greeting = "Bonjour";

            public void greet() {
                System.out.println(greeting);
            }

            public void greetWithName(String name) {
                System.out.println(greeting + " " + name + "!");
            }
        };

        englishGreeting.greet();
        englishGreeting.greetWithName("Vinay");

        frenchGreeting.greet();
        frenchGreeting.greetWithName("Vinay");
    }
}

public class AnonymousClassExample {
    public static void main() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.sayHello();
    }
}

// optional interface methods
// why interface is not enforcing variables
// how to declare abstract variables in an interface
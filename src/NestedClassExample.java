class OuterClass {
    private int value;
    private static int staticValue = 0;

    OuterClass(int value) {
        this.value = value;
        staticValue++;
    }

    public void incrementValue() {
        this.value++;
    }

    public int accessValue() {
        return this.value;
    }

    public static int accessStaticValue() {
        return staticValue;
    }

    class InnerClass {
        // inner classes cannot declare static members because if the JVM has a special location
        // for static members, then it won't make much sense to pollute that space with static
        // members of inner class unless an instance of the outer class has been created to further
        // create an instance of an inner class hence, the below line of code is invalid
        // static int innerStaticValue = 1;

        // for further explanation refer these
        // https://www.quora.com/Why-can-inner-classes-in-Java-not-have-static-initializers
        // https://www.quora.com/Why-can%E2%80%99t-inner-class-methods-be-static-in-Java

        // this is fine as it's a compile-time constant
        static final int innerStaticValue = 1;

        // this is not a compile-constant as they can only be of
        // either a primitive type or String (because its immutable)
        // static final Integer innerStaticValue = 1;

        public int incrementAndAccessOuterClassValue() {
            OuterClass.this.incrementValue();
            return value;
        }

        public int accessOuterClassStaticValue() {
            return staticValue;
        }
    }

    protected static class StaticNestedClass {
        // works fine as the class isn't associated
        // with an instance of the outer class
        static int foo = 1;

        public int accessOuterClassStaticValue() {
            return staticValue;
        }

        public static int accessOuterClassStaticValueWithStaticMethod() {
            return staticValue;
        }
    }
}

class NestedClassExample {
    public static void main() {
        OuterClass outerClassObject = new OuterClass(10);

        // an object can obviously access its own instance members and static members
        System.out.println("outerClassObject value " + outerClassObject.accessValue());
        System.out.println("outerClassObject staticValue " + OuterClass.accessStaticValue());
        System.out.println();

        // an inner class can also access the instance members and static members
        OuterClass.InnerClass innerClassObject = outerClassObject.new InnerClass();
        System.out.println("innerClassObject value " + innerClassObject.incrementAndAccessOuterClassValue());
        System.out.println("innerClassObject staticValue " + innerClassObject.accessOuterClassStaticValue());
        System.out.println();

        // a static nested class can access only the static members of the parent class
        OuterClass.StaticNestedClass staticNestedClassObject = new OuterClass.StaticNestedClass();
        System.out.println("staticNestedClassObject staticValue " + staticNestedClassObject.accessOuterClassStaticValue());
        System.out.println("staticNestedClassObject staticValue " + OuterClass.StaticNestedClass.accessOuterClassStaticValueWithStaticMethod());
    }
}
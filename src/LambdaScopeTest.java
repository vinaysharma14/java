import java.util.function.Consumer;

public class LambdaScopeTest {
    private int x = 0;

    class FirstLevel {
        private int x = 1;

        void methodInFirstLevel(int x) {
            int z = 2; // has to be final or effectively final

            // the lambda expression here is lexically scoped, doesn't cause shadowing issues
            // and, it doesn't introduce a new level of scoping, so it works as if declared in
            // the enclosing scope. and as it works as if declared in the enclosing scope, the parameter
            // cannot be named as that of a local variable because "already defined in scope" error will throw
            Consumer<Integer> consumer = (y) -> {
                // the access of fields, methods, members remains same as if this
                // body of lambda expression was written in the enclosing scope
                System.out.println("x: " + x);
                System.out.println("y: " + y);
                System.out.println("z: " + z);
                System.out.println("this.x: " + this.x);
                System.out.println("LambdaScopeTest.this.x: " + LambdaScopeTest.this.x);
            };

            consumer.accept(x);
        }
    }

    public static void main() {
        LambdaScopeTest lambdaScopeTest = new LambdaScopeTest();
        LambdaScopeTest.FirstLevel firstLevel = lambdaScopeTest.new FirstLevel();

        firstLevel.methodInFirstLevel(23);
    }
}

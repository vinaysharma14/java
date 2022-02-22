class Calculator {
    interface IntegerMath {
        int operation(int valueA, int valueB);
    }

    public int operateBinary(int valueA, int valueB, IntegerMath intMath) {
        return intMath.operation(valueA, valueB);
    }

    public static void main() {
        Calculator calculatorApp = new Calculator();

        // lambda function declarations
        IntegerMath addition = (valueA, valueB) -> valueA + valueB;
        IntegerMath subtraction = (valueA, valueB) -> valueA - valueB;

        System.out.println(calculatorApp.operateBinary(1, 2, addition));
        System.out.println(calculatorApp.operateBinary(1, 2, subtraction));
    }
}
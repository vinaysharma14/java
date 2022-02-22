class PhoneNumberApp {
    private static final String phoneNumberRegExp = "[^0-9]";

    public static void validatePhoneNumbers(String phoneNumberInput) {
        // only final or effectively final local
        // variables can be accessed by a local class
        final int phoneNumberLength = 10;

        /*
         cannot declare a local interface as
         they are inherently static in nature

         interface PhoneNumberInterface {
            String printPhoneNumberInput();
            String getFormattedPhoneNumber();
         }
        */

        class PhoneNumber {
            // similar to inner classes, a static value or
            // method cannot be declared in a local class
            // static int staticValue = 1;

            // this is fine as it's a compile-time constant
            static final int innerStaticValue = 1;

            private String formattedPhoneNumber = "";

            PhoneNumber(String phoneNumber) {
                String formattedPhoneNumber = phoneNumber.replaceAll(phoneNumberRegExp, "");

                // the local class captures the local variables, here `phoneNumberLength`
                if (formattedPhoneNumber.length() == phoneNumberLength) {
                    this.formattedPhoneNumber = formattedPhoneNumber;
                }
            }

            public String getPhoneNumberInput() {
                return phoneNumberInput;
            }

            public String getFormattedPhoneNumber() {
                return this.formattedPhoneNumber;
            }
        }

        // instantiate the local class
        PhoneNumber phoneNumber = new PhoneNumber(phoneNumberInput);

        // the local class can also capture the enclosing method's parameters
        System.out.println(phoneNumber.getPhoneNumberInput());

        // access the instance member of the local class
        if (phoneNumber.getFormattedPhoneNumber() == null) {
            System.out.println("Phone number is invalid " + phoneNumberInput);
        } else {
            System.out.println("Phone number is valid " + phoneNumberInput);
        }
    }
}

public class LocalClassExample {
    public static void main() {
        String phoneNumberInput = "0-123-456-789";

        PhoneNumberApp.validatePhoneNumbers(phoneNumberInput);
    }
}

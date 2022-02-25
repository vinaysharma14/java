import java.lang.Override;
import java.lang.Deprecated;
import java.lang.SuppressWarnings;

import java.lang.annotation.Target;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.ElementType;

@Documented // to make the information in below annotation appear in the JavaDoc generated documentation
@interface ClassPreamble {
    String author();

    String date();

    int currentRevision() default 1;

    String lastModified() default "N/A";

    String lastModifiedBy() default "N/A";

    String[] reviewers();
}

@Repeatable(ScheduleContainer.class)
@Target(ElementType.METHOD) // restricts the annotation to be applied only to methods
@interface Schedule {
    int hour() default 12;

    String dayOfWeek() default "Mon";

    String dayOfMonth() default "first";
}

/**
 * the target of a container annotation must be
 * the subset of the target of individual annotation
 */
@Target(ElementType.METHOD)
@interface ScheduleContainer {
    Schedule[] value();
}

@ClassPreamble(
        date = "3/17/2002",
        author = "John Doe",
        currentRevision = 6,
        lastModified = "4/12/2004",
        lastModifiedBy = "Jane Doe",
        reviewers = {"Alice", "Bill", "Cindy"}
)
class ClassWithDeprecatedMethod {
    // repetition of the Schedule annotation
    @Schedule(dayOfMonth = "last")
    @Schedule(dayOfWeek = "Fri", hour = 23)
    public void periodicMethod() {
    }

    /**
     * @deprecated Javadoc comment explaining why it was deprecated
     */
    @Deprecated
    static void deprecatedMethod() {
        System.out.println("deprecatedMethod()");
    }
}

public class AnnotationExample {
    @SuppressWarnings("deprecation")
    public static void main() {
        ClassWithDeprecatedMethod.deprecatedMethod();
    }
}

package seedu.address.financialtracker.model.expense;

import static java.util.Objects.requireNonNull;

public class Type {

    public static final String MESSAGE_CONSTRAINTS =
            "Type should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String value;

    public Type(String desc) {
        requireNonNull(desc);
        this.value = desc;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidType(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }
}

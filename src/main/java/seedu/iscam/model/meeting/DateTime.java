package seedu.iscam.model.meeting;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a meeting's date and time in the iscam book.
 * Guarantees: immutable; is valid as declared in {@link #isStringValidDateTime(String)}
 */
public class DateTime {
    public static final String MESSAGE_INVALID_FORMAT = "The given date-time is invalid. Possibly due to: \n"
            + ". Incorrect format (Should be of the format of dd-MM-yyyy HH:mm)\n"
            + ". Invalid date (e.g. 29-02-2021, a date that is only valid in a leap year)";
    public static final String MESSAGE_IN_PAST = "Date and time cannot be in the past.";
    public static final DateTimeFormatter DATETIME_PATTERN = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);

    public final LocalDateTime dateTime;

    /**
     * Constructs a {@code DateTime} with a {@code String dateTime}
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        this.dateTime = LocalDateTime.parse(dateTime, DATETIME_PATTERN);
    }

    /**
     * Checks if {@code string} follows the correct date-time format.
     */
    public static boolean isStringValidFormat(String str) {
        try {
            LocalDateTime validDateTime = LocalDateTime.parse(str, DATETIME_PATTERN);
            return true;
        } catch (DateTimeParseException exception) {
            return false;
        }
    }

    /**
     * Checks if {@code string} can be converted into a valid {@code DateTime}.
     */
    public static boolean isStringValidDateTime(String str) {
        try {
            LocalDateTime toVerify = LocalDateTime.parse(str, DATETIME_PATTERN);
            LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
            return toVerify.isEqual(now) || toVerify.isAfter(now);
        } catch (DateTimeParseException exception) {
            return false;
        }
    }

    public LocalDateTime get() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return dateTime.format(DATETIME_PATTERN);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof DateTime && this.dateTime.isEqual(((DateTime) other).dateTime));
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }
}

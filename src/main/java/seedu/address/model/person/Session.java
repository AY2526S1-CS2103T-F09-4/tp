package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a training session slot for a person.
 * Guarantees: immutable; is valid as declared in {@link #fromString(String)}
 */
public class Session {

    public static final String MESSAGE_CONSTRAINTS_FORMAT = "Error: Session format must be either: "
            + "YYYY-MM-DD HH:MM OR [WEEKLY|BIWEEKLY|MONTHLY]:[DAY] HH:MM";
    public static final String MESSAGE_CONSTRAINTS_TIME = "Error: Invalid time. Use 24-hour format (00:00–23:59).";
    public static final String MESSAGE_CONSTRAINTS_DAY = "Error: Invalid day. Use MON, TUE, etc.";
    public static final String MESSAGE_CONSTRAINTS_PAST_DATE = "Error: Session date cannot be in the past.";

    private static final Pattern ONE_OFF_PATTERN = Pattern.compile(
            "^(?<date>\\d{4}-\\d{2}-\\d{2})\\s+(?<time>\\d{2}:\\d{2})$");
    private static final Pattern RECURRING_PATTERN = Pattern.compile(
            "^(?<type>WEEKLY|BIWEEKLY|MONTHLY):(?<spec>[^\\s]+)\\s+(?<time>\\d{2}:\\d{2})$",
            Pattern.CASE_INSENSITIVE);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);
    private static final int MONTH_LOOKAHEAD = 24; // For conflict checks between monthly and weekly/biweekly sessions

    private final SessionType type;
    private final LocalDateTime oneOffDateTime;
    private final DayOfWeek dayOfWeek;
    private final int dayOfMonth;
    private final LocalTime time;
    private final String value;

    private final Clock clock;

    private Session(SessionType type, LocalDateTime oneOffDateTime, DayOfWeek dayOfWeek,
            int dayOfMonth, LocalTime time, String value, Clock clock) {
        this.type = type;
        this.oneOffDateTime = oneOffDateTime;
        this.dayOfWeek = dayOfWeek;
        this.dayOfMonth = dayOfMonth;
        this.time = time;
        this.value = value;
        this.clock = clock;
    }

    /**
     * Creates a {@code Session} by parsing the provided {@code raw} string.
     */
    public static Session fromString(String raw) {
        return fromString(raw, Clock.systemDefaultZone());
    }

    /**
     * Internal factory that allows injecting a {@link Clock} for testing.
     */
    static Session fromString(String raw, Clock clock) {
        requireNonNull(raw);
        requireNonNull(clock);
        String trimmed = raw.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS_FORMAT);
        }

        Matcher oneOffMatcher = ONE_OFF_PATTERN.matcher(trimmed);
        if (oneOffMatcher.matches()) {
            LocalDate date = parseDate(oneOffMatcher.group("date"));
            LocalTime time = parseTime(oneOffMatcher.group("time"));
            LocalDateTime dateTime = LocalDateTime.of(date, time);
            if (dateTime.isBefore(LocalDateTime.now(clock))) {
                throw new IllegalArgumentException(MESSAGE_CONSTRAINTS_PAST_DATE);
            }
            String canonical = dateTime.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
            return new Session(SessionType.ONE_OFF, dateTime, null, -1, time, canonical, clock);
        }

        Matcher recurringMatcher = RECURRING_PATTERN.matcher(trimmed.toUpperCase(Locale.ROOT));
        if (recurringMatcher.matches()) {
            SessionType type = SessionType.fromString(recurringMatcher.group("type"));
            LocalTime time = parseTime(recurringMatcher.group("time"));
            switch (type) {
            case WEEKLY:
            case BIWEEKLY:
                DayOfWeek day = parseDayOfWeek(recurringMatcher.group("spec"));
                String canonicalWeekly = type + ":" + day.name() + " " + formatTime(time);
                return new Session(type, null, day, -1, time, canonicalWeekly, clock);
            case MONTHLY:
                int dayOfMonth = parseDayOfMonth(recurringMatcher.group("spec"));
                String canonicalMonthly = type + ":" + String.format(Locale.ROOT, "%02d", dayOfMonth)
                        + " " + formatTime(time);
                return new Session(type, null, null, dayOfMonth, time, canonicalMonthly, clock);
            default:
                throw new IllegalStateException("Unhandled session type: " + type);
            }
        }

        throw new IllegalArgumentException(MESSAGE_CONSTRAINTS_FORMAT);
    }

    /**
     * Returns true if the given {@code raw} string can be parsed into a {@code Session}.
     */
    public static boolean isValidSession(String raw) {
        try {
            fromString(raw);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS_FORMAT, ex);
        }
    }

    private static LocalTime parseTime(String timeStr) {
        try {
            return LocalTime.parse(timeStr, TIME_FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS_TIME, ex);
        }
    }

    private static DayOfWeek parseDayOfWeek(String raw) {
        try {
            return DayOfWeek.valueOf(raw.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS_DAY, ex);
        }
    }

    private static int parseDayOfMonth(String raw) {
        try {
            int value = Integer.parseInt(raw);
            if (value < 1 || value > 31) {
                throw new NumberFormatException();
            }
            return value;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS_FORMAT, ex);
        }
    }

    private static String formatTime(LocalTime time) {
        return time.format(TIME_FORMATTER);
    }

    public SessionType getType() {
        return type;
    }

    public String toStorageString() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, oneOffDateTime, dayOfWeek, dayOfMonth, time);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Session)) {
            return false;
        }

        Session otherSession = (Session) other;
        return type == otherSession.type
                && Objects.equals(oneOffDateTime, otherSession.oneOffDateTime)
                && Objects.equals(dayOfWeek, otherSession.dayOfWeek)
                && dayOfMonth == otherSession.dayOfMonth
                && Objects.equals(time, otherSession.time);
    }

    /**
     * Returns true if this session conflicts with {@code other}.
     */
    public boolean conflictsWith(Session other) {
        requireNonNull(other);
        if (this.type == SessionType.ONE_OFF) {
            return other.occursOn(oneOffDateTime);
        }
        if (other.type == SessionType.ONE_OFF) {
            return this.occursOn(other.oneOffDateTime);
        }

        if (this.type == SessionType.MONTHLY && other.type == SessionType.MONTHLY) {
            return this.dayOfMonth == other.dayOfMonth && this.time.equals(other.time);
        }

        if (this.type == SessionType.MONTHLY) {
            return conflictsMonthlyWithRecurring(this, other);
        }
        if (other.type == SessionType.MONTHLY) {
            return conflictsMonthlyWithRecurring(other, this);
        }

        // Both weekly or biweekly
        return this.dayOfWeek == other.dayOfWeek && this.time.equals(other.time);
    }

    private boolean occursOn(LocalDateTime candidate) {
        switch (type) {
        case ONE_OFF:
            return oneOffDateTime.equals(candidate);
        case WEEKLY:
        case BIWEEKLY:
            return candidate.getDayOfWeek() == dayOfWeek && candidate.toLocalTime().equals(time);
        case MONTHLY:
            return candidate.getDayOfMonth() == dayOfMonth && candidate.toLocalTime().equals(time);
        default:
            throw new IllegalStateException("Unhandled session type: " + type);
        }
    }

    private static boolean conflictsMonthlyWithRecurring(Session monthly, Session recurring) {
        if (!monthly.time.equals(recurring.time)) {
            return false;
        }
        DayOfWeek recurringDay = recurring.dayOfWeek;
        if (recurringDay == null) {
            // Should not happen unless recurring is monthly
            return false;
        }
        YearMonth start = YearMonth.from(LocalDate.now(monthly.clock));
        for (int i = 0; i < MONTH_LOOKAHEAD; i++) {
            YearMonth current = start.plusMonths(i);
            if (monthly.dayOfMonth > current.lengthOfMonth()) {
                continue;
            }
            LocalDate date = current.atDay(monthly.dayOfMonth);
            if (date.getDayOfWeek() == recurringDay) {
                return true;
            }
        }
        return false;
    }

    /**
     * Represents the supported session recurrence types.
     */
    public enum SessionType {
        ONE_OFF,
        WEEKLY,
        BIWEEKLY,
        MONTHLY;

        static SessionType fromString(String raw) {
            try {
                return valueOf(raw.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException(MESSAGE_CONSTRAINTS_FORMAT, ex);
            }
        }
    }
}

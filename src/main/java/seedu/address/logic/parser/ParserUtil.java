package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Bodyfat;
import seedu.address.model.person.Deadline;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Goal;
import seedu.address.model.person.Height;
import seedu.address.model.person.Name;
import seedu.address.model.person.Paid;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Session;
import seedu.address.model.person.Weight;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses a {@code String paid} into a {@code Paid}.
     * Leading and trailing whitespaces will be trimmed.
     * Accepts case-insensitive values such as "true" or "false".
     *
     * @throws ParseException if the given {@code paid} value is invalid.
     */
    public static Paid parsePaid(String paid) throws ParseException {
        requireNonNull(paid);
        String trimmedPaid = paid.trim().toLowerCase();
        if (!Paid.isValidPaid(trimmedPaid)) {
            throw new ParseException(Paid.MESSAGE_CONSTRAINTS);
        }
        return new Paid(trimmedPaid);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String deadline} into a {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmed = deadline.trim();
        if (!Deadline.isValidDeadline(trimmed)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
        return Deadline.fromString(deadline);
    }

    /**
     * Parses a {@code Optional<String> deadline} into a {@code Deadline}.
     * If the optional is empty, treats it as an empty deadline.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(Optional<String> rawOpt) throws ParseException {
        // If the prefix is missing entirely, treat as empty (no deadline)
        return parseDeadline(rawOpt.orElse(""));
    }

    /**
     * Parses a {@code String session} into a {@code Session}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code session} is invalid.
     */
    public static Session parseSession(String session) throws ParseException {
        requireNonNull(session);
        String trimmedSession = session.trim();
        try {
            return Session.fromString(trimmedSession);
        } catch (IllegalArgumentException ex) {
            throw new ParseException(ex.getMessage(), ex);
        }
    }
    /**
     * Parses a {@code String height} into a {@code Height}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code height} is invalid.
     */
    public static Height parseHeight(String height) throws ParseException {
        requireNonNull(height);
        String trimmedHeight = height.trim();
        if (!Height.isValidHeight(trimmedHeight)) {
            throw new ParseException(Height.MESSAGE_CONSTRAINTS);
        }
        return new Height(trimmedHeight);
    }

    /**
     * Parses a {@code String weight} into a {@code Weight}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code weight} is invalid.
     */
    public static Weight parseWeight(String weight) throws ParseException {
        requireNonNull(weight);
        String trimmedWeight = weight.trim();
        if (!Weight.isValidWeight(trimmedWeight)) {
            throw new ParseException(Weight.MESSAGE_CONSTRAINTS);
        }
        return new Weight(trimmedWeight);
    }

    /**
     * Parses a {@code String age} into an {@code Age}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code age} is invalid.
     */
    public static Age parseAge(String age) throws ParseException {
        requireNonNull(age);
        String trimmedAge = age.trim();
        if (!Age.isValidAge(trimmedAge)) {
            throw new ParseException(Age.MESSAGE_CONSTRAINTS);
        }
        return new Age(trimmedAge);
    }

    /**
     * Parses a {@code String gender} into a {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses a {@code String bodyfat} into a {@code Bodyfat}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code bodyfat} is invalid.
     */
    public static Bodyfat parseBodyfat(String bodyfat) throws ParseException {
        requireNonNull(bodyfat);
        String trimmedBodyfat = bodyfat.trim();
        if (!Bodyfat.isValidBodyfat(trimmedBodyfat)) {
            throw new ParseException(Bodyfat.MESSAGE_CONSTRAINTS);
        }
        return new Bodyfat(trimmedBodyfat);
    }

    /**
     * Parses a {@code String goal} into a {@code Goal}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code goal} is invalid.
     */
    public static Goal parseGoal(String goal) throws ParseException {
        requireNonNull(goal);
        String trimmedGoal = goal.trim();
        if (!Goal.isValidGoal(trimmedGoal)) {
            throw new ParseException(Goal.MESSAGE_CONSTRAINTS);
        }
        return new Goal(trimmedGoal);
    }

}

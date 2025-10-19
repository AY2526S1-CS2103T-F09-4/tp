package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BODYFAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        sb.append(PREFIX_HEIGHT + String.valueOf(person.getHeight().value) + " ");
        sb.append(PREFIX_WEIGHT + String.valueOf(person.getWeight().value) + " ");
        sb.append(PREFIX_AGE + String.valueOf(person.getAge().value) + " ");
        sb.append(PREFIX_GENDER + person.getGender().value + " ");
        sb.append(PREFIX_DEADLINE + person.getDeadline().toStorageString() + " ");
        sb.append(PREFIX_PAID + person.getPaymentStatus().toString() + " ");
        sb.append(PREFIX_BODYFAT + String.valueOf(person.getBodyfat().value) + " ");
        sb.append(PREFIX_SESSION + person.getSession().toStorageString() + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getHeight().ifPresent(height -> sb.append(PREFIX_HEIGHT).append(height.value).append(" "));
        descriptor.getWeight().ifPresent(weight -> sb.append(PREFIX_WEIGHT).append(weight.value).append(" "));
        descriptor.getAge().ifPresent(age -> sb.append(PREFIX_AGE).append(age.value).append(" "));
        descriptor.getGender().ifPresent(gender -> sb.append(PREFIX_GENDER).append(gender.value).append(" "));
        descriptor.getDeadline().ifPresent(deadline -> sb.append(PREFIX_DEADLINE)
                .append(deadline.toStorageString()).append(" "));
        descriptor.getPaid().ifPresent(paid -> sb.append(PREFIX_PAID).append(paid.toString()).append(" "));
        descriptor.getBodyfat().ifPresent(bodyfat -> sb.append(PREFIX_BODYFAT).append(bodyfat.value).append(" "));
        descriptor.getSession().ifPresent(session -> sb.append(PREFIX_SESSION).append(session.toStorageString())
                .append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}

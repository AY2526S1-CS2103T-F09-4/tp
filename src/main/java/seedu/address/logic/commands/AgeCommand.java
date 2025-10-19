package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Age;
import seedu.address.model.person.Person;

/**
 * Changes the age of an existing person in the address book.
 */
public class AgeCommand extends Command {

    public static final String COMMAND_WORD = "age";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the age of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing age will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "age/ [AGE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "age/ 25";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Age: %2$s";

    public static final String MESSAGE_ADD_AGE_SUCCESS = "Added age to Person: %1$s";
    public static final String MESSAGE_DELETE_AGE_SUCCESS = "Removed age from Person: %1$s";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!\n%1$s";

    private final Index index;
    private final Age age;

    /**
     * @param index of the person in the filtered person list to edit the age
     * @param age of the person to be updated to
     */
    public AgeCommand(Index index, Age age) {
        requireAllNonNull(index, age);

        this.index = index;
        this.age = age;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(),
                personToEdit.getPhone(),
                personToEdit.getEmail(),
                personToEdit.getAddress(),
                personToEdit.getGoal(),
                personToEdit.getHeight(),
                personToEdit.getWeight(),
                age,
                personToEdit.getGender(),
                personToEdit.getDeadline(),
                personToEdit.getPaymentStatus(),
                personToEdit.getBodyfat(),
                personToEdit.getSession(),
                personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the age is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !age.toString().isEmpty() ? MESSAGE_ADD_AGE_SUCCESS : MESSAGE_DELETE_AGE_SUCCESS;
        return String.format(message, Messages.format(personToEdit));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AgeCommand)) {
            return false;
        }

        AgeCommand e = (AgeCommand) other;
        return index.equals(e.index)
                && age.equals(e.age);
    }
}

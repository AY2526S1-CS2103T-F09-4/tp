package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Person;

/**
 * Changes the gender of an existing person in the address book.
 */
public class GenderCommand extends Command {

    public static final String COMMAND_WORD = "gender";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the gender of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing gender will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "g/ [GENDER]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "g/ male";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Gender: %2$s";

    public static final String MESSAGE_ADD_GENDER_SUCCESS = "Added gender to Person: %1$s";
    public static final String MESSAGE_DELETE_GENDER_SUCCESS = "Removed gender from Person: %1$s";

    private final Index index;
    private final Gender gender;

    /**
     * @param index of the person in the filtered person list to edit the gender
     * @param gender of the person to be updated to
     */
    public GenderCommand(Index index, Gender gender) {
        requireAllNonNull(index, gender);

        this.index = index;
        this.gender = gender;
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
                personToEdit.getAge(),
                gender,
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
     * the gender is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !gender.value.isEmpty() ? MESSAGE_ADD_GENDER_SUCCESS : MESSAGE_DELETE_GENDER_SUCCESS;
        return String.format(message, Messages.format(personToEdit));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GenderCommand)) {
            return false;
        }

        GenderCommand e = (GenderCommand) other;
        return index.equals(e.index)
                && gender.equals(e.gender);
    }
}

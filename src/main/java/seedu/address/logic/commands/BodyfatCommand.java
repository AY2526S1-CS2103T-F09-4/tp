package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Bodyfat;
import seedu.address.model.person.Person;

/**
 * Edits the body fat percentage of an existing person in the address book.
 */
public class BodyfatCommand extends Command {

    public static final String COMMAND_WORD = "bodyfat";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the body fat percentage of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing body fat value will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "bf/BODYFAT\n"
            + "Example: " + COMMAND_WORD + " 1 bf/18.5";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Bodyfat: %2$s";

    public static final String MESSAGE_ADD_BODYFAT_SUCCESS = "Updated body fat for Person: %1$s";
    public static final String MESSAGE_DELETE_BODYFAT_SUCCESS = "Removed body fat from Person: %1$s";

    private final Index index;
    private final Bodyfat bodyfat;

    /**
     * @param index of the person in the filtered person list to edit the body fat
     * @param bodyfat of the person to be updated to
     */
    public BodyfatCommand(Index index, Bodyfat bodyfat) {
        requireAllNonNull(index, bodyfat);
        this.index = index;
        this.bodyfat = bodyfat;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
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
                personToEdit.getGender(),
                personToEdit.getDeadline(),
                personToEdit.getPaymentStatus(),
                bodyfat,
                personToEdit.getSession(),
                personToEdit.getTags()
        );

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = (bodyfat != null) ? MESSAGE_ADD_BODYFAT_SUCCESS : MESSAGE_DELETE_BODYFAT_SUCCESS;
        return String.format(message, Messages.format(personToEdit));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BodyfatCommand)) {
            return false;
        }

        BodyfatCommand e = (BodyfatCommand) other;
        return index.equals(e.index)
                && bodyfat.equals(e.bodyfat);
    }
}

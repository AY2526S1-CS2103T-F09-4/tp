package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Height;
import seedu.address.model.person.Person;

/**
 * Updates the height of an existing person in the address book.
 */
public class HeightCommand extends Command {

    public static final String COMMAND_WORD = "height";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the height of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing height will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "h/HEIGHT\n"
            + "Example: " + COMMAND_WORD + " 1 h/170";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Height: %2$s";

    public static final String MESSAGE_UPDATE_HEIGHT_SUCCESS = "Updated height for Person: %1$s";

    private final Index index;
    private final Height height;

    /**
     * @param index of the person in the filtered person list to edit the height
     * @param height of the person to be updated to
     */
    public HeightCommand(Index index, Height height) {
        requireAllNonNull(index, height);

        this.index = index;
        this.height = height;
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
                height,
                personToEdit.getWeight(),
                personToEdit.getAge(),
                personToEdit.getGender(),
                personToEdit.getDeadline(),
                personToEdit.getPaymentStatus(),
                personToEdit.getBodyfat(),
                personToEdit.getSession(),
                personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_UPDATE_HEIGHT_SUCCESS, Messages.format(editedPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HeightCommand)) {
            return false;
        }

        HeightCommand e = (HeightCommand) other;
        return index.equals(e.index)
                && height.equals(e.height);
    }
}

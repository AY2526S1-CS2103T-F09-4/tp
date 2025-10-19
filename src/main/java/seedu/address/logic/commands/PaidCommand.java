package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Paid;
import seedu.address.model.person.Person;

/**
 * Edits the paid status of a Person
 */
public class PaidCommand extends Command {

    public static final String COMMAND_WORD = "paid";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the paid status of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing paid status will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "paid/ [true|false]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "paid/ true";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Paid: %2$s";

    public static final String MESSAGE_EDIT_PAID_SUCCESS = "Updated paid status of Person: %1$s";

    private final Index index;
    private final Paid paid;

    /**
     * @param index of the person in the filtered person list to edit the paid status
     * @param paid of the person to be updated to
     */
    public PaidCommand(Index index, Paid paid) {
        requireAllNonNull(index, paid);
        this.index = index;
        this.paid = paid;
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
                personToEdit.getGender(),
                personToEdit.getDeadline(),
                paid, // updated paid status
                personToEdit.getBodyfat(),
                personToEdit.getSession(),
                personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_EDIT_PAID_SUCCESS, Messages.format(personToEdit));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PaidCommand)) {
            return false;
        }

        PaidCommand e = (PaidCommand) other;
        return index.equals(e.index) && paid.equals(e.paid);
    }
}

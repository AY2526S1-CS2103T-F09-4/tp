package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Weight;

/**
 * Updates the weight of an existing person in the address book.
 */
public class WeightCommand extends Command {

    public static final String COMMAND_WORD = "weight";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the weight of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing weight will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "w/WEIGHT\n"
            + "Example: " + COMMAND_WORD + " 1 w/70";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Weight: %2$s";

    public static final String MESSAGE_UPDATE_WEIGHT_SUCCESS = "Updated weight for Person: %1$s";

    private final Index index;
    private final Weight weight;

    /**
     * @param index of the person in the filtered person list to edit the weight
     * @param weight of the person to be updated to
     */
    public WeightCommand(Index index, Weight weight) {
        requireAllNonNull(index, weight);

        this.index = index;
        this.weight = weight;
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
                weight,
                personToEdit.getAge(),
                personToEdit.getGender(),
                personToEdit.getDeadline(),
                personToEdit.getPaymentStatus(),
                personToEdit.getBodyfat(),
                personToEdit.getSession(),
                personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_UPDATE_WEIGHT_SUCCESS, Messages.format(editedPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof WeightCommand)) {
            return false;
        }

        WeightCommand e = (WeightCommand) other;
        return index.equals(e.index)
                && weight.equals(e.weight);
    }
}

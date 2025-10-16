package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAID;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.PaidCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Paid;

/**
 * Parses input arguments and creates a new {@code PaidCommand} object
 */
public class PaidCommandParser implements Parser<PaidCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code PaidCommand}
     * and returns a {@code PaidCommand} object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public PaidCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PAID);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PaidCommand.MESSAGE_USAGE), ive);
        }

        String paidString = argMultimap.getValue(PREFIX_PAID)
                .orElseThrow(() -> new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PaidCommand.MESSAGE_USAGE)));

                
        try {
            Paid paid = new Paid(paidString);
            return new PaidCommand(index, paid);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }
    }
}

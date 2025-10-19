package seedu.address.model.person.exceptions;

/**
 * Signals that the operation would result in two persons having conflicting session slots.
 */
public class ConflictingSessionException extends RuntimeException {
    public ConflictingSessionException() {
        super("Operation would result in conflicting session slots");
    }
}

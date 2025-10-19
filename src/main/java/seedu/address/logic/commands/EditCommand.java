package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
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
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Session;
import seedu.address.model.person.Weight;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_DEADLINE + "DEADLINE(yyyy-MM-dd)]  "
            + "[" + PREFIX_HEIGHT + "HEIGHT] "
            + "[" + PREFIX_WEIGHT + "WEIGHT] "
            + "[" + PREFIX_PAID + "PAID] "
            + "[" + PREFIX_SESSION + "SESSION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_PAID + "true";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        if (model.hasSessionConflict(editedPerson, personToEdit)) {
            throw new CommandException(AddCommand.MESSAGE_CONFLICTING_SESSION);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Deadline updatedDeadline = editPersonDescriptor.getDeadline().orElse(personToEdit.getDeadline());
        Goal updatedGoal = editPersonDescriptor.getGoal().orElse(personToEdit.getGoal());
        Height updatedHeight = editPersonDescriptor.getHeight().orElse(personToEdit.getHeight());
        Weight updatedWeight = editPersonDescriptor.getWeight().orElse(personToEdit.getWeight());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Paid updatedPaid = editPersonDescriptor.getPaid().orElse(personToEdit.getPaymentStatus());
        Bodyfat updatedBodyfat = editPersonDescriptor.getBodyfat().orElse(personToEdit.getBodyfat());
        Age updatedAge = editPersonDescriptor.getAge().orElse(personToEdit.getAge());
        Gender updatedGender = editPersonDescriptor.getGender().orElse(personToEdit.getGender());
        Session updatedSession = editPersonDescriptor.getSession().orElse(personToEdit.getSession());
        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedGoal,
                updatedHeight, updatedWeight, updatedAge, updatedGender, updatedDeadline, updatedPaid,
                updatedBodyfat, updatedSession, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Deadline deadline;
        private Height height;
        private Weight weight;
        private Age age;
        private Gender gender;
        private Goal goal;
        private Paid paid;
        private Bodyfat bodyfat;
        private Session session;
        private Set<Tag> tags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setDeadline(toCopy.deadline);
            setHeight(toCopy.height);
            setWeight(toCopy.weight);
            setAge(toCopy.age);
            setGender(toCopy.gender);
            setPaid(toCopy.paid);
            setGoal(toCopy.goal);
            setBodyfat(toCopy.bodyfat);
            setSession(toCopy.session);
            setTags(toCopy.tags);
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, height, weight, age, gender,
                    deadline, tags, paid, bodyfat, session);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setDeadline(Deadline deadline) {
            this.deadline = deadline;
        }

        public Optional<Deadline> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setHeight(Height height) {
            this.height = height;
        }

        public Optional<Height> getHeight() {
            return Optional.ofNullable(height);
        }

        public void setWeight(Weight weight) {
            this.weight = weight;
        }

        public Optional<Weight> getWeight() {
            return Optional.ofNullable(weight);
        }


        public void setGoal(Goal goal) {
            this.goal = goal;
        }

        public Optional<Goal> getGoal() {
            return Optional.ofNullable(goal);
        }

        public void setBodyfat(Bodyfat bodyfat) {
            this.bodyfat = bodyfat;
        }

        public Optional<Bodyfat> getBodyfat() {
            return Optional.ofNullable(bodyfat);
        }

        public void setAge(Age age) {
            this.age = age;
        }

        public Optional<Age> getAge() {
            return Optional.ofNullable(age);
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setPaid(Paid paid) {
            this.paid = paid;
        }

        public Optional<Paid> getPaid() {
            return Optional.ofNullable(paid);
        }

        public void setSession(Session session) {
            this.session = session;
        }

        public Optional<Session> getSession() {
            return Optional.ofNullable(session);
        }

        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            EditPersonDescriptor otherEditPersonDescriptor = (EditPersonDescriptor) other;
            return Objects.equals(name, otherEditPersonDescriptor.name)
                    && Objects.equals(phone, otherEditPersonDescriptor.phone)
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(address, otherEditPersonDescriptor.address)
                    && Objects.equals(deadline, otherEditPersonDescriptor.deadline)
                    && Objects.equals(height, otherEditPersonDescriptor.height)
                    && Objects.equals(weight, otherEditPersonDescriptor.weight)
                    && Objects.equals(goal, otherEditPersonDescriptor.goal)
                    && Objects.equals(bodyfat, otherEditPersonDescriptor.bodyfat)
                    && Objects.equals(paid, otherEditPersonDescriptor.paid)
                    && Objects.equals(session, otherEditPersonDescriptor.session)
                    && Objects.equals(tags, otherEditPersonDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("deadline", deadline)
                    .add("height", height)
                    .add("weight", weight)
                    .add("goal", goal)
                    .add("bodyfat", bodyfat)
                    .add("paid", paid)
                    .add("session", session)
                    .add("tags", tags)
                    .toString();
        }
    }
}

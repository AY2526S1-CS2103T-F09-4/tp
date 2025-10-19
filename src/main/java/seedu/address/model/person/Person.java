package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Payment fields
    private final Deadline deadline;
    private Paid paid;

    // Data fields
    private final Address address;
    private final Goal goal;
    private final Height height;
    private final Weight weight;
    private final Age age;
    private final Gender gender;
    private final Bodyfat bodyfat;
    private final Session session;

    private final Set<Tag> tags = new HashSet<>();
    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Goal goal, Height height, Weight weight,
            Age age, Gender gender, Deadline deadline, Paid paid, Bodyfat bodyfat, Session session, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, goal, height, weight, age, gender, paid, bodyfat, session,
                tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.deadline = deadline;
        this.goal = goal;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
        this.tags.addAll(tags);
        this.paid = paid;
        this.bodyfat = bodyfat;
        this.session = session;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public Goal getGoal() {
        return goal;
    }

    public Height getHeight() {
        return height;
    }

    public Weight getWeight() {
        return weight;
    }

    public Age getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public Paid getPaymentStatus() {
        return paid;
    }

    public Bodyfat getBodyfat() {
        return bodyfat;
    }

    public Session getSession() {
        return session;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && deadline.equals(otherPerson.deadline)
                && goal.equals(otherPerson.goal)
                && height.equals(otherPerson.height)
                && weight.equals(otherPerson.weight)
                && age.equals(otherPerson.age)
                && gender.equals(otherPerson.gender)
                && bodyfat.equals(otherPerson.bodyfat)
                && session.equals(otherPerson.session)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, goal, height, weight, age, gender,
                deadline, paid, bodyfat, session, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("deadline", deadline)
                .add("goal", goal)
                .add("height", height)
                .add("weight", weight)
                .add("age", age)
                .add("gender", gender)
                .add("paid", paid)
                .add("bodyfat", bodyfat)
                .add("session", session)
                .add("tags", tags)
                .toString();
    }

}

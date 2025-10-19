package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

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
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_GOAL = "Lose 5kg";
    public static final String DEFAULT_HEIGHT = "170";
    public static final String DEFAULT_WEIGHT = "65"; // default weight in kg
    public static final String DEFAULT_AGE = "25";
    public static final String DEFAULT_GENDER = "female";
    public static final String DEFAULT_DEADLINE = "2025-12-31";
    public static final String DEFAULT_PAID = "true";
    public static final String DEFAULT_BODYFAT = "30.0";
    public static final String DEFAULT_SESSION = "WEEKLY:MON 18:00";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Goal goal;
    private Height height;
    private Weight weight;
    private Age age;
    private Gender gender;
    private Deadline deadline;
    private Paid paid;
    private Bodyfat bodyfat;
    private Session session;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        goal = new Goal(DEFAULT_GOAL);
        height = new Height(DEFAULT_HEIGHT);
        weight = new Weight(DEFAULT_WEIGHT);
        age = new Age(DEFAULT_AGE);
        gender = new Gender(DEFAULT_GENDER);
        deadline = Deadline.fromString(DEFAULT_DEADLINE);
        paid = new Paid(DEFAULT_PAID);
        bodyfat = new Bodyfat(DEFAULT_BODYFAT);
        session = Session.fromString(DEFAULT_SESSION);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        goal = personToCopy.getGoal();
        height = personToCopy.getHeight();
        weight = personToCopy.getWeight();
        age = personToCopy.getAge();
        gender = personToCopy.getGender();
        deadline = personToCopy.getDeadline();
        paid = personToCopy.getPaymentStatus();
        bodyfat = personToCopy.getBodyfat();
        session = personToCopy.getSession();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Goal} of the {@code Person} that we are building.
     */
    public PersonBuilder withGoal(String goal) {
        this.goal = new Goal(goal);
        return this;
    }

    /**
     * Sets the {@code Height} of the {@code Person} that we are building.
     */
    public PersonBuilder withHeight(String height) {
        this.height = new Height(height);
        return this;
    }

    /**
     * Sets the {@code Weight} of the {@code Person} that we are building.
     */
    public PersonBuilder withWeight(String weight) {
        this.weight = new Weight(weight);
        return this;
    }

    /**
     * Sets the {@code Age} of the {@code Person} that we are building.
     */
    public PersonBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Person} that we are building.
     */
    public PersonBuilder withDeadline(String deadline) {
        this.deadline = Deadline.fromString(deadline);
        return this;
    }

    /**
     * Sets the {@code Paid} of the {@code Person} that we are building.
     */
    public PersonBuilder withPaid(String paid) {
        this.paid = new Paid(paid);
        return this;
    }

    /**
     * Sets the {@code Bodyfat} of the {@code Person} that we are building.
     */
    public PersonBuilder withBodyfat(String bodyfat) {
        this.bodyfat = new Bodyfat(bodyfat);
        return this;
    }

    /**
     * Sets the {@code Session} of the {@code Person} that we are building.
     */
    public PersonBuilder withSession(String session) {
        this.session = Session.fromString(session);
        return this;
    }

    /**
     * Builds the person with the specified attributes.
     *
     * @return the built person
     */
    public Person build() {
        return new Person(name, phone, email, address, goal, height, weight, age, gender,
                deadline, paid, bodyfat, session, tags);
    }

}

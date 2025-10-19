package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
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
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String deadline;
    private final String goal;
    private final String height;
    private final String weight;
    private final String age;
    private final String gender;
    private final String bodyfat;
    private final String paid; // New field for payment status
    private final String session;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name,
                             @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email,
                             @JsonProperty("address") String address,
                             @JsonProperty("goal") String goal,
                             @JsonProperty("height") String height,
                             @JsonProperty("weight") String weight,
                             @JsonProperty("age") String age,
                             @JsonProperty("gender") String gender,
                             @JsonProperty("deadline") String deadline,
                             @JsonProperty("paid") String paid,
                             @JsonProperty("session") String session,
                             @JsonProperty("bodyfat") String bodyfat,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags) {
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
        this.paid = paid;
        this.session = session;
        this.bodyfat = bodyfat;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        deadline = source.getDeadline().toStorageString();
        goal = source.getGoal().value;
        height = String.valueOf(source.getHeight().value); // convert int → String
        weight = String.valueOf(source.getWeight().value); // convert Double → String
        age = String.valueOf(source.getAge().value); // convert int → String
        gender = source.getGender().value; // convert Gender to String
        paid = source.getPaymentStatus().toString(); // Convert Paid to String
        session = source.getSession().toStorageString();
        bodyfat = source.getBodyfat().toString();
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final String rawDeadline = (deadline == null) ? "" : deadline;

        if (!Deadline.isValidDeadline(rawDeadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }

        final Deadline modelDeadline = Deadline.fromString(rawDeadline);

        if (goal == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Goal.class.getSimpleName()));
        }
        final Goal modelGoal = new Goal(goal);

        if (height == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName()));
        }
        if (!Height.isValidHeight(height)) {
            throw new IllegalValueException(Height.MESSAGE_CONSTRAINTS);
        }
        final Height modelHeight = new Height(height);

        if (weight == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName()));
        }
        if (!Weight.isValidWeight(weight)) {
            throw new IllegalValueException(Weight.MESSAGE_CONSTRAINTS);
        }
        final Weight modelWeight = new Weight(weight);


        if (age == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName()));
        }
        if (!Age.isValidAge(age)) {
            throw new IllegalValueException(Age.MESSAGE_CONSTRAINTS);
        }
        final Age modelAge = new Age(age);

        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (paid == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Paid.class.getSimpleName()));
        }
        if (!Paid.isValidPaid(paid)) {
            throw new IllegalValueException(Paid.MESSAGE_CONSTRAINTS);
        }
        final Paid modelPaid = new Paid(paid); // Convert string to Paid

        if (bodyfat == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Bodyfat.class.getSimpleName()));
        }
        if (!Bodyfat.isValidBodyfat(bodyfat)) {
            throw new IllegalValueException(Bodyfat.MESSAGE_CONSTRAINTS);
        }
        final Bodyfat modelBodyfat = new Bodyfat(bodyfat);

        if (session == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Session.class.getSimpleName()));
        }
        final Session modelSession;
        try {
            modelSession = Session.fromString(session);
        } catch (IllegalArgumentException ex) {
            throw new IllegalValueException(ex.getMessage(), ex);
        }

        final Set<Tag> modelTags = new HashSet<>(personTags);

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelGoal,
                modelHeight, modelWeight, modelAge, modelGender, modelDeadline, modelPaid, modelBodyfat,
                modelSession, modelTags);
    }

}

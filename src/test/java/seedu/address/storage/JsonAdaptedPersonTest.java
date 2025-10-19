package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Height;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Session;
import seedu.address.model.person.Weight;

public class JsonAdaptedPersonTest {

    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_DEADLINE = "20";
    private static final String INVALID_HEIGHT = "-10"; // invalid height
    private static final String INVALID_WEIGHT = "-5"; // invalid weight
    private static final String INVALID_AGE = "150"; // invalid age
    private static final String INVALID_GENDER = "invalid"; // invalid gender
    private static final String INVALID_PAID = "notaboolean";
    private static final String INVALID_SESSION = "WEEKLY:FUNDAY 18:00";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_DEADLINE = BENSON.getDeadline().toString();
    private static final String VALID_GOAL = BENSON.getGoal().toString();
    private static final String VALID_HEIGHT = String.valueOf(BENSON.getHeight().value);
    private static final String VALID_WEIGHT = String.valueOf(BENSON.getWeight().value);
    private static final String VALID_AGE = String.valueOf(BENSON.getAge().value);
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_PAID = BENSON.getPaymentStatus().toString();
    private static final String VALID_BODYFAT = BENSON.getBodyfat().toString();
    private static final String VALID_SESSION = BENSON.getSession().toStorageString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GOAL,
                        VALID_HEIGHT, VALID_WEIGHT, VALID_AGE, VALID_GENDER, VALID_DEADLINE,
                        VALID_PAID, VALID_SESSION, VALID_BODYFAT, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_GOAL, VALID_HEIGHT, VALID_WEIGHT, VALID_AGE, VALID_GENDER, VALID_DEADLINE,
                VALID_PAID, VALID_SESSION, VALID_BODYFAT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_GOAL, VALID_HEIGHT, VALID_WEIGHT, VALID_AGE, VALID_GENDER, VALID_DEADLINE,
                        VALID_PAID, VALID_SESSION, VALID_BODYFAT, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_GOAL, VALID_HEIGHT, VALID_WEIGHT, VALID_AGE, VALID_GENDER, VALID_DEADLINE,
                VALID_PAID, VALID_SESSION, VALID_BODYFAT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_GOAL, VALID_HEIGHT, VALID_WEIGHT, VALID_AGE, VALID_GENDER, VALID_DEADLINE,
                        VALID_PAID, VALID_SESSION, VALID_BODYFAT, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_GOAL, VALID_HEIGHT, VALID_WEIGHT, VALID_AGE, VALID_GENDER, VALID_DEADLINE,
                VALID_PAID, VALID_SESSION, VALID_BODYFAT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_GOAL, VALID_HEIGHT, VALID_WEIGHT, VALID_AGE, VALID_GENDER, VALID_DEADLINE,
                        VALID_PAID, VALID_SESSION, VALID_BODYFAT, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidSession_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GOAL,
                        VALID_HEIGHT, VALID_WEIGHT, VALID_AGE, VALID_GENDER, VALID_DEADLINE,
                        VALID_PAID, INVALID_SESSION, VALID_BODYFAT, VALID_TAGS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_nullSession_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_GOAL, VALID_HEIGHT, VALID_WEIGHT, VALID_AGE, VALID_GENDER, VALID_DEADLINE,
                VALID_PAID, null, VALID_BODYFAT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Session.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    // --- New Height Tests ---
    @Test
    public void toModelType_invalidHeight_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GOAL,
                        INVALID_HEIGHT, VALID_WEIGHT, VALID_AGE, VALID_GENDER, VALID_DEADLINE,
                        VALID_PAID, VALID_SESSION, VALID_BODYFAT, VALID_TAGS);
        String expectedMessage = Height.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullHeight_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GOAL,
                        null, VALID_WEIGHT, VALID_AGE, VALID_GENDER, VALID_DEADLINE,
                        VALID_PAID, VALID_SESSION, VALID_BODYFAT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_GOAL, VALID_HEIGHT, VALID_WEIGHT, VALID_AGE, VALID_GENDER, VALID_DEADLINE,
                VALID_PAID, VALID_SESSION, VALID_BODYFAT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_GOAL, VALID_HEIGHT, VALID_WEIGHT, VALID_AGE, VALID_GENDER, VALID_DEADLINE,
                        VALID_PAID, VALID_SESSION, VALID_BODYFAT, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidWeight_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GOAL,
                VALID_HEIGHT, INVALID_WEIGHT, VALID_AGE, VALID_GENDER, VALID_DEADLINE,
                VALID_PAID, VALID_SESSION, VALID_BODYFAT, VALID_TAGS);
        String expectedMessage = Weight.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullWeight_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GOAL,
                VALID_HEIGHT, null, VALID_AGE, VALID_GENDER, VALID_DEADLINE,
                VALID_PAID, VALID_SESSION, VALID_BODYFAT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}

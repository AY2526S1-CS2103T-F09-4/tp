package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SESSION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SESSION_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withGoal("Lose 5kg")
            .withHeight("165").withWeight("60").withAge("25").withGender("female").withDeadline("2025-12-31")
            .withPaid("true").withBodyfat("18.5").withSession("WEEKLY:MON 08:00").withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withGoal("Lose 5kg")
            .withHeight("175").withWeight("75").withAge("30").withGender("male").withDeadline("2025-12-31")
            .withPaid("true").withBodyfat("20.0").withSession("WEEKLY:TUE 09:00")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withHeight("180").withWeight("80").withAge("28").withGender("male").withDeadline("2025-12-31")
            .withPaid("true").withBodyfat("22.0").withSession("WEEKLY:WED 10:00").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withHeight("170").withWeight("70").withAge("32").withGender("male").withDeadline("2025-12-31")
            .withPaid("true").withBodyfat("19.5").withSession("WEEKLY:THU 11:00").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withHeight("160").withWeight("55").withAge("26").withGender("female").withDeadline("2025-12-31")
            .withPaid("true").withBodyfat("17.5").withSession("WEEKLY:FRI 12:00").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withHeight("155").withWeight("50").withAge("24").withGender("female").withDeadline("2025-12-31")
            .withPaid("true").withBodyfat("16.0").withSession("WEEKLY:SAT 13:00").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withHeight("185").withWeight("85").withAge("35").withGender("male").withDeadline("2025-12-31")
            .withPaid("true").withBodyfat("21.0").withSession("WEEKLY:SUN 14:00").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .withHeight("175").withAge("29").withGender("male").withSession("BIWEEKLY:MON 18:00").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withHeight("165").withAge("27").withGender("female").withSession("BIWEEKLY:TUE 18:30").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withHeight("160").withAge("25").withGender("female").withSession(VALID_SESSION_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withHeight("175").withAge("30").withGender("male")
            .withGoal("Lose 5kg").withPaid("true").withSession(VALID_SESSION_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

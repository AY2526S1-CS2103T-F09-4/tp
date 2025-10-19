package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
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
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                new Goal("Lose 5kg"),
                new Height("170"), // added height
                new Weight("69.5"), // added weight
                new Age("25"), // added age
                new Gender("male"), // added gender
                Deadline.fromString("2025-11-15"),
                new Paid("false"), // added Paid
                new Bodyfat("30.0"),
                Session.fromString("WEEKLY:MON 08:00"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new Goal("Lose 5kg"),
                new Height("165"), // added height
                new Weight("55.0"), // added weight
                new Age("28"), // added age
                new Gender("female"), // added gender
                Deadline.fromString("2025-11-15"),
                new Paid("true"),
                new Bodyfat("30.0"),
                Session.fromString("WEEKLY:TUE 09:00"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new Goal("Lose 5kg"),
                new Height("160"), // added height
                new Weight("48.0"), // added weight
                new Age("22"), // added age
                new Gender("female"), // added gender
                Deadline.fromString("2025-11-15"),
                new Paid("false"),
                new Bodyfat("30.0"),
                Session.fromString("WEEKLY:WED 10:00"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new Goal("Lose 5kg"),
                new Height("175"),
                new Weight("70.0"), // added weight
                new Age("30"), // added age
                new Gender("male"), // added gender
                Deadline.fromString("2025-11-15"),
                new Paid("false"),
                new Bodyfat("30.0"),
                Session.fromString("WEEKLY:THU 11:00"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                new Goal("Lose 5kg"),
                new Height("180"),
                new Weight("85.0"), // added weight
                new Age("26"), // added age
                new Gender("male"), // added gender
                Deadline.fromString("2025-11-15"),
                new Paid("true"),
                new Bodyfat("30.0"),
                Session.fromString("WEEKLY:FRI 12:00"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                new Goal("Lose 5kg"),
                new Height("172"),
                new Weight("68.0"), // added weight
                new Age("24"), // added age
                new Gender("male"), // added gender
                Deadline.fromString("2025-11-15"),
                new Paid("false"),
                new Bodyfat("30.0"),
                Session.fromString("WEEKLY:SAT 13:00"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}

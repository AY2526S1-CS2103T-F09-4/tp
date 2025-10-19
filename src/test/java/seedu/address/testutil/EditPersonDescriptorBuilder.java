package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
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
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setHeight(person.getHeight());
        descriptor.setWeight(person.getWeight());
        descriptor.setAge(person.getAge());
        descriptor.setGender(person.getGender());
        descriptor.setDeadline(person.getDeadline());
        descriptor.setPaid(person.getPaymentStatus());
        descriptor.setBodyfat(person.getBodyfat());
        descriptor.setSession(person.getSession());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Height} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withHeight(String height) {
        descriptor.setHeight(new Height(height));
        return this;
    }

    /**
     * Sets the {@code Weight} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withWeight(String weight) {
        descriptor.setWeight(new Weight(weight));
        return this;
    }

    /**
     * Sets the {@code Age} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAge(String age) {
        descriptor.setAge(new Age(age));
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withGender(String gender) {
        descriptor.setGender(new Gender(gender));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(Deadline.fromString(deadline));
        return this;
    }

    /**
     * Sets the {@code Goal} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withGoal(String goal) {
        descriptor.setGoal(new Goal(goal));
        return this;
    }

    /**
     * Sets the {@code Paid} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPaid(String paid) {
        descriptor.setPaid(new Paid(paid));
        return this;
    }

    /**
     * Sets the {@code Bodyfat} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withBodyfat(String bodyfat) {
        descriptor.setBodyfat(new Bodyfat(bodyfat));
        return this;
    }

    /**
     * Sets the {@code Session} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withSession(String session) {
        descriptor.setSession(Session.fromString(session));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}

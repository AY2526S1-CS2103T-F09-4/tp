package seedu.address.ui;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AgeCommand;
import seedu.address.logic.commands.BodyfatCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeadlineCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.GenderCommand;
import seedu.address.logic.commands.GoalCommand;
import seedu.address.logic.commands.HeightCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.PaidCommand;
import seedu.address.logic.commands.WeightCommand;

/**
 * Overlay that shows in-app help for available commands.
 */
public class HelpOverlay extends UiPart<Region> {

    private static final String FXML = "HelpOverlay.fxml";

    private static final List<HelpEntry> HELP_ENTRIES = List.of(
            HelpEntry.fromCommand(AddCommand.COMMAND_WORD, AddCommand.MESSAGE_USAGE),
            HelpEntry.fromCommand(EditCommand.COMMAND_WORD, EditCommand.MESSAGE_USAGE),
            HelpEntry.fromCommand(DeleteCommand.COMMAND_WORD, DeleteCommand.MESSAGE_USAGE),
            new HelpEntry(ClearCommand.COMMAND_WORD,
                    "Clears all trainees from FitBook.\nExample: " + ClearCommand.COMMAND_WORD),
            HelpEntry.fromCommand(FindCommand.COMMAND_WORD, FindCommand.MESSAGE_USAGE),
            HelpEntry.fromCommand(ListCommand.COMMAND_WORD,
                    "Lists every trainee in FitBook.\nExample: " + ListCommand.COMMAND_WORD),
            HelpEntry.fromCommand(HelpCommand.COMMAND_WORD, HelpCommand.MESSAGE_USAGE),
            HelpEntry.fromCommand(ExitCommand.COMMAND_WORD,
                    "Exits the application.\nExample: " + ExitCommand.COMMAND_WORD),
            HelpEntry.fromCommand(GoalCommand.COMMAND_WORD, GoalCommand.MESSAGE_USAGE),
            HelpEntry.fromCommand(BodyfatCommand.COMMAND_WORD, BodyfatCommand.MESSAGE_USAGE),
            HelpEntry.fromCommand(DeadlineCommand.COMMAND_WORD, DeadlineCommand.MESSAGE_USAGE),
            HelpEntry.fromCommand(AgeCommand.COMMAND_WORD, AgeCommand.MESSAGE_USAGE),
            HelpEntry.fromCommand(GenderCommand.COMMAND_WORD, GenderCommand.MESSAGE_USAGE),
            HelpEntry.fromCommand(PaidCommand.COMMAND_WORD, PaidCommand.MESSAGE_USAGE),
            HelpEntry.fromCommand(HeightCommand.COMMAND_WORD, HeightCommand.MESSAGE_USAGE),
            HelpEntry.fromCommand(WeightCommand.COMMAND_WORD, WeightCommand.MESSAGE_USAGE)
    );

    private final Runnable onClose;

    @FXML
    private VBox commandListContainer;

    @FXML
    private Button closeButton;

    @FXML
    private ScrollPane commandScrollPane;

    public HelpOverlay(Runnable onClose) {
        super(FXML);
        this.onClose = onClose;
        populateHelpEntries();
    }

    void focusOnCloseButton() {
        closeButton.requestFocus();
        commandScrollPane.setVvalue(0);
    }

    @FXML
    private void handleClose() {
        onClose.run();
    }

    private void populateHelpEntries() {
        commandListContainer.getChildren().clear();
        HELP_ENTRIES.stream()
                .map(this::createEntry)
                .forEach(commandListContainer.getChildren()::add);
    }

    private VBox createEntry(HelpEntry entry) {
        VBox entryContainer = new VBox();
        entryContainer.getStyleClass().add("help-overlay__command");
        entryContainer.setSpacing(6);

        Label title = new Label(entry.commandWord());
        title.getStyleClass().add("help-overlay__command-title");

        Label description = new Label(entry.description());
        description.setWrapText(true);
        description.getStyleClass().add("help-overlay__command-description");

        entryContainer.getChildren().addAll(title, description);
        return entryContainer;
    }

    private record HelpEntry(String commandWord, String description) {
        private static HelpEntry fromCommand(String commandWord, String usage) {
            return new HelpEntry(commandWord, removeCommandWordPrefix(commandWord, usage));
        }

        private static String removeCommandWordPrefix(String commandWord, String usage) {
            if (usage == null) {
                return "";
            }

            String normalisedPrefix = commandWord + ":";
            if (usage.startsWith(normalisedPrefix)) {
                return usage.substring(normalisedPrefix.length()).stripLeading();
            }

            return usage;
        }
    }
}

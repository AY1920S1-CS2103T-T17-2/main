package seedu.address.diaryfeature.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.diaryfeature.model.DiaryEntry;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class DiaryEntryCard extends UiPart<Region> {

    private static final String FXML = "DiaryEntryCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final DiaryEntry entry;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label date;
    @FXML
    private Label id;


    public DiaryEntryCard(DiaryEntry entryInput, int displayedIndex) {
        super(FXML);
        this.entry = entryInput;
        id.setText("DIARY ENTRY NO:" + displayedIndex + ". ");
        title.setText("TITLE: " + entry.getTitle().toString());
        date.setText("DATE: "+ entry.getDate().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DiaryEntryCard)) {
            return false;
        }

        // state check
        DiaryEntryCard card = (DiaryEntryCard) other;
        return id.getText().equals(card.id.getText())
                && title.equals(card.title);
    }
}

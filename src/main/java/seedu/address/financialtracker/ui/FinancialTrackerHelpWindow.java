package seedu.address.financialtracker.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.ui.UiPart;

/**
 * Controller for a help page.
 */
@SuppressWarnings("unused")
public class FinancialTrackerHelpWindow extends UiPart<Stage> {

    private static final String HELP_URL = "https://se-education.org/addressbook-level3/UserGuide.html";
    private static final String HELP_MESSAGE = "For more information check out our user guide here: \n" + HELP_URL;
    private static final String SUMMARY_MESSAGE = "summary";
    private static final String HELP = "help";
    private static final String GOTO_MESSAGE = "goto PAGE";
    private static final String EXIT_MESSAGE = "exit";
    private static final String ADD_MESSAGE = "add a/AMOUNT d/DESCRIPTION t/[TYPE_OF_EXPENDITURE] "
            + "[date/DATE] [time/TIME]";
    private static final String DELETE_MESSAGE = "delete INDEX";
    private static final String EDIT_MESSAGE = "edit INDEX a/AMOUNT d/DESCRIPTION t/[TYPE_OF_EXPENDITURE] "
            + "[date/DATE] [time/TIME]";
    private static final String SORT_MESSAGE = "sort amount";
    private static final Logger logger = LogsCenter.getLogger(FinancialTrackerHelpWindow.class);
    private static final String FXML = "FinancialTrackerHelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Button summaryCommand;

    @FXML
    private Button helpCommand;

    @FXML
    private Button gotoCommand;

    @FXML
    private Button exitCommand;

    @FXML
    private Button addCommand;

    @FXML
    private Button deleteCommand;

    @FXML
    private Button editCommand;

    @FXML
    private Button sortCommand;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    private FinancialTrackerHelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public FinancialTrackerHelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(HELP_URL);
        clipboard.setContent(url);
    }

    /**
     * Copies the summary command template to the clipboard.
     */
    @FXML
    private void copySummary() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(SUMMARY_MESSAGE);
        clipboard.setContent(url);
    }

    /**
     * Copies the summary command template to the clipboard.
     */
    @FXML
    private void copyHelp() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(HELP);
        clipboard.setContent(url);
    }

    /**
     * Copies the goto command template to the clipboard.
     */
    @FXML
    private void copyGoto() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(GOTO_MESSAGE);
        clipboard.setContent(url);
    }

    /**
     * Copies the exit command template to the clipboard.
     */
    @FXML
    private void copyExit() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(EXIT_MESSAGE);
        clipboard.setContent(url);
    }

    /**
     * Copies the add command template to the clipboard.
     */
    @FXML
    private void copyAdd() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(ADD_MESSAGE);
        clipboard.setContent(url);
    }

    /**
     * Copies the delete command template to the clipboard.
     */
    @FXML
    private void copyDelete() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(DELETE_MESSAGE);
        clipboard.setContent(url);
    }

    /**
     * Copies the delete command template to the clipboard.
     */
    @FXML
    private void copyEdit() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(EDIT_MESSAGE);
        clipboard.setContent(url);
    }

    /**
     * Copies the sort command template to the clipboard.
     */
    @FXML
    private void copySort() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(SORT_MESSAGE);
        clipboard.setContent(url);
    }
}

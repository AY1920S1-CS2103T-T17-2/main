package seedu.travezy.achievements.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.travezy.achievements.logic.AchievementsLogic;
import seedu.travezy.commons.core.LogsCenter;
import seedu.travezy.logic.commands.CommandResult;
import seedu.travezy.logic.commands.exceptions.CommandException;
import seedu.travezy.logic.parser.exceptions.ParseException;
import seedu.travezy.ui.CodeWindow;
import seedu.travezy.ui.CommandBox;
import seedu.travezy.ui.HelpWindow;
import seedu.travezy.ui.Page;
import seedu.travezy.ui.PageType;
import seedu.travezy.ui.ResultDisplay;
import seedu.travezy.ui.UiPart;

/**
 * Page for showing the user achievements
 */
public class AchievementsPage extends UiPart<Region> implements Page {

    private static final PageType pagetype = PageType.ACHIEVEMENTS;

    private static final String FXML = "Achievements.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private Scene achievementsScene;

    @FXML
    private ImageView title;

    @FXML
    private BorderPane achievementsPane;

    private AchievementsLogic achievementsLogic;

    private Stage primaryStage;

    private ResultDisplay resultDisplay;

    private HelpWindow helpWindow;

    private CodeWindow codeWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private Label test;

    public AchievementsPage AchievementsPage(AchievementsPage achievementsPage) {
        return new AchievementsPage(achievementsPage.primaryStage, achievementsPage.achievementsLogic);
    }

    public AchievementsPage(Stage primaryStage, AchievementsLogic achievementsLogic) {

        super(FXML, new BorderPane());
        this.primaryStage = primaryStage;
        this.achievementsLogic = achievementsLogic;
        this.achievementsScene = new Scene(achievementsPane);
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666 is fixed in later version of
         * SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will not
         * work when the focus is in them because the key event is consumed by the
         * TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is in
         * CommandBox or ResultDisplay.
         */
        achievementsScene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }


    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = achievementsLogic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
    /**
     * Opens the code window or focuses on it if it's already opened.
     */
    @FXML
    public void handleCode() {
        if (!codeWindow.isShowing()) {
            codeWindow.show();
        } else {
            codeWindow.focus();
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
//        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(),
//                primaryStage.getHeight(),
//                (int) primaryStage.getX(),
//                (int) primaryStage.getY());
//        addressBookLogic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    @Override
    public Scene getScene() {

        title.setImage(new Image(this.getClass().getResourceAsStream("/images/achievements.png")));

        setAccelerators();

        this.helpWindow = new HelpWindow();
        this.codeWindow = new CodeWindow();
        fillInnerParts();
        test.setText("Total Number of Persons: " + achievementsLogic.getTotalPersons());
        return achievementsScene;
    }

    @Override
    public PageType getPageType() {
        return pagetype;
    }
}

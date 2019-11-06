package seedu.address.calendar.ui;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Optional;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seedu.address.address.logic.AddressBookLogic;
import seedu.address.calendar.logic.CalendarLogic;
import seedu.address.calendar.model.Calendar;
import seedu.address.calendar.model.ReadOnlyCalendar;
import seedu.address.calendar.model.date.MonthOfYear;
import seedu.address.calendar.model.date.ViewOnlyMonth;
import seedu.address.calendar.model.date.Year;
import seedu.address.calendar.storage.CalendarStorage;
import seedu.address.calendar.storage.JsonCalendarStorage;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.Page;
import seedu.address.ui.PageManager;
import seedu.address.ui.PageType;
import seedu.address.ui.ResultDisplay;
import seedu.address.ui.UiPart;

public class CalendarPage extends UiPart<Region> implements Page {
    private static final String FXML = "CalendarPage.fxml";
    private static final PageType pageType = PageType.CALENDAR;
    private static final String FILE_OPS_ERROR_MESSAGE = "Unable to save calendar";

    private ResultDisplay resultDisplay;
    private CalendarLogic calendarLogic;
    private ReadOnlyDoubleProperty monthViewWidth;
    private ListWindow listWindow;

    @FXML
    StackPane commandBoxPlaceholder;
    @FXML
    StackPane monthHeaderPlaceholder;
    @FXML
    StackPane yearHeaderPlaceholder;
    @FXML
    StackPane monthViewPlaceholder;
    @FXML
    VBox resultDisplayPlaceholder;
    @FXML
    GridPane weekHeader;

    public CalendarPage() {
        super(FXML);
        Calendar calendar = new Calendar();
        CalendarStorage calendarStorage = new JsonCalendarStorage(Paths.get("data" , "calendar.json"));

        try {
            Optional<ReadOnlyCalendar> calendarOptional = calendarStorage.readCalendar();
            calendar.updateCalendar(calendarOptional);
        } catch (DataConversionException e) {
            System.out.println("Data file not in the correct format. Will be starting with an empty Calendar");
        } catch (NoSuchFileException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.out.println("Problem while reading from the file. Will be starting with an empty Calendar");
        }
        calendarLogic = new CalendarLogic(calendar, calendarStorage);
        monthViewWidth = weekHeader.widthProperty();

        fillInnerParts();
        listWindow = new ListWindow();
    }

    public PageType getPageType() {
        return pageType;
    }

    /**
     * Sets up calendar page by laying out nodes.
     */

    private void fillInnerParts() {
        ViewOnlyMonth currentViewOnlyMonth = calendarLogic.getVisibleMonth();
        MonthOfYear monthOfYear = currentViewOnlyMonth.getMonthOfYear();
        MonthHeader monthHeader = new MonthHeader(monthOfYear);
        monthHeaderPlaceholder.getChildren().add(monthHeader.getRoot());

        Year year = currentViewOnlyMonth.getYear();
        YearHeader yearHeader = new YearHeader(year);
        yearHeaderPlaceholder.getChildren().add(yearHeader.getRoot());

        monthViewPlaceholder.getChildren().add(MonthView.generateMonthGrid(currentViewOnlyMonth, monthViewWidth));

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    private void updateCalendarPage(ViewOnlyMonth updatedViewOnlyMonth) {
        Year year = updatedViewOnlyMonth.getYear();
        MonthOfYear monthOfYear = updatedViewOnlyMonth.getMonthOfYear();

        updateYearHeader(year);
        updateMonthHeader(monthOfYear);
        updateMonthView(updatedViewOnlyMonth);
    }

    private void updateYearHeader(Year year) {
        YearHeader yearHeader = new YearHeader(year);
        yearHeaderPlaceholder.getChildren().clear();
        yearHeaderPlaceholder.getChildren().add(yearHeader.getRoot());
    }

    private void updateMonthHeader(MonthOfYear monthOfYear) {
        MonthHeader monthHeader = new MonthHeader(monthOfYear);
        monthHeaderPlaceholder.getChildren().clear();
        monthHeaderPlaceholder.getChildren().add(monthHeader.getRoot());
    }

    private void updateMonthView(ViewOnlyMonth viewOnlyMonth) {
        monthViewPlaceholder.getChildren().clear();
        monthViewPlaceholder.getChildren().add(MonthView.generateMonthGrid(viewOnlyMonth, monthViewWidth));
    }

    @FXML
    private void handleExit() {
        PageManager.closeWindows();
    }

    private void handleShowList(String feedback) {
        if (!listWindow.isShowing()) {
            listWindow.show(feedback);
        } else {
            listWindow.requestFocus();
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @see AddressBookLogic#execute(String)
     */

    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = calendarLogic.executeCommand(commandText);

            if (calendarLogic.hasVisibleUpdates()) {
                ViewOnlyMonth updatedViewOnlyMonth = calendarLogic.getVisibleMonth();
                updateCalendarPage(updatedViewOnlyMonth);
                calendarLogic.completeVisibleUpdates();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowList()) {
                handleShowList(commandResult.getFeedbackToUser());
                resultDisplay.setFeedbackToUser("");
            }

            return commandResult;
        } catch (ParseException | CommandException e) {
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
    }

    @Override
    public Parent getParent() {
        return super.getRoot();
    }
}

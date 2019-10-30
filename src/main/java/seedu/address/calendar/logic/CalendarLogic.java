package seedu.address.calendar.logic;

import seedu.address.calendar.model.Calendar;
import seedu.address.calendar.model.date.ViewOnlyMonth;
import seedu.address.calendar.parser.CalendarParser;
import seedu.address.calendar.storage.CalendarStorage;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

import java.io.IOException;

public class CalendarLogic {
    private CalendarStorage calendarStorage;
    private Calendar calendar;

    public CalendarLogic(Calendar calendar, CalendarStorage calendarStorage) {
        this.calendarStorage = calendarStorage;
        this.calendar = calendar;
    }

    public CommandResult executeCommand(String commandText) throws CommandException, ParseException, IOException {
        Command<Calendar> command = new CalendarParser().parseCommand(commandText);
        CommandResult commandResult = command.execute(calendar);
        calendarStorage.saveCalendar(calendar.getCalendar());
        return commandResult;
    }

    public boolean hasVisibleUpdates() {
        return calendar.hasVisibleUpdates();
    }

    public ViewOnlyMonth getVisibleMonth() {
        return calendar.getMonth();
    }

    public void completeVisibleUpdates() {
        calendar.completeVisibleUpdates();
    }

}

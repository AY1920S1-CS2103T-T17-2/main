package seedu.address.calendar.commands;

import seedu.address.calendar.model.Calendar;
import seedu.address.calendar.model.date.ViewOnlyMonth;
import seedu.address.calendar.model.date.MonthOfYear;
import seedu.address.calendar.model.date.Year;
import seedu.address.calendar.parser.CliSyntax;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.Command;

import java.util.Optional;

/**
 * Shows user the his/her calendar for the specified month.
 */
public class ShowCommand extends Command<Calendar> {
    public static final String COMMAND_WORD = "show";
    public static final String MESSAGE_SHOWING_REQUESTED = "Showing month view for %s %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a month view of the requested month"
            + "Parameters: "
            + CliSyntax.PREFIX_MONTH + " MONTH "
            + "[" + CliSyntax.PREFIX_YEAR + " YEAR]" + "\n"
            + "Example: " + COMMAND_WORD + " " + CliSyntax.PREFIX_MONTH + " Dec " + CliSyntax.PREFIX_YEAR + " 2019";

    private MonthOfYear monthOfYear;
    private Year year;

    public ShowCommand(MonthOfYear monthOfYear, Optional<Year> year) {
        if (year.isEmpty()) {
            this.monthOfYear = monthOfYear;
            // get current year
            java.util.Calendar currentDate = java.util.Calendar.getInstance();
            int currentYear = currentDate.get(java.util.Calendar.YEAR);
            this.year = new Year(currentYear);
        } else {
            this.year = year.get();
        }

        this.monthOfYear = monthOfYear;
    }

    public CommandResult execute(Calendar calendar) {
        ViewOnlyMonth requestedViewOnlyMonth = new ViewOnlyMonth(monthOfYear, year);
        calendar.updateMonthShown(requestedViewOnlyMonth);
        String formattedFeedback = String.format(MESSAGE_SHOWING_REQUESTED, monthOfYear.toString(), year.toString());
        return new CommandResult(formattedFeedback);
    }
}

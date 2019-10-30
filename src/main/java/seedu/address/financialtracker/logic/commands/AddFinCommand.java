package seedu.address.financialtracker.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.financialtracker.model.expense.Expense;
import seedu.address.financialtracker.model.Model;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * add financial expenses command for Financial Tracker.
 */
public class AddFinCommand extends Command<Model> {

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an expense to the financial tracker.\n"
            + "e.g. add a/AMOUNT d/DESCRIPTION t/TYPE_OF_EXPENDITURE " +
            "(Remember to select where you at currently!)\n"
            + "You can also specify date and time with [date/DATE] and [time/TIME]";
    public static final String MESSAGE_SUCCESS = "Expense added";

    private final Expense expense;

    /**
     * Creates an AddFinCommand to add the specified {@code Expense}
     */
    public AddFinCommand(Expense expense) {
        requireNonNull(expense);
        this.expense = expense;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.addExpense(expense);
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddFinCommand // instanceof handles nulls
                && expense.equals(((AddFinCommand) other).expense));
    }
}

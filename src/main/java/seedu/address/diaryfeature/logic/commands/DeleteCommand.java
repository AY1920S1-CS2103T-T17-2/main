package seedu.address.diaryfeature.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.diaryfeature.model.diaryEntry.DiaryEntry;
import seedu.address.diaryfeature.model.DiaryModel;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Deletes an entry identified by the index from the Diary Book
 */
public class DeleteCommand extends Command<DiaryModel> {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the diary entry identified by the index number used in the displayed diary entry list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Entry: %1$s";
    private final Index targetIndex;

    /**
     * Generates a Delete command with index specified as {@code targetIndex}
     * @param targetIndex
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the delete command by deleted the target index
     * @param model {@code model} which the command should operate on.
     * @return
     * @throws CommandException if the index is beyond the size of the list
     */

    @Override
    public CommandResult execute(DiaryModel model) throws CommandException {
        requireNonNull(model);
        List<DiaryEntry> lastShownList = model.getFilteredDiaryEntryList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_USAGE);
        }

        DiaryEntry entryToDelete = model.deleteDiaryEntry(targetIndex.getOneBased());
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, entryToDelete));
    }

    /**
     * Checks if the 2 delete commands are equal
     * @param other another object to check
     * @return true if the object is the same as this command
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}

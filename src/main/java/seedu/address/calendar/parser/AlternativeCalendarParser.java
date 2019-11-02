package seedu.address.calendar.parser;

import seedu.address.calendar.commands.AddCommand;
import seedu.address.calendar.model.Calendar;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

public class AlternativeCalendarParser extends Parser {
    private static final String VALID_COMMAND_YES = "yes";
    private static final String VALID_COMMAND_NO = "no";

    @Override
    protected Command<Calendar> parseCommand(String commandWord, String arguments) throws ParseException {
        switch(commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandAlternativeParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    public Option parseOptionCommand(String commandText) {
        if (isYes(commandText)) {
            return Option.getTrue();
        }

        if (isNo(commandText)) {
            return Option.getFalse();
        }

        return Option.getInstance(textToInt(commandText));
    }

    private int textToInt(String commandText) {
        return Integer.parseInt(commandText.trim());
    }

    public static boolean isAlternativeCommand(String commandWord) {
        return isValidCommand(commandWord);
    }

    private static boolean isValidCommand(String commandWord) {
        String commandWordTrimmed = commandWord.trim();

        boolean isYes = isYes(commandWordTrimmed);
        boolean isNo = isNo(commandWordTrimmed);
        boolean isInteger = isInteger(commandWordTrimmed);

        return isYes || isNo || isInteger;
    }

    private static boolean isYes(String commandWord) {
        return commandWord.equalsIgnoreCase(VALID_COMMAND_YES);
    }

    private static boolean isNo(String commandWord) {
        return commandWord.equalsIgnoreCase(VALID_COMMAND_NO);
    }

    private static boolean isInteger(String commandWord) {
        boolean isInteger;
        try {
            Integer.parseInt(commandWord);
            isInteger = true;
        } catch (NumberFormatException e) {
            isInteger = false;
        }
        return isInteger;
    }
}

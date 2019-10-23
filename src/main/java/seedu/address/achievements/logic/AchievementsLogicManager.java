package seedu.address.achievements.logic;

import java.util.function.Supplier;
import java.util.logging.Logger;

import seedu.address.achievements.model.StatisticsModel;
import seedu.address.achievements.logic.parser.AchievementsParser;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.commands.Command;


public class AchievementsLogicManager implements AchievementsLogic {
    private final AchievementsParser achievementsParser;
    private final Logger logger = LogsCenter.getLogger(AchievementsLogicManager.class);
    private final Supplier<StatisticsModel> statisticsModelSupplier;

    public AchievementsLogicManager(Supplier<StatisticsModel> statisticsModelSupplier) {
        this.achievementsParser = new AchievementsParser();
        this.statisticsModelSupplier = statisticsModelSupplier;
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command<StatisticsModel> command = achievementsParser.parseCommand(commandText);
        commandResult = command.execute(statisticsModelSupplier.get());

        return commandResult;
    }

    @Override
    public int getTotalPersons() {
        return statisticsModelSupplier.get().getTotalPersons();
    }

}

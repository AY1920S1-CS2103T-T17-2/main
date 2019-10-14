package seedu.address.ui;

import javafx.scene.Scene;
import seedu.address.logic.commands.CommandResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pages {
    private static List<Page> pages;

    // todo: change this parameter to the specified scenes to minimise possible errors
    // keep PageScene constructor package private to prevent creation of this class elsewhere
    Pages(Page ... pages) {
        this.pages = Stream.of(pages)
                .collect(Collectors.toList());
    }

    public static Scene getPage(CommandResult commandResult) {
        PageType request = PageType.of(commandResult.getFeedback());
        Optional<Scene> requestedPage = pages.stream()
                .filter(p -> p.getPageType().equals(request))
                .map(p -> p.getScene())
                .findFirst();

        if (requestedPage.isEmpty()) {
            assert false : "Every get page command should have a page class implemented for it";
        }

        return requestedPage.get();
    }
}

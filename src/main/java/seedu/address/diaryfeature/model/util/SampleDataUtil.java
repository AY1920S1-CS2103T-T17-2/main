package seedu.address.diaryfeature.model.util;

import java.text.ParseException;

import seedu.address.diaryfeature.model.diaryEntry.DateFormatter;
import seedu.address.diaryfeature.model.diaryEntry.DiaryEntry;
import seedu.address.diaryfeature.model.diaryEntry.Memory;
import seedu.address.diaryfeature.model.diaryEntry.Place;
import seedu.address.diaryfeature.model.diaryEntry.Title;
import seedu.address.diaryfeature.model.exceptions.TitleException;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static DiaryEntry[] getSampleDiaryEntry() {
        try {
            return new DiaryEntry[] {
                new DiaryEntry(new Title("Temp"), DateFormatter.convertToDate("25/10/2019 1200"),
                        new Place("Copacabana"), new Memory("Her name was Lola, she was a showgirl")),
                new DiaryEntry(new Title("Temp 2"), DateFormatter.convertToDate("31/12/2019 1400"),
                        new Place("Kokomo"),new Memory("Aruba, Bahama, come on pretty mama"))
            };
        } catch (TitleException | ParseException ex) {
            return null;
        }

    }
}

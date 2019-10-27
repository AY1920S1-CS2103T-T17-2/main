package seedu.address.itinerary.model.event;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * The events which makes up the itinerary list.
 */
public class Event {
    private Title title;
    private Location location;
    private Description desc;
    private Date date;
    private Time time;
    private Tag tag;
    private boolean isDone;

    public Event(Title title, Date date, Location location, Description desc, Time time, Tag tag) {
        this.title = title;
        this.location = location;
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.tag = tag;
        this.isDone = false;
    }

    public Title getTitle() {
        return title;
    }

    public Description getDesc() {
        return desc;
    }

    public Date getDate() {
        return date;
    }

    public Location getLocation() {
        return location;
    }

    public Time getTime() {
        return time;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markIsDone() {
        isDone = true;
    }


    /**
     * Check whether two events have similar attribute value.
     * @param otherEvent another event in comparison to the current event.
     * @return boolean result whether the two event have the same attribute value.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && (otherEvent.getTitle().equals(getTitle())
                || otherEvent.getDate().equals(getDate())
                || otherEvent.getTime().equals(getTime()));
    }

    /**
     * Comparator to sort events in the events list in order of Title.
     */
    public static Comparator<Event> titleComparator = Comparator.comparing((Event e) -> e.getTitle().toString())
            .thenComparing(Object::toString);

    /**
     * Comparator to sort events in the events list in order of Location.
     */
    public static Comparator<Event> locationComparator = Comparator.comparing((Event e) -> e.getLocation().toString())
            .thenComparing(Object::toString);

    /**
     * Comparator to sort events in the events list in order of date.
     */
    public static Comparator<Event> dateComparator = new Comparator<Event>() {

        @Override
        public int compare(Event e1, Event e2) {
            long d1 = formatTime(e1.getDate().toString(), e1.getTime().toString());
            long d2 = formatTime(e2.getDate().toString(), e2.getTime().toString());

            return (int) (d1 - d2);
        }

        private long formatTime(String date, String time) {
            String result = date.substring(6) + date.substring(3, 5) + date.substring(0, 2);
            String zone = time.substring(6);
            if (zone.equals("a.m.")) {
                String hour = time.substring(0, 2);
                if (hour.equals("12")) {
                    result = result + "00" + time.substring(3, 5);
                } else {
                    result = result + time.substring(0, 2) + time.substring(3, 5);
                }
            } else {
                int changeTime = Integer.parseInt(time.substring(0, 2)) + 12;
                result = result + changeTime + time.substring(3, 5);
            }
            return Long.parseLong(result);
        }
    }.thenComparing(Object::toString);


    /**
     * Comparator to sort events in the events list in order of Completion.
     */
    public static Comparator<Event> completionComparator = new Comparator<Event>() {

        @Override
        public int compare(Event e1, Event e2) {
            boolean doneE1 = e1.getIsDone();
            boolean doneE2 = e2.getIsDone();

            if (doneE1) {
                return -1;
            } else if (doneE2) {
                return 1;
            } else {
                return 0;
            }
        }
    }.thenComparing(Object::toString);

    /**
     * Comparator to sort events in the events list in order of Completion.
     */
    public static Comparator<Event> priorityComparator = new Comparator<Event>() {

        @Override
        public int compare(Event e1, Event e2) {
            ArrayList<String> comparisonTag = new ArrayList<>();
            comparisonTag.add("Critical");
            comparisonTag.add("High");
            comparisonTag.add("Medium");
            comparisonTag.add("Low");
            comparisonTag.add("None");

            int index1 = comparisonTag.indexOf(e1.getTag().toString().split(" ")[1]);
            int index2 = comparisonTag.indexOf(e2.getTag().toString().split(" ")[1]);

            return index1 - index2;
        }
    }.thenComparing(Object::toString);
}

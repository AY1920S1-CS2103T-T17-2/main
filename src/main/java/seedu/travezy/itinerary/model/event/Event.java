package seedu.travezy.itinerary.model.event;

/**
 * The events which makes up the itinerary list.
 */
public class Event {
    private Title title;
    private Location location;
    private Description desc;
    private Date date;
    private Time time;
    private boolean isDone;

    public Event(Title title, Date date, Location location, Description desc, Time time) {
        this.title = title;
        this.location = location;
        this.desc = desc;
        this.date = date;
        this.time = time;
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
}

package seedu.iScam.model.meeting.exceptions;

public class MeetingNotFoundException extends RuntimeException {
    public MeetingNotFoundException() {
        super("Desired Meeting cannot be found");
    }
}
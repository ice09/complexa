package tech.indus340.complexa.chatbot.model;

import java.time.LocalDateTime;

public record CalendarEntry(String description, LocalDateTime startTime, LocalDateTime endTime) {

    boolean isAllDayEvent() {
        return startTime == null;
    }

}

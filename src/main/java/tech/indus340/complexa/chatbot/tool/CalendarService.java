package tech.indus340.complexa.chatbot.tool;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Service;
import tech.indus340.complexa.calendar.GoogleCalendarService;
import tech.indus340.complexa.chatbot.model.CalendarEntry;

import java.time.LocalDate;
import java.util.List;

@Service
public class CalendarService {

    private final GoogleCalendarService googleCalendarService;

    public CalendarService(GoogleCalendarService googleCalendarService) {
        this.googleCalendarService = googleCalendarService;
    }


    @Tool("Returns next events from calendar")
    public List<CalendarEntry> getNextEvents()  {
        try {
            return googleCalendarService.getNextCalendarEvents();
        } catch (Exception ex) {
            return List.of();
        }
    }

    @Tool("Returns today's date, use whenever today's date is needed")
    public LocalDate getCurrentDate() {
        System.out.println("getCurrentDate was called");
        return LocalDate.now();
    }

}

package tech.indus340.complexa.chatbot.model;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecurringSchoolSchedule {

    List<RecurringWeeklyCalendarEntry> scheduleEmilia = new ArrayList<>();
    List<RecurringWeeklyCalendarEntry> scheduleSimon = new ArrayList<>();

    public RecurringSchoolSchedule() {
        // Monday schedule Emilia
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Deutsch", DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 45)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Deutsch", DayOfWeek.MONDAY, LocalTime.of(8, 45), LocalTime.of(9, 30)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Englisch", DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 15)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Englisch", DayOfWeek.MONDAY, LocalTime.of(10, 35), LocalTime.of(11, 20)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Religion", DayOfWeek.MONDAY, LocalTime.of(11, 35), LocalTime.of(12, 20)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Religion", DayOfWeek.MONDAY, LocalTime.of(12, 20), LocalTime.of(13, 5)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("HA Betreuung", DayOfWeek.MONDAY, LocalTime.of(13, 50), LocalTime.of(14, 35)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Theater AG", DayOfWeek.MONDAY, LocalTime.of(14, 45), LocalTime.of(15, 20)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Theater AG", DayOfWeek.MONDAY, LocalTime.of(15, 20), LocalTime.of(16, 0)));

        // Tuesday schedule Emilia
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Musik", DayOfWeek.TUESDAY, LocalTime.of(8, 0), LocalTime.of(8, 45)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Musik", DayOfWeek.TUESDAY, LocalTime.of(8, 45), LocalTime.of(9, 30)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Englisch", DayOfWeek.TUESDAY, LocalTime.of(9, 30), LocalTime.of(10, 15)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Englisch", DayOfWeek.TUESDAY, LocalTime.of(10, 35), LocalTime.of(11, 20)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Mathe", DayOfWeek.TUESDAY, LocalTime.of(11, 35), LocalTime.of(12, 20)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Mathe", DayOfWeek.TUESDAY, LocalTime.of(12, 20), LocalTime.of(13, 5)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("HA Betreuung", DayOfWeek.TUESDAY, LocalTime.of(13, 50), LocalTime.of(14, 35)));

        // Wednesday schedule Emilia
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Deutsch", DayOfWeek.WEDNESDAY, LocalTime.of(8, 0), LocalTime.of(8, 45)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Englisch", DayOfWeek.WEDNESDAY, LocalTime.of(8, 45), LocalTime.of(9, 30)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Geographie", DayOfWeek.WEDNESDAY, LocalTime.of(9, 30), LocalTime.of(10, 15)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Kunst", DayOfWeek.WEDNESDAY, LocalTime.of(10, 35), LocalTime.of(11, 20)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("HA Betreuung", DayOfWeek.WEDNESDAY, LocalTime.of(13, 50), LocalTime.of(14, 35)));

        // Thursday schedule Emilia
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Deutsch", DayOfWeek.THURSDAY, LocalTime.of(8, 0), LocalTime.of(8, 45)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Englisch", DayOfWeek.THURSDAY, LocalTime.of(8, 45), LocalTime.of(9, 30)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Sport", DayOfWeek.THURSDAY, LocalTime.of(9, 30), LocalTime.of(10, 15)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Mathe", DayOfWeek.THURSDAY, LocalTime.of(10, 35), LocalTime.of(11, 20)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("HA Betreuung", DayOfWeek.THURSDAY, LocalTime.of(13, 50), LocalTime.of(14, 35)));

        // Friday schedule Emilia
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Bio", DayOfWeek.FRIDAY, LocalTime.of(8, 0), LocalTime.of(8, 45)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Bio", DayOfWeek.FRIDAY, LocalTime.of(8, 45), LocalTime.of(9, 30)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Sport", DayOfWeek.FRIDAY, LocalTime.of(9, 30), LocalTime.of(10, 15)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Mathe", DayOfWeek.FRIDAY, LocalTime.of(10, 35), LocalTime.of(11, 20)));
        scheduleEmilia.add(new RecurringWeeklyCalendarEntry("Mathe", DayOfWeek.FRIDAY, LocalTime.of(11, 35), LocalTime.of(12, 20)));

        // Monday schedule
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Deutsch", DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 45)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Deutsch", DayOfWeek.MONDAY, LocalTime.of(8, 45), LocalTime.of(9, 30)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("SU", DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 15)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Bewegung", DayOfWeek.MONDAY, LocalTime.of(10, 35), LocalTime.of(11, 20)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Mathe", DayOfWeek.MONDAY, LocalTime.of(11, 35), LocalTime.of(12, 20)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Englisch", DayOfWeek.MONDAY, LocalTime.of(12, 20), LocalTime.of(13, 5)));

        // Tuesday schedule
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Mathe", DayOfWeek.TUESDAY, LocalTime.of(8, 0), LocalTime.of(8, 45)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Deutsch", DayOfWeek.TUESDAY, LocalTime.of(8, 45), LocalTime.of(9, 30)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Sport", DayOfWeek.TUESDAY, LocalTime.of(9, 30), LocalTime.of(10, 15)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Sport", DayOfWeek.TUESDAY, LocalTime.of(10, 35), LocalTime.of(11, 20)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("SU", DayOfWeek.TUESDAY, LocalTime.of(11, 35), LocalTime.of(12, 20)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Lernzeit", DayOfWeek.TUESDAY, LocalTime.of(12, 20), LocalTime.of(13, 5)));

        // Wednesday schedule
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Musik", DayOfWeek.WEDNESDAY, LocalTime.of(8, 0), LocalTime.of(8, 45)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Deutsch", DayOfWeek.WEDNESDAY, LocalTime.of(8, 45), LocalTime.of(9, 30)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("SU", DayOfWeek.WEDNESDAY, LocalTime.of(9, 30), LocalTime.of(10, 15)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Mathe", DayOfWeek.WEDNESDAY, LocalTime.of(10, 35), LocalTime.of(11, 20)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Lernzeit", DayOfWeek.WEDNESDAY, LocalTime.of(11, 35), LocalTime.of(12, 20)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Englisch", DayOfWeek.WEDNESDAY, LocalTime.of(12, 20), LocalTime.of(13, 5)));

        // Thursday schedule
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Mathe", DayOfWeek.THURSDAY, LocalTime.of(8, 0), LocalTime.of(8, 45)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Religion/Ethik", DayOfWeek.THURSDAY, LocalTime.of(8, 45), LocalTime.of(9, 30)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Religion/Ethik", DayOfWeek.THURSDAY, LocalTime.of(9, 30), LocalTime.of(10, 15)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Lernzeit", DayOfWeek.THURSDAY, LocalTime.of(10, 35), LocalTime.of(11, 20)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Lernzeit", DayOfWeek.THURSDAY, LocalTime.of(11, 35), LocalTime.of(12, 20)));

        // Friday schedule
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Deutsch", DayOfWeek.FRIDAY, LocalTime.of(8, 0), LocalTime.of(8, 45)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("SU", DayOfWeek.FRIDAY, LocalTime.of(8, 45), LocalTime.of(9, 30)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Musik", DayOfWeek.FRIDAY, LocalTime.of(9, 30), LocalTime.of(10, 15)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Kunst", DayOfWeek.FRIDAY, LocalTime.of(10, 35), LocalTime.of(11, 20)));
        scheduleSimon.add(new RecurringWeeklyCalendarEntry("Kunst", DayOfWeek.FRIDAY, LocalTime.of(11, 35), LocalTime.of(12, 20)));
    }

    @Tool("Return school schedule for Emilia for the whole week")
    public List<RecurringWeeklyCalendarEntry> getRecurringSchoolSchedulingEmilia() {
        System.out.println("getRecurringSchoolSchedulingEmilia");
        return scheduleEmilia;
    }

    @Tool("Return school schedule for Simon for the whole week")
    public List<RecurringWeeklyCalendarEntry> getRecurringSchoolSchedulingSimon() {
        System.out.println("getRecurringSchoolSchedulingSimon");
        return scheduleSimon;
    }

    @Tool("Return school schedule for Emilia for a specific day of the week")
    public List<RecurringWeeklyCalendarEntry> getSchoolSchedulingForEmiliaForThisDay(DayOfWeek dayOfWeek) {
        System.out.println(("getSchoolSchedulingForEmiliaForThisDay with " + dayOfWeek));
        return scheduleEmilia.stream().filter(it -> it.getDay() == dayOfWeek).collect(Collectors.toList());
    }

    @Tool("Return school schedule for Simon for a specific day of the week")
    public List<RecurringWeeklyCalendarEntry> getSchoolSchedulingForSimonForThisDay(DayOfWeek dayOfWeek) {
        return scheduleSimon.stream().filter(it -> it.getDay() == dayOfWeek).collect(Collectors.toList());
    }

    @Tool("Return current day of week")
    public DayOfWeek getCurrentDayOfWeek() {
        return LocalDate.now().getDayOfWeek();
    }

}

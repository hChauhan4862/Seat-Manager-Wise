package wise.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTime {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private LocalDateTime dt;

    // Default constructor initializes with the current date and time
    public DateTime() {
        this.dt = LocalDateTime.now();
    }

    // Constructor with LocalDateTime
    public DateTime(LocalDateTime dateTime) {
        this.dt = dateTime;
    }

    // Constructor with date and time strings (yyyyMMdd and HHmmss)
    public DateTime(String date, String time) {
        this.dt = LocalDateTime.of(LocalDate.parse(date, DATE_FORMATTER), LocalTime.parse(time, TIME_FORMATTER));
    }

    // Constructor with combined datetime string (yyyyMMddHHmmss)
    public DateTime(String dateTime) {
        this.dt = LocalDateTime.parse(dateTime, DATETIME_FORMATTER);
    }

    public DateTime copy() {
        return new DateTime(this.dt);
    }

    // Getters for LocalDate, LocalTime, and LocalDateTime
    public LocalDate getDate() {
        return dt.toLocalDate();
    }

    public LocalTime getTime() {
        return dt.toLocalTime();
    }

    public LocalDateTime getDateTime() {
        return dt;
    }

    // Methods to get formatted date, time, and datetime strings
    public String getDateString() {
        return dt.format(DATE_FORMATTER);
    }

    public String getTimeString() {
        return dt.format(TIME_FORMATTER);
    }

    public String getDateTimeString() {
        return dt.format(DATETIME_FORMATTER);
    }

    // Overriding toString to provide a custom format while retaining default behavior
    @Override
    public String toString() {
        return "Custom DateTime Format: " + dt.format(DATETIME_FORMATTER);
    }

    // Forward the method todefault
    public DateTime plusSeconds(long seconds) {
        this.dt = dt.plusSeconds(seconds);
        return this;
    }

    public DateTime plusDays(long days) {
        this.dt = dt.plusDays(days);
        return this;
    }

    public DateTime truncatedTo(ChronoUnit unit) {
        this.dt = dt.truncatedTo(unit);
        return this;
    }
}

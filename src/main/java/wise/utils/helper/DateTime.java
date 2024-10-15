package wise.utils.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private DateTime() {}

    // DATE -> yyyyMMdd
    public static LocalDate currentDate() {
        String now = LocalDate.now().format(DATE_FORMATTER);
        return LocalDate.parse(now, DATE_FORMATTER);
    }

    public static LocalDate dateParser(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    public static String currentDateString() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    // TIME -> HHmmss
    public static LocalTime currentTime() {
        String now = LocalTime.now().format(TIME_FORMATTER);
        return LocalTime.parse(now, TIME_FORMATTER);
    }

    public static LocalTime timeParser(String time) {
        return LocalTime.parse(time, TIME_FORMATTER);
    }

    public static String currentTimeString() {
        return LocalTime.now().format(TIME_FORMATTER);
    }

    // DATETIME WITH SPACE -> yyyyMMddHHmmss
    public static LocalDateTime currentDateTime() {
        String now = LocalDateTime.now().format(DATETIME_FORMATTER);
        return LocalDateTime.parse(now, DATETIME_FORMATTER);
    }

    public static LocalDateTime dateTimeParser(String dateTime) {
        return LocalDateTime.parse(dateTime, DATETIME_FORMATTER);
    }

    public static String currentDateTimeString() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }
}
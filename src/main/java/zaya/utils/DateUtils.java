package zaya.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

public class DateUtils {

    private static DateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat simpleDateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static DateFormat simpleDateTimeFormatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static Timestamp getStringAsTimeStamp(String dateString) {
        LocalDateTime dateTime = LocalDateTime.parse(dateString.substring(0, 19), dateTimeFormatter);
        return Timestamp.valueOf(dateTime);
    }

    public static Calendar getStringAsZonedCalendar() {
        return Calendar.getInstance(TimeZone.getTimeZone("GMT-03:00"));
    }

    public static Date getStringAsDate(String dateString) throws ParseException {
        return new java.sql.Date(simpleDateFormatter.parse(dateString).getTime());
    }

    public static Date getStringAsDateTime(String dateString) throws ParseException {
        return new java.sql.Date(simpleDateTimeFormatter2.parse(dateString).getTime());
    }

    public static String getDateAsString(java.util.Date data) {
        return simpleDateFormatter.format(data);
    }

    public static String getDateTimeAsString(java.util.Date data) {
        return simpleDateTimeFormatter.format(data);
    }
}

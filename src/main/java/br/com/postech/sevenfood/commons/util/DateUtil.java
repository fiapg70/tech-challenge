package br.com.postech.sevenfood.commons.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * The type Date util.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {

    /**
     * Convert local date time.
     *
     * @param date the date
     * @return the local date time
     */
    public static LocalDateTime convert(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        ZonedDateTime zdt = cal.toZonedDateTime();
        return zdt.toLocalDateTime();
    }

    /**
     * Convert date.
     *
     * @param ldt the ldt
     * @return the date
     */
    public static Date convert(LocalDateTime ldt) {
        ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        GregorianCalendar cal = GregorianCalendar.from(zdt);
        return cal.getTime();
    }

    /**
     * Convert br time string.
     *
     * @param ldt the ldt
     * @return the string
     */
    public static String convertBRTime(LocalDateTime ldt) {
        var brFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return ldt.format(brFormatter);
    }

    /**
     * Convert date.
     *
     * @param date the date
     * @return the date
     */
    public static Date convert(String date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Convert date string.
     *
     * @param date the date
     * @return the string
     */
    public static String convertDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        return formatter.format(date);
    }
}

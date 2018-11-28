package br.com.hub.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
public class ConvertStringToTimestamp {

    public ConvertStringToTimestamp() {
        // default constructor
    }

    public static Timestamp convertStringToTimestamp(String dateEve) {

        try {

            DateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = formatter.parse(dateEve);
            Timestamp timeStampDate = new Timestamp(date.getTime());

            return timeStampDate;

        } catch (ParseException e) {
            System.out.println("Error parse date :" + e);
            return null;
        }
    }

    public static LocalDateTime convertStringToLocalDate(String localDate) {

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;

            LocalDateTime dateTime = LocalDateTime.parse(localDate, formatter);

            return dateTime;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertDateTimeForString(String date) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        String strDate = dateFormat.format(date);

        log.info("Converted Date to String: " + strDate);

        return strDate;
    }
}

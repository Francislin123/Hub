package br.com.hub.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

/**
 * Created by Francislin Dos Reis on 17/08/2018
 */

@Slf4j
public class GeneratedSequenceData {

    public static String generatedSequenceNumb() {

        int range = (int) (Math.random() * 999999);

        LocalDate date = LocalDate.now();

        String yer = date.toString().substring(0, 4);

        String month = date.toString().substring(5, 7);

        String day = date.toString().substring(8, 10);

        String yerAndMonthAndDayAndRange = yer + month + day + "-" + range;

        log.info("Sequence of numbs with date: " + yer + month + day + "-" + range);

        return yerAndMonthAndDayAndRange;
    }

    public static Long generatedSequenceForItens() {

        Long range = Long.valueOf((int) (Math.random() * 9999));

        Long yerAndMonthAndDayAndRange = range;

        log.info("Sequence of numbs with date: " + range);

        return yerAndMonthAndDayAndRange;
    }
}

package com.gabrielpolak.ticket.helpers;

import java.time.ZonedDateTime;

public class DateHelper {

    public static ZonedDateTime getDayStart(ZonedDateTime date) {
        return date.withHour(0).withMinute(0).withSecond(0);
    }

    public static ZonedDateTime getDayEnd(ZonedDateTime date) {
        return date.withHour(23).withMinute(59).withSecond(59);
    }

}

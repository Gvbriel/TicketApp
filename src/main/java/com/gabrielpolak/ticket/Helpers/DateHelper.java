package com.gabrielpolak.ticket.Helpers;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class DateHelper {

    public static ZonedDateTime getDayStart(ZonedDateTime date){
        return date.withHour(0).withMinute(0).withSecond(0);
    }

    public static ZonedDateTime getDayEnd(ZonedDateTime date){
        return date.withHour(23).withMinute(59).withSecond(59);
    }

}

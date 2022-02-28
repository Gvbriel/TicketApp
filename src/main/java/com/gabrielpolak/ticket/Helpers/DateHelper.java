package com.gabrielpolak.ticket.Helpers;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

public class DateHelper {

    public static LocalDateTime getDayStart(LocalDateTime date){
        return date.withHour(0).withMinute(0).withSecond(0);
    }

    public static LocalDateTime getDayEnd(LocalDateTime date){
        return date.withHour(23).withMinute(59).withSecond(59);
    }

}

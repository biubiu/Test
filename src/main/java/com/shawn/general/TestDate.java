package com.shawn.general;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.Days;

public class TestDate {
 public static void main(String[] args) {
    DateTime dateTime = new DateTime();
    DateTime threeDaysBefore = dateTime.minus(3*3600*24*1000);
    DateTime fifteenDaysBefore = dateTime.minus(15*3600*24*1000);
    DateTime thirtyDaysBefore = dateTime.minus(30L*3600L*24L*1000L);
    System.out.println("Days " + threeDaysBefore.toString() );
    System.out.println("15 Days " + fifteenDaysBefore.toString());
    System.out.println("30 Days " + "|"+ new Long(30*3600*24*1000) +"|" +thirtyDaysBefore.toString());
 }
}

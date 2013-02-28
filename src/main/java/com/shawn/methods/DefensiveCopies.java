package com.shawn.methods;

import java.util.Date;

public class DefensiveCopies {

    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date();
        Period period = new Period(start, end);
        start.setYear(78);
    }
}
/**
 *
 * intend to design Period as immutable class ,however,Date is mutable class ,
 * there will be problem with the constructor
 *
 *
 */
final class Period{
    private final Date start;
    private final Date end;

    //date is mutable, it rebel the purpose of the Period
    /*Period(Date start,Date end) {
        if(start.compareTo(end) >0){
            throw new IllegalArgumentException();
        }
        this.start= start;
        this.end = end;
    }*/

    /**
     * repaired constructor,return the date object
     * note did not use date clone since date is nonfinal, clone is not guaranteed to return an
     * object whose class is Date,may return a untrusted subclass
     */
    Period(Date start,Date end) {
        this.start= new Date(start.getTime());
        this.end = new Date(end.getTime());
        if(start.compareTo(end) >0){
            throw new IllegalArgumentException(start +" after " +end);
        }
    }
    //to prevent period.end().setYear(78)--->
    public Date start(){
        //return start;
        return new Date(start.getTime());
    }

    public Date end(){
        //return end;
        return new Date(end.getTime());
    }
}

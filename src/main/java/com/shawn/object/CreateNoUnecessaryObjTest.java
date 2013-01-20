package com.shawn.object;

import java.sql.Date;
import java.util.Calendar;
import java.util.TimeZone;

public class CreateNoUnecessaryObjTest {

    //create new string each time it is executed
    String string = new String("stringngngnng");
    //improved
    String string2 = "stringaninigaing";

    //avoid to reuse immutable obj,
}

class Person{
    private Date birthDate;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    //unnecessary to create a new calendar,timezone, each time it is invoked
    public boolean isBabyBoomer(){

        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY,1,0,0,0);
        Date boomStart = (Date) gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY,1,0,0,0);
        Date boomEnd = (Date)gmtCal.getTime();
        return birthDate.compareTo(boomStart) >=0 && birthDate.compareTo(boomEnd) <0;
    }
    //improved
    private static  Date BOOM_START;
    private static  Date BOOM_END;
    //will be initialized at the beginning, waste resource only if the method never used
    //could be lazy initializing these field alternatively
    static{
         Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
         gmtCal.set(1946, Calendar.JANUARY,1,0,0,0);
         BOOM_START = (Date)gmtCal.getTime();
         gmtCal.set(1965, Calendar.JANUARY,1,0,0,0);
         BOOM_END = (Date)gmtCal.getTime();
    }

    public boolean betterisBabyBoomer(){
        return birthDate.compareTo(BOOM_START)>=0 && birthDate.compareTo(BOOM_END)<0;
    }
}

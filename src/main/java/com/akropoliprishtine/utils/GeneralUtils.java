package com.akropoliprishtine.utils;

import java.util.Calendar;
import java.util.Date;

public class GeneralUtils {
    public static Date addDaysToDate(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}

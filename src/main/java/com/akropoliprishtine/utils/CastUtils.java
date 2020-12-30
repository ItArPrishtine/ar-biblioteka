package com.akropoliprishtine.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CastUtils {
    public static Date convertToSqlDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat(GeneralConstants.SQL_DATE_FORMAT);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.utils;

import java.text.SimpleDateFormat;

/**
 * Created by liran on 5/25/17.
 */
public class CustomDate {

     static SimpleDateFormat dateFormat = null;

    private CustomDate(){

    }

    public static SimpleDateFormat getDateFormat(){

        if(dateFormat == null)
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat;
    }
}

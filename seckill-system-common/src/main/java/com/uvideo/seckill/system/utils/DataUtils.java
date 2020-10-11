package com.uvideo.seckill.system.utils;


import jdk.nashorn.internal.parser.DateParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wencai.xu
 */
public class DataUtils {

    public static long getDataTimeMillis(String data) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = dateFormat.parse(data);
        return parse.getTime();
    }

    public static long timeDiff(String startDate,String endDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = null;
        Date end   = null;
        try {
            start = dateFormat.parse(startDate);
            end   = dateFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(end == null || start == null){
            return -500L;
        }
        return end.getTime() - start.getTime();
    }

}

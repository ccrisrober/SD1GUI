/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distribuidos.sd12015;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Cristian
 */
public class Common {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    public static Date strToDate(String str) throws ParseException {
        return format.parse(str);
    }
    
    public static String dateToStr(Date date) throws ParseException {
        return format.format(date);
    }
}

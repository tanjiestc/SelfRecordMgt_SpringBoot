package com.tanjie.selfrecordmgt;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilTest {


    public static void main(String[] args) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String format = f.format(new Date());
        System.out.println(format);

    }
}

package com.kingmed.immuno.util;

import java.util.Calendar;
import java.util.Date;

/*
 *统计当天批次
 */
public class DateTimeUtil {

    /*
     *获取当天的0点整时刻
     */
    public static Date getStNow() {
        // 创建一个Calendar对象
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        // 将时间修改为零点和24点整
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /*
     *获取当天的24点整时刻
     */
    public static Date getEdNow() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // 将时间修改为当天的24点整
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999); // 将毫秒数设置为999，表示当天的最后一毫秒

        return calendar.getTime();
    }
    /**
     * 获取到当前时间精确度到秒的时间
     */
    public static Date getNow(){
        long currentTimeMillis = System.currentTimeMillis();
        long currentTimeSeconds = currentTimeMillis / 1000;

        Date currentDate = new Date(currentTimeSeconds * 1000);
        return currentDate;
    }

    /**
     * 当前日期加指定的年月日
     * @param date
     * @return
     */
    public static Date addTime(Date date,int year,int month,int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH,month);
        calendar.add(Calendar.DAY_OF_MONTH,day);
        return calendar.getTime();
    }
}

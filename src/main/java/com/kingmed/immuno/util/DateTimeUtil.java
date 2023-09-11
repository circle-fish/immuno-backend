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
    public Date getStNow() {
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
    public Date getEdNow() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // 将时间修改为当天的24点整
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999); // 将毫秒数设置为999，表示当天的最后一毫秒

        return calendar.getTime();
    }
}

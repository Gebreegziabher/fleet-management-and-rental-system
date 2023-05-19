package miu.edu.carrental.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {
    public static long getDateDifference(Date start, Date end) {
        long diffInMilliseconds = end.getTime() - start.getTime();
        long diffInDays = TimeUnit.DAYS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
        return diffInDays + 1;
    }
}

package pere.maineventtool.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {
    public static Date parseStringDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return dateFormat.parse(date.replace('T', ' ').replace("+00:00", ""));
        } catch (ParseException e) {
            System.out.println(String.format("Failed parsing date: %s", e.getMessage()));

            return new Date();
        }
    }
}

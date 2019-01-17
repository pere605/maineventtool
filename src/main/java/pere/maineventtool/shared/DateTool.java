package pere.maineventtool.shared;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {
    public static Date parseStringDate(String date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        try {
            return dateFormat.parse(date.replace('T', ' ').replace("+00:00", ""));
        } catch (ParseException e) {
            System.out.println(String.format("Failed parsing date: %s", e.getMessage()));

            return new Date();
        }
    }

    public static String parseDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        return dateFormat.format(date);
    }
}

package opensource.haptik.task.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Rajan Maurya on 02/01/17.
 */

public class Utils {

    public static String getStringOfDate(String dateObj) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateObj);

        } catch (ParseException e) {
        }
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return df.format(date.getTime());
    }
}

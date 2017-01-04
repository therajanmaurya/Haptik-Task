package opensource.haptik.task.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import opensource.haptik.task.data.model.Message;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Rajan Maurya on 02/01/17.
 */

public class Utils {

    public static String getStringOfDate(String dateObj) {
        SimpleDateFormat toFullDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        Date fullDate = null;
        try {
            fullDate = toFullDate.parse(dateObj);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a", Locale.ENGLISH);
        return sdf.format(fullDate);
    }

    public static Map<String, List<Message>> filterMessages(List<Message> messages) {
        Map<String, List<Message>> userMessagesMap = new HashMap<>();
        for (Message msg : messages) {
            if (!userMessagesMap.containsKey(msg.getUserName())) {
                userMessagesMap.put(msg.getUserName(), new ArrayList<Message>());
            }
            userMessagesMap.get(msg.getUserName()).add(msg);
        }
        return userMessagesMap;
    }

    public static Integer getFavorites(List<Message> messages) {
        final List<Integer> favorites = new ArrayList<>();
        Observable.from(messages)
                .filter(new Func1<Message, Boolean>() {
                    @Override
                    public Boolean call(Message message) {
                        return message.getFavourite();
                    }
                })
                .subscribe(new Action1<Message>() {
                    @Override
                    public void call(Message message) {
                        favorites.add(1);
                    }
                });
        return favorites.size();
    }
}

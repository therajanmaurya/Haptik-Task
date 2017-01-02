package opensource.haptik.task.ui.interfaces;

import java.util.List;

import opensource.haptik.task.data.model.Message;

/**
 * Created by Rajan Maurya on 03/01/17.
 */

public interface UpdateChat {

    void updateChats(List<Message> messages);
}

package opensource.haptik.task.ui.main;

import java.util.List;

import opensource.haptik.task.data.model.Chat;
import opensource.haptik.task.data.model.Message;
import opensource.haptik.task.ui.base.MvpView;

/**
 * Created by Rajan Maurya on 02/01/17.
 */

public interface MainContracts {

    interface View extends MvpView {

        void showChats(List<Message> messages);

        void ShowChatDetails(Chat chat);

        void showProgressbar(Boolean show);

        void showError();
    }

    interface Presenter {

        void loadChats();
    }
 }

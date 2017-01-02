package opensource.haptik.task.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import opensource.haptik.task.R;
import opensource.haptik.task.data.model.Chat;
import opensource.haptik.task.ui.interfaces.UpdateChatDetails;

/**
 * Created by Rajan Maurya on 02/01/17.
 */

public class ChatDetailsFragment extends Fragment implements UpdateChatDetails {


    @BindView(R.id.data)
    TextView mTextView;

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_chat_details, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }


    @Override
    public void updateChatDetails(Chat chat) {
        mTextView.setText(String.valueOf(chat.getCount()));
    }
}

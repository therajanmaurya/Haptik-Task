package opensource.haptik.task.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import opensource.haptik.task.R;
import opensource.haptik.task.data.model.Chat;
import opensource.haptik.task.ui.adapter.ChatDetailAdapter;
import opensource.haptik.task.ui.base.BaseActivity;
import opensource.haptik.task.ui.interfaces.UpdateChatDetails;

/**
 * Created by Rajan Maurya on 02/01/17.
 */

public class ChatDetailsFragment extends Fragment implements UpdateChatDetails {


    @BindView(R.id.rv_chat)
    RecyclerView rvChat;

    @Inject
    ChatDetailAdapter chatDetailAdapter;

    View rootView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).activityComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_chat_details, container, false);
        ButterKnife.bind(this, rootView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvChat.setLayoutManager(layoutManager);
        rvChat.setHasFixedSize(true);
        rvChat.setAdapter(chatDetailAdapter);

        return rootView;
    }


    @Override
    public void updateChatDetails(Chat chat) {
        chatDetailAdapter.setChat(chat.getMessages());
    }
}


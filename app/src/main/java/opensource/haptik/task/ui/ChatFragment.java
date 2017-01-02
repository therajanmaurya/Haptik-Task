package opensource.haptik.task.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import opensource.haptik.task.R;
import opensource.haptik.task.data.model.Message;
import opensource.haptik.task.ui.adapter.ChatAdapter;
import opensource.haptik.task.ui.base.BaseActivity;
import opensource.haptik.task.ui.interfaces.UpdateChat;

/**
 * Created by Rajan Maurya on 02/01/17.
 */
public class ChatFragment extends Fragment implements UpdateChat {

    @BindView(R.id.rv_chat)
    RecyclerView rvChat;

    @Inject
    ChatAdapter mChatAdapter;

    View rootView;

    private List<Message> messages;

    public static ChatFragment newInstance() {
        Bundle arguments = new Bundle();
        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(arguments);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity)getActivity()).activityComponent().inject(this);
        messages = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, rootView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvChat.setLayoutManager(layoutManager);
        rvChat.setHasFixedSize(true);
        rvChat.setAdapter(mChatAdapter);

        return rootView;
    }


    @Override
    public void updateChats(List<Message> messages) {
        this.messages = messages;
        mChatAdapter.setTask(messages);
    }
}

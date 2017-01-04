package opensource.haptik.task.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import opensource.haptik.task.ui.base.RecyclerItemClickListener;
import opensource.haptik.task.ui.interfaces.Update;
import opensource.haptik.task.ui.interfaces.UpdateChat;

/**
 * Created by Rajan Maurya on 02/01/17.
 */
public class ChatFragment extends Fragment implements UpdateChat,
        RecyclerItemClickListener.OnItemClickListener  {

    @BindView(R.id.rv_chat)
    RecyclerView rvChat;

    @Inject
    ChatAdapter mChatAdapter;

    View rootView;

    private List<Message> messages;
    private ActionModeCallback actionModeCallback;
    private ActionMode actionMode;

    @Override
    public void onItemClick(View childView, int position) {
        if (actionMode != null) {
            toggleSelection(position);
        }
    }

    @Override
    public void onItemLongPress(View childView, int position) {
        if (actionMode == null) {
            actionMode = ((BaseActivity) getActivity()).startSupportActionMode
                    (actionModeCallback);
        }
        toggleSelection(position);
    }

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
        actionModeCallback = new ActionModeCallback();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, rootView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvChat.setLayoutManager(layoutManager);
        rvChat.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), this));
        rvChat.setHasFixedSize(true);
        mChatAdapter.setContext(getActivity());
        rvChat.setAdapter(mChatAdapter);

        return rootView;
    }


    @Override
    public void updateChats(List<Message> messages) {
        this.messages = messages;
        mChatAdapter.setChats(messages);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //As the Fragment Detach Finish the ActionMode
        if (actionMode != null) actionMode.finish();
    }

    /**
     * Toggle the selection state of an item.
     * <p>
     * If the item was the last one in the selection and is unselected, the selection is stopped.
     * Note that the selection must already be started (actionMode must not be null).
     *
     * @param position Position of the item to toggle the selection state
     */
    private void toggleSelection(int position) {
        mChatAdapter.toggleSelection(position);
        int count = mChatAdapter.getSelectedItemCount();

        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }

    /**
     * This ActionModeCallBack Class handling the User Event after the Selection of Chats. Like
     * Click of Menu Sync Button and finish the ActionMode
     */
    private class ActionModeCallback implements ActionMode.Callback {
        @SuppressWarnings("unused")
        private final String LOG_TAG = ActionModeCallback.class.getSimpleName();

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_favorites, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_favorites:

                    for (Integer position : mChatAdapter.getSelectedItems()) {
                        messages.get(position).setFavourite(true);
                    }
                    ((Update)getActivity()).update(messages);

                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mChatAdapter.clearSelection();
            actionMode = null;
        }
    }
}

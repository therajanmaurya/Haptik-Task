package opensource.haptik.task.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import opensource.haptik.task.R;
import opensource.haptik.task.data.model.Message;
import opensource.haptik.task.ui.base.SelectableAdapter;
import opensource.haptik.task.utils.CircularImageView;
import opensource.haptik.task.utils.Utils;

/**
 * Created by Rajan Maurya on 04/01/17.
 */
public class ChatDetailAdapter extends SelectableAdapter<ChatDetailAdapter.ViewHolder> {

    private List<Message> mMessages;
    private List<String> users;
    Map<String, List<Message>> chatMap;

    @Inject
    public ChatDetailAdapter() {
        mMessages = new ArrayList<>();
        users = new ArrayList<>();
        chatMap = new HashMap<>();
    }


    @Override
    public ChatDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat_details, parent, false);
        return new ChatDetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatDetailAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(users.get(position));
        holder.tvChats.setText(String.valueOf(chatMap.get(users.get(position)).size()));
        holder.tv_favorites.setText(String.valueOf(Utils.getFavorites(chatMap.get(users.get(position)))));
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return chatMap.size();
    }

    public void setChat(List<Message> messages) {
        mMessages = messages;
        chatMap = Utils.filterMessages(mMessages);
        users = new ArrayList<>(chatMap.keySet());
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.circularImageView)
        CircularImageView circularImageView;

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_chats)
        TextView tvChats;

        @BindView(R.id.tv_favorites)
        TextView tv_favorites;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
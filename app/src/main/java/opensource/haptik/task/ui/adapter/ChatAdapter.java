package opensource.haptik.task.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import opensource.haptik.task.R;
import opensource.haptik.task.data.model.Message;
import opensource.haptik.task.ui.base.SelectableAdapter;
import opensource.haptik.task.utils.Constants;

/**
 * Created by Rajan Maurya on 02/01/17.
 */
public class ChatAdapter extends SelectableAdapter<ChatAdapter.ViewHolder> {

    private List<Message> messages;

    private final int VIEW_ME = 1;
    private final int VIEW_OTHER = 0;

    @Inject
    public ChatAdapter() {
        messages = new ArrayList<>();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case VIEW_ME:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_chat_me, parent, false);
                return new ChatAdapter.ViewHolder(view);
            case VIEW_OTHER:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_chat_other, parent, false);
                return new ChatAdapter.ViewHolder(view);
            default:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_chat_me, parent, false);
                return new ChatAdapter.ViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).getName().equals(Constants.ME)? VIEW_ME: VIEW_OTHER;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(messages.get(position).getName());
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setTask(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
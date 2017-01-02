package opensource.haptik.task.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import opensource.haptik.task.R;
import opensource.haptik.task.data.model.Message;
import opensource.haptik.task.ui.base.SelectableAdapter;

/**
 * Created by Rajan Maurya on 02/01/17.
 */
public class ChatAdapter extends SelectableAdapter<ChatAdapter.ViewHolder> {

        private List<Message> messages;

        @Inject
        public ChatAdapter() {
            messages = new ArrayList<>();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_chat, parent, false);
            return new ChatAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //holder.mTextView.setText(messages.get(position).getUserName());
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

            @BindView(R.id.avatar)
            ImageView mImageView;

            @BindView(R.id.message)
            TextView mTextView;


            public ViewHolder(View v) {
                super(v);
                ButterKnife.bind(this, v);
            }
        }
    }
package opensource.haptik.task.ui.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import opensource.haptik.task.R;
import opensource.haptik.task.data.model.Chat;
import opensource.haptik.task.data.model.Message;
import opensource.haptik.task.ui.ChatDetailsFragment;
import opensource.haptik.task.ui.ChatFragment;
import opensource.haptik.task.ui.adapter.HaptikAdapter;
import opensource.haptik.task.ui.base.BaseActivity;
import opensource.haptik.task.ui.interfaces.Update;
import opensource.haptik.task.ui.interfaces.UpdateChat;
import opensource.haptik.task.ui.interfaces.UpdateChatDetails;
import opensource.haptik.task.utils.Constants;

public class MainActivity extends BaseActivity
        implements MainContracts.View, Update {

    @BindView(R.id.vp_chat)
    ViewPager vpChat;

    @BindView(R.id.tl_chat)
    TabLayout mTabLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_main);
        mMainPresenter.attachView(this);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setupViewPager(vpChat);
        mTabLayout.setupWithViewPager(vpChat);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mMainPresenter.loadChats();
    }

    @Override
    public void showChats(List<Message> messages) {
        ((UpdateChat)getSupportFragmentManager()
                .findFragmentByTag(getFragmentTag(0))).updateChats(messages);
    }

    @Override
    public void showChatDetails(Chat chat) {
        ((UpdateChatDetails)getSupportFragmentManager()
                .findFragmentByTag(getFragmentTag(1))).updateChatDetails(chat);
    }

    @Override
    public void showProgressbar(Boolean show) {

    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.error_loading_chats), Toast.LENGTH_SHORT).show();
    }

    private String getFragmentTag(int position) {
        return "android:switcher:" + R.id.vp_chat + ":" + position;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    private void setupViewPager(ViewPager viewPager) {
        HaptikAdapter adapter = new HaptikAdapter(getSupportFragmentManager());
        adapter.addFragment(ChatFragment.newInstance(), Constants.CHAT);
        adapter.addFragment(new ChatDetailsFragment(), Constants.CHAT_DETAILS);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void update(List<Message> messages) {
        Chat chat = new Chat();
        chat.setMessages(messages);
        showChatDetails(chat);
    }
}

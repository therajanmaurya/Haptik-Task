package opensource.haptik.task.ui.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import opensource.haptik.task.R;
import opensource.haptik.task.data.model.Message;
import opensource.haptik.task.ui.base.BaseActivity;
import opensource.haptik.task.ui.ChatFragment;
import opensource.haptik.task.ui.ChatDetailsFragment;

public class MainActivity extends BaseActivity
        implements MainContracts.View {

    @BindView(R.id.vp_chat)
    ViewPager vpChat;

    @BindView(R.id.tl_chat)
    TabLayout tlChat;

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
        tlChat.setupWithViewPager(vpChat);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMainPresenter.loadChats();
    }

    @Override
    public void showChats(List<Message> messages) {
        Toast.makeText(this, messages.get(0).getUserName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowChatDetails() {

    }

    @Override
    public void showProgressbar(Boolean show) {

    }

    @Override
    public void showError() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new ChatFragment(), "Chat");
        adapter.addFragment(new ChatDetailsFragment(), "Details");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}

package opensource.haptik.task.ui.main;

import javax.inject.Inject;

import opensource.haptik.task.data.DataManager;
import opensource.haptik.task.data.model.Chat;
import opensource.haptik.task.injection.ConfigPersistent;
import opensource.haptik.task.ui.base.BasePresenter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Rajan Maurya on 02/01/17.
 */
@ConfigPersistent
public class MainPresenter extends BasePresenter<MainContracts.View>
        implements MainContracts.Presenter {

    private final DataManager mDataManager;
    private final CompositeSubscription mSubscriptions;

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void attachView(MainContracts.View mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        mSubscriptions.clear();
    }

    @Override
    public void loadChats() {
        checkViewAttached();
        getMvpView().showProgressbar(true);
        mSubscriptions.add(mDataManager.syncChats()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Chat>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showProgressbar(false);
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(Chat chat) {
                        getMvpView().showProgressbar(false);
                        getMvpView().showChats(chat.getMessages());
                        getMvpView().showChatDetails(chat);
                    }
                })
        );
    }
}

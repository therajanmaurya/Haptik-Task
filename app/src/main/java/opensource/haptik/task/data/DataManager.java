package opensource.haptik.task.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import opensource.haptik.task.data.local.DatabaseHelper;
import opensource.haptik.task.data.model.Chat;
import opensource.haptik.task.data.remote.HapTikService;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Rajan Maurya on 02/01/17.
 */
@Singleton
public class DataManager {

    private final HapTikService mHapTikService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(HapTikService hapTikService, PreferencesHelper preferencesHelper,
            DatabaseHelper databaseHelper) {
        mHapTikService = hapTikService;
        mDatabaseHelper = databaseHelper;
        mPreferencesHelper = preferencesHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<Chat> syncChats() {
        return mHapTikService.getChats()
                .concatMap(new Func1<Chat, Observable<? extends Chat>>() {
                    @Override
                    public Observable<? extends Chat> call(Chat chat) {
                        return Observable.just(chat);
                    }
                });
    }
}

package opensource.haptik.task.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import opensource.haptik.task.data.DataManager;
import opensource.haptik.task.data.PreferencesHelper;
import opensource.haptik.task.data.local.DatabaseHelper;
import opensource.haptik.task.data.remote.HapTikService;
import opensource.haptik.task.injection.ApplicationContext;
import opensource.haptik.task.injection.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();
    Application application();
    HapTikService hapTikService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
}

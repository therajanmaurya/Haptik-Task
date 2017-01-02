package opensource.haptik.task;

import android.app.Application;
import android.content.Context;

import opensource.haptik.task.injection.component.ApplicationComponent;
import opensource.haptik.task.injection.component.DaggerApplicationComponent;
import opensource.haptik.task.injection.module.ApplicationModule;

/**
 * Created by Rajan Maurya on 02/01/17.
 */

public class HaptikApplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static HaptikApplication get(Context context) {
        return (HaptikApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}

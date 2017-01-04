package opensource.haptik.task.injection.component;

import dagger.Subcomponent;
import opensource.haptik.task.injection.PerActivity;
import opensource.haptik.task.injection.module.ActivityModule;
import opensource.haptik.task.ui.ChatDetailsFragment;
import opensource.haptik.task.ui.ChatFragment;
import opensource.haptik.task.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(ChatFragment chatFragment);

    void inject(ChatDetailsFragment chatDetailsFragment);

}
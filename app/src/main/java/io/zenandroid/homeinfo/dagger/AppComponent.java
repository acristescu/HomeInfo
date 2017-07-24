package io.zenandroid.homeinfo.dagger;

import dagger.Component;
import io.zenandroid.homeinfo.base.BaseActivity;
import io.zenandroid.homeinfo.landing.LandingPresenter;

/**
 * Created by acristescu on 22/06/2017.
 */

@Component(modules={AppModule.class, DarkSkyServiceModule.class})
public interface AppComponent {

	void inject(BaseActivity activity);

	void inject(LandingPresenter presenter);

}

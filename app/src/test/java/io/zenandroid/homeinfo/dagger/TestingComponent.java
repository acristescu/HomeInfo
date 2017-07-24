package io.zenandroid.homeinfo.dagger;

import dagger.Component;

/**
 * Created by acristescu on 02/07/2017.
 */
@Component(modules={AppModule.class, MockDarkSkyServiceModule.class})
public interface TestingComponent extends AppComponent {
}

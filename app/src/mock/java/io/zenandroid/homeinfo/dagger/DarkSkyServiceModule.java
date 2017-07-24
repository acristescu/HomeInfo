package io.zenandroid.homeinfo.dagger;

import dagger.Binds;
import dagger.Module;
import io.zenandroid.homeinfo.service.DarkSkyService;
import io.zenandroid.homeinfo.service.MockDarkSkyService;

/**
 * Created by acristescu on 30/06/2017.
 */
@Module
public abstract class DarkSkyServiceModule {

	@Binds
	abstract DarkSkyService provideBBCService(MockDarkSkyService bbcService);
}

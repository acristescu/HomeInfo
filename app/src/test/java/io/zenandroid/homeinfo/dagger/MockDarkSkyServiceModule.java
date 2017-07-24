package io.zenandroid.homeinfo.dagger;

import dagger.Module;
import dagger.Provides;
import io.zenandroid.homeinfo.service.DarkSkyService;

/**
 * Created by acristescu on 02/07/2017.
 */

@Module
public class MockDarkSkyServiceModule {

	private DarkSkyService mockService;

	public MockDarkSkyServiceModule(DarkSkyService mockService) {
		this.mockService = mockService;
	}

	@Provides
	public DarkSkyService provideBBCService() {
		return mockService;
	}
}

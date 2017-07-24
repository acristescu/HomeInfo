package io.zenandroid.homeinfo.landing;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStreamReader;

import io.reactivex.Single;
import io.zenandroid.homeinfo.dagger.DaggerTestingComponent;
import io.zenandroid.homeinfo.dagger.Injector;
import io.zenandroid.homeinfo.dagger.MockDarkSkyServiceModule;
import io.zenandroid.homeinfo.model.darksky.Request;
import io.zenandroid.homeinfo.model.darksky.WeatherResponse;
import io.zenandroid.homeinfo.service.DarkSkyService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by acristescu on 02/07/2017.
 */

public class LandingPresenterTest {

	@Mock LandingContract.View view;

	@Mock
	DarkSkyService service;

	private LandingPresenter presenter;
	private WeatherResponse response;

	@Before
	public void setup() throws IOException {
		MockitoAnnotations.initMocks(this);
		Injector.setComponent(
				DaggerTestingComponent.builder().
						mockDarkSkyServiceModule(new MockDarkSkyServiceModule(service)).build()
		);
		presenter = new LandingPresenter(view);

		try (InputStreamReader reader = new InputStreamReader(LandingPresenterTest.class.getClassLoader().getResourceAsStream("weather_response.json"))) {
			response = new Gson().fromJson(reader, WeatherResponse.class);
		}

		when(service.fetchWeather(any(Request.class))).thenReturn(Single.just(response));
	}

	@Test
	public void testSongsAreLoaded() {
		presenter.subscribe();

		verify(view).showProgressDialog();
		verify(service).fetchWeather(any(Request.class));

		verify(view).displayWeatherReport(response);
		verify(view).dismissProgressDialog();

		verifyNoMoreInteractions(view);
		verifyNoMoreInteractions(service);
		presenter.unsubscribe();
	}

	@Test
	public void testApiErrorIsHandled() {
		final Exception exception = new Exception("An exception");
		when(service.fetchWeather(any(Request.class))).thenReturn(Single.error(exception));

		presenter.subscribe();

		verify(view).showProgressDialog();
		verify(service).fetchWeather(any(Request.class));

		verify(view).dismissProgressDialog();
		verify(view).showErrorMessage(exception.getMessage());

		verifyNoMoreInteractions(view);
		verifyNoMoreInteractions(service);
		presenter.unsubscribe();
	}
}

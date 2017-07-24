package io.zenandroid.homeinfo.service;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.zenandroid.homeinfo.api.DarkSkyApi;
import io.zenandroid.homeinfo.model.darksky.Request;
import io.zenandroid.homeinfo.model.darksky.WeatherResponse;
import io.zenandroid.homeinfo.util.EspressoIdlingResource;

public class DarkSkyServiceImpl implements DarkSkyService {

	private final static String TAG = DarkSkyServiceImpl.class.getSimpleName();

	private final DarkSkyApi darkSkyApi;

	@Inject
	public DarkSkyServiceImpl(DarkSkyApi api) {
		darkSkyApi = api;
	}

	public Single<WeatherResponse> fetchWeather(Request request) {
		EspressoIdlingResource.getInstance().increment();
		return darkSkyApi.getWeather(request.getLat(), request.getLng(), request.getQueryParams())
				.subscribeOn(Schedulers.computation())
				.observeOn(AndroidSchedulers.mainThread())
				.doFinally(() -> EspressoIdlingResource.getInstance().decrement());
	}
}

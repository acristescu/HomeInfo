package io.zenandroid.homeinfo.service;

import io.reactivex.Single;
import io.zenandroid.homeinfo.model.darksky.Request;
import io.zenandroid.homeinfo.model.darksky.WeatherResponse;

/**
 * Created by acristescu on 30/06/2017.
 */

public interface DarkSkyService {
	Single<WeatherResponse> fetchWeather(Request request);
}

package io.zenandroid.homeinfo.landing;

import io.zenandroid.homeinfo.model.darksky.WeatherResponse;

/**
 * Created by acristescu on 02/07/2017.
 */

public interface LandingContract {
	interface View extends io.zenandroid.homeinfo.base.View<Presenter> {
		void displayWeatherReport(WeatherResponse response);
	}

	interface Presenter extends io.zenandroid.homeinfo.base.Presenter {

	}
}

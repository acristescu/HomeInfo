package io.zenandroid.homeinfo.landing;

import javax.inject.Inject;

import io.zenandroid.homeinfo.base.BasePresenter;
import io.zenandroid.homeinfo.dagger.Injector;
import io.zenandroid.homeinfo.model.darksky.Request;
import io.zenandroid.homeinfo.model.darksky.WeatherResponse;
import io.zenandroid.homeinfo.service.DarkSkyService;

/**
 * Created by acristescu on 02/07/2017.
 */

public class LandingPresenter extends BasePresenter implements LandingContract.Presenter {

	private LandingContract.View view;

	@Inject
	DarkSkyService darkSkyService;

	public LandingPresenter(LandingContract.View view) {
		super(view);
		this.view = view;
		Injector.get().inject(this);
	}

	@Override
	public void subscribe() {
		final Request request = new Request();
		request.setLat("51.5074");
		request.setLng("0.1278");
		request.setUnits(Request.Units.SI);

		view.showProgressDialog();
		addDisposable(
				darkSkyService.fetchWeather(request)
				.subscribe(this::onWeatherResponse, this::onError)
		);
	}

	private void onWeatherResponse(WeatherResponse response) {
		view.dismissProgressDialog();
		view.displayWeatherReport(response);
	}

}

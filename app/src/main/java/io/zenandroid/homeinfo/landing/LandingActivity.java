package io.zenandroid.homeinfo.landing;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.zenandroid.homeinfo.R;
import io.zenandroid.homeinfo.base.BaseActivity;
import io.zenandroid.homeinfo.dagger.Injector;
import io.zenandroid.homeinfo.model.darksky.DataPoint;
import io.zenandroid.homeinfo.model.darksky.WeatherResponse;

public class LandingActivity extends BaseActivity implements LandingContract.View {

	@BindView(R.id.text) TextView textView;

	private LandingContract.Presenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_landing);
		Injector.get().inject(this);
		ButterKnife.bind(this);

		presenter = new LandingPresenter(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		presenter.subscribe();
	}

	@Override
	protected void onPause() {
		super.onPause();
		presenter.unsubscribe();
	}

	@Override
	public void displayWeatherReport(WeatherResponse response) {
		System.out.println("Forecast received:");
		final SimpleDateFormat sdf = new SimpleDateFormat("EE");
		final StringBuilder sb = new StringBuilder();
		for(DataPoint point : response.getDaily().getData()) {
			final String line = sdf.format(new Date(point.getTime() * 1000)) + ":\t" +
					Math.round(point.getTemperatureMin()) + "°C - " +
					Math.round(point.getTemperatureMax()) + "°C\t" +
					point.getSummary();

			System.out.println(line);
			if(sb.length() != 0) {
				sb.append("\n");
			}
			sb.append(line);
		}
		textView.setText(sb.toString());
	}

}

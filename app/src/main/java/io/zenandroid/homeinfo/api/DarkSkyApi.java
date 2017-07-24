package io.zenandroid.homeinfo.api;

import java.util.Map;

import io.reactivex.Single;
import io.zenandroid.homeinfo.model.darksky.WeatherResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface DarkSkyApi {
	@GET("{lat},{lon}")
	Single<WeatherResponse> getWeather(@Path("lat") String lat, @Path("lon") String lon, @QueryMap Map<String, String> map);
}

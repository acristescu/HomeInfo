package io.zenandroid.homeinfo.util;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.fonts.MeteoconsIcons;

/**
 * Created by acristescu on 30/07/2017.
 */

public class WeatherIconsUtil {

    public static String getIconString(String darkSkyIcon) {
        switch (darkSkyIcon) {
            case "clear-day":
                return "{mc-sun}";
            case "clear-night":
                return "{mc-moon}";
            case "rain":
                return "{mc-cloud-rain}";
            case "snow":
                return "{mc-cloud-snow}";
            case "sleet":
                return "{mc-cloud-rain2}";
            case "wind":
                return "{mc-wind-o}";
            case "fog":
                return "{mc-cloudy-o}";
            case "cloudy":
                return "{mc-cloud}";
            case "partly-cloudy-day":
                return "{mc-sun-cloud}";
            case "partly-cloudy-night":
                return "{mc-moon-cloud}";
        }
        return "{mc-not-applicable}";
    }

    public static Icon getIcon(String iconString) {
        switch (iconString) {
            case "clear-day":
                return MeteoconsIcons.mc_sun;
            case "clear-night":
                return MeteoconsIcons.mc_moon;
            case "rain":
                return MeteoconsIcons.mc_cloud_rain;
            case "snow":
                return MeteoconsIcons.mc_snow;
            case "sleet":
                return MeteoconsIcons.mc_cloud_rain2;
            case "wind":
                return MeteoconsIcons.mc_wind_o;
            case "fog":
                return MeteoconsIcons.mc_cloudy_o;
            case "cloudy":
                return MeteoconsIcons.mc_cloud;
            case "partly-cloudy-day":
                return MeteoconsIcons.mc_sun_cloud;
            case "partly-cloudy-night":
                return MeteoconsIcons.mc_moon_cloud;
        }
        return MeteoconsIcons.mc_not_applicable;
    }
}

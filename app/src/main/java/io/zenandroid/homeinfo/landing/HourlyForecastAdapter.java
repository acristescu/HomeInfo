package io.zenandroid.homeinfo.landing;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.zenandroid.homeinfo.R;
import io.zenandroid.homeinfo.model.darksky.DataBlock;
import io.zenandroid.homeinfo.model.darksky.DataPoint;
import io.zenandroid.homeinfo.util.WeatherIconsUtil;

/**
 * Created by acristescu on 27/07/2017.
 */

public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder> {

    private DataBlock forecast;
    private double minTemp = 100;
    private double maxTemp;

    public void setForecast(DataBlock forecast) {
        this.forecast = forecast;
        for(DataPoint point : forecast.getData()) {
            if(point.getTemperature() > maxTemp) {
                maxTemp = point.getTemperature();
            }
            if(point.getTemperature() < minTemp) {
                minTemp = point.getTemperature();
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.i_hourly_forecast, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DataPoint point = forecast.getData().get(position);
        final DataPoint prev = position == 0 ? point : forecast.getData().get(position - 1);
        final DataPoint next = position == getItemCount() - 1 ? point : forecast.getData().get(position + 1);

        holder.textView.setText(String.format("%d°", Math.round(point.getTemperature())));
        Date date = new Date(point.getTime() * 1000);
        holder.time.setText(String.format("%tH:%tM", date, date));

        holder.graphTemperatureView.setMaxTemp(maxTemp);
        holder.graphTemperatureView.setMinTemp(minTemp);
        holder.graphTemperatureView.setPrevTemp(prev.getTemperature());
        holder.graphTemperatureView.setNextTemp(next.getTemperature());
        holder.graphTemperatureView.setTemp(point.getTemperature());

        holder.icon.setImageDrawable(
                new IconDrawable(holder.icon.getContext(), WeatherIconsUtil.getIcon(point.getIcon()))
                        .sizeDp(35)
                        .color(0x01579B)
        );

        if(point.getPrecipProbability() != null && point.getPrecipProbability() > 0) {
            holder.rainChanceView.setText(String.format("{wi_raindrops 140%%} %d%%", (int)(point.getPrecipProbability() * 100)));
        } else {
            holder.rainChanceView.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return forecast == null ? 0 : forecast.getData().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView) TextView textView;
        @BindView(R.id.time) TextView time;
        @BindView(R.id.graph) GraphTemperatureView graphTemperatureView;
        @BindView(R.id.icon) AppCompatImageView icon;
        @BindView(R.id.rain_chance) IconTextView rainChanceView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

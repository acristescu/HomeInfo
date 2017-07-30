package io.zenandroid.homeinfo.landing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by acristescu on 27/07/2017.
 */

public class GraphTemperatureView extends View {

    private double minTemp;
    private double maxTemp;
    private double temp;
    private double prevTemp;
    private double nextTemp;

    public GraphTemperatureView(Context context) {
        super(context);
    }

    public GraphTemperatureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GraphTemperatureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GraphTemperatureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setPrevTemp(double prevTemp) {
        this.prevTemp = prevTemp;
    }

    public void setNextTemp(double nextTemp) {
        this.nextTemp = nextTemp;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();

        double tipLevel = calculateTipLevel(temp);
        double nextTipLevel = calculateTipLevel(nextTemp);
        double prevTipLevel = calculateTipLevel(prevTemp);

        Path path = new Path();
        path.setFillType(Path.FillType.WINDING);
        path.moveTo(w / 2, (int)tipLevel);
        path.lineTo(w, (int)((tipLevel + nextTipLevel) / 2));
        path.lineTo(w, h);
        path.lineTo(0, h);
        path.lineTo(0, (int)((tipLevel + prevTipLevel) / 2));
        path.lineTo(w, (int)((tipLevel + nextTipLevel) / 2));
        path.close();

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(0x66FFFFFF);
        canvas.drawPath(path, paint);

        paint.setColor(0xFFB3E5FC);
        paint.setStrokeWidth(3);
        canvas.drawLine(0, (int)((tipLevel + prevTipLevel) / 2), w / 2, (int)tipLevel, paint);
        canvas.drawLine(w / 2, (int)tipLevel, w, (int)((tipLevel + nextTipLevel) / 2), paint);

    }

    private double calculateTipLevel(double temp) {
        return getHeight() - (temp - minTemp) / (maxTemp - minTemp) * getHeight();
    }
}

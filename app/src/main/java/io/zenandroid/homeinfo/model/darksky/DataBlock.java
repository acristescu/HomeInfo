package io.zenandroid.homeinfo.model.darksky;

import java.util.List;

public class DataBlock {

    private String summary;
    private String icon;
    private List<DataPoint> data;

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public List<DataPoint> getData() {
        return data;
    }
}

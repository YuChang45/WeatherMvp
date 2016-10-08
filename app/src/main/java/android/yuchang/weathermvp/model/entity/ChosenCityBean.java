package android.yuchang.weathermvp.model.entity;

import android.yuchang.weathermvp.model.base.BaseModel;

/**
 * @author MrChang
 *         created  at  2016/1/12.
 * @description
 */
public class ChosenCityBean implements BaseModel {

    /***
     * 城市名称
     */
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getTemperatureStr() {
        return temperatureStr;
    }

    public void setTemperatureStr(String temperatureStr) {
        this.temperatureStr = temperatureStr;
    }

    public int getSelectedFlag() {
        return selectedFlag;
    }

    public void setSelectedFlag(int selectedFlag) {
        this.selectedFlag = selectedFlag;
    }

    public int getTemperatureCode() {
        return temperatureCode;
    }

    public void setTemperatureCode(int temperatureCode) {
        this.temperatureCode = temperatureCode;
    }

    @Override
    public boolean equals(Object o) {
        if (cityName.equals(((ChosenCityBean) o).getCityName())) {
            return true;
        } else {
            return false;
        }
    }

    /***
     * 最高温度
     */
    private String maxTemperature;
    /***
     * 最低温度
     */
    private String minTemperature;
    /***
     * 天气描述
     */
    private String temperatureStr;
    /***
     * 是否被选中 1 选中
     */
    private int selectedFlag;
    /***
     * 天气code
     */
    private int temperatureCode;

    private boolean isAdd = false;

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }
}

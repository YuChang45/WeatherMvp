package android.yuchang.weathermvp.model.db;

/**
 * @author MrChang45
 * @time 2016/9/19
 * @desc
 */
public class ChosenCityTable {

    public static final String TABLE_NAME = "ChosenCity";
    /***
     * 城市名称
     */
    public static final String cityName = "cityName";
    /**
     * 最高温度
     */
    public static final String maxTemperature = "maxTemperature";
    /**
     * 最低温度
     */
    public static final String minTemperature = "minTemperature";
    /***
     * 温度描述
     */
    public static final String temperatureStr = "temperatureStr";
    /***
     * 天气编码
     */
    public static final String temperatureCode = "temperatureCode";
    /***
     * 选中标志
     */
    public static final String selectedFlag = "selectedFlag";
}

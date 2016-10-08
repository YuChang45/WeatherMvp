package android.yuchang.weathermvp.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author MrChang
 *         created  at  2016/1/12.
 * @description
 */
public class ChosenCityDataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "yc.db";
    private static final int DATABASE_VERSION = 1;

    public ChosenCityDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + ChosenCityTable.TABLE_NAME
                + " (_id INTEGER PRIMARY KEY AUTOINCREMENT," + ChosenCityTable.cityName + " VARCHAR(20)," + ChosenCityTable.maxTemperature + " VARCHAR(20)," + ChosenCityTable.minTemperature + " VARCHAR(20) ," + ChosenCityTable.temperatureStr + " VARCHAR(20)," + ChosenCityTable.temperatureCode + " INTEGER," + ChosenCityTable.selectedFlag + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package android.yuchang.weathermvp.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.yuchang.weathermvp.commom.Const;
import android.yuchang.weathermvp.model.entity.ChosenCityBean;

import java.util.ArrayList;
import java.util.List;


/**
 * @author MrChang
 *         created  at  2016/1/12.
 * @description
 */
public class ChosenCityHelper {

    private ChosenCityDataBase chosenCityDataBase;
    private SQLiteDatabase db;

    public ChosenCityHelper(Context context) {
        chosenCityDataBase = new ChosenCityDataBase(context);
        db = chosenCityDataBase.getWritableDatabase();
    }


    /***
     * 数据库是否存在城市
     *
     * @return
     */
    public boolean hasSelectedCity() {
        boolean result = false;
        if (db != null) {
            String sql = "select * from   " + ChosenCityTable.TABLE_NAME;
            Cursor mCursor = db.rawQuery(sql, null);
            if (mCursor != null && mCursor.moveToNext()) {
                result = true;
            }
        }
        return result;
    }

    /***
     * 数据库是否只存在一个城市了
     *
     * @return
     */
    public boolean hasOnlyOneSelectedCity() {
        boolean result = false;
        if (db != null) {
            String sql = "select * from   " + ChosenCityTable.TABLE_NAME;
            Cursor mCursor = db.rawQuery(sql, null);
            if (mCursor != null && mCursor.moveToNext()) {
                if (!mCursor.moveToNext()) {
                    result = true;
                }
            }
        }
        return result;
    }

    /***
     * 数据库是否只存在一个城市了
     *
     * @return
     */
    public boolean hasRemianByCityName(String cityName) {
        boolean result = false;
        cityName = cityName.replace("市", "");
        if (db != null) {
            String sql = "select * from " + ChosenCityTable.TABLE_NAME + " where  " + ChosenCityTable.cityName + " like '" + cityName + "'";
            Cursor mCursor = db.rawQuery(sql, null);
            if (mCursor != null && mCursor.moveToNext()) {
                result = true;
            }
        }
        return result;
    }

    /***
     * 获取添加天气城市列表
     *
     * @return
     */
    public List<ChosenCityBean> getSelectorBean() {
        List<ChosenCityBean> mResult = null;
        if (db != null) {

            mResult = new ArrayList<ChosenCityBean>();
            String sql = "select * from " + ChosenCityTable.TABLE_NAME + "  order by " + ChosenCityTable.selectedFlag + "  desc ";
            Cursor mCursor = db.rawQuery(sql, null);
            while (mCursor != null && mCursor.moveToNext()) {

                ChosenCityBean showListBean = new ChosenCityBean();
                showListBean.setCityName(mCursor.getString(mCursor
                        .getColumnIndex(ChosenCityTable.cityName)));
                showListBean.setMaxTemperature(mCursor.getString(mCursor
                        .getColumnIndex(ChosenCityTable.maxTemperature)));
                showListBean.setMinTemperature(mCursor.getString(mCursor
                        .getColumnIndex(ChosenCityTable.minTemperature)));
                showListBean.setSelectedFlag(mCursor.getInt(mCursor
                        .getColumnIndex(ChosenCityTable.selectedFlag)));
                showListBean.setTemperatureCode(mCursor.getInt(mCursor
                        .getColumnIndex(ChosenCityTable.temperatureCode)));
                showListBean.setTemperatureStr(mCursor.getString(mCursor
                        .getColumnIndex(ChosenCityTable.temperatureStr)));
                mResult.add(showListBean);

            }
        }
        return mResult.size() > 0 ? mResult : null;
    }

    /***
     * 获取添加天气城市列表
     *
     * @return
     */
    public List<String> getRemainsCityName() {
        List<String> mResult = null;
        if (db != null) {
            mResult = new ArrayList<String>();
            String sql = "select * from " + ChosenCityTable.TABLE_NAME + "  order by " + ChosenCityTable.selectedFlag + "  desc ";
            Cursor mCursor = db.rawQuery(sql, null);
            while (mCursor != null && mCursor.moveToNext()) {
                mResult.add(mCursor.getString(mCursor
                        .getColumnIndex(ChosenCityTable.cityName)));

            }
        }
        return mResult.size() > 0 ? mResult : null;
    }

    public int storeWeatherInfo(ChosenCityBean bean) {
        int result = Const.DELETE_ERRO;
        if (db != null) {
            try {
                if (!IsRemainsByCityName(bean.getCityName())) {
                    db.execSQL(
                            "INSERT INTO " + ChosenCityTable.TABLE_NAME + "(" + ChosenCityTable.cityName + "," + ChosenCityTable.maxTemperature + " , " + ChosenCityTable.minTemperature + " , " + ChosenCityTable.temperatureStr + " ," + ChosenCityTable.temperatureCode + "," + ChosenCityTable.selectedFlag + ") VALUES(?, ?, ?, ?,?,?)",
                            new Object[]{bean.getCityName(), bean.getMaxTemperature(),
                                    bean.getMinTemperature(), bean.getTemperatureStr(), bean.getTemperatureCode(), bean.getSelectedFlag()});
                }
                result = 1;
            } catch (SQLException e) {
            }
        }
        return result;
    }

    public boolean IsRemainsByCityName(String cityName) {
        boolean hasRecord = false;
        if (db != null) {
            Cursor c = db.query(ChosenCityTable.TABLE_NAME, null, " " + ChosenCityTable.cityName + "=?",
                    new String[]{cityName
                    }, null, null, null);
            if (c.moveToNext()) {
                hasRecord = true;
            }
        }
        return hasRecord;
    }

    public void UpdateInfo(String selectedCityName) {
        if (db != null) {

            ContentValues values = new ContentValues();
            values.put(ChosenCityTable.selectedFlag, 0);
            db.update(ChosenCityTable.TABLE_NAME, values, null, null);


            values.put(ChosenCityTable.selectedFlag, 1);
            String[] args = {selectedCityName};
            db.update(ChosenCityTable.TABLE_NAME, values, ChosenCityTable.cityName + "=?", args);
        }
    }

    /***
     * 删除主表以及从表
     *
     * @param cityName -1代表删除失败
     */
    public int DeletedByCityName(String cityName) {
        int result = Const.DELETE_ERRO;
        if (db != null) {

            result = db.delete(ChosenCityTable.TABLE_NAME, ChosenCityTable.cityName +  "  =?", new String[]{cityName
                    + ""});
        }
        return result;
    }


    /***
     * 销毁
     */
    public void destorySelf() {
        if (db != null) {
            db.close();
            chosenCityDataBase.close();
            db = null;
        }
    }

}

package android.yuchang.weathermvp.model.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.yuchang.weathermvp.R;
import android.yuchang.weathermvp.commom.utils.StrUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 操作 省市数据库
 */
public class CityHelper {

    private SQLiteDatabase db;
    private Context context;
    private CityDataBase dbm;

    public CityHelper(Context context) {
        super();
        this.context = context;
        dbm = new CityDataBase(context);
    }

    public List<String> getData(String inParam) {
        if (StrUtil.isEmpty(inParam)) {
            return getProvinces();
        } else {
            //判断是否是直辖市
            if (zxs.contains(inParam)) {
                return getCountryNames(inParam);
            } else {
                //判断是否是省份
                if (getProvinces().contains(inParam)) {
                    return getCityNames(inParam);
                } else {
                    return getCountryNames(inParam);
                }
            }
        }
    }

    public List<String> getProvinces() {

        db = dbm.getDatabase();
        List<String> data = new ArrayList<>();
        try {

            String sql = "select distinct province_name from city ";
            Cursor cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                data.add(cursor.getString(cursor
                        .getColumnIndex("province_name")));
            }

        } catch (Exception e) {
            return null;
        }
        dbm.closeDatabase();
        db.close();

        return data;
    }

    private List<String> zxs;

    /***
     * 根据省份名称 获取该省下的城市
     *
     * @param code
     * @return
     */
    public List<String> getCityNames(String code) {
        db = dbm.getDatabase();
        zxs = Arrays.asList(context.getResources().getStringArray(R.array.province_level_city_names));
        List<String> data = new ArrayList<>();
        try {
            if (zxs.contains(code)) {
                data = getCountryNames(code);
            } else {
                String sql = "select distinct city_name from city where province_name = '"
                        + code.trim() + "'";
                Cursor cursor = db.rawQuery(sql, null);

                while (cursor.moveToNext()) {
                    data.add(cursor.getString(cursor
                            .getColumnIndex("city_name")));
                }
            }

        } catch (Exception e) {
            return null;
        }
        dbm.closeDatabase();
        db.close();

        return data;
    }

    /***
     * 根据城市名称 获取该市下的区
     *
     * @param code
     * @return
     */
    public List<String> getCountryNames(String code) {
        db = dbm.getDatabase();
        List<String> data = new ArrayList<>();
        try {

            String sql = "select distinct country_name from city a where city_name ='"
                    + code.trim() + "'";
            Cursor cursor = db.rawQuery(sql, null);

            while (cursor.moveToNext()) {
                data.add(cursor.getString(cursor
                        .getColumnIndex("country_name")));
            }

        } catch (Exception e) {
            return null;
        }
        dbm.closeDatabase();
        db.close();

        return data;
    }
}

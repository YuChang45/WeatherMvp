package android.yuchang.weathermvp.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.yuchang.weathermvp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CityDataBase {

    private final int BUFFER_SIZE = 1024;
    private static final String DB_NAME = "yc_city.db";
    private SQLiteDatabase database;
    private Context context;
    private File file = null;
    public String DB_PATH = "";


    public CityDataBase(Context context) {
        this.context = context;
    }

    public SQLiteDatabase getDatabase() {
        return openDatabase();
    }

    private SQLiteDatabase openDatabase() {

        try {
            DB_PATH = context.getFilesDir().getPath() + File.separator + DB_NAME;
            file = new File(DB_PATH);
            if (!file.exists()) {
                InputStream is = context.getResources().openRawResource(R.raw.yc_city);
                if (is != null) {
                } else {
                }
                FileOutputStream fos = new FileOutputStream(DB_PATH);
                if (is != null) {
                } else {
                }
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                    fos.flush();
                }
                fos.close();
                is.close();
            }
            database = SQLiteDatabase.openOrCreateDatabase(DB_PATH, null);
            return database;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public void closeDatabase() {

        if (this.database != null)
            this.database.close();
    }

}
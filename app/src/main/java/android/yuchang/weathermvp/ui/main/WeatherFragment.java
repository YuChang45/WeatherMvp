package android.yuchang.weathermvp.ui.main;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.yuchang.weathermvp.R;
import android.yuchang.weathermvp.commom.Const;
import android.yuchang.weathermvp.commom.url.ApiUrl;
import android.yuchang.weathermvp.commom.utils.GsonHelper;
import android.yuchang.weathermvp.commom.utils.StrUtil;
import android.yuchang.weathermvp.commom.utils.WeatherIcoHelper;
import android.yuchang.weathermvp.model.entity.WeatherBean;
import android.yuchang.weathermvp.model.entity.WeatherQulityBean;
import android.yuchang.weathermvp.presenter.impl.WeatherPresenter;
import android.yuchang.weathermvp.ui.base.BaseFragment;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class WeatherFragment extends BaseFragment<WeatherPresenter> implements WeatherFragmentView{
    private static final String CITY_NAME = "cityName";

    private String cityName;
    private LinearLayout llAqiBg;
    private ImageView ivAqiIco;
    private TextView tvAqiNum;
    private TextView tvAqiTxt;
    private TextView tvFbTime;
    private TextView tvWeatherPlace;
    private TextView tvWeatherTxt;
    private TextView tvWeatherNum;
    private TextView tvTodayTxt;
    private TextView tvTodayMaxTemp;
    private TextView tvTodayMinTemp;
    private TextView tvTodayWeatherTxt;
    private ImageView ivTodayWeatherIco;
    private TextView tvTomorrowTxt;
    private TextView tvTomorrowMaxTemp;
    private TextView tvTomorrowMinTemp;
    private TextView tvTomorrowWeatherTxt;
    private ImageView ivtomorrowWeatherIco;
    private TextView tvThedayaftertomorrowTxt;
    private TextView tvThedayaftertomorrowMaxTemp;
    private TextView tvThedayaftertomorrowMinTemp;
    private TextView tvThedayaftertomorrowWeatherTxt;
    private ImageView ivThedayaftertomorrowWeatherIco;
    private LineChartView chartMaxTemp;
    private LineChartView chartMinTemp;
    private TextView tv01;
    private TextView tv01Date;
    private TextView tv01Wind;
    private TextView tv01WindPowerly;
    private ImageView iv01Max;
    private ImageView iv01Min;
    private TextView tv02;
    private TextView tv02Date;
    private TextView tv02Wind;
    private TextView tv02WindPowerly;
    private ImageView iv02Max;
    private ImageView iv02Min;
    private TextView tv03;
    private TextView tv03Date;
    private TextView tv03Wind;
    private TextView tv03WindPowerly;
    private ImageView iv03Max;
    private ImageView iv03Min;
    private TextView tv04;
    private TextView tv04Date;
    private TextView tv04Wind;
    private TextView tv04WindPowerly;
    private ImageView iv04Max;
    private ImageView iv04Min;
    private TextView tv05;
    private TextView tv05Date;
    private TextView tv05Wind;
    private TextView tv05WindPowerly;
    private ImageView iv05Max;
    private ImageView iv05Min;
    private TextView tv06;
    private TextView tv06Date;
    private TextView tv06Wind;
    private TextView tv06WindPowerly;
    private ImageView iv06Max;
    private ImageView iv06Min;
    private TextView tvSunrise;
    private TextView tvSunset;
    private TextView tvBodyTemperature;
    private TextView tvRelativeHumidity;
    private TextView tvPressure;
    private TextView tvVisibility;
    private TextView tvWindPower;
    private TextView tvDressing;
    private TextView tvComfortableDegree;
    private TextView tvCarWash;
    private TextView tvCold;
    private TextView tvUltravioletRays;
    private TextView tvSports;

    public WeatherFragment() {
        // Required empty public constructor
    }

    public static WeatherFragment newInstance(String cityName) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putString(CITY_NAME, cityName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cityName = getArguments().getString(CITY_NAME);
        }
    }

    @Override
    protected int initContentView(Bundle savedInstanceState) {
        return R.layout.fragment_weather;
    }

    @Override
    public void setUpViews(View view) {

        //aqi
        llAqiBg = (LinearLayout) view.findViewById(R.id.ll_01);
        ivAqiIco = (ImageView) view.findViewById(R.id.iv_01);
        tvAqiNum = (TextView) view.findViewById(R.id.tv_01);
        tvAqiTxt = (TextView) view.findViewById(R.id.tv_02);

        //time
        tvFbTime = (TextView) view.findViewById(R.id.tv_03);

        tvWeatherPlace = (TextView) view.findViewById(R.id.tv_04);
        tvWeatherTxt = (TextView) view.findViewById(R.id.tv_05);
        tvWeatherNum =  (TextView) view.findViewById(R.id.tv_06);

        tvTodayTxt = (TextView) view.findViewById(R.id.tv_07);
        tvTodayMaxTemp = (TextView) view.findViewById(R.id.tv_10);
        tvTodayMinTemp = (TextView) view.findViewById(R.id.tv_11);
        tvTodayWeatherTxt = (TextView) view.findViewById(R.id.tv_16);
        ivTodayWeatherIco = (ImageView) view.findViewById(R.id.iv_03);

        tvTomorrowTxt = (TextView) view.findViewById(R.id.tv_08);
        tvTomorrowMaxTemp = (TextView) view.findViewById(R.id.tv_12);
        tvTomorrowMinTemp = (TextView) view.findViewById(R.id.tv_13);
        tvTomorrowWeatherTxt = (TextView) view.findViewById(R.id.tv_17);
        ivtomorrowWeatherIco = (ImageView) view.findViewById(R.id.iv_04);

        tvThedayaftertomorrowTxt = (TextView) view.findViewById(R.id.tv_09);
        tvThedayaftertomorrowMaxTemp = (TextView) view.findViewById(R.id.tv_14);
        tvThedayaftertomorrowMinTemp = (TextView) view.findViewById(R.id.tv_15);
        tvThedayaftertomorrowWeatherTxt = (TextView) view.findViewById(R.id.tv_18);
        ivThedayaftertomorrowWeatherIco = (ImageView) view.findViewById(R.id.iv_05);


        chartMaxTemp = (LineChartView) view.findViewById(R.id.chart1);
        chartMinTemp = (LineChartView) view.findViewById(R.id.chart2);

        tv01 = (TextView) view.findViewById(R.id.tv_19);
        tv01Date =  (TextView) view.findViewById(R.id.tv_20);
        tv01Wind = (TextView) view.findViewById(R.id.tv_31);
        tv01WindPowerly = (TextView) view.findViewById(R.id.tv_32);
        iv01Max = (ImageView) view.findViewById(R.id.iv_06);
        iv01Min = (ImageView) view.findViewById(R.id.iv_07);

        tv02 = (TextView) view.findViewById(R.id.tv_21);
        tv02Date = (TextView) view.findViewById(R.id.tv_22);
        tv02Wind = (TextView) view.findViewById(R.id.tv_33);
        tv02WindPowerly = (TextView) view.findViewById(R.id.tv_34);
        iv02Max = (ImageView) view.findViewById(R.id.iv_08);
        iv02Min = (ImageView) view.findViewById(R.id.iv_09);


        tv03 = (TextView) view.findViewById(R.id.tv_23);
        tv03Date = (TextView) view.findViewById(R.id.tv_24);
        tv03Wind = (TextView) view.findViewById(R.id.tv_35);
        tv03WindPowerly = (TextView) view.findViewById(R.id.tv_36);
        iv03Max = (ImageView) view.findViewById(R.id.iv_10);
        iv03Min = (ImageView) view.findViewById(R.id.iv_11);

        tv04 = (TextView) view.findViewById(R.id.tv_25);
        tv04Date = (TextView) view.findViewById(R.id.tv_26);
        tv04Wind = (TextView) view.findViewById(R.id.tv_37);
        tv04WindPowerly = (TextView) view.findViewById(R.id.tv_38);
        iv04Max = (ImageView) view.findViewById(R.id.iv_12);
        iv04Min = (ImageView) view.findViewById(R.id.iv_13);

        tv05 = (TextView) view.findViewById(R.id.tv_27);
        tv05Date = (TextView) view.findViewById(R.id.tv_28);
        tv05Wind = (TextView) view.findViewById(R.id.tv_39);
        tv05WindPowerly = (TextView) view.findViewById(R.id.tv_40);
        iv05Max = (ImageView) view.findViewById(R.id.iv_14);
        iv05Min = (ImageView) view.findViewById(R.id.iv_15);

        tv06 = (TextView) view.findViewById(R.id.tv_29);
        tv06Date = (TextView) view.findViewById(R.id.tv_30);
        tv06Wind = (TextView) view.findViewById(R.id.tv_41);
        tv06WindPowerly = (TextView) view.findViewById(R.id.tv_42);
        iv06Max = (ImageView) view.findViewById(R.id.iv_16);
        iv06Min = (ImageView) view.findViewById(R.id.iv_17);


        tvSunrise = (TextView) view.findViewById(R.id.tv_43);
        tvSunset = (TextView) view.findViewById(R.id.tv_44);

        //体感温度
        tvBodyTemperature = (TextView) view.findViewById(R.id.tv_45);
        //相对湿度
        tvRelativeHumidity = (TextView) view.findViewById(R.id.tv_46);

        //气压
        tvPressure = (TextView) view.findViewById(R.id.tv_47);
        //能见度
        tvVisibility = (TextView) view.findViewById(R.id.tv_48);
        //风力
        tvWindPower = (TextView) view.findViewById(R.id.tv_49);

        //穿衣
        tvDressing = (TextView) view.findViewById(R.id.tv_50);
        //舒适度
        tvComfortableDegree = (TextView) view.findViewById(R.id.tv_51);
        //洗车
        tvCarWash = (TextView) view.findViewById(R.id.tv_52);
        //感冒
        tvCold = (TextView) view.findViewById(R.id.tv_53);
        //紫外线
        tvUltravioletRays = (TextView) view.findViewById(R.id.tv_54);
        //运动
        tvSports = (TextView) view.findViewById(R.id.tv_55);

        mPresenter.FetchWeather(cityName);
        mPresenter.FetchWeatherQulity(cityName);

    }

    @Override
    public void setUpLisener() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void FillWeatherInfo(WeatherBean weatherBean) {

        if (null != weatherBean && weatherBean.getStatus().trim().equals("ok")) {

            tvWeatherPlace.setText(weatherBean.getBasic().getCity());
            tvWeatherTxt.setText(weatherBean.getNow().getCond().getTxt());
            tvWeatherNum.setText(weatherBean.getNow().getTmp() + "°");
            //穿衣
            tvDressing.setText(weatherBean.getSuggestion().getDrsg().getBrf());
            //舒适度
            tvComfortableDegree.setText(weatherBean.getSuggestion().getComf().getBrf());
            //洗车
            tvCarWash.setText(weatherBean.getSuggestion().getCw().getBrf());
            //感冒
            tvCold.setText(weatherBean.getSuggestion().getFlu().getBrf());
            //紫外线
            tvUltravioletRays.setText(weatherBean.getSuggestion().getUv().getBrf());
            //运动
            tvSports.setText(weatherBean.getSuggestion().getSport().getBrf());


            tvSunrise.setText(weatherBean.getDaily_forecast().get(0).getAstro().getSr());
            tvSunset.setText(weatherBean.getDaily_forecast().get(0).getAstro().getSs());

            //体感温度
            tvBodyTemperature.setText(weatherBean.getNow().getFl() + Const.TEMPRATURE_SUFFIXS);
            //相对湿度
            tvRelativeHumidity.setText(weatherBean.getNow().getHum() + "%");

            //气压
            tvPressure.setText(weatherBean.getNow().getPres() + " 百帕");
            //能见度
            tvVisibility.setText(weatherBean.getNow().getVis() + " km");
            //风力
            tvWindPower.setText(weatherBean.getNow().getWind().getDir() + "   " + weatherBean.getNow().getWind().getSc() + "级");

            tvTodayTxt.setText("今天");
            tvTodayMaxTemp.setText(weatherBean.getDaily_forecast().get(0).getTmp().getMax() + Const.TEMPRATURE_SUFFIXS);
            tvTodayMinTemp.setText(weatherBean.getDaily_forecast().get(0).getTmp().getMin() + Const.TEMPRATURE_SUFFIXS);
            tvTodayWeatherTxt.setText(weatherBean.getDaily_forecast().get(0).getCond().getTxt_d());
            //iv_today_weather_ico.setText(weatherBean.getDaily_forecast().get(0).getAstro().getSr());
            String imgUrl;
            int weatherIcoResourceId = WeatherIcoHelper.changeWeatherCodeToIcoResourceId(weatherBean.getDaily_forecast().get(0).getCond().getCode_d(), false);
            if (weatherIcoResourceId != -1) {
                ivTodayWeatherIco.setBackgroundResource(weatherIcoResourceId);
            } else {
                imgUrl =ApiUrl.IMAGE_URL_PRE+ weatherBean.getDaily_forecast().get(0).getCond().getCode_d() + Const.IMAGE_HZ;
                Picasso.with(getActivity()).load(imgUrl)
                        .into(ivTodayWeatherIco);
            }
            tvTomorrowTxt.setText("明天");
            tvTomorrowMaxTemp.setText(weatherBean.getDaily_forecast().get(1).getTmp().getMax() + Const.TEMPRATURE_SUFFIXS);
            tvTomorrowMinTemp.setText(weatherBean.getDaily_forecast().get(1).getTmp().getMin() + Const.TEMPRATURE_SUFFIXS);
            tvTomorrowWeatherTxt.setText(weatherBean.getDaily_forecast().get(1).getCond().getTxt_d());
            //  ivtomorrow_weather_ico .setText(weatherBean.getDaily_forecast().get(0).getAstro().getSr());

            weatherIcoResourceId = WeatherIcoHelper.changeWeatherCodeToIcoResourceId(weatherBean.getDaily_forecast().get(1).getCond().getCode_d(), false);
            if (weatherIcoResourceId != -1) {
                ivtomorrowWeatherIco.setBackgroundResource(weatherIcoResourceId);
            } else {
                imgUrl = ApiUrl.IMAGE_URL_PRE+ weatherBean.getDaily_forecast().get(1).getCond().getCode_d() + Const.IMAGE_HZ;
                Picasso.with(getActivity()).load(imgUrl)
                        .into(ivtomorrowWeatherIco);
            }


            tvThedayaftertomorrowTxt.setText("后天");
            tvThedayaftertomorrowMaxTemp.setText(weatherBean.getDaily_forecast().get(2).getTmp().getMax() + Const.TEMPRATURE_SUFFIXS);
            tvThedayaftertomorrowMinTemp.setText(weatherBean.getDaily_forecast().get(2).getTmp().getMin() + Const.TEMPRATURE_SUFFIXS);
            tvThedayaftertomorrowWeatherTxt.setText(weatherBean.getDaily_forecast().get(2).getCond().getTxt_d());
            //  iv_thedayaftertomorrow_weather_ico .setText(weatherBean.getDaily_forecast().get(0).getAstro().getSr());
            weatherIcoResourceId = WeatherIcoHelper.changeWeatherCodeToIcoResourceId(weatherBean.getDaily_forecast().get(2).getCond().getCode_d(), false);
            if (weatherIcoResourceId != -1) {
                ivThedayaftertomorrowWeatherIco.setBackgroundResource(weatherIcoResourceId);
            } else {

                imgUrl = ApiUrl.IMAGE_URL_PRE + weatherBean.getDaily_forecast().get(2).getCond().getCode_d() + Const.IMAGE_HZ;
                Picasso.with(getActivity()).load(imgUrl)
                        .into(ivThedayaftertomorrowWeatherIco);
            }

            //高温
            List<PointValue> values = new ArrayList<>();
            for (int i = 1; i < weatherBean.getDaily_forecast().size(); i++) {
                values.add(new PointValue(i, Integer.parseInt(weatherBean.getDaily_forecast().get(i).getTmp().getMax())).setLabel(weatherBean.getDaily_forecast().get(i).getTmp().getMax() + "°"));
            }
            Line line = new Line(values).setColor(getActivity().getResources().getColor(R.color.max_temp_zxt)).setHasLabels(true);
            List<Line> lines = new ArrayList<>();
            lines.add(line);
            LineChartData data = new LineChartData();
            data.setLines(lines);
            chartMaxTemp.setLineChartData(data);

            //低温
            values = new ArrayList<>();
            for (int i = 1; i < weatherBean.getDaily_forecast().size(); i++) {
                values.add(new PointValue(i, Integer.parseInt(weatherBean.getDaily_forecast().get(i).getTmp().getMin())).setLabel(weatherBean.getDaily_forecast().get(i).getTmp().getMin() + "°"));
            }
            line = new Line(values).setColor(getActivity().getResources().getColor(R.color.min_temp_zxt)).setHasLabels(true);
            lines = new ArrayList<>();
            lines.add(line);
            data = new LineChartData();
            data.setLines(lines);
            chartMinTemp.setLineChartData(data);

            //time
            tvFbTime.setText(StrUtil.changStr3(weatherBean.getBasic().getUpdate().getLoc()) + " 更新");

            tv01.setText(StrUtil.changStr2(weatherBean.getDaily_forecast().get(1).getDate()));
            tv01Date.setText(StrUtil.changStr(weatherBean.getDaily_forecast().get(1).getDate()));
            tv01Wind.setText(weatherBean.getDaily_forecast().get(1).getCond().getTxt_d());
            tv01WindPowerly.setText(weatherBean.getDaily_forecast().get(1).getCond().getTxt_n());

            weatherIcoResourceId = WeatherIcoHelper.changeWeatherCodeToIcoResourceId(weatherBean.getDaily_forecast().get(1).getCond().getCode_d(), false);
            if (weatherIcoResourceId != -1) {
                iv01Max.setBackgroundResource(weatherIcoResourceId);
            } else {
                imgUrl = ApiUrl.IMAGE_URL_PRE + weatherBean.getDaily_forecast().get(1).getCond().getCode_d() + Const.IMAGE_HZ;
                Picasso.with(getActivity()).load(imgUrl)
                        .into(iv01Max);
            }

            weatherIcoResourceId = WeatherIcoHelper.changeWeatherCodeToIcoResourceId(weatherBean.getDaily_forecast().get(1).getCond().getCode_n(), true);
            if (weatherIcoResourceId != -1) {
                iv01Min.setBackgroundResource(weatherIcoResourceId);
            } else {
                imgUrl = ApiUrl.IMAGE_URL_PRE + weatherBean.getDaily_forecast().get(1).getCond().getCode_n() + Const.IMAGE_HZ;
                Picasso.with(getActivity()).load(imgUrl)
                        .into(iv01Min);
            }




            tv02.setText(StrUtil.changStr2(weatherBean.getDaily_forecast().get(2).getDate()));
            tv02Date.setText(StrUtil.changStr(weatherBean.getDaily_forecast().get(2).getDate()));
            tv02Wind.setText(weatherBean.getDaily_forecast().get(2).getCond().getTxt_d());
            tv02WindPowerly.setText(weatherBean.getDaily_forecast().get(2).getCond().getTxt_n());


            weatherIcoResourceId = WeatherIcoHelper.changeWeatherCodeToIcoResourceId(weatherBean.getDaily_forecast().get(2).getCond().getCode_d(), false);
            if (weatherIcoResourceId != -1) {
                iv02Max.setBackgroundResource(weatherIcoResourceId);
            } else {
                imgUrl = ApiUrl.IMAGE_URL_PRE + weatherBean.getDaily_forecast().get(2).getCond().getCode_d() + Const.IMAGE_HZ;
                Picasso.with(getActivity()).load(imgUrl)
                        .into(iv02Max);
            }

            weatherIcoResourceId = WeatherIcoHelper.changeWeatherCodeToIcoResourceId(weatherBean.getDaily_forecast().get(2).getCond().getCode_n(), true);
            if (weatherIcoResourceId != -1) {
                iv02Min.setBackgroundResource(weatherIcoResourceId);
            } else {
                imgUrl = ApiUrl.IMAGE_URL_PRE + weatherBean.getDaily_forecast().get(2).getCond().getCode_n() + Const.IMAGE_HZ;
                Picasso.with(getActivity()).load(imgUrl)
                        .into(iv02Min);
            }

            tv03.setText(StrUtil.changStr2(weatherBean.getDaily_forecast().get(3).getDate()));
            tv03Date.setText(StrUtil.changStr(weatherBean.getDaily_forecast().get(3).getDate()));
            tv03Wind.setText(weatherBean.getDaily_forecast().get(3).getCond().getTxt_d());
            tv03WindPowerly.setText(weatherBean.getDaily_forecast().get(3).getCond().getTxt_n());

            weatherIcoResourceId = WeatherIcoHelper.changeWeatherCodeToIcoResourceId(weatherBean.getDaily_forecast().get(3).getCond().getCode_d(), false);
            if (weatherIcoResourceId != -1) {
                iv03Max.setBackgroundResource(weatherIcoResourceId);
            } else {
                imgUrl = ApiUrl.IMAGE_URL_PRE + weatherBean.getDaily_forecast().get(3).getCond().getCode_d() + Const.IMAGE_HZ;
                Picasso.with(getActivity()).load(imgUrl)
                        .into(iv03Max);
            }

            weatherIcoResourceId = WeatherIcoHelper.changeWeatherCodeToIcoResourceId(weatherBean.getDaily_forecast().get(3).getCond().getCode_n(), true);
            if (weatherIcoResourceId != -1) {
                iv03Min.setBackgroundResource(weatherIcoResourceId);
            } else {
                imgUrl = ApiUrl.IMAGE_URL_PRE + weatherBean.getDaily_forecast().get(3).getCond().getCode_n() + Const.IMAGE_HZ;
                Picasso.with(getActivity()).load(imgUrl)
                        .into(iv03Min);
            }


            tv04.setText(StrUtil.changStr2(weatherBean.getDaily_forecast().get(4).getDate()));
            tv04Date.setText(StrUtil.changStr(weatherBean.getDaily_forecast().get(4).getDate()));
            tv04Wind.setText(weatherBean.getDaily_forecast().get(4).getCond().getTxt_d());
            tv04WindPowerly.setText(weatherBean.getDaily_forecast().get(4).getCond().getTxt_n());
            weatherIcoResourceId = WeatherIcoHelper.changeWeatherCodeToIcoResourceId(weatherBean.getDaily_forecast().get(4).getCond().getCode_d(), false);
            if (weatherIcoResourceId != -1) {
                iv04Max.setBackgroundResource(weatherIcoResourceId);
            } else {
                imgUrl = ApiUrl.IMAGE_URL_PRE + weatherBean.getDaily_forecast().get(4).getCond().getCode_d() + Const.IMAGE_HZ;
                Picasso.with(getActivity()).load(imgUrl)
                        .into(iv04Max);
            }

            weatherIcoResourceId = WeatherIcoHelper.changeWeatherCodeToIcoResourceId(weatherBean.getDaily_forecast().get(4).getCond().getCode_n(), true);
            if (weatherIcoResourceId != -1) {
                iv04Min.setBackgroundResource(weatherIcoResourceId);
            } else {
                imgUrl = ApiUrl.IMAGE_URL_PRE + weatherBean.getDaily_forecast().get(4).getCond().getCode_n() + Const.IMAGE_HZ;
                Picasso.with(getActivity()).load(imgUrl)
                        .into(iv04Min);

            }




            tv05.setText(StrUtil.changStr2(weatherBean.getDaily_forecast().get(5).getDate()));
            tv05Date.setText(StrUtil.changStr(weatherBean.getDaily_forecast().get(5).getDate()));
            tv05Wind.setText(weatherBean.getDaily_forecast().get(5).getCond().getTxt_d());
            tv05WindPowerly.setText(weatherBean.getDaily_forecast().get(5).getCond().getTxt_n());

            weatherIcoResourceId = WeatherIcoHelper.changeWeatherCodeToIcoResourceId(weatherBean.getDaily_forecast().get(5).getCond().getCode_d(), false);
            if (weatherIcoResourceId != -1) {
                iv05Max.setBackgroundResource(weatherIcoResourceId);
            } else {
                imgUrl = ApiUrl.IMAGE_URL_PRE + weatherBean.getDaily_forecast().get(5).getCond().getCode_d() + Const.IMAGE_HZ;
                Picasso.with(getActivity()).load(imgUrl)
                        .into(iv05Max);
            }

            weatherIcoResourceId = WeatherIcoHelper.changeWeatherCodeToIcoResourceId(weatherBean.getDaily_forecast().get(5).getCond().getCode_n(), true);
            if (weatherIcoResourceId != -1) {
                iv05Min.setBackgroundResource(weatherIcoResourceId);
            } else {
                imgUrl = ApiUrl.IMAGE_URL_PRE + weatherBean.getDaily_forecast().get(5).getCond().getCode_n() + Const.IMAGE_HZ;
                Picasso.with(getActivity()).load(imgUrl)
                        .into(iv05Min);

            }

            tv06.setText(StrUtil.changStr2(weatherBean.getDaily_forecast().get(6).getDate()));
            tv06Date.setText(StrUtil.changStr(weatherBean.getDaily_forecast().get(6).getDate()));

            tv06Wind.setText(weatherBean.getDaily_forecast().get(6).getCond().getTxt_d());
            tv06WindPowerly.setText(weatherBean.getDaily_forecast().get(6).getCond().getTxt_n());

            weatherIcoResourceId = WeatherIcoHelper.changeWeatherCodeToIcoResourceId(weatherBean.getDaily_forecast().get(6).getCond().getCode_d(), false);
            if (weatherIcoResourceId != -1) {
                iv06Max.setBackgroundResource(weatherIcoResourceId);
            } else {
                imgUrl = ApiUrl.IMAGE_URL_PRE + weatherBean.getDaily_forecast().get(6).getCond().getCode_d() + Const.IMAGE_HZ;
                Picasso.with(getActivity()).load(imgUrl)
                        .into(iv06Max);
            }

            weatherIcoResourceId = WeatherIcoHelper.changeWeatherCodeToIcoResourceId(weatherBean.getDaily_forecast().get(6).getCond().getCode_n(), true);
            if (weatherIcoResourceId != -1) {
                iv06Min.setBackgroundResource(weatherIcoResourceId);
            } else {
                imgUrl = ApiUrl.IMAGE_URL_PRE + weatherBean.getDaily_forecast().get(6).getCond().getCode_n() + Const.IMAGE_HZ;
                Picasso.with(getActivity()).load(imgUrl)
                        .into(iv06Min);

            }


        } else {
            Toast.makeText(getActivity(), "获取天气信息失败！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void FillWeatherQulity(WeatherQulityBean weatherQulityBean) {
        if (null != weatherQulityBean) {
            llAqiBg.setVisibility(View.VISIBLE);
            int aqi = weatherQulityBean.getRetData().getAqi();
            if (aqi > 0 && aqi <= 50) {
                //优
                llAqiBg.setBackgroundResource(R.drawable.aqi_grren_bg);
                ivAqiIco.setBackgroundResource(R.mipmap.aqi_icon_optimal);
            }
            if (aqi > 50 && aqi <= 100) {
                //良
                llAqiBg.setBackgroundResource(R.drawable.aqi_yellow_bg);
                ivAqiIco.setBackgroundResource(R.mipmap.aqi_icon_optimal);
            }
            if (aqi > 101 && aqi <= 150) {
                //轻度污染
                llAqiBg.setBackgroundResource(R.drawable.aqi_orange_bg);
                ivAqiIco.setBackgroundResource(R.mipmap.aqi_icon_moderate);
            }
            if (aqi > 151 && aqi <= 200) {
                //中度污染
                llAqiBg.setBackgroundResource(R.drawable.aqi_red_bg);
                ivAqiIco.setBackgroundResource(R.mipmap.aqi_icon_moderate);
            }
            if (aqi > 201 && aqi <= 300) {
                //重度污染
                llAqiBg.setBackgroundResource(R.drawable.aqi_purple_bg);
                ivAqiIco.setBackgroundResource(R.mipmap.aqi_icon_bader);
            }
            if (aqi > 300) {
                //严重污染
                llAqiBg.setBackgroundResource(R.drawable.aqi_maroon_bg);
                ivAqiIco.setBackgroundResource(R.mipmap.aqi_icon_bader);
            }
            tvAqiNum.setText(weatherQulityBean.getRetData().getAqi() + "");
            tvAqiTxt.setText(weatherQulityBean.getRetData().getLevel());
        }
    }
}

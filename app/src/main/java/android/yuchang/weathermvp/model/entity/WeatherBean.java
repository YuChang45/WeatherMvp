package android.yuchang.weathermvp.model.entity;

import android.yuchang.weathermvp.model.base.BaseModel;

import java.util.List;

/**
 * @author MrChang
 *         created  at  2016/1/8.
 * @description http://apis.baidu.com/heweather/weather/free 返回的天气数据
 * http://www.heweather.com/documents/api
 * http://files.heweather.com/cond_icon/100.png
 */
public class WeatherBean implements BaseModel {

    /**
     * city : {"aqi":"32","co":"0","no2":"16","o3":"58","pm10":"31","pm25":"10","qlty":"优","so2":"4"}
     */
    private AqiEntity aqi;

    /**
     * city : 北京
     * cnty : 中国
     * id : CN101010100
     * lat : 39.904000
     * lon : 116.391000
     * update : {"loc":"2016-01-08 15:21","utc":"2016-01-08 07:21"}
     */
    private BasicEntity basic;

    /**
     * cond : {"code":"100","txt":"晴"}
     * fl : -1
     * hum : 20
     * pcpn : 0
     * pres : 1027
     * tmp : 2
     * vis : 10
     * wind : {"deg":"10","dir":"东北风","sc":"4-5","spd":"17"}
     */

    private NowEntity now;
    /**
     * aqi : {"city":{"aqi":"32","co":"0","no2":"16","o3":"58","pm10":"31","pm25":"10","qlty":"优","so2":"4"}}
     * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.904000","lon":"116.391000","update":{"loc":"2016-01-08 15:21","utc":"2016-01-08 07:21"}}
     * daily_forecast : [{"astro":{"sr":"07:35","ss":"17:05"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2016-01-08","hum":"18","pcpn":"0.0","pop":"0","pres":"1030","tmp":{"max":"2","min":"-8"},"vis":"10","wind":{"deg":"356","dir":"北风","sc":"3-4","spd":"10"}},{"astro":{"sr":"07:35","ss":"17:06"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2016-01-09","hum":"17","pcpn":"0.0","pop":"0","pres":"1026","tmp":{"max":"3","min":"-6"},"vis":"10","wind":{"deg":"152","dir":"无持续风向","sc":"微风","spd":"1"}},{"astro":{"sr":"07:35","ss":"17:07"},"cond":{"code_d":"101","code_n":"100","txt_d":"多云","txt_n":"晴"},"date":"2016-01-10","hum":"19","pcpn":"0.0","pop":"0","pres":"1034","tmp":{"max":"1","min":"-8"},"vis":"10","wind":{"deg":"5","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"sr":"07:35","ss":"17:08"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2016-01-11","hum":"18","pcpn":"0.0","pop":"0","pres":"1038","tmp":{"max":"-1","min":"-8"},"vis":"10","wind":{"deg":"49","dir":"北风","sc":"3-4","spd":"15"}},{"astro":{"sr":"07:35","ss":"17:09"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2016-01-12","hum":"13","pcpn":"0.0","pop":"0","pres":"1031","tmp":{"max":"0","min":"-7"},"vis":"10","wind":{"deg":"318","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"sr":"07:34","ss":"17:10"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-01-13","hum":"13","pcpn":"0.0","pop":"0","pres":"1026","tmp":{"max":"2","min":"-8"},"vis":"10","wind":{"deg":"286","dir":"无持续风向","sc":"微风","spd":"3"}},{"astro":{"sr":"07:34","ss":"17:11"},"cond":{"code_d":"101","code_n":"100","txt_d":"多云","txt_n":"晴"},"date":"2016-01-14","hum":"22","pcpn":"0.0","pop":"1","pres":"1025","tmp":{"max":"3","min":"-8"},"vis":"10","wind":{"deg":"316","dir":"无持续风向","sc":"微风","spd":"1"}}]
     * hourly_forecast : [{"date":"2016-01-08 16:00","hum":"24","pop":"0","pres":"1030","tmp":"3","wind":{"deg":"205","dir":"西南风","sc":"微风","spd":"5"}},{"date":"2016-01-08 19:00","hum":"30","pop":"0","pres":"1030","tmp":"1","wind":{"deg":"184","dir":"南风","sc":"微风","spd":"4"}},{"date":"2016-01-08 22:00","hum":"33","pop":"0","pres":"1030","tmp":"-1","wind":{"deg":"269","dir":"西风","sc":"微风","spd":"3"}}]
     * now : {"cond":{"code":"100","txt":"晴"},"fl":"-1","hum":"20","pcpn":"0","pres":"1027","tmp":"2","vis":"10","wind":{"deg":"10","dir":"东北风","sc":"4-5","spd":"17"}}
     * status : ok
     * suggestion : {"comf":{"brf":"较不舒适","txt":"白天天气晴好，但仍会使您感觉偏冷，不很舒适，请注意适时添加衣物，以防感冒。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"冷","txt":"天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"},"flu":{"brf":"较易发","txt":"昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"},"sport":{"brf":"较不宜","txt":"天气较好，但考虑天气寒冷，风力较强，推荐您进行室内运动，若户外运动请注意保暖并做好准备活动。"},"trav":{"brf":"一般","txt":"天气较好，温度稍低，而且风稍大，让您感觉有些冷，会对外出有一定影响，外出注意防风保暖。"},"uv":{"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}}
     */

    private String status;
    /**
     * comf : {"brf":"较不舒适","txt":"白天天气晴好，但仍会使您感觉偏冷，不很舒适，请注意适时添加衣物，以防感冒。"}
     * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
     * drsg : {"brf":"冷","txt":"天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"}
     * flu : {"brf":"较易发","txt":"昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"}
     * sport : {"brf":"较不宜","txt":"天气较好，但考虑天气寒冷，风力较强，推荐您进行室内运动，若户外运动请注意保暖并做好准备活动。"}
     * trav : {"brf":"一般","txt":"天气较好，温度稍低，而且风稍大，让您感觉有些冷，会对外出有一定影响，外出注意防风保暖。"}
     * uv : {"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}
     */

    private SuggestionEntity suggestion;
    /**
     * astro : {"sr":"07:35","ss":"17:05"}
     * cond : {"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"}
     * date : 2016-01-08
     * hum : 18
     * pcpn : 0.0
     * pop : 0
     * pres : 1030
     * tmp : {"max":"2","min":"-8"}
     * vis : 10
     * wind : {"deg":"356","dir":"北风","sc":"3-4","spd":"10"}
     */

    private List<DailyForecastEntity> daily_forecast;
    /**
     * date : 2016-01-08 16:00
     * hum : 24
     * pop : 0
     * pres : 1030
     * tmp : 3
     * wind : {"deg":"205","dir":"西南风","sc":"微风","spd":"5"}
     */

    private List<HourlyForecastEntity> hourly_forecast;

    public void setAqi(AqiEntity aqi) {
        this.aqi = aqi;
    }

    public void setBasic(BasicEntity basic) {
        this.basic = basic;
    }

    public void setNow(NowEntity now) {
        this.now = now;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSuggestion(SuggestionEntity suggestion) {
        this.suggestion = suggestion;
    }

    public void setDaily_forecast(List<DailyForecastEntity> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }

    public void setHourly_forecast(List<HourlyForecastEntity> hourly_forecast) {
        this.hourly_forecast = hourly_forecast;
    }

    public AqiEntity getAqi() {
        return aqi;
    }

    public BasicEntity getBasic() {
        return basic;
    }

    public NowEntity getNow() {
        return now;
    }

    public String getStatus() {
        return status;
    }

    public SuggestionEntity getSuggestion() {
        return suggestion;
    }

    public List<DailyForecastEntity> getDaily_forecast() {
        return daily_forecast;
    }

    public List<HourlyForecastEntity> getHourly_forecast() {
        return hourly_forecast;
    }

    public static class AqiEntity {
        /**
         * aqi : 32
         * co : 0
         * no2 : 16
         * o3 : 58
         * pm10 : 31
         * pm25 : 10
         * qlty : 优
         * so2 : 4
         */

        private CityEntity city;

        public void setCity(CityEntity city) {
            this.city = city;
        }

        public CityEntity getCity() {
            return city;
        }

        public static class CityEntity {
            private String aqi;
            private String co;
            private String no2;
            private String o3;
            private String pm10;
            private String pm25;
            private String qlty;
            private String so2;

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

            public void setCo(String co) {
                this.co = co;
            }

            public void setNo2(String no2) {
                this.no2 = no2;
            }

            public void setO3(String o3) {
                this.o3 = o3;
            }

            public void setPm10(String pm10) {
                this.pm10 = pm10;
            }

            public void setPm25(String pm25) {
                this.pm25 = pm25;
            }

            public void setQlty(String qlty) {
                this.qlty = qlty;
            }

            public void setSo2(String so2) {
                this.so2 = so2;
            }

            public String getAqi() {
                return aqi;
            }

            public String getCo() {
                return co;
            }

            public String getNo2() {
                return no2;
            }

            public String getO3() {
                return o3;
            }

            public String getPm10() {
                return pm10;
            }

            public String getPm25() {
                return pm25;
            }

            public String getQlty() {
                return qlty;
            }

            public String getSo2() {
                return so2;
            }
        }
    }

    public static class BasicEntity {
        private String city;
        private String cnty;
        private String id;
        private String lat;
        private String lon;
        /**
         * loc : 2016-01-08 15:21
         * utc : 2016-01-08 07:21
         */

        private UpdateEntity update;

        public void setCity(String city) {
            this.city = city;
        }

        public void setCnty(String cnty) {
            this.cnty = cnty;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public void setUpdate(UpdateEntity update) {
            this.update = update;
        }

        public String getCity() {
            return city;
        }

        public String getCnty() {
            return cnty;
        }

        public String getId() {
            return id;
        }

        public String getLat() {
            return lat;
        }

        public String getLon() {
            return lon;
        }

        public UpdateEntity getUpdate() {
            return update;
        }

        public static class UpdateEntity {
            private String loc;
            private String utc;

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }

            public String getLoc() {
                return loc;
            }

            public String getUtc() {
                return utc;
            }
        }
    }

    public static class NowEntity {
        /**
         * code : 100
         * txt : 晴
         */

        private CondEntity cond;
        private String fl;
        private String hum;
        private String pcpn;
        private String pres;
        private String tmp;
        private String vis;
        /**
         * deg : 10
         * dir : 东北风
         * sc : 4-5
         * spd : 17
         */

        private WindEntity wind;

        public void setCond(CondEntity cond) {
            this.cond = cond;
        }

        public void setFl(String fl) {
            this.fl = fl;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public void setWind(WindEntity wind) {
            this.wind = wind;
        }

        public CondEntity getCond() {
            return cond;
        }

        public String getFl() {
            return fl;
        }

        public String getHum() {
            return hum;
        }

        public String getPcpn() {
            return pcpn;
        }

        public String getPres() {
            return pres;
        }

        public String getTmp() {
            return tmp;
        }

        public String getVis() {
            return vis;
        }

        public WindEntity getWind() {
            return wind;
        }

        public static class CondEntity {
            private String code;
            private String txt;

            public void setCode(String code) {
                this.code = code;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }

            public String getCode() {
                return code;
            }

            public String getTxt() {
                return txt;
            }
        }

        public static class WindEntity {
            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }

            public String getDeg() {
                return deg;
            }

            public String getDir() {
                return dir;
            }

            public String getSc() {
                return sc;
            }

            public String getSpd() {
                return spd;
            }
        }
    }

    public static class SuggestionEntity {
        /**
         * brf : 较不舒适
         * txt : 白天天气晴好，但仍会使您感觉偏冷，不很舒适，请注意适时添加衣物，以防感冒。
         */

        private ComfEntity comf;
        /**
         * brf : 较适宜
         * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
         */

        private CwEntity cw;
        /**
         * brf : 冷
         * txt : 天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。
         */

        private DrsgEntity drsg;
        /**
         * brf : 较易发
         * txt : 昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。
         */

        private FluEntity flu;
        /**
         * brf : 较不宜
         * txt : 天气较好，但考虑天气寒冷，风力较强，推荐您进行室内运动，若户外运动请注意保暖并做好准备活动。
         */

        private SportEntity sport;
        /**
         * brf : 一般
         * txt : 天气较好，温度稍低，而且风稍大，让您感觉有些冷，会对外出有一定影响，外出注意防风保暖。
         */

        private TravEntity trav;
        /**
         * brf : 弱
         * txt : 紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。
         */

        private UvEntity uv;

        public void setComf(ComfEntity comf) {
            this.comf = comf;
        }

        public void setCw(CwEntity cw) {
            this.cw = cw;
        }

        public void setDrsg(DrsgEntity drsg) {
            this.drsg = drsg;
        }

        public void setFlu(FluEntity flu) {
            this.flu = flu;
        }

        public void setSport(SportEntity sport) {
            this.sport = sport;
        }

        public void setTrav(TravEntity trav) {
            this.trav = trav;
        }

        public void setUv(UvEntity uv) {
            this.uv = uv;
        }

        public ComfEntity getComf() {
            return comf;
        }

        public CwEntity getCw() {
            return cw;
        }

        public DrsgEntity getDrsg() {
            return drsg;
        }

        public FluEntity getFlu() {
            return flu;
        }

        public SportEntity getSport() {
            return sport;
        }

        public TravEntity getTrav() {
            return trav;
        }

        public UvEntity getUv() {
            return uv;
        }

        public static class ComfEntity {
            private String brf;
            private String txt;

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }

            public String getBrf() {
                return brf;
            }

            public String getTxt() {
                return txt;
            }
        }

        public static class CwEntity {
            private String brf;
            private String txt;

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }

            public String getBrf() {
                return brf;
            }

            public String getTxt() {
                return txt;
            }
        }

        public static class DrsgEntity {
            private String brf;
            private String txt;

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }

            public String getBrf() {
                return brf;
            }

            public String getTxt() {
                return txt;
            }
        }

        public static class FluEntity {
            private String brf;
            private String txt;

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }

            public String getBrf() {
                return brf;
            }

            public String getTxt() {
                return txt;
            }
        }

        public static class SportEntity {
            private String brf;
            private String txt;

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }

            public String getBrf() {
                return brf;
            }

            public String getTxt() {
                return txt;
            }
        }

        public static class TravEntity {
            private String brf;
            private String txt;

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }

            public String getBrf() {
                return brf;
            }

            public String getTxt() {
                return txt;
            }
        }

        public static class UvEntity {
            private String brf;
            private String txt;

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }

            public String getBrf() {
                return brf;
            }

            public String getTxt() {
                return txt;
            }
        }
    }

    public static class DailyForecastEntity {
        /**
         * sr : 07:35
         * ss : 17:05
         */

        private AstroEntity astro;
        /**
         * code_d : 100
         * code_n : 100
         * txt_d : 晴
         * txt_n : 晴
         */

        private CondEntity cond;
        private String date;
        private String hum;
        private String pcpn;
        private String pop;
        private String pres;
        /**
         * max : 2
         * min : -8
         */

        private TmpEntity tmp;
        private String vis;
        /**
         * deg : 356
         * dir : 北风
         * sc : 3-4
         * spd : 10
         */

        private WindEntity wind;

        public void setAstro(AstroEntity astro) {
            this.astro = astro;
        }

        public void setCond(CondEntity cond) {
            this.cond = cond;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public void setTmp(TmpEntity tmp) {
            this.tmp = tmp;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public void setWind(WindEntity wind) {
            this.wind = wind;
        }

        public AstroEntity getAstro() {
            return astro;
        }

        public CondEntity getCond() {
            return cond;
        }

        public String getDate() {
            return date;
        }

        public String getHum() {
            return hum;
        }

        public String getPcpn() {
            return pcpn;
        }

        public String getPop() {
            return pop;
        }

        public String getPres() {
            return pres;
        }

        public TmpEntity getTmp() {
            return tmp;
        }

        public String getVis() {
            return vis;
        }

        public WindEntity getWind() {
            return wind;
        }

        public static class AstroEntity {
            private String sr;
            private String ss;

            public void setSr(String sr) {
                this.sr = sr;
            }

            public void setSs(String ss) {
                this.ss = ss;
            }

            public String getSr() {
                return sr;
            }

            public String getSs() {
                return ss;
            }
        }

        public static class CondEntity {
            private String code_d;
            private String code_n;
            private String txt_d;
            private String txt_n;

            public void setCode_d(String code_d) {
                this.code_d = code_d;
            }

            public void setCode_n(String code_n) {
                this.code_n = code_n;
            }

            public void setTxt_d(String txt_d) {
                this.txt_d = txt_d;
            }

            public void setTxt_n(String txt_n) {
                this.txt_n = txt_n;
            }

            public String getCode_d() {
                return code_d;
            }

            public String getCode_n() {
                return code_n;
            }

            public String getTxt_d() {
                return txt_d;
            }

            public String getTxt_n() {
                return txt_n;
            }
        }

        public static class TmpEntity {
            private String max;
            private String min;

            public void setMax(String max) {
                this.max = max;
            }

            public void setMin(String min) {
                this.min = min;
            }

            public String getMax() {
                return max;
            }

            public String getMin() {
                return min;
            }
        }

        public static class WindEntity {
            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }

            public String getDeg() {
                return deg;
            }

            public String getDir() {
                return dir;
            }

            public String getSc() {
                return sc;
            }

            public String getSpd() {
                return spd;
            }
        }
    }

    public static class HourlyForecastEntity {
        private String date;
        private String hum;
        private String pop;
        private String pres;
        private String tmp;
        /**
         * deg : 205
         * dir : 西南风
         * sc : 微风
         * spd : 5
         */

        private WindEntity wind;

        public void setDate(String date) {
            this.date = date;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public void setWind(WindEntity wind) {
            this.wind = wind;
        }

        public String getDate() {
            return date;
        }

        public String getHum() {
            return hum;
        }

        public String getPop() {
            return pop;
        }

        public String getPres() {
            return pres;
        }

        public String getTmp() {
            return tmp;
        }

        public WindEntity getWind() {
            return wind;
        }

        public static class WindEntity {
            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }

            public String getDeg() {
                return deg;
            }

            public String getDir() {
                return dir;
            }

            public String getSc() {
                return sc;
            }

            public String getSpd() {
                return spd;
            }
        }
    }
}

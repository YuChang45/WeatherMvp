package android.yuchang.weathermvp.model.entity;

/**
 * @author MrChang45
 * @time 2016/10/9
 * @desc
 */
public class AddMoreCityBean {
    public AddMoreCityBean(String name, Boolean selected) {
        this.name = name;
        this.enable = selected;
    }

    public String name;
    public Boolean enable;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        AddMoreCityBean user = (AddMoreCityBean) obj;
        if (!name.equals(user.name)) {
            return false;
        }
        return true;
    }


}

package com.giants3.hd.entity;

import com.giants3.hd.utils.StringUtils;
import com.giants3.hd.utils.interf.Valuable;

import java.io.Serializable;

/**
 * 包装材质大分类
 *
 */


public class PackMaterialClass   implements Serializable,Valuable {



    public long id;



    public String name;






    public PackMaterialType type;


    //匹配的默认位置。


    public PackMaterialPosition position;





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }



    public static final String  CLASS_BOX="外箱";

    public static final String CLASS_INSIDE_BOX="内盒";


    public static final String CLASS_JIAODAI="胶带";

    public static final String CLASS_ZHANSHIHE="展示盒";

    public static final String CLASS_QIPAODAI="气泡袋";
    public static final String CLASS_CAIHE = "彩盒";
    //public static final String CLASS_BAOLILONG = "保丽隆";
    public static final String CLASS_TESHU_BAOLILONG = "特殊保丽隆";


    public static final String [] PRESERVED_CLASS=new String[]
            {CLASS_BOX,CLASS_INSIDE_BOX,CLASS_JIAODAI,CLASS_ZHANSHIHE,CLASS_QIPAODAI,CLASS_CAIHE,CLASS_TESHU_BAOLILONG};


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PackMaterialClass)) return false;

        PackMaterialClass that = (PackMaterialClass) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return !(position != null ? !position.equals(that.position) : that.position != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }

    @Override
    public boolean isEmpty() {

        return id<=0&& StringUtils.isEmpty(name);

    }
}

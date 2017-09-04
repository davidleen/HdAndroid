package com.giants3.hd.entity;

/**
* 用户列表
*/

import com.giants3.hd.utils.StringUtils;
import com.giants3.hd.utils.interf.Valuable;

import java.io.Serializable;


public class User implements Serializable,Valuable {
    public static final String ADMIN = "admin";
  
    public long id;

     
    public String code;

      
    public String name;


    public String chineseName;

    public String password;




    public boolean isSalesman;



    public String  email;


    public String  tel;

    //职位
    public int position;
    //职位名称
    public String  positionName;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (isSalesman != user.isSalesman) return false;
        if (code != null ? !code.equals(user.code) : user.code != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (chineseName != null ? !chineseName.equals(user.chineseName) : user.chineseName != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return !(tel != null ? !tel.equals(user.tel) : user.tel != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (chineseName != null ? chineseName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (isSalesman ? 1 : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        return result;
    }

    @Override
    public boolean isEmpty() {
        return StringUtils.isEmpty(name) ;
    }

    @Override
    public String toString() {


        return "["+code+"]["+name+"]"+chineseName;
    }
}

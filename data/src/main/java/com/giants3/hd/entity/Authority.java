package com.giants3.hd.entity;

import java.io.Serializable;

/**
 * Created by davidleen29 on 2015/7/8.
 */

public class Authority implements Serializable{


    public long id;



    public User user;



    public Module module;


    /**
     * 查看权限
     */
    public boolean viewable;


    /**
     * 可修改权限
     */
    public boolean editable;
    /**
     * 可添加权限
      */
    public  boolean addable;

    /**
     * 可删除权限
     */
    public  boolean deletable;

    /**
     * 可导入权限
     */
    public boolean importable;


    /**
     * 可审核权限
     */

    public boolean checkable;

    /**
     * 可导出权限
     */
    public boolean  exportable;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;

        Authority authority = (Authority) o;

        if (id != authority.id) return false;
        if (viewable != authority.viewable) return false;
        if (editable != authority.editable) return false;
        if (addable != authority.addable) return false;
        if (deletable != authority.deletable) return false;
        if (importable != authority.importable) return false;
        if (checkable != authority.checkable) return false;
        if (exportable != authority.exportable) return false;
        if (user != null ? !user.equals(authority.user) : authority.user != null) return false;
        return !(module != null ? !module.equals(authority.module) : authority.module != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (module != null ? module.hashCode() : 0);
        result = 31 * result + (viewable ? 1 : 0);
        result = 31 * result + (editable ? 1 : 0);
        result = 31 * result + (addable ? 1 : 0);
        result = 31 * result + (deletable ? 1 : 0);
        result = 31 * result + (importable ? 1 : 0);
        result = 31 * result + (checkable ? 1 : 0);
        result = 31 * result + (exportable ? 1 : 0);
        return result;
    }
}

package com.giants3.hd.utils.noEntity;

/**
 * Created by davidleen29 on 2015/8/8.
 */

import com.giants3.hd.utils.entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 本地缓存数据
 */
public class BufferData implements Serializable{

    public   List<PClass> pClasses;
    public   List<Customer> customers;
    public   List<PackMaterialType> packMaterialTypes;
    public   List<PackMaterialPosition> packMaterialPositions;
    public   List<PackMaterialClass> packMaterialClasses;

    public   List<Pack> packs=new ArrayList<>();
    public   List<MaterialClass> materialClasses;
    public   List<MaterialType> materialTypes;
    //public    List<MaterialEquation> materialEquations=new ArrayList<>();
    public   List<User> salesmans;
    public List<Authority> authorities;
    public QuoteAuth quoteAuth;

    public  User loginUser;

    public  GlobalData globalData;

    /**
     * 产品录入的模板数据
     */
    public List<ProductDetail> demos;

    public List<Factory> factories;
}

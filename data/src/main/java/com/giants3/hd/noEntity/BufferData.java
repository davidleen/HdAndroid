package com.giants3.hd.noEntity;

/**
 * Created by davidleen29 on 2015/8/8.
 */

import com.giants3.hd.entity.Authority;
import com.giants3.hd.entity.Customer;
import com.giants3.hd.entity.Factory;
import com.giants3.hd.entity.GlobalData;
import com.giants3.hd.entity.MaterialClass;
import com.giants3.hd.entity.MaterialType;
import com.giants3.hd.entity.PClass;
import com.giants3.hd.entity.Pack;
import com.giants3.hd.entity.PackMaterialClass;
import com.giants3.hd.entity.PackMaterialPosition;
import com.giants3.hd.entity.PackMaterialType;
import com.giants3.hd.entity.QuoteAuth;
import com.giants3.hd.entity.User;
import com.giants3.hd.entity.WorkFlow;
import com.giants3.hd.entity.WorkFlowArranger;
import com.giants3.hd.entity.WorkFlowWorker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 本地缓存数据
 */

/**
 * 本地缓存数据
 */
public class BufferData implements Serializable {

    public List<PClass> pClasses;
    public List<Customer> customers;
    public List<PackMaterialType> packMaterialTypes;
    public List<PackMaterialPosition> packMaterialPositions;
    public List<PackMaterialClass> packMaterialClasses;

    public List<Pack> packs = new ArrayList<>();
    public List<MaterialClass> materialClasses;
    public List<MaterialType> materialTypes;
    //public    List<MaterialEquation> materialEquations=new ArrayList<>();
    public List<User> salesmans;
    public List<Authority> authorities;
    public QuoteAuth quoteAuth;

    /**
     * 生产全流程
     */
    public List<WorkFlow> workFlows;


    public User loginUser;

    public GlobalData globalData;

    /**
     * 产品录入的模板数据
     */
    public List<ProductDetail> demos;

    public List<Factory> factories;


    /**
     * 流程相关权限（登录用户的）
     */
    public WorkFlowArranger workFlowArranger;

    /**
     * 流程相关权限（登录用户的）
     */
    public List<WorkFlowWorker> workFlowWorkers;
}
package com.giants3.hd.appdata;

import com.giants3.hd.utils.entity.Authority;
import com.giants3.hd.utils.entity.QuoteAuth;

import java.io.Serializable;
import java.util.List;

/**
 * Created by david on 2016/1/2.
 */
public class AUser   implements Serializable {



    public long id;


    public String code;


    public String name;


    public String chineseName;

    public String password;




    public boolean isSalesman;



    public String  email;


    public String  tel;
    public String token;

    public List<Authority> authorities;
    public QuoteAuth quoteAuth;
}

package com.giants3.hd.appdata;

import java.io.Serializable;
import java.util.List;

/**
 * Created by david on 2016/2/16.
 */
public class AQuotationDetail implements Serializable {


    public String qName;


    public long id;
    public String salesman;
    public String vDate;
    public String qDate;
    List<AQuotationItem> itemList;

}

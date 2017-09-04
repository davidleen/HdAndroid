package com.giants3.hd.appdata;


import java.io.Serializable;

/**
 * 产品数据
 */

public class AProduct implements Serializable {

	public long id;

	/**
	 * 备注
	 */

	public String memo="";




	/**
	 * 产品类别id
	 */

	public long pClassId;
	/**
	 * 产品类别名称
	 */

	public String pClassName="";
	/**
	 * 产品单位id
	 */

	public String pUnitId="";
	/**
	 * 产品单位名称
	 */

	public String pUnitName="S/1" ;
	/**
	 * 净重
	 */
	public float weight;



	public String  name;


	public String pVersion;

	public float cost;

	public  float fob;
	public float price;
	public String url;

    public String thumbnail;
}
package com.giants3.hd.data.entity;


import java.io.Serializable;
import java.util.Arrays;

/**
 * 产品数据
 */

public class Product implements Serializable {

	/**
	 * 备注
	 */

	public String memo="";


	/**
	 * 图片，存放缩略图
	 */

	public byte[] photo;

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

}
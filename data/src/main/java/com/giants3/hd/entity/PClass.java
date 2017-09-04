package com.giants3.hd.entity;

import java.io.Serializable;

/**
 * 产品类别比表
 * 
 * 产品类别细分
 */


public class PClass  implements Serializable {


	/**
	 * 类别id
	 */

	public long id			;
	/**
	 * 类别名称
	 */

	public String name;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
}
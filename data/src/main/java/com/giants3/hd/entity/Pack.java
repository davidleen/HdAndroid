package com.giants3.hd.entity;

import java.io.Serializable;

/**
 * 包装，
 * 
 * 主要有三种包装类型
 * 
 * 普通包装  加强包装
 * 
 * 特殊包装
 */

public class Pack  implements Serializable {




	/**
	 * 折叠包装常量  （咸康普通）
	 */
	public  static final int PACK_XIANKANG_ZHEDIE=5;
	/**
	 * 折叠普通摔箱常量 （咸康加强）
	 */
	public static final int PACK_XIANKANG_PUTONG_SHUAIXIANG=6;
	/**
	 * 折叠加强摔箱常量  （咸康加强）
	 */
	public static final int PACK_XIANKANG_JIAQIANG_SHUAIXIANG=7;

	/**
	 * 包装类型id
	 */

	public long id;
	/**
	 * 包装名称
	 */


	public String name;




	/**
	 * 序号id
	 */

	public int pIndex;

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


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Pack)) return false;

		Pack pack = (Pack) o;

		return id == pack.id;

	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}


	/**
	 * 判断是否咸康包装
	 * @return
	 */
	public boolean isXkPack()
	{
		return pIndex==PACK_XIANKANG_ZHEDIE||pIndex==PACK_XIANKANG_PUTONG_SHUAIXIANG||pIndex==PACK_XIANKANG_JIAQIANG_SHUAIXIANG;
	}
}
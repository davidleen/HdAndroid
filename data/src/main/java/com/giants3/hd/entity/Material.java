package com.giants3.hd.entity;


import java.io.Serializable;

/**
 * 材料数据
 *
 */


public class Material  implements Serializable {

	/**
	 * 材料id
	 */

	public long id;



	/**
	 * 材料名称
	 */

	public String name;
	/**
	 * 材料单价
	 */

	public float price;
	/**
	 * 材料单位id
	 */

	public String unitId;
	/**
	 * 材料单位名称
	 */

	public String unitName;
	/**
	 * 材料类型id
	 */

	public int typeId;
	/**
	 * 材料类型名称
	 */

	public String typeName;
	/**
	 * 材料备注
	 */

	public String memo;
	/**
	 * 材料毛料规格 宽
	 */

	public float wWidth;
	/**
	 * 材料规格  长
	 */

	public float wLong;
	/**
	 * 材料规格高
	 */

	public float wHeight;
	/**
	 * 材料利用率  默认
	 */

	public float available=1;
	/**
	 * 物料 编码
	 */

	public String  code;


	/**
	 * 损耗率
	 */

	public float discount;




	/**
	 * 类别
	 */

	public  String spec;


	/**
	 * 缓存数据  buffer  即材料分类id
	 */

	public String classId;


	/**
	 * 类别即材料分类名称
	 */

	public  String className;


	/**
	 * 换算单位 默认为1
	 */

	public float unitRatio=1;


	/**
	 * 定额的计算公式。
	 */

	public int equationId;

	/**
	 * 最新图片更新时间
	 */


	public long lastPhotoUpdateTime=0;


	/**
	 * 配料比例
	 */

	public float ingredientRatio=0f;


	/**
	 * 图片，存放缩略图
	 */

	public byte[] photo;




	public String url;


	/**
	 * 是否停用
	 */

	public boolean outOfService;


	/**
	 * 停用日期
	 */
	public long outOfServiceDate;

	/**
	 * 停用日期字符串
	 */
	public String outOfServiceDateString;



	public String getClassId() {
		return classId;
	}

	public void setClassId(String buffer) {
		this.classId = buffer;
	}




	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public float getwWidth() {
		return wWidth;
	}

	public void setwWidth(float wWidth) {
		this.wWidth = wWidth;
	}

	public float getwLong() {
		return wLong;
	}

	public void setwLong(float wLong) {
		this.wLong = wLong;
	}

	public float getwHeight() {
		return wHeight;
	}

	public void setwHeight(float wHeight) {
		this.wHeight = wHeight;
	}

	public float getAvailable() {
		return available;
	}

	public void setAvailable(float available) {
		this.available = available;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public float getUnitRatio() {
		return unitRatio;
	}

	public void setUnitRatio(Float unitRatio) {
		this.unitRatio = unitRatio;
	}

	public long getLastPhotoUpdateTime() {
		return lastPhotoUpdateTime;
	}

	public void setLastPhotoUpdateTime(long lastPhotoUpdateTime) {
		this.lastPhotoUpdateTime = lastPhotoUpdateTime;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public static final class MCLass
	{


		/**
		 * 箱子类
		 */
		public static final String C_BAZZ ="BAZZ";
		/**
		 * 内盒
		 */
		public static final String C_2201 ="2201";
		/**
		 * 展示盒
		 */

		public static final String C_BZAF = "BZAF";
		/**
		 * 	胶带类型
		 */
		public static final String C_BZAE = "BZAE";
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Material)) return false;

		Material material = (Material) o;

		if (id != material.id) return false;
		if (Float.compare(material.price, price) != 0) return false;
		if (typeId != material.typeId) return false;
		if (Float.compare(material.wWidth, wWidth) != 0) return false;
		if (Float.compare(material.wLong, wLong) != 0) return false;
		if (Float.compare(material.wHeight, wHeight) != 0) return false;
		if (Float.compare(material.available, available) != 0) return false;
		if (Float.compare(material.discount, discount) != 0) return false;
		if (Float.compare(material.unitRatio, unitRatio) != 0) return false;
		if (equationId != material.equationId) return false;
		if (lastPhotoUpdateTime != material.lastPhotoUpdateTime) return false;
		if (Float.compare(material.ingredientRatio, ingredientRatio) != 0) return false;
		if (outOfService != material.outOfService) return false;
		if (outOfServiceDate != material.outOfServiceDate) return false;
		if (name != null ? !name.equals(material.name) : material.name != null) return false;
		if (unitId != null ? !unitId.equals(material.unitId) : material.unitId != null) return false;
		if (unitName != null ? !unitName.equals(material.unitName) : material.unitName != null) return false;
		if (typeName != null ? !typeName.equals(material.typeName) : material.typeName != null) return false;
		if (memo != null ? !memo.equals(material.memo) : material.memo != null) return false;
		if (code != null ? !code.equals(material.code) : material.code != null) return false;
		if (spec != null ? !spec.equals(material.spec) : material.spec != null) return false;
		if (classId != null ? !classId.equals(material.classId) : material.classId != null) return false;
		if (className != null ? !className.equals(material.className) : material.className != null) return false;
		if (url != null ? !url.equals(material.url) : material.url != null) return false;
		return !(outOfServiceDateString != null ? !outOfServiceDateString.equals(material.outOfServiceDateString) : material.outOfServiceDateString != null);

	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
		result = 31 * result + (unitId != null ? unitId.hashCode() : 0);
		result = 31 * result + (unitName != null ? unitName.hashCode() : 0);
		result = 31 * result + typeId;
		result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
		result = 31 * result + (memo != null ? memo.hashCode() : 0);
		result = 31 * result + (wWidth != +0.0f ? Float.floatToIntBits(wWidth) : 0);
		result = 31 * result + (wLong != +0.0f ? Float.floatToIntBits(wLong) : 0);
		result = 31 * result + (wHeight != +0.0f ? Float.floatToIntBits(wHeight) : 0);
		result = 31 * result + (available != +0.0f ? Float.floatToIntBits(available) : 0);
		result = 31 * result + (code != null ? code.hashCode() : 0);
		result = 31 * result + (discount != +0.0f ? Float.floatToIntBits(discount) : 0);
		result = 31 * result + (spec != null ? spec.hashCode() : 0);
		result = 31 * result + (classId != null ? classId.hashCode() : 0);
		result = 31 * result + (className != null ? className.hashCode() : 0);
		result = 31 * result + (unitRatio != +0.0f ? Float.floatToIntBits(unitRatio) : 0);
		result = 31 * result + equationId;
		result = 31 * result + (int) (lastPhotoUpdateTime ^ (lastPhotoUpdateTime >>> 32));
		result = 31 * result + (ingredientRatio != +0.0f ? Float.floatToIntBits(ingredientRatio) : 0);
		result = 31 * result + (url != null ? url.hashCode() : 0);
		result = 31 * result + (outOfService ? 1 : 0);
		result = 31 * result + (int) (outOfServiceDate ^ (outOfServiceDate >>> 32));
		result = 31 * result + (outOfServiceDateString != null ? outOfServiceDateString.hashCode() : 0);
		return result;
	}
}
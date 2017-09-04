package com.giants3.hd.entity;

import com.giants3.hd.utils.FloatHelper;
import com.giants3.hd.utils.StringUtils;
import com.giants3.hd.utils.interf.Valuable;

import java.io.Serializable;

/**
 * 产品油漆信息
 * 包括 材料工序
 */




public class ProductPaint  implements Serializable,Summariable,Valuable {

	public long id;
	public long productId;



	public int itemIndex;
	/**
	 * 工序id
	 */

	private long processId;
	/**
	 * 物料id
	 */

	public long materialId;
	/**
	 * 产品名称
	 */

	private String productName;

	/**
	 * 流程id
	 */

	public long flowId;

	public  String memo;




	public long getFlowId() {
		return flowId;
	}

	public void setFlowId(long flowId) {
		this.flowId = flowId;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	/**
	 * 物料编码
	 */

	public String materialCode;
	/**
	 * 物料名称
	 */

	public String materialName;


	/**
	 * 物料单位
	 */

	public String unitName;




	/**
	 * 工序编码
	 */

	public String processCode;

	/**
	 * 工序名称
	 */

	public String processName;


	/**
	 * 混合后的材料单价
	 */

	public  float price;
	/**
	 * 原材料单价
	 */

	public float materialPrice;
	/**
	 * 工序单价
	 */

	public float processPrice;
	/**
	 * 配料比例  值【0，】  配料都是稀释剂
	 */

	public float ingredientRatio;


	/**
	 * 混合材料的用量
	 */

	public float quantity;
	/**
	 * 原材料用量  
	 */

	public float materialQuantity;
	/**
	 * 材料费用=（materialPrice*materialQuantity）
	 */

	public float cost;

	/**
	 * 配料（稀释剂）的用量   =材料用量×（配料比例/（1+配料比例））
	 * 
	 * =materialQuantity*(ingredientRatio/(1+ingredientRatio))
	 */

	public float ingredientQuantity;


	/**
	 * 材料分类类型
	 */

	public int materialType;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getProcessId() {
		return processId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	public long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(long materialId) {
		this.materialId = materialId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}



	public float getProcessPrice() {
		return processPrice;
	}

	public void setProcessPrice(float processPrice) {
		this.processPrice = processPrice;
	}

	public float getIngredientRatio() {
		return ingredientRatio;
	}








	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}


	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}


	/**
	 * 更新材料数据
	 * @param material
	 */
	public void updateMaterial(Material material,GlobalData globalData) {




		this.materialCode=material.code;
		this.materialName=material.name;
		this.materialId=material.id;
		this.materialPrice=material.price;


		this.unitName=material.unitName;
		this.materialType=material.typeId;
		this.ingredientRatio=material.ingredientRatio;
		 this.memo=material.memo;



		updatePriceAndCostAndQuantity(globalData);





	}










	/**
	 * 更新材料费用与配料费用
	 */
	public  void updatePriceAndCostAndQuantity(GlobalData globalData)
	{

		GlobalData configData=globalData;
		price=FloatHelper.scale((materialPrice + configData.price_of_diluent * ingredientRatio)/(1+ingredientRatio),3);
		cost=FloatHelper.scale(quantity*price);

		//配料 即主成分用量
		materialQuantity= FloatHelper.scale(quantity/(1+ingredientRatio),3) ;
		ingredientQuantity=quantity-materialQuantity ;


	}

	@Override
	public int getType() {
		return materialType;
	}

	@Override
	public float getAmount() {
		return cost;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMemo() {
		return memo;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ProductPaint)) return false;

		ProductPaint that = (ProductPaint) o;

		if (id != that.id) return false;
		if (productId != that.productId) return false;
		if (processId != that.processId) return false;
		if (materialId != that.materialId) return false;
		if (flowId != that.flowId) return false;
		if (Float.compare(that.price, price) != 0) return false;
		if (Float.compare(that.materialPrice, materialPrice) != 0) return false;
		if (Float.compare(that.processPrice, processPrice) != 0) return false;
		if (Float.compare(that.ingredientRatio, ingredientRatio) != 0) return false;
		if (Float.compare(that.quantity, quantity) != 0) return false;
		if (Float.compare(that.itemIndex, itemIndex) != 0) return false;
		if (Float.compare(that.materialQuantity, materialQuantity) != 0) return false;
		if (Float.compare(that.cost, cost) != 0) return false;
		if (Float.compare(that.ingredientQuantity, ingredientQuantity) != 0) return false;
		if (materialType != that.materialType) return false;
		if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
		if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;
		if (materialCode != null ? !materialCode.equals(that.materialCode) : that.materialCode != null) return false;
		if (materialName != null ? !materialName.equals(that.materialName) : that.materialName != null) return false;
		if (unitName != null ? !unitName.equals(that.unitName) : that.unitName != null) return false;
		if (processCode != null ? !processCode.equals(that.processCode) : that.processCode != null) return false;
		return !(processName != null ? !processName.equals(that.processName) : that.processName != null);

	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (int) (productId ^ (productId >>> 32));
		result = 31 * result + (int) (processId ^ (processId >>> 32));
		result = 31 * result + (int) (materialId ^ (materialId >>> 32));
		result = 31 * result + (productName != null ? productName.hashCode() : 0);
		result = 31 * result + (int) (flowId ^ (flowId >>> 32));
		result = 31 * result + (memo != null ? memo.hashCode() : 0);
		result = 31 * result + (materialCode != null ? materialCode.hashCode() : 0);
		result = 31 * result + (materialName != null ? materialName.hashCode() : 0);
		result = 31 * result + (unitName != null ? unitName.hashCode() : 0);
		result = 31 * result + (processCode != null ? processCode.hashCode() : 0);
		result = 31 * result + (processName != null ? processName.hashCode() : 0);
		result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
		result = 31 * result + (materialPrice != +0.0f ? Float.floatToIntBits(materialPrice) : 0);
		result = 31 * result + (processPrice != +0.0f ? Float.floatToIntBits(processPrice) : 0);
		result = 31 * result + (ingredientRatio != +0.0f ? Float.floatToIntBits(ingredientRatio) : 0);
		result = 31 * result + (quantity != +0.0f ? Float.floatToIntBits(quantity) : 0);
		result = 31 * result +itemIndex;
		result = 31 * result + (materialQuantity != +0.0f ? Float.floatToIntBits(materialQuantity) : 0);
		result = 31 * result + (cost != +0.0f ? Float.floatToIntBits(cost) : 0);
		result = 31 * result + (ingredientQuantity != +0.0f ? Float.floatToIntBits(ingredientQuantity) : 0);
		result = 31 * result + materialType;
		return result;
	}

	@Override
	public boolean isEmpty() {

	return 	StringUtils.isEmpty(processName)&&StringUtils.isEmpty(processCode)&&materialId<=0;

	}



//	/**
//	 * 设置配料材质
//	 * @param material
//	 */
//	public void setIngredientMaterial(Material material) {
//
//
//
//		//price_of_diluent=material.price;
//		updateCostAndQuantity();
//
//	}
}
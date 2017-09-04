package com.giants3.hd.entity;


import com.giants3.hd.utils.FloatHelper;

import java.io.Serializable;

/**
 * 产品表
 */

public class Product implements Serializable {

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
	/**
	 * 人工成本
	 */

	public float costWage;
	/**
	 * 板料成本
	 */

	public float cost8;
	/**
	 * 配件成本
	 */

	public float cost7;
	/**
	 * 木料成本
	 */

	public float cost1;
	/**
	 * 玻璃镜片成本
	 */

	public float cost6;
	/**
	 * 铁配件成本
	 */

	public float cost11_15;
	/**
	 * 木油成本
	 */

	public float cost5;

	public String attribute="";


	/**
	 * 规格尺寸  厘米为单位
	 */

	public String  specCm="";


	/**
	 *包装类型  必选
	 */

	public Pack pack;
	/**
	 * id 标识字段
	 */

	public long id;
	/**
	 * 产品名称
	 */

	public String  name="";
	/**
	 * 登记日期
	 */

	public String rDate="";


	/**
	 * 规格描述
	 * @return
	 */

	public String spec="";



	/**
	 * 版本号     手动录入 规格目前不限定
	 * @return
	 */

	public String pVersion="";


	/**
	 * 最近基本数据更新时间
	 * 不参与数据一致比较
	 */

	public long lastUpdateTime=0;


	/**
	 * 最近图片更新时间
	 * 不参与数据一致比较
	 */

	public long lastPhotoUpdateTime;



	/**
	 * 材料构成 成分
	 * @return
	 */

	public String constitute="";






	/**
	 * 油漆材料成本
	 */

	public
	float paintCost;
	/**
	 * 油漆工资成本
	 */

	public float paintWage;


	/**
	 * 产品实际成本 由 白胚 组装 油漆 包装 合成
	 */

	public float productCost;



	/**
	 * 港杂费用
	 */

	public float gangza;
	/**
	 * 组装工资汇总
	 */

	public float assembleWage;

	/**
	 * 组装材料汇总
	 */

	public float assembleCost;

	/**
	 * 胚体材料汇总
	 */

	public float conceptusCost;
	/**
	 * 胚体工资汇总
	 */

	public float conceptusWage;


	/**
	 * 修理工资  独立计算
	 */

	public  float repairCost;


	/**
	 * 是否咸康信息
	 */

	public boolean isXK=false;


	public Xiankang xiankang;






	/**
	 * 包装才材料成本
	 */

	public float packCost;

	/**
	 * 包装才材料工资
	 */

	public float packWage;



	/**
	 * 出口价  即是FOB
	 */

	public float fob;
	/**
	 * 成本价     实际成本*(1+管理费用比例)
	 */

	public float cost;
	/**
	 * 出产价格 公式  成本价/ 利润比例
	 */

	public float price;
	/**
	 * 包装产品数量  即一个包装内有几个产品
	 */

	public int packQuantity;
	/**
	 * 包装体积
	 */

	public float packVolume;
	/**
	 * 包装的宽
	 */

	public float packWidth;
	/**
	 * 包装的高
	 */

	public float packHeight;
	/**
	 * 包装长
	 */

	public float packLong;
	/**
	 * 包材成本
	 */

	public float packMaterialCost;


	/**
	 * 内盒数量
	 */

	public int insideBoxQuantity;
	/**
	 * 包材统计
	 */

	public float cost4;


	/**
	 * 镜面尺寸
	 */

	public String  mirrorSize="";


	/**
	 * 附件列表   以分号隔开。
	 */

	public String attaches="";



	/**
	 * 图片url
	 */

	public  String url;
	/**
	 *  thumbnail
	 */
	public  String thumbnail;


	/**
	 * 工厂编号
	 */

	public String  factoryCode;
	/**
	 * 工厂名称
	 */

	public String factoryName;





	public float getFob() {
		return fob;
	}

	public void setFob(float fob) {
		this.fob = fob;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getPackQuantity() {
		return packQuantity;
	}

	public void setPackQuantity(int quantity) {
		this.packQuantity = quantity;
	}

	public float getPackVolume() {
		return packVolume;
	}

	public void setPackVolume(float packVolume) {
		this.packVolume = packVolume;
	}

	public float getPackWidth() {
		return packWidth;
	}

	public void setPackWidth(float packWidth) {
		this.packWidth = packWidth;
	}

	public float getPackHeight() {
		return packHeight;
	}

	public void setPackHeight(float packHeight) {
		this.packHeight = packHeight;
	}

	public float getPackLong() {
		return packLong;
	}

	public void setPackLong(float packLong) {
		this.packLong = packLong;
	}

	public float getPackMaterialCost() {
		return packMaterialCost;
	}

	public void setPackMaterialCost(float packMaterialCost) {
		this.packMaterialCost = packMaterialCost;
	}





	public String getpUnitId() {
		return pUnitId;
	}

	public void setpUnitId(String pUnitId) {
		this.pUnitId = pUnitId;
	}

	public String getpUnitName() {
		return pUnitName;
	}

	public void setpUnitName(String pUnitName) {
		this.pUnitName = pUnitName;
	}

	public float getProductCost() {
		return productCost;
	}

	public void setProductCost(float productCost) {
		this.productCost = productCost;
	}

	public float getAssembleWage() {
		return assembleWage;
	}

	public void setAssembleWage(float assembleWage) {
		this.assembleWage = assembleWage;
	}

	public float getAssembleCost() {
		return assembleCost;
	}

	public void setAssembleCost(float assembleCost) {
		this.assembleCost = assembleCost;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}



	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getpClassId() {
		return pClassId;
	}

	public void setpClassId(long pClassId) {
		this.pClassId = pClassId;
	}

	public String getpClassName() {
		return pClassName;
	}

	public void setpClassName(String pClassName) {
		this.pClassName = pClassName;
	}

	public String getpPUnitId() {
		return pUnitId;
	}

	public void setpPUnitId(String pTypeId) {
		this.pUnitId = pTypeId;
	}

	public String getpPUnitName() {
		return pUnitName;
	}

	public void setpPUnitName(String pTypeName) {
		this.pUnitName = pTypeName;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getCostWage() {
		return costWage;
	}

	public void setCostWage(float costWage) {
		this.costWage = costWage;
	}

	public float getCost8() {
		return cost8;
	}

	public void setCost8(float cost8) {
		this.cost8 = cost8;
	}

	public float getCost7() {
		return cost7;
	}

	public void setCost7(float cost7) {
		this.cost7 = cost7;
	}

	public float getCost1() {
		return cost1;
	}

	public void setCost1(float cost1) {
		this.cost1 = cost1;
	}

	public float getCost6() {
		return cost6;
	}

	public void setCost6(float cost6) {
		this.cost6 = cost6;
	}

	public float getCost11_15() {
		return cost11_15;
	}

	public void setCost11_15(float cost11_15) {
		this.cost11_15 = cost11_15;
	}

	public float getCost5() {
		return cost5;
	}

	public void setCost5(float cost5) {
		this.cost5 = cost5;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
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

	public String getrDate() {
		return rDate;
	}

	public void setrDate(String rDate) {
		this.rDate = rDate;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}


	public long getLastPhotoUpdateTime() {
		return lastPhotoUpdateTime;
	}

	public void setLastPhotoUpdateTime(long lastPhotoUpdateTime) {
		this.lastPhotoUpdateTime = lastPhotoUpdateTime;
	}


	public String getpVersion() {
		return pVersion;
	}

	public void setpVersion(String pVersion) {
		this.pVersion = pVersion;
	}


	public String getConstitute() {
		return constitute;
	}

	public void setConstitute(String constitute) {
		this.constitute = constitute;
	}

	public void setPaintCost(float paintCost) {
		this.paintCost = paintCost;
	}

	public float getPaintCost() {
		return paintCost;
	}


	public void setPaintWage(float paintWage) {
		this.paintWage = paintWage;
	}

	public float getPaintWage() {
		return paintWage;
	}


	public float getConceptusCost() {
		return conceptusCost;
	}

	public void setConceptusCost(float conceptusCost) {
		this.conceptusCost = conceptusCost;
	}

	public float getConceptusWage() {
		return conceptusWage;
	}

	public void setConceptusWage(float conceptusWage) {
		this.conceptusWage = conceptusWage;
	}

	/**
	 * 更新油漆的汇总信息
	 * @param paintCost
	 * @param paintWage
	 */
	public void updatePaintData(float paintCost, float paintWage,GlobalData globalData) {

		this.paintCost=FloatHelper.scale(paintCost);
		this.paintWage=FloatHelper.scale(paintWage);
		calculateTotalCost(globalData);
	}


	/**
	 * 计算总成本
	 */
	public void calculateTotalCost(GlobalData globalData)
	{


		productCost= FloatHelper.scale( paintCost+paintWage+assembleCost+assembleWage+conceptusCost+conceptusWage+packCost+packWage);



		//计算体积
		packVolume= FloatHelper.scale(packLong*packWidth*packHeight/1000000,3);


		//计算修理工资
		repairCost=FloatHelper.scale(packQuantity<=0?0:packVolume*globalData.repairPrice/packQuantity);


		 GlobalData configData= globalData ;


		//获取管理系数  咸康跟普通不一致
		float manageRatio=globalData.manageRatioNormal;
		if(pack!=null&&pack.isXkPack())
		{
			manageRatio=globalData.manageRatioXK;
		}


		//计算成本价   (实际成本+修理工资)*（1+管理系数）
		cost=FloatHelper.scale((productCost+repairCost)*(1+manageRatio));





		price=FloatHelper.scale(cost/configData.cost_price_ratio);

		if(packQuantity <=0)
			fob=0;
		else
		//售价+海外运费
		fob=FloatHelper.scale((price * (1 + configData.addition) + packVolume * configData.price_of_export / packQuantity)/configData.exportRate);



	}


	public String getSpecCm() {
		return specCm;
	}

	public void setSpecCm(String specCm) {
		this.specCm = specCm;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Product)) return false;

		Product product = (Product) o;

		if (pClassId != product.pClassId) return false;
		if (Float.compare(product.weight, weight) != 0) return false;
		if (Float.compare(product.costWage, costWage) != 0) return false;
		if (Float.compare(product.cost8, cost8) != 0) return false;
		if (Float.compare(product.cost7, cost7) != 0) return false;
		if (Float.compare(product.cost1, cost1) != 0) return false;
		if (Float.compare(product.cost6, cost6) != 0) return false;
		if (Float.compare(product.cost11_15, cost11_15) != 0) return false;
		if (Float.compare(product.cost5, cost5) != 0) return false;
		if (id != product.id) return false;

		if (Float.compare(product.paintCost, paintCost) != 0) return false;
		if (Float.compare(product.paintWage, paintWage) != 0) return false;
		if (Float.compare(product.productCost, productCost) != 0) return false;
		if (Float.compare(product.assembleWage, assembleWage) != 0) return false;
		if (Float.compare(product.assembleCost, assembleCost) != 0) return false;
		if (Float.compare(product.conceptusCost, conceptusCost) != 0) return false;
		if (Float.compare(product.conceptusWage, conceptusWage) != 0) return false;
		if (Float.compare(product.packCost, packCost) != 0) return false;
		if (Float.compare(product.packWage, packWage) != 0) return false;
		if (Float.compare(product.gangza, gangza) != 0) return false;
		if (Float.compare(product.fob, fob) != 0) return false;
		if (Float.compare(product.cost, cost) != 0) return false;
		if (Float.compare(product.price, price) != 0) return false;
		if (packQuantity != product.packQuantity) return false;
		if (Float.compare(product.packVolume, packVolume) != 0) return false;
		if (Float.compare(product.packWidth, packWidth) != 0) return false;
		if (Float.compare(product.packHeight, packHeight) != 0) return false;
		if (Float.compare(product.packLong, packLong) != 0) return false;
		if (Float.compare(product.packMaterialCost, packMaterialCost) != 0) return false;
		if (insideBoxQuantity != product.insideBoxQuantity) return false;
		if (Float.compare(product.cost4, cost4) != 0) return false;
		if (memo != null ? !memo.equals(product.memo) : product.memo != null) return false;
		if (factoryCode != null ? !factoryCode.equals(product.factoryCode) : product.factoryCode != null) return false;
		if (attaches != null ? !attaches.equals(product.attaches) : product.attaches != null) return false;

		if (url != null ? !url.equals(product.url) : product.url != null) return false;
		if (pClassName != null ? !pClassName.equals(product.pClassName) : product.pClassName != null) return false;
		if (pUnitId != null ? !pUnitId.equals(product.pUnitId) : product.pUnitId != null) return false;
		if (pUnitName != null ? !pUnitName.equals(product.pUnitName) : product.pUnitName != null) return false;
		if (attribute != null ? !attribute.equals(product.attribute) : product.attribute != null) return false;
		if (specCm != null ? !specCm.equals(product.specCm) : product.specCm != null) return false;
		if (pack != null ? !pack.equals(product.pack) : product.pack != null) return false;
		if (name != null ? !name.equals(product.name) : product.name != null) return false;
		if (rDate != null ? !rDate.equals(product.rDate) : product.rDate != null) return false;
		if (spec != null ? !spec.equals(product.spec) : product.spec != null) return false;
		if (pVersion != null ? !pVersion.equals(product.pVersion) : product.pVersion != null) return false;
		if (constitute != null ? !constitute.equals(product.constitute) : product.constitute != null) return false;
		if (isXK != product.isXK) return false;
		if (xiankang != null ? !xiankang.equals(product.xiankang) : product.xiankang != null) return false;
		return !(mirrorSize != null ? !mirrorSize.equals(product.mirrorSize) : product.mirrorSize != null);

	}

	@Override
	public int hashCode() {
		int result = memo != null ? memo.hashCode() : 0;

		result = 31 * result + (url != null ? url.hashCode() : 0);
		result = 31 * result + (int) (pClassId ^ (pClassId >>> 32));
		result = 31 * result + (pClassName != null ? pClassName.hashCode() : 0);
		result = 31 * result + (pUnitId != null ? pUnitId.hashCode() : 0);
		result = 31 * result + (pUnitName != null ? pUnitName.hashCode() : 0);
		result = 31 * result + (weight != +0.0f ? Float.floatToIntBits(weight) : 0);
		result = 31 * result + (costWage != +0.0f ? Float.floatToIntBits(costWage) : 0);
		result = 31 * result + (cost8 != +0.0f ? Float.floatToIntBits(cost8) : 0);
		result = 31 * result + (cost7 != +0.0f ? Float.floatToIntBits(cost7) : 0);
		result = 31 * result + (cost1 != +0.0f ? Float.floatToIntBits(cost1) : 0);
		result = 31 * result + (cost6 != +0.0f ? Float.floatToIntBits(cost6) : 0);
		result = 31 * result + (cost11_15 != +0.0f ? Float.floatToIntBits(cost11_15) : 0);
		result = 31 * result + (cost5 != +0.0f ? Float.floatToIntBits(cost5) : 0);
		result = 31 * result + (attribute != null ? attribute.hashCode() : 0);
		result = 31 * result + (specCm != null ? specCm.hashCode() : 0);
		result = 31 * result + (pack != null ? pack.hashCode() : 0);
		result = 31 * result + (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (rDate != null ? rDate.hashCode() : 0);
		result = 31 * result + (spec != null ? spec.hashCode() : 0);
		result = 31 * result + (pVersion != null ? pVersion.hashCode() : 0);
		result = 31 * result + (int) (lastPhotoUpdateTime ^ (lastPhotoUpdateTime >>> 32));
		result = 31 * result + (int) (lastUpdateTime ^ (lastUpdateTime >>> 32));
		result = 31 * result + (constitute != null ? constitute.hashCode() : 0);
		result = 31 * result + (paintCost != +0.0f ? Float.floatToIntBits(paintCost) : 0);
		result = 31 * result + (paintWage != +0.0f ? Float.floatToIntBits(paintWage) : 0);
		result = 31 * result + (productCost != +0.0f ? Float.floatToIntBits(productCost) : 0);
		result = 31 * result + (assembleWage != +0.0f ? Float.floatToIntBits(assembleWage) : 0);
		result = 31 * result + (assembleCost != +0.0f ? Float.floatToIntBits(assembleCost) : 0);
		result = 31 * result + (conceptusCost != +0.0f ? Float.floatToIntBits(conceptusCost) : 0);
		result = 31 * result + (conceptusWage != +0.0f ? Float.floatToIntBits(conceptusWage) : 0);
		result = 31 * result + (repairCost != +0.0f ? Float.floatToIntBits(repairCost) : 0);
		result = 31 * result + (gangza != +0.0f ? Float.floatToIntBits(gangza) : 0);
		result = 31 * result + (isXK ? 1 : 0);
		result = 31 * result + (xiankang != null ? xiankang.hashCode() : 0);
		result = 31 * result + (packCost != +0.0f ? Float.floatToIntBits(packCost) : 0);
		result = 31 * result + (packWage != +0.0f ? Float.floatToIntBits(packWage) : 0);
		result = 31 * result + (fob != +0.0f ? Float.floatToIntBits(fob) : 0);
		result = 31 * result + (cost != +0.0f ? Float.floatToIntBits(cost) : 0);
		result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
		result = 31 * result + packQuantity;
		result = 31 * result + (packVolume != +0.0f ? Float.floatToIntBits(packVolume) : 0);
		result = 31 * result + (packWidth != +0.0f ? Float.floatToIntBits(packWidth) : 0);
		result = 31 * result + (packHeight != +0.0f ? Float.floatToIntBits(packHeight) : 0);
		result = 31 * result + (packLong != +0.0f ? Float.floatToIntBits(packLong) : 0);
		result = 31 * result + (packMaterialCost != +0.0f ? Float.floatToIntBits(packMaterialCost) : 0);
		result = 31 * result + insideBoxQuantity;
		result = 31 * result + (cost4 != +0.0f ? Float.floatToIntBits(cost4) : 0);
		result = 31 * result + (mirrorSize != null ? mirrorSize.hashCode() : 0);
		result = 31 * result + (attaches != null ? attaches.hashCode() : 0);
		result = 31 * result + (factoryCode != null ? factoryCode.hashCode() : 0);
		return result;
	}


	/**
	 * 更新包装相关数据 影响到volume值
	 * @param inboxCount
	 * @param quantity
	 * @param packLong
	 * @param packWidth
	 * @param packHeight
	 */
	public void updatePackData(GlobalData globalData,int inboxCount, int quantity, float packLong, float packWidth, float packHeight) {



		this.insideBoxQuantity=inboxCount;
		this.packQuantity=quantity;
		this.packLong=packLong;
		this.packWidth=packWidth;
		this.packHeight=packHeight;


		//计算体积
		packVolume= FloatHelper.scale(packLong*packWidth*packHeight/1000000,3);


		//外厂才计算港杂
		//计算出港杂      立方数* 出口运费/装箱数
		gangza=calculateGangza(globalData);
		updateCostOnForignFactory(globalData,productCost);


	}


	public void updateForeignFactoryRelate(GlobalData globalData)
	{

			calculateGangza(globalData);
		updateCostOnForignFactory(globalData,productCost);

	}


	/**
	 * 根据外厂实际成本值 港杂费用 更新到 成本价 出厂价，美金价格
	 * @param productCost  实际成本
	 *
	 */
	public void updateCostOnForignFactory(GlobalData globalData,float productCost ) {


		this.productCost=productCost;


//		计算外厂港杂

//		calculateForeignGangza();
//		this.gangza=gangza;







		//更新成本价格
		cost=FloatHelper.scale(productCost+gangza);




		//更新出厂价格(加上管理费用  即 除以换算比率)
		price=FloatHelper.scale(cost*(1+globalData.manageRatioForeign));


		//更新美金价格
		fob=FloatHelper.scale(price/globalData.exportRate);



	}


	private float calculateGangza(GlobalData globalData)
	{

		gangza=FloatHelper.scale(packQuantity>0?globalData.price_of_export*packVolume/packQuantity:0);
		return gangza;
	}
}
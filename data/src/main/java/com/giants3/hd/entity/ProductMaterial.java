package com.giants3.hd.entity;

import com.giants3.hd.utils.FloatHelper;
import com.giants3.hd.utils.interf.Valuable;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 * 产品材料列表
 */
 
public class ProductMaterial  implements Serializable,Summariable ,Valuable {





	 
	public long id;
	/**
	 * 产品id
	 */
	 
	public long productId;



	 
	public int itemIndex;
	/**
	 * 配给额度。  重要字段。
	 */
	
	public float quota;
	/**
	 * 材料id
	 */
	
	public long materialId;

	/**
	 * 材料编码
	 */

	public String materialCode;

	/**
	 * 材料名称
	 */

	public String materialName;
	/**
	 * 单位用量
	 */

	public float quantity;
	/**
	 * 材料规格长
	 */

	public float mLong;
	/**
	 * 材料规格 宽
	 */

	public float mWidth;
	/**
	 * 材料规格 高
	 */

	public float mHeight;



	/**
	 * 产品规格长
	 */

	public float pLong;
	/**
	 * 产品规格 宽
	 */

	public float pWidth;
	/**
	 * 产品规格 高
	 */

	public float pHeight;
	/**
	 * 毛料长度
	 */

	public float wLong;
	/**
	 * 毛料宽度
	 */

	public float wWidth;
	/**
	 * 毛料高度
	 */

	public float wHeight;
	/**
	 * 利用率
	 */

	public float available;
	/**
	 * 单位名称
	 */

	public String unitName;
	/**
	 * 材料类别   重要  参与定额计算
	 */

	public int type;
	/**
	 * 材料单价
	 */

	public float price;
	/**
	 * 金额字段  
	 */

	public float amount;
	/**
	 * 分件备注字段
	 */

	public String memo;


	/**
	 * 流程id
	 */

	public  long flowId;

	/**
	 * 流程名称
	 */

	public String flowName;


	/**
	 * 材质类型选择
	 */

	public PackMaterialType packMaterialType;


	/**
	 * 包装材质位置选择  可选
	 */

	public PackMaterialPosition packMaterialPosition;



	/**
	 * 包装材质大分类   当材质是包装类型  即  flowId=Flow.FLOW_PACK 时候 必填
	 */

	public PackMaterialClass packMaterialClass;


	/**
	 * 材料分类 名称
	 */

	public String className;


	/**
	 * 材料分类id
	 */

	public String classId;


	/**
	 * 材料的换算单位  默认1；
	 * @return
	 */

	public float unitRatio=1;




	public PackMaterialType getPackMaterialType() {
		return packMaterialType;
	}

	public void setPackMaterialType(PackMaterialType packMaterialType) {
		this.packMaterialType = packMaterialType;
	}

	public PackMaterialPosition getPackMaterialPosition() {
		return packMaterialPosition;
	}

	public void setPackMaterialPosition(PackMaterialPosition packMaterialPosition) {
		this.packMaterialPosition = packMaterialPosition;
	}

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

	public float getQuota() {
		return quota;
	}

	public void setQuota(float quota) {
		this.quota = quota;
	}

	public long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(long materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public float getmLong() {
		return mLong;
	}

	public void setmLong(float mLong) {
		this.mLong = mLong;
	}

	public float getmWidth() {
		return mWidth;
	}

	public void setmWidth(float mWidth) {
		this.mWidth = mWidth;
	}

	public float getmHeight() {
		return mHeight;
	}

	public void setmHeight(float mHeight) {
		this.mHeight = mHeight;
	}

	public float getwLong() {
		return wLong;
	}

	public void setwLong(float wLong) {
		this.wLong = wLong;
	}

	public float getwWidth() {
		return wWidth;
	}

	public void setwWidth(float wWidth) {
		this.wWidth = wWidth;
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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}


	public long getFlowId() {
		return flowId;
	}

	public void setFlowId(long flowId) {
		this.flowId = flowId;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	/**
	 * 设置新材料  更新各项数据
	 * @param material
	 */
	public void updateMaterial(Material material) {

		materialCode=material.code;
		materialName=material.name;
		materialId=material.id;

		price=FloatHelper.scale(  material.price);
		available=material.available;

		unitName=material.unitName;
		type=material.typeId;

		mWidth=material.wWidth;
		mHeight=material.wHeight;
		mLong =material.wLong;
		classId=material.classId;
		className=material.className;
		memo=material.memo;
		unitRatio=material.unitRatio;
		update();






	}

	public float getpLong() {
		return pLong;
	}

	public void setpLong(float pLong) {
		this.pLong = pLong;
	}

	public float getpWidth() {
		return pWidth;
	}

	public void setpWidth(float pWidth) {
		this.pWidth = pWidth;
	}

	public float getpHeight() {
		return pHeight;
	}

	public void setpHeight(float pHeight) {
		this.pHeight = pHeight;
	}


	public PackMaterialClass getPackMaterialClass() {
		return packMaterialClass;
	}

	public void setPackMaterialClass(PackMaterialClass packMaterialClass) {
		this.packMaterialClass = packMaterialClass;

		 packMaterialType=packMaterialClass==null?null:packMaterialClass.type;
		 packMaterialPosition=packMaterialClass==null?null:packMaterialClass.position;


	}

	/**
	 * 更新材料相关数据
	 */
	public void update()
	{




		wLong=mLong+pLong;
		wHeight=mHeight+pHeight;
		wWidth=mWidth+pWidth;






		//计算定额



		float newQuota=0;





		if(materialId<=0)
			newQuota=0;
		else
		if(quantity<=0)
			newQuota=0;
		else


		//如果是包装材料  采用特殊计算方式
		if(flowId==Flow.FLOW_PACK&&packMaterialClass!=null)
		{




			switch (packMaterialClass.name)
			{

				case PackMaterialClass.CLASS_BOX:
					if(pWidth<15) wWidth=pWidth*2;
					if( pLong+pWidth>130)
					{
						newQuota=(pLong/100+pWidth/100+0.17f)*(wWidth/100+pHeight/100+0.07f)*2*quantity;
					}else
					{
						newQuota=(pLong/100+pWidth/100+0.09f)*(wWidth/100+pHeight/100+0.07f)*2*quantity;
					}


					break;

				case
						PackMaterialClass.CLASS_INSIDE_BOX:

					 	newQuota = (pLong / 100 + pWidth / 100 + 0.07f) * (pWidth / 100 + pHeight / 100 + 0.04f) * 2 * quantity;


					break;

				case
						PackMaterialClass.CLASS_CAIHE:
					 // 彩盒计算
						newQuota=(pLong/100+pWidth/100*4)*(pWidth/100+pHeight/100+0.06f)*2*quantity;


					break;
				case PackMaterialClass.CLASS_ZHANSHIHE:

					newQuota=(pLong/100+pWidth/100*2+0.42f)*(pWidth/100*4+pHeight/100+0.02f)*quantity;

					break;

				case PackMaterialClass.CLASS_JIAODAI:
					//TODO  胶带计算公式

					//这个计算公式独立计算。即有关联其他材料进行计算  @link  updateJiaodaiQuota


					break;
				case PackMaterialClass.CLASS_TESHU_BAOLILONG:
					//TODO  保丽隆计算公式

					//这个计算公式独立计算。即有关联其他材料进行计算  @link  updateJiaodaiQuota
					break;

				case PackMaterialClass.CLASS_QIPAODAI:

					newQuota=defaultCalculateQuota();

					break;


				default:

					newQuota=defaultCalculateQuota();
					break;

			}

		}else {
			newQuota=defaultCalculateQuota();
		}


			//加上计算单位换算比例（）
		  quota = FloatHelper.scale(newQuota*unitRatio,5);




		updateAmount();




	}


	/**
	 * 更新总额值
	 * 根据定额与单价计算。
	 *
	 */
	public void updateAmount()
	{


		amount= FloatHelper.scale(quota*price );
	}


	/**
	 * 更新胶带计算量
	 * chuanru
	 * @param neiheMaterial
	 */
	public void updateJiaodaiQuota(Product product,   ProductMaterial neiheMaterial)
	{




		//PackMaterialClass mPackMaterialClass=neiheMaterial.getPackMaterialClass();

	//验证 当前材料是 封口胶带类       关联的材料是 内盒子



			float boxQuantity=neiheMaterial==null?0:neiheMaterial.quantity;
			float boxLong=neiheMaterial==null?0:neiheMaterial.pLong;
			float boxWidth=neiheMaterial==null?0:neiheMaterial.pWidth;
			float boxHeight=neiheMaterial==null?0:neiheMaterial.pHeight;


			//内盒胶带用量
			float boxQuota=0;
			//装箱胶带用量
			float newQuota=0;
			int packIndex=product.pack==null?0:product.pack.pIndex;
			switch (packIndex)
			{
				//折叠盒包装的胶带公式
				case Pack.PACK_XIANKANG_ZHEDIE:

					//不计算内盒胶带用量
					boxQuota=0;
					//装箱数大于0
					if(product.packQuantity>0) {
						if (pWidth >= 20 && pLong < 80) {
							newQuota = ((pLong + 20) * 2 + (pWidth + 10) * 4 + (pWidth + 20) * 2) * quantity / 100f;
						}
						else if (pWidth >= 20 && pLong >= 80) {
							newQuota = ((pLong + 20) * 2 + (pWidth + 10) * 4+ (pWidth + 20) * 4) * quantity / 100f;
						}
						else if (pWidth < 20 && pLong < 80) {
							newQuota = ((pLong + 20) * 2 + (pWidth + 10) * 4+ (pWidth + 20) * 2) * quantity / 100f;
						} else   {
							newQuota = ((pLong + 20) * 2 + (pWidth + 10) * 4 + (pWidth + 20) * 8) * quantity / 100f;
						}

					}






					break;

				//咸康加强的胶带公式
				case Pack.PACK_XIANKANG_JIAQIANG_SHUAIXIANG:
				case Pack.PACK_XIANKANG_PUTONG_SHUAIXIANG:


					//不计算内盒胶带用量

					//装箱数大于0
					if(product.packQuantity>0) {
						if (pWidth >= 20 && pLong < 80) {
							newQuota = ((pLong + 20) * 2 + (pWidth + 10) * 4 + (pWidth + 20) * 2) * quantity / 100f;
						}
						else if (pWidth >= 20 && pLong >= 80) {
							newQuota = ((pLong + 20) * 2 + (pWidth + 10) * 4+ (pWidth + 20) * 4) * quantity / 100f;
						}
						else if (pWidth < 20 && pLong < 80) {
							newQuota = ((pLong + 20) * 2 + (pWidth + 10) * 4+ (pWidth + 20) * 2) * quantity / 100f;
						} else   {
							newQuota = ((pLong + 20) * 2 + (pWidth + 10) * 4 + (pWidth + 20) * 8) * quantity / 100f;
						}

					}

					//内盒胶带用量
					if(boxQuantity>0)
					{

						if(pLong<80&&pWidth>=20)
						{
							boxQuota=((boxLong+20)*2+(boxWidth+10)*4)*boxQuantity/100f;
						}else
						if(pLong>=80&&pWidth>=20)
						{
							boxQuota=((boxLong+20)*2+(boxWidth+10)*4+(boxWidth+20)*2)*boxQuantity/100f;
						}else
						if(pWidth<20&&pLong<80)
						{
							boxQuota=((boxLong+20)*2+(boxWidth+10)*4+(boxWidth+20)*2)*boxQuantity/100f;
						}else

						{
							boxQuota=((boxLong+20)*2+(boxWidth+10)*4+(boxWidth+20)*8)*boxQuantity/100f;
						}



					}





				break;


				//默认普通公式
				default:

					//计算内盒胶带用量
					if(boxQuantity>0)
					{
						if(pLong<80&&pWidth>=20)
						{
							boxQuota=((boxLong+20)*2+(boxWidth+10)*4)*boxQuantity/100f;
						}else
							if(pLong>=80&&pWidth>=20)
							{
								boxQuota=((boxLong+20)*2+(boxWidth+10)*4+(boxWidth+20)*2)*boxQuantity/100f;
							}else
							if(pWidth<20&&pLong<80)
							{
								boxQuota=((boxLong+20)*2+(boxWidth+10)*4)*boxQuantity/100f;
							}else
								if(pLong>=80&&pWidth<20)
								{
									boxQuota=((boxLong+20)*2+(boxWidth+10)*4+(boxWidth+20)*4)*boxQuantity/100f;
								}

					}
					//装箱数大于0
					if(product.packQuantity>0) {
							if (pWidth >= 20 && pLong < 80) {
								newQuota = ((pLong + 20) * 2 + (pWidth + 10) * 4) * quantity / 100f;
							} else if (pWidth >= 20 && pLong >= 80) {
								newQuota = ((pLong + 20) * 2 + (pWidth + 10) * 4 + (pWidth + 20) * 2) * quantity / 100f;
							} else if (pWidth < 20 && pLong < 80) {
								newQuota = ((pLong + 20) * 2 + (pWidth + 10) * 4) * quantity / 100f;
							} else if (pWidth < 20 && pLong >= 80) {
								newQuota = ((pLong + 20) * 2 + (pWidth + 10) * 4 + (pWidth + 20) * 4) * quantity / 100f;
							}

						}


					}






			Logger.getLogger("TEST").info("boxQuota:"+boxQuota+",newQuota:"+newQuota);
			quota=FloatHelper.scale(newQuota+boxQuota,5);

			updateAmount();













	}


	/**
	 * 更新保丽龙的材料成本
	 */
	public void updateBAOLILONGQuota(Product product,ProductMaterial waixiang )
	{

		if(product==null) return;
		float waixiangLong=waixiang.pLong;
		float waixiangWidth=waixiang.pWidth;
		float waixiangHeight=waixiang.pHeight;
		Xiankang xiankang=product.xiankang;

		int unitSize=1;
		try {
			unitSize=Integer.valueOf(product.pUnitName.substring(product.pUnitName.lastIndexOf("/") +1 ));
		}catch (Throwable t)
		{}

		float newQuota=0;
		int packIndex=product.pack==null?0:product.pack.pIndex;
		switch (packIndex)
		{

				//折叠盒包装的胶带公式
				case Pack.PACK_XIANKANG_ZHEDIE:



					newQuota=	waixiangLong*waixiangHeight/10000*2.54f/100f*(xiankang.pack_front+xiankang.pack_middle*(unitSize-1));
					break;

			//咸康加强的胶带公式
			case Pack.PACK_XIANKANG_JIAQIANG_SHUAIXIANG:
			case Pack.PACK_XIANKANG_PUTONG_SHUAIXIANG:
				//newQuota=	waixiangLong*waixiangHeight/10000*2.54f/100f*(xiankang.pack_front+xiankang.pack_middle*(unitSize-1));
				if(xiankang.pack_perimeter>0&&xiankang.pack_front_back>0&&xiankang.pack_middle>0)
				{
					newQuota=	waixiangLong*waixiangWidth/10000f*xiankang.pack_perimeter*2.54f/100f*2
							+waixiangHeight*waixiangWidth/10000f*xiankang.pack_perimeter*2.54f/100f*2
							+waixiangLong*waixiangHeight/10000f*xiankang.pack_front_back*2.54f/100f*2
							+waixiangLong*waixiangHeight/10000f*xiankang.pack_middle*2.54f/100f*(unitSize-1);
				}else
				if(xiankang.pack_perimeter>0&&xiankang.pack_front_back>0)
				{

					newQuota=	waixiangLong*waixiangWidth/10000f*xiankang.pack_perimeter*2.54f/100f*2
							+waixiangHeight*waixiangWidth/10000f*xiankang.pack_perimeter*2.54f/100f*2
							+waixiangLong*waixiangHeight/10000f*xiankang.pack_front_back*2.54f/100f*2;
				}else
				if(xiankang.pack_cube>0&&xiankang.pack_middle>0)
				{
					newQuota=	waixiangLong*waixiangWidth/10000f*xiankang.pack_cube*2.54f/100f*2
							+waixiangHeight*waixiangWidth/10000f*xiankang.pack_cube*2.54f/100f*2
							+waixiangLong*waixiangHeight/10000f*xiankang.pack_cube*2.54f/100f*2
							+waixiangLong*waixiangHeight/10000f*xiankang.pack_middle*2.54f/100f*(unitSize-1);
				}else
				if(xiankang.pack_cube>0)
				{
					newQuota=		waixiangLong*waixiangWidth/10000f*xiankang.pack_cube*2.54f/100f*2
							+waixiangHeight*waixiangWidth/10000f*xiankang.pack_cube*2.54f/100f*2
							+waixiangLong*waixiangHeight/10000f*xiankang.pack_cube*2.54f/100f*2;
				}



				break;


			default:

				 newQuota=0;


		}


		 quota=newQuota;
		updateAmount();

	}


	/**
	 * 默认计算公式  长*宽*高*数量、利用率
	 */
	private float defaultCalculateQuota()
	{

		float newQuota=0;
		//默认计算公式
		if(pLong<=0&&pWidth<=0&&pHeight<=0)
		{
			newQuota=available<=0?0:quantity/available;

		}else
		if(pWidth<=0&&pHeight<=0)
		{
			newQuota=available<=0?0:quantity*wLong/100/available;
		}
		else
		if(pHeight<=0)
		{
			newQuota=available<=0?0:quantity*wLong*wWidth/10000/available;
		}
		else
		{newQuota=available<=0?0:quantity*wLong*wWidth*wHeight/1000000/available;}


		return newQuota;


	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ProductMaterial)) return false;

		ProductMaterial that = (ProductMaterial) o;

		if (id != that.id) return false;
		if (productId != that.productId) return false;
		if (Float.compare(that.quota, quota) != 0) return false;
		if (materialId != that.materialId) return false;
		if (Float.compare(that.quantity, quantity) != 0) return false;
		if (Float.compare(that.mLong, mLong) != 0) return false;
		if (Float.compare(that.mWidth, mWidth) != 0) return false;
		if (Float.compare(that.mHeight, mHeight) != 0) return false;
		if (Float.compare(that.pLong, pLong) != 0) return false;
		if (Float.compare(that.pWidth, pWidth) != 0) return false;
		if (Float.compare(that.pHeight, pHeight) != 0) return false;
		if (Float.compare(that.wLong, wLong) != 0) return false;
		if (Float.compare(that.wWidth, wWidth) != 0) return false;
		if (Float.compare(that.wHeight, wHeight) != 0) return false;
		if (Float.compare(that.available, available) != 0) return false;


		if (type != that.type) return false;
		if (Float.compare(that.price, price) != 0) return false;
		if (Float.compare(that.amount, amount) != 0) return false;
		if (flowId != that.flowId) return false;
		if (itemIndex != that.itemIndex) return false;
		if (Float.compare(that.unitRatio, unitRatio) != 0) return false;
		if (materialCode != null ? !materialCode.equals(that.materialCode) : that.materialCode != null) return false;
		if (materialName != null ? !materialName.equals(that.materialName) : that.materialName != null) return false;
		if (unitName != null ? !unitName.equals(that.unitName) : that.unitName != null) return false;
		if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;
		if (flowName != null ? !flowName.equals(that.flowName) : that.flowName != null) return false;
		if (packMaterialType != null ? !packMaterialType.equals(that.packMaterialType) : that.packMaterialType != null)
			return false;
		if (packMaterialPosition != null ? !packMaterialPosition.equals(that.packMaterialPosition) : that.packMaterialPosition != null)
			return false;
		if (packMaterialClass != null ? !packMaterialClass.equals(that.packMaterialClass) : that.packMaterialClass != null)
			return false;
		if (className != null ? !className.equals(that.className) : that.className != null) return false;
		return !(classId != null ? !classId.equals(that.classId) : that.classId != null);

	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (int) (productId ^ (productId >>> 32));
		result = 31 * result + (quota != +0.0f ? Float.floatToIntBits(quota) : 0);
		result = 31 * result + (int) (materialId ^ (materialId >>> 32));
		result = 31 * result + (materialCode != null ? materialCode.hashCode() : 0);
		result = 31 * result + (materialName != null ? materialName.hashCode() : 0);
		result = 31 * result + (quantity != +0.0f ? Float.floatToIntBits(quantity) : 0);
		result = 31 * result + (mLong != +0.0f ? Float.floatToIntBits(mLong) : 0);
		result = 31 * result + (mWidth != +0.0f ? Float.floatToIntBits(mWidth) : 0);
		result = 31 * result + (mHeight != +0.0f ? Float.floatToIntBits(mHeight) : 0);
		result = 31 * result + (pLong != +0.0f ? Float.floatToIntBits(pLong) : 0);
		result = 31 * result + (pWidth != +0.0f ? Float.floatToIntBits(pWidth) : 0);
		result = 31 * result + (pHeight != +0.0f ? Float.floatToIntBits(pHeight) : 0);
		result = 31 * result + (wLong != +0.0f ? Float.floatToIntBits(wLong) : 0);
		result = 31 * result + (wWidth != +0.0f ? Float.floatToIntBits(wWidth) : 0);
		result = 31 * result + (wHeight != +0.0f ? Float.floatToIntBits(wHeight) : 0);
		result = 31 * result + (available != +0.0f ? Float.floatToIntBits(available) : 0);
		result = 31 * result + (unitName != null ? unitName.hashCode() : 0);
		result = 31 * result + type;
		result = 31 * result + itemIndex;
		result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
		result = 31 * result + (amount != +0.0f ? Float.floatToIntBits(amount) : 0);
		result = 31 * result + (memo != null ? memo.hashCode() : 0);
		result = 31 * result + (int) (flowId ^ (flowId >>> 32));
		result = 31 * result + (flowName != null ? flowName.hashCode() : 0);
		result = 31 * result + (packMaterialType != null ? packMaterialType.hashCode() : 0);
		result = 31 * result + (packMaterialPosition != null ? packMaterialPosition.hashCode() : 0);
		result = 31 * result + (packMaterialClass != null ? packMaterialClass.hashCode() : 0);
		result = 31 * result + (className != null ? className.hashCode() : 0);
		result = 31 * result + (classId != null ? classId.hashCode() : 0);
		result = 31 * result + (unitRatio != +0.0f ? Float.floatToIntBits(unitRatio) : 0);

		return result;
	}

	@Override
	public boolean isEmpty() {


		//无设定材料  数据为空
		return (materialId<=0);



	}


}
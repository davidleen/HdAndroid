package com.giants3.hd.noEntity;

import com.giants3.hd.entity.GlobalData;
import com.giants3.hd.entity.PackMaterialClass;
import com.giants3.hd.entity.Product;
import com.giants3.hd.entity.ProductLog;
import com.giants3.hd.entity.ProductMaterial;
import com.giants3.hd.entity.ProductPaint;
import com.giants3.hd.entity.ProductProcess;
import com.giants3.hd.entity.ProductWage;
import com.giants3.hd.entity.Summariable;
import com.giants3.hd.entity.Xiankang;
import com.giants3.hd.utils.FloatHelper;
import com.giants3.hd.utils.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品详细信息
 */
public class ProductDetail implements Serializable {


    public ProductDetail() {

        product = new Product();
        product.xiankang = new Xiankang();
        conceptusMaterials = new ArrayList<>();
        assembleMaterials = new ArrayList<>();
        paints = new ArrayList<>();
        conceptusWages = new ArrayList<>();
        assembleWages = new ArrayList<>();
        packMaterials = new ArrayList<>();
        packWages = new ArrayList<>();

    }

    public Product product;


    /**
     * 记录表  该数据仅仅参与显示 不参与数据修改与否的比较
     */
    public ProductLog productLog;
    /**
     * 白胚材料列表
     */
    public List<ProductMaterial> conceptusMaterials;
    /**
     * 组装材料列表
     */
    public List<ProductMaterial> assembleMaterials;

    /**
     * 油漆材料工序列表
     */
    public List<ProductPaint> paints;


    /**
     * 白胚工资列表
     */

    public List<ProductWage> conceptusWages;
    /**
     * 组装工资列表
     */
    public List<ProductWage> assembleWages;
    /**
     * 包装材料列表
     */
    public List<ProductMaterial> packMaterials;
    /**
     * 包装工资列表
     */
    public List<ProductWage> packWages;


    /**
     * 更新产品的统计数据
     */
    public void updateProductStatistics(GlobalData globalData) {


        float paintWage = 0;
        float paintCost = 0;
        for (ProductPaint paint : paints) {
            paintWage += paint.processPrice;
            paintCost += paint.cost;

        }
        product.updatePaintData(paintCost, paintWage,
                globalData);


        //汇总计算白胚材料
        float conceptusCost = 0;
        for (ProductMaterial material : conceptusMaterials) {
            conceptusCost += material.getAmount();
        }
        product.conceptusCost = FloatHelper.scale(conceptusCost);
        //汇总计算组白胚工资
        float conceptusWage = 0;
        for (ProductWage wage : conceptusWages) {
            conceptusWage += wage.getAmount();
        }
        product.conceptusWage = FloatHelper.scale(conceptusWage);


        //汇总计算组装材料
        float assembleCost = 0;
        for (ProductMaterial material : assembleMaterials) {
            assembleCost += material.getAmount();
        }
        product.assembleCost = FloatHelper.scale(assembleCost);

        //汇总计算组装工资
        float assembleWage = 0;
        for (ProductWage wage : assembleWages) {
            assembleWage += wage.getAmount();
        }
        product.assembleWage = FloatHelper.scale(assembleWage);

        //汇总计算包装材料
        float packCost = 0;
        for (ProductMaterial material : packMaterials) {


            packCost += material.getAmount();


            //如果包材是外箱  则更新产品外包装材料信息


            if (material.getPackMaterialClass() != null) {

                String className = material.getPackMaterialClass().getName();
                if (className.equals(PackMaterialClass.CLASS_BOX)) {

                    product.packLong = material.getpLong();
                    product.packHeight = material.getpHeight();
                    product.packWidth = material.getpWidth();

                } else if (PackMaterialClass.CLASS_INSIDE_BOX.equals(className)) {
                    product.insideBoxQuantity = (int) material.quantity;
                }
            }


        }


        //计算包装成本  平摊箱数， 如无箱数 则默认1
        product.packCost = FloatHelper.scale(product.packQuantity == 0 ? packCost : packCost / product.packQuantity);

        //汇总计算包装工资
        float packWage = 0;
        for (ProductWage wage : packWages) {
            packWage += wage.getAmount();
        }
        //计算包装工资 平摊箱数， 如无箱数 则默认1
        product.packWage = FloatHelper.scale(product.packQuantity == 0 ? packWage : packWage / product.packQuantity);


        summariables.clear();

        summariables.addAll(conceptusMaterials);
        summariables.addAll(assembleMaterials);
        summariables.addAll(paints);
        summariables.addAll(packMaterials);

        //分类型统计数据
        float cost1 = 0;
        float cost7 = 0;
        float cost8 = 0;
        float cost5 = 0;
        float cost6 = 0;
        float cost4 = 0;
        float cost11_15 = 0;
        for (Summariable summariable : summariables) {
            int type = summariable.getType();
            float amount = summariable.getAmount();
            switch (type) {

                case 1:
                    cost1 += amount;
                    break;
                case 8:
                    cost8 += amount;
                    break;
                case 5:
                    cost5 += amount;
                    break;
                case 6:
                    cost6 += amount;
                    break;
                case 7:
                    cost7 += amount;
                    break;

                case 4:
                    cost4 += amount;
                    break;
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                    cost11_15 += amount;
                    break;


            }

        }
        summariables.clear();

        product.cost1 = FloatHelper.scale(cost1);
        product.cost5 = FloatHelper.scale(cost5);
        product.cost6 = FloatHelper.scale(cost6);
        product.cost7 = FloatHelper.scale(cost7);
        product.cost8 = FloatHelper.scale(cost8);
        product.cost11_15 = FloatHelper.scale(cost11_15);

        //计算包装材料 平摊箱数， 如无箱数 则默认1
        product.cost4 = FloatHelper.scale(product.packQuantity == 0 ? cost4 : cost4 / product.packQuantity);


        /**
         * 重新计算总成本
         */
        product.calculateTotalCost(globalData);


    }


    public List<Summariable> summariables = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDetail)) return false;

        ProductDetail detail = (ProductDetail) o;

        if (product != null ? !product.equals(detail.product) : detail.product != null)
            return false;
        if (conceptusMaterials != null ? !conceptusMaterials.equals(detail.conceptusMaterials) : detail.conceptusMaterials != null)
            return false;
        if (assembleMaterials != null ? !assembleMaterials.equals(detail.assembleMaterials) : detail.assembleMaterials != null)
            return false;
        if (paints != null ? !paints.equals(detail.paints) : detail.paints != null) return false;
        if (conceptusWages != null ? !conceptusWages.equals(detail.conceptusWages) : detail.conceptusWages != null)
            return false;
        if (assembleWages != null ? !assembleWages.equals(detail.assembleWages) : detail.assembleWages != null)
            return false;
        if (packMaterials != null ? !packMaterials.equals(detail.packMaterials) : detail.packMaterials != null)
            return false;
        return !(packWages != null ? !packWages.equals(detail.packWages) : detail.packWages != null);

    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (conceptusMaterials != null ? conceptusMaterials.hashCode() : 0);
        result = 31 * result + (assembleMaterials != null ? assembleMaterials.hashCode() : 0);
        result = 31 * result + (paints != null ? paints.hashCode() : 0);
        result = 31 * result + (conceptusWages != null ? conceptusWages.hashCode() : 0);
        result = 31 * result + (assembleWages != null ? assembleWages.hashCode() : 0);
        result = 31 * result + (packMaterials != null ? packMaterials.hashCode() : 0);
        result = 31 * result + (packWages != null ? packWages.hashCode() : 0);
        return result;
    }


    /**
     * 擦除相关记录id 信息 使其处于新增状态
     */
    public void swipe() {
        product.id = 0;
        productLog = null;


        product.xiankang.setId(0);
        product.xiankang.xiankang_dengju.setId(0);
        product.xiankang.xiankang_jiaju.setId(0);
        product.xiankang.xiankang_jingza.setId(0);


        for (ProductMaterial productMaterial : conceptusMaterials) {
            productMaterial.productId = 0;
            productMaterial.id = 0;
        }


        for (ProductMaterial productMaterial : assembleMaterials) {
            productMaterial.productId = 0;
            productMaterial.id = 0;
        }

        for (ProductPaint productPaint : paints) {
            productPaint.productId = 0;
            productPaint.id = 0;
        }


        for (ProductWage productWage : conceptusWages) {
            productWage.productId = 0;
            productWage.id = 0;
        }

        for (ProductWage productWage : assembleWages) {
            productWage.productId = 0;
            productWage.id = 0;
        }

        for (ProductMaterial productMaterial : packMaterials) {
            productMaterial.productId = 0;
            productMaterial.id = 0;
        }

        for (ProductWage productWage : packWages) {
            productWage.productId = 0;
            productWage.id = 0;
        }

    }


    /**
     * 更新配料洗刷枪的费用的数据量值。
     *
     * @return 被更新的index
     */
    public int updateQuantityOfIngredient(GlobalData globalData) {


        ProductPaint ingredientPaint = null;
        float totalIngredientQuantity = 0;
        for (ProductPaint paint : paints) {

            if (paint.processName == null || !paint.processName.contains(ProductProcess.XISHUA)) {
                if (paint.materialId > 0)
                    totalIngredientQuantity += paint.ingredientQuantity;
            } else
                ingredientPaint = paint;

        }

        if (ingredientPaint != null) {


            ingredientPaint.quantity = FloatHelper.scale(totalIngredientQuantity * globalData.extra_ratio_of_diluent, 3);
            ingredientPaint.updatePriceAndCostAndQuantity(globalData);
            int index = paints.indexOf(ingredientPaint);
            return index;

        }

        return -1;


    }


    /**
     * 更新相关产品包装数据
     */
    public void updateProductPackRelateData() {


        //更新数据
        List<ProductMaterial> datas = packMaterials;


        //胶带信息 与产品的包装类型相关

        //找出胶带
        int size = datas.size();

        //找出内盒数据
        ProductMaterial neihe = null;

        //找出外箱数据
        ProductMaterial waixiang = null;


        //找出胶带
        List<ProductMaterial> jiaodais = new ArrayList<>();

        //找出保丽隆
        List<ProductMaterial> baolilongs = new ArrayList<>();


        for (int i = 0; i < size; i++) {
            ProductMaterial material = datas.get(i);
            PackMaterialClass packMaterialClass = material.getPackMaterialClass();
            String packMaterialClassName = packMaterialClass == null ? "" : packMaterialClass.name;
            if (!StringUtils.isEmpty(packMaterialClassName))
                switch (packMaterialClassName) {


                    case PackMaterialClass.CLASS_BOX:
                        if (waixiang == null)
                            waixiang = material;
                        break;
                    case PackMaterialClass.CLASS_INSIDE_BOX:
                        if (neihe == null)
                            neihe = material;
                        break;
                    case PackMaterialClass.CLASS_JIAODAI:

                        jiaodais.add(material);
                        break;

                    case PackMaterialClass.CLASS_TESHU_BAOLILONG:

                        baolilongs.add(material);
                        break;

                }


        }

        if (neihe != null)
            for (ProductMaterial jiaodai : jiaodais)
                jiaodai.updateJiaodaiQuota(product, neihe);


        if (waixiang != null)
            for (ProductMaterial baolilong : baolilongs) {
                baolilong.updateBAOLILONGQuota(product, waixiang);
            }


    }


    /**
     * 根据产品材料的包装大类别 ，调整相应的包装数据
     */
    public void updatePackDataOnPackMaterialClass(ProductMaterial material) {


        //检查包装

        if (material != null && material.getPackMaterialClass() != null&&!StringUtils.isEmpty(material.getPackMaterialClass().name)) {
            switch (material.getPackMaterialClass().name) {

                //如果是内盒
                //找出胶带 更新胶带信息
                case PackMaterialClass.CLASS_INSIDE_BOX:

                    for (ProductMaterial productMaterial : packMaterials) {
                        PackMaterialClass packMaterialClass = productMaterial.getPackMaterialClass();
                        if (packMaterialClass != null) {
                            if (packMaterialClass.name.equals(PackMaterialClass.CLASS_JIAODAI)) {

                                productMaterial.updateJiaodaiQuota(product, material);
                                break;
                            }

                        }


                    }


                    break;


                case PackMaterialClass.CLASS_JIAODAI:


                    //找出内盒  更新胶带信息
                    ProductMaterial foundNeihe = null;
                    for (ProductMaterial productMaterial : packMaterials) {

                        PackMaterialClass packMaterialClass = productMaterial.getPackMaterialClass();
                        if (packMaterialClass != null) {
                            if (packMaterialClass.name.equals(PackMaterialClass.CLASS_INSIDE_BOX)) {

                                foundNeihe = productMaterial;

                                break;

                            }

                        }

                    }
                    //找出内盒  更新胶带信息
                    material.updateJiaodaiQuota(product, foundNeihe);


                    break;


                //外箱数据
                case PackMaterialClass.CLASS_BOX:


                    //找出保丽隆
                    ProductMaterial foundBaolilong = null;
                    for (ProductMaterial productMaterial : packMaterials) {

                        PackMaterialClass packMaterialClass = productMaterial.getPackMaterialClass();
                        if (packMaterialClass != null) {
                            if (packMaterialClass.name.equals(PackMaterialClass.CLASS_TESHU_BAOLILONG)) {
                                foundBaolilong = productMaterial;
                                foundBaolilong.updateBAOLILONGQuota(product, material);
                                break;

                            }

                        }

                    }


                    break;


                //外箱数据
                case PackMaterialClass.CLASS_TESHU_BAOLILONG:


                    //找出保丽隆
                    ProductMaterial foundWaixiang = null;
                    for (ProductMaterial productMaterial : packMaterials) {

                        PackMaterialClass packMaterialClass = productMaterial.getPackMaterialClass();
                        if (packMaterialClass != null) {
                            if (packMaterialClass.name.equals(PackMaterialClass.CLASS_BOX)) {
                                foundWaixiang = productMaterial;

                                break;

                            }

                        }

                    }

                    if (foundWaixiang != null)
                        material.updateBAOLILONGQuota(product, foundWaixiang);


                    break;

            }

        }

    }

}

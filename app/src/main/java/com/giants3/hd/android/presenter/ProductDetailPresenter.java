package com.giants3.hd.android.presenter;

/**
 * Created by david on 2016/4/13.
 */
public interface ProductDetailPresenter  extends  BasePresenter{


    /**
     * 白胚 组装 油漆 包装 模板四选一 点击事件
     * @param index
     */
    public void onPanelForClick( int index);


    /**
     * 工资材料 面板点击
     * @param index
     */
    public void onMaterialWageClick(int index);

    /**
     * 编辑点击
     */
   public  void toEditProductDetail();

    public void onItemAdd(int position);
    public void onItemModify(Object object,int position);
    public void onItemDelete(Object object,int position);

   public  void saveProductDetail();

    void onUnitClick();

    void onPVersionEdit();

    void onProductNameEdit();

    void onWeightEdit();

    void onSpecCmEdit();

    void onPackQuantityEdit();

    /**
     * 包装类型点击
     */
    void onPackEdit();
}

package com.giants3.hd.android.ViewImpl;


import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.adapter.ItemListAdapter;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.android.helper.ImageViewerHelper;
import com.giants3.hd.android.presenter.ProductDetailPresenter;
import com.giants3.hd.android.viewer.ProductDetailViewer;
import com.giants3.hd.android.widget.ExpandableHeightListView;
import com.giants3.hd.data.net.HttpUrl;
import com.giants3.hd.utils.StringUtils;
import com.giants3.hd.utils.entity.Product;
import com.giants3.hd.utils.entity.ProductDetail;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class ProductDetailViewerImpl extends BaseViewerImpl implements ProductDetailViewer, View.OnClickListener {


    private static final int MAX_MEMO_ROW_LINE = 3;
    @Bind(R.id.photo)
    ImageView photo;


    @Bind(R.id.loading)
    View loading;


    @Bind(R.id.pversion)
    TextView pversion;
    @Bind(R.id.name)
    TextView name;


    @Bind(R.id.unit)
    TextView unit;

    @Bind(R.id.pClass)
    TextView pClass;
    @Bind(R.id.pack)
    TextView pack;
    @Bind(R.id.weight)
    TextView weight;
    @Bind(R.id.factory)
    TextView factory;
    @Bind(R.id.specCm)
    TextView specCm;
    @Bind(R.id.packSize)
    TextView packSize;
    @Bind(R.id.fob)
    TextView fob;
    @Bind(R.id.price)
    TextView price;
    @Bind(R.id.cost)
    TextView cost;
    @Bind(R.id.conceptusCost)
    TextView conceptusCost;
    @Bind(R.id.conceptusWage)
    TextView conceptusWage;
    @Bind(R.id.assembleCost)
    TextView assembleCost;
    @Bind(R.id.assembleWage)
    TextView assembleWage;
    @Bind(R.id.paintCost)
    TextView paintCost;
    @Bind(R.id.paintWage)
    TextView paintWage;
    @Bind(R.id.packCost)
    TextView packCost;
    @Bind(R.id.packWage)
    TextView packWage;
    @Bind(R.id.memo)
    TextView memo;


    @Bind(R.id.panel_conceptus)
    TextView panel_conceptus;
    @Bind(R.id.panel_assemble)
    TextView panel_assemble;
    @Bind(R.id.panel_paint)
    TextView panel_paint;
    @Bind(R.id.panel_pack)
    TextView panel_pack;

    @Bind(R.id.panel_material_wage)
    View panel_material_wage;

    @Bind(R.id.segment_material)
    View segment_material;

    @Bind(R.id.segment_wage)
    View segment_wage;

    //四合一控件 用作选中状态控制
    @Bind({R.id.panel_conceptus,R.id.panel_assemble,R.id.panel_paint,R.id.panel_pack})
    View[] panels;
    //二合一 材料工资  选中状态控制
    @Bind({R.id.segment_material,R.id.segment_wage})
    View[] materialWage;
    @Bind(R.id.product_item_list)
    ExpandableHeightListView listView;


    @Bind(R.id.showMore)
    View showMore;


    ItemListAdapter adapter;



    //表格模型 对应的数据结构
    private TableData productMaterialTableData;
     private TableData productWageTableData;
    private TableData productPaintTableData;
     private TableData productPackMaterialTableData;
    private ProductDetailPresenter productDetailPresenter;


    public ProductDetailViewerImpl(Context context) {
        super(context);


        productMaterialTableData = TableData.resolveData(context, R.array.table_head_product_material_item);
         productWageTableData=TableData.resolveData(context,R.array.table_head_product_wage_item);
        productPaintTableData=TableData.resolveData(context,R.array.table_head_product_paint_item);
        productPackMaterialTableData=TableData.resolveData(context,R.array.table_head_product_pack_material_item);
        adapter = new ItemListAdapter(context);
    }


    @Override
    public void onCreateView(View v) {
        super.onCreateView(v);



        panel_paint.setOnClickListener(this);
        panel_pack.setOnClickListener(this);
        panel_conceptus.setOnClickListener(this);
        panel_assemble.setOnClickListener(this);

        segment_material.setOnClickListener(this);

        segment_wage.setOnClickListener(this);
        showMore.setOnClickListener(this);

        listView.setAdapter(adapter);
        listView.setExpanded(true);
        adapter.setRowHeight(40);
        photo.setOnClickListener(this);

    }

    @Override
    public void bindData(ProductDetail productDetail) {



        final Product product = productDetail.product;
        String url = product.url;
        ImageLoader.getInstance().displayImage(HttpUrl.completeUrl(url), photo);
        photo.setTag(url);

        name.setText(product.name);
        pversion.setText(product.pVersion);
        unit.setText(product.pUnitName);

        pClass.setText(product.pClassName);
        pack.setText(product.pack.getName());
        weight.setText(String.valueOf(product.weight));
        factory.setText(String.valueOf(product.factoryName));
        specCm.setText(String.valueOf(product.specCm));
        String packString = product.insideBoxQuantity + "/" + product.packQuantity + "/" + product.packLong + "*" + product.packWidth + "*" + product.packHeight;
        packSize.setText(String.valueOf(packString));
        fob.setText(String.valueOf(product.fob));
        price.setText(String.valueOf(product.price));
        cost.setText(String.valueOf(product.cost));


        //白胚组装油漆包装成本工资

        conceptusCost.setText(addUnderLineStringForTextView(String.valueOf(product.conceptusCost)));
        conceptusWage.setText(addUnderLineStringForTextView(String.valueOf(product.conceptusWage)));
        assembleCost.setText(addUnderLineStringForTextView(String.valueOf(product.assembleCost)));
        assembleWage.setText(addUnderLineStringForTextView(String.valueOf(product.assembleWage)));
        paintCost.setText(addUnderLineStringForTextView(String.valueOf(product.paintCost)));
        paintWage.setText(addUnderLineStringForTextView(String.valueOf(product.paintWage)));
        packCost.setText(addUnderLineStringForTextView(String.valueOf(product.packCost)));
        packWage.setText(addUnderLineStringForTextView(String.valueOf(product.packWage)));

        memo.setText(String.valueOf(product.memo));
        memo.setMaxLines(MAX_MEMO_ROW_LINE);
        showMore.setVisibility(View.VISIBLE);
        memo.post(new Runnable() {
            @Override
            public void run() {


                int count = MAX_MEMO_ROW_LINE;
                boolean more = false;


                if (memo.getLineCount() > MAX_MEMO_ROW_LINE) {

                    int width = memo.getLayout().getLineEnd(count - 1);

                    more = product.memo.length() > width;


                }


                showMore.setVisibility(more ? View.VISIBLE : View.GONE);


            }
        });




    }

    @Override
    public void setPresenter(ProductDetailPresenter productDetailPresenter) {

        this.productDetailPresenter = productDetailPresenter;
    }

    @Override
    public void showConceptusMaterial(ProductDetail productDetail) {

        setPanelSelected(panel_conceptus);
        setMaterialWageSelected(segment_material);
        panel_material_wage.setVisibility(View.VISIBLE);
        adapter.setTableData(productMaterialTableData);
        setData(productDetail.conceptusMaterials);
        listView.setAdapter(adapter);
    }

    @Override
    public void showConceptusWage(ProductDetail productDetail) {
        setPanelSelected(panel_conceptus);
        setMaterialWageSelected(segment_wage);
        panel_material_wage.setVisibility(View.VISIBLE);
        adapter.setTableData(productWageTableData);
        setData(productDetail.conceptusWages);
        listView.setAdapter(adapter);
    }

    @Override
    public void showAssembleMaterial(ProductDetail productDetail) {


        setPanelSelected(panel_assemble);
        setMaterialWageSelected(segment_material);
        panel_material_wage.setVisibility(View.VISIBLE);

        adapter.setTableData(productMaterialTableData);
        setData(productDetail.assembleMaterials);
        listView.setAdapter(adapter);

    }

    @Override
    public void showAssembleWage(ProductDetail productDetail) {
        setPanelSelected(panel_assemble);
        setMaterialWageSelected(segment_wage);
        panel_material_wage.setVisibility(View.VISIBLE);


        adapter.setTableData(productWageTableData);
        setData(productDetail.assembleWages);
        listView.setAdapter(adapter);
    }

    @Override
    public void showPaintMaterialWage(ProductDetail productDetail) {
        setPanelSelected(panel_paint);
        panel_material_wage.setVisibility(View.GONE);

        adapter.setTableData(productPaintTableData);
        setData(productDetail.paints);
        listView.setAdapter(adapter);
    }

    @Override
    public void showPackMaterial(ProductDetail productDetail) {

        setPanelSelected(panel_pack);
        setMaterialWageSelected(segment_material);
        panel_material_wage.setVisibility(View.VISIBLE);

        adapter.setTableData(productPackMaterialTableData);
        setData(productDetail.packMaterials);
        listView.setAdapter(adapter);
    }

    @Override
    public void showPackWage(ProductDetail productDetail) {
        setPanelSelected(panel_pack);
        setMaterialWageSelected(segment_wage);
        panel_material_wage.setVisibility(View.VISIBLE);

        adapter.setTableData(productWageTableData);
        setData(productDetail.packWages);
        listView.setAdapter(adapter);
    }

    List<Object> objects = new ArrayList<>(100);

    private void setData(List<?> datas) {

        objects.clear();
        for (Object object
                : datas
                ) {
            objects.add(object);
        }

        adapter.setDataArray(objects);
    }



    private CharSequence addUnderLineStringForTextView(String underLine) {
        return Html.fromHtml("<u>" + underLine + "</u>");

    }

    @Override
    public void showWaiting() {

        loading.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideWaiting() {
        if(loading!=null)
        loading.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (v.getId()) {


            case R.id.showMore:
                memo.setMaxLines(Integer.MAX_VALUE);
                showMore.setVisibility(View.GONE);

                break;

            case R.id.panel_conceptus:
            case R.id.panel_assemble:
            case R.id.panel_pack:
            case R.id.panel_paint: {
                //找出点击index
                int index=-1;
                for (int i = 0; i < panels.length; i++) {
                    if(v==panels[i])
                    {
                        index=i;
                        break;
                    }
                }
                if(index!=-1)
                {
                    productDetailPresenter.onPanelForClick(index);
                }


            }
                break;

            case R.id.segment_material:

            case R.id.segment_wage:

                    productDetailPresenter.onMaterialWageClick(id==R.id.segment_material?0:1);


                break;
            case R.id.photo:

                String url= (String) v.getTag();
                if(StringUtils.isEmpty(url)) return ;
                ImageViewerHelper.view(context,url);


                break;
        }
    }

    /**
     * 设置面板选中
     * @param v
     */
    private void setPanelSelected(View v)
    {
        for(View view:panels)
        {

            view.setSelected(v==view);
        }
    }

    private void setMaterialWageSelected(View v)
    {
        for(View view:materialWage)
        {
            view.setSelected(v==view);
        }
    }
}

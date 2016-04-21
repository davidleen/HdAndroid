package com.giants3.hd.android.ViewImpl;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.adapter.ProductItemAdapter;
import com.giants3.hd.android.viewer.ProductDetailViewer;
import com.giants3.hd.android.widget.ExpandableGridView;
import com.giants3.hd.data.net.HttpUrl;
import com.giants3.hd.utils.entity.Product;
import com.giants3.hd.utils.entity.ProductDetail;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import butterknife.Bind;

public class ProductDetailViewerImpl extends BaseViewerImpl implements ProductDetailViewer {


    @Bind(R.id.photo)
    ImageView photo;

    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.loading)
    View loading;
    @Bind(R.id.gridView)
    ExpandableGridView gridView;

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
    ProductItemAdapter adapter;




    public ProductDetailViewerImpl(Context context) {
        super(context);
        adapter = new ProductItemAdapter(context);
    }


    @Override
    public void onCreateView(View v) {
        super.onCreateView(v);

        gridView.setAdapter(adapter        );

    }

    @Override
    public void bindData(ProductDetail productDetail) {

        Product product=productDetail.product;
        String url = product.url;
        ImageLoader.getInstance().displayImage(HttpUrl.completeUrl(url), photo);

        textView.setText(product.name);

        name.setText(product.name);
        pversion.setText(product.pVersion);
        unit.setText(product.pUnitName);

        pClass.setText(product.pClassName);
        pack.setText(product.pack.getName());
        weight.setText(String.valueOf(product.weight));
        factory.setText(String.valueOf(product.factoryName));
        specCm.setText(String.valueOf(product.specCm));
        String packString=product.insideBoxQuantity+"/"+product.packQuantity+"/"+product.packLong+"*"+product.packWidth+"*"+product.packHeight;
        packSize.setText(String.valueOf(packString));
        fob.setText(String.valueOf(product.fob));
        price.setText(String.valueOf(product.price));
        cost.setText(String.valueOf(product.cost));



    }

    @Override
    public void showWaiting() {

        loading.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideWaiting() {
        loading.setVisibility(View.GONE);

    }
}

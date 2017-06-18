package ir.rezabidar.onlineorder;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.rezabidar.onlineorder.adapter.ProductAdapter;
import ir.rezabidar.onlineorder.dummydata.ProductDummy;
import ir.rezabidar.onlineorder.models.ProductModel;

public class ProductListActivity extends AppCompatActivity implements FireMissilesDialogFragment.DialogCallback {
    List<ProductModel> products ;
    ListView lv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        lv = (ListView) findViewById(R.id.listView) ;
        products = ProductDummy.getList() ;

        ProductAdapter adapter = new ProductAdapter(this, (ArrayList<ProductModel>) products);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                confirmFireMissiles(products.get(position));
            }
        });
    }

    public void confirmFireMissiles(ProductModel product) {
        Bundle arg = new Bundle();
        arg.putString("pic",product.getPic());
        DialogFragment newFragment = new FireMissilesDialogFragment();
        newFragment.setArguments(arg);
        newFragment.show(getSupportFragmentManager(), "missiles");
    }

    @Override
    public void fireMissilesCallback(int count) {
        Toast.makeText(this,"alaki masalan " + count + " be sabad ezafe shod ..." , Toast.LENGTH_SHORT).show();
    }
}

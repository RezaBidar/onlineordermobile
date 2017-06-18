package ir.rezabidar.onlineorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ir.rezabidar.onlineorder.adapter.ProductAdapter;
import ir.rezabidar.onlineorder.dummydata.ProductDummy;
import ir.rezabidar.onlineorder.models.ProductModel;

public class ContactUsActivity extends AppCompatActivity {
    List<ProductModel> products ;
    ListView lv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        lv = (ListView) findViewById(R.id.listView) ;
        products = ProductDummy.getList() ;

        ProductAdapter adapter = new ProductAdapter(this, (ArrayList<ProductModel>) products);
        lv.setAdapter(adapter);




    }
}

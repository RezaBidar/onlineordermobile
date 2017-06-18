package ir.rezabidar.onlineorder;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ir.rezabidar.onlineorder.Auth.Auth;
import ir.rezabidar.onlineorder.adapter.ProductAdapter;
import ir.rezabidar.onlineorder.datastore.ShoppingCart;
import ir.rezabidar.onlineorder.lib.Constants;
import ir.rezabidar.onlineorder.lib.HttpManager;
import ir.rezabidar.onlineorder.models.ProductModel;

public class BasketActivity extends AppCompatActivity {
    protected int mCompanyId = 1 ;
    protected ListView lv;
    protected TextView emptyTv ;
    private ArrayList<ProductModel> mAllProducts;
    private ArrayList<ProductModel> mProducts;
    private Button mBuyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        Intent intent = getIntent();
        mCompanyId = intent.getIntExtra("companyId" , 0);
        mAllProducts = intent.getParcelableArrayListExtra("products");
        mProducts = (ArrayList<ProductModel>) ShoppingCart.getProducts(this,mCompanyId,mAllProducts);

//        Map<Integer,List<ProductModel>> productsMap ;
//        List<ProductModel> products = new ArrayList<>();

        lv = (ListView) findViewById(android.R.id.list) ;
        emptyTv = (TextView) findViewById(android.R.id.empty) ;
        ProductAdapter adapter = new ProductAdapter(this, (ArrayList<ProductModel>) mProducts);
        lv.setAdapter(adapter);
        lv.setItemsCanFocus(true);
        lv.setEmptyView(emptyTv);

        mBuyBtn = (Button) findViewById(R.id.buyBtn);
        mBuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyTask task = new BuyTask();
                task.execute();
            }
        });
        if(mProducts.isEmpty())
            mBuyBtn.setEnabled(false);

    }

    class BuyTask extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mBuyBtn.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String uri = Constants.URL_ADD_ORDER + "?products=" + ShoppingCart.getJson(BasketActivity.this,mCompanyId)
                    + "&api=" + Auth.api();
            return HttpManager.getDate( uri , BasketActivity.this);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mBuyBtn.setEnabled(true);

//            ShoppingCart.clearAll(BasketActivity.this);
            Toast.makeText(BasketActivity.this , s , Toast.LENGTH_LONG).show();
        }
    }
}

package ir.rezabidar.onlineorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mBtOrderList ;
    Button mBtProductCategory ;
    Button mBtFactorList ;
    Button mBtBasket ;
    Button mBtContactUs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtOrderList = (Button) findViewById(R.id.btOrderList);
        mBtProductCategory = (Button) findViewById(R.id.btProductCategory);
        mBtFactorList = (Button) findViewById(R.id.btFactorList);
        mBtBasket = (Button) findViewById(R.id.btBasket);
        mBtContactUs = (Button) findViewById(R.id.btContactUs);

        mBtOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrderListActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        mBtProductCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProductListTab.class);
                MainActivity.this.startActivity(intent);
            }
        });

        mBtFactorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FactorListActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        mBtBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BasketActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        mBtContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactUsActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}

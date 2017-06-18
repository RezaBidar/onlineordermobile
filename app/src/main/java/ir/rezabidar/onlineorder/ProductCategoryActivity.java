package ir.rezabidar.onlineorder;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProductCategoryActivity extends AppCompatActivity{
    Button button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductCategoryActivity.this, ProductListActivity.class);
                ProductCategoryActivity.this.startActivity(intent);
            }
        });

//        confirmFireMissiles();

    }

//    public void confirmFireMissiles() {
//        DialogFragment newFragment = new FireMissilesDialogFragment();
//        newFragment.show(getSupportFragmentManager(), "missiles");
//    }
//
//
//    @Override
//    public void fireMissilesCallback(int count) {
//        Toast.makeText(this, " " + count , Toast.LENGTH_LONG).show();
//    }
}


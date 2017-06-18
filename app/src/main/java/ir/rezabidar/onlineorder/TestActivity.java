package ir.rezabidar.onlineorder;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import ir.rezabidar.onlineorder.lib.HttpManager;
import ir.rezabidar.onlineorder.models.ProductModel;
import ir.rezabidar.onlineorder.parser.ProductJsonParser;

public class TestActivity extends AppCompatActivity {
    TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textView = (TextView) findViewById(R.id.textView);

        GetDataTask task = new GetDataTask();
        task.execute();
    }



    public class GetDataTask extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... params) {
            // get from url
//            String data = HttpManager.getDate("http://10.0.2.2/laravel/onlineorder/public/products");
//            String data = HttpManager.getDate("http://google.com");
            //List<ProductModel> products = ProductJsonParser.parse(data);
            return  null;
//            String urlString = "http://google.com" ;
//            StringBuffer chaine = new StringBuffer();
//            try{
//                URL url = new URL(urlString);
//                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//                connection.setRequestProperty("User-Agent", "");
//                connection.setRequestMethod("POST");
//                connection.setDoInput(true);
//                connection.connect();
//
//                InputStream inputStream = connection.getInputStream();
//
//                BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
//                String line = "";
//                while ((line = rd.readLine()) != null) {
//                    chaine.append(line);
//                }
//
//            } catch (IOException e) {
//                // writing exception to log
//                e.printStackTrace();
//                Log.d("Reza" , e.getMessage());
//            }
//
//            return chaine.toString();

        }

        @Override
        protected void onPostExecute(String s) {
            action(s);
            Toast.makeText(TestActivity.this, "GOOGLE", Toast.LENGTH_LONG).show();
        }
    }

    private void action(String s) {
        textView.setText(s);
    }
}

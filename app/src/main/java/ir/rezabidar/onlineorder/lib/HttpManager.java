package ir.rezabidar.onlineorder.lib;

import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.util.Log;
import android.widget.Toast;

import com.novoda.merlin.MerlinsBeard;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import ir.rezabidar.onlineorder.datastore.ServerDataStore;

/**
 * Created by ReZaBiDaR on 7/27/2015.
 */
public class HttpManager {

    public static String getDate(String uri, Activity activity){
        MerlinsBeard merlinsBeard = MerlinsBeard.from(activity);
        if(merlinsBeard.isConnected()) {
            AndroidHttpClient client = AndroidHttpClient.newInstance("AndroidAgent");
            HttpGet request = new HttpGet(uri);
            HttpResponse response;

            try {
                response = client.execute(request);
                return EntityUtils.toString(response.getEntity());
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("reza", e.getMessage());
                return null;
            } finally {
                client.close();
            }
        }
//        else
//        {
//            Toast.makeText(activity, "Connect your internet plzzz...", Toast.LENGTH_SHORT).show();
//        }

        return null ;
    }

    public static String getProductsDate(String uri, Activity activity , int companyId){

        MerlinsBeard merlinsBeard = MerlinsBeard.from(activity);

        if(merlinsBeard.isConnected())
        {
            AndroidHttpClient client = AndroidHttpClient.newInstance("AndroidAgent");
            HttpGet request = new HttpGet(uri);
            HttpResponse response ;

            try{
                response = client.execute(request);
                ServerDataStore.saveProduct(activity, companyId, EntityUtils.toString(response.getEntity()));
                return EntityUtils.toString(response.getEntity());
            }catch(Exception e){
                e.printStackTrace();
                Log.d("reza", e.getMessage());
                return ServerDataStore.getProducts(activity,companyId);
            }finally {
                client.close();
            }
        }
//        else
//        {
//            Toast.makeText(activity, "Connect your internet plzzz...", Toast.LENGTH_SHORT).show();
//        }

        return ServerDataStore.getProducts(activity, companyId);
    }

    public static String getCompaniesData(String uri, Activity activity)
    {
        MerlinsBeard merlinsBeard = MerlinsBeard.from(activity);

        if(merlinsBeard.isConnected())
        {
            AndroidHttpClient client = AndroidHttpClient.newInstance("AndroidAgent");
            HttpGet request = new HttpGet(uri);
            HttpResponse response ;

            try{
                response = client.execute(request);
                ServerDataStore.saveCompanies(activity, EntityUtils.toString(response.getEntity()));
                return EntityUtils.toString(response.getEntity());
            }catch(Exception e){
                e.printStackTrace();
                Log.d("reza" , e.getMessage());
                return ServerDataStore.getCompanies(activity);
            }finally {
                client.close();
            }
        }
//        else
//        {
//            Toast.makeText(activity, "Connect your internet plzzz...", Toast.LENGTH_SHORT).show();
//        }

        return ServerDataStore.getCompanies(activity);
    }
}

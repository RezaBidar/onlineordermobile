package ir.rezabidar.onlineorder.datastore;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by ReZaBiDaR on 7/20/2016.
 */
public class ServerDataStore {

    private static final String PREFERENCE_NAME = "serverdatastore";

    public static void saveProduct(Activity activity, int companyId, String data){
        SharedPreferences store = activity.getSharedPreferences(PREFERENCE_NAME + companyId , activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = store.edit();
        editor.putString("products" , data);
        editor.commit();
    }

    public static String getProducts(Activity activity, int companyId)
    {
        SharedPreferences store = activity.getSharedPreferences(PREFERENCE_NAME + companyId , activity.MODE_PRIVATE);
        return store.getString("products", null);
    }

    public static void saveCompanies(Activity activity, String data){
        SharedPreferences store = activity.getSharedPreferences(PREFERENCE_NAME + "_co" , activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = store.edit();
        editor.putString("companies" , data);
        editor.commit();
    }

    public static String getCompanies(Activity activity)
    {
        SharedPreferences store = activity.getSharedPreferences(PREFERENCE_NAME + "_co" , activity.MODE_PRIVATE);
        return store.getString("companies", null);
    }
}

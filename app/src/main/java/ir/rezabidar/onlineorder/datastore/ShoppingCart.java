package ir.rezabidar.onlineorder.datastore;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ir.rezabidar.onlineorder.models.ProductModel;

/**
 * Created by ReZaBiDaR on 7/6/2016.
 */
public class ShoppingCart {

    public static final String PREFERENCE_NAME = "basket" ;

    public static Map<String, ?> getAll(Activity activity, int CompanyId)
    {
        SharedPreferences basket = activity.getSharedPreferences(PREFERENCE_NAME + CompanyId , activity.MODE_PRIVATE);
        return basket.getAll();
    }

    public static boolean addToBasket(Activity activity, int id , int CompanyId)
    {
        return addToBasket(activity, id, 1, CompanyId);
    }

    public static boolean addToBasket(Activity activity, int id , int count, int CompanyId)
    {
        SharedPreferences basket = activity.getSharedPreferences(PREFERENCE_NAME + CompanyId , activity.MODE_PRIVATE);

        int newValue = count ;
        newValue += basket.getInt( Integer.toString(id) , 0) ;

        SharedPreferences.Editor editor = basket.edit();
        editor.putInt(Integer.toString(id) , newValue);
        editor.commit();

        return true ;
    }


    public static boolean removeFromBasket(Activity activity, int id, int CompanyId)
    {
        return removeFromBasket(activity, id, 1, CompanyId);
    }

    public static boolean removeFromBasket(Activity activity, int id , int count, int CompanyId)
    {
        SharedPreferences basket = activity.getSharedPreferences(PREFERENCE_NAME + CompanyId , activity.MODE_PRIVATE);

        int newValue = basket.getInt( Integer.toString(id) , 0) ;
        newValue -= count ;

        SharedPreferences.Editor editor = basket.edit();

        if(newValue <= 0)
            editor.remove(Integer.toString(id));
        else
            editor.putInt(Integer.toString(id) , newValue);

        editor.commit();
        return true ;
    }

    public static boolean isInBasket(Activity activity, int id, int CompanyId)
    {
        SharedPreferences basket = activity.getSharedPreferences(PREFERENCE_NAME + CompanyId , activity.MODE_PRIVATE);
        int val = basket.getInt(Integer.toString(id), 0) ;

        if(val > 0)
            return true ;

        return false ;
    }

    public static int getCount(Activity activity , int id, int CompanyId)
    {
        SharedPreferences basket = activity.getSharedPreferences(PREFERENCE_NAME + CompanyId , activity.MODE_PRIVATE);
        return basket.getInt( Integer.toString(id) , 0) ;
    }


    public static void clearAll(Activity activity , int id, int CompanyId)
    {
        SharedPreferences basket = activity.getSharedPreferences(PREFERENCE_NAME + CompanyId , activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = basket.edit();
        editor.clear();
        editor.commit();
    }


    public static int getSize(Activity activity, int CompanyId)
    {
        SharedPreferences basket = activity.getSharedPreferences(PREFERENCE_NAME + CompanyId , activity.MODE_PRIVATE);
        Map<String , ?> all = basket.getAll();
        return all.size();
    }


    public static List<ProductModel> getProducts(Activity activity, int companyId, List<ProductModel> allProducts)
    {
        int id ;
        Map<String , ?> productsMap = ShoppingCart.getAll(activity, companyId);
        List<ProductModel> products = new ArrayList<>();

        for(Map.Entry<String, ?> entry : productsMap.entrySet())
        {
            id = Integer.parseInt(entry.getKey());
            for(ProductModel product : allProducts)
            {
                if(product.getId() == id){
                    products.add(product);
                    break;
                }
            }
        }

        return products;
    }

    public static String getJson(Activity activity, int companyId)
    {
        int id,count ;
        Map<String , Integer> productsMap = (Map<String, Integer>) ShoppingCart.getAll(activity, companyId);

        JSONArray json = new JSONArray();

        for(Map.Entry<String, Integer> entry : productsMap.entrySet())
        {
            id = Integer.parseInt(entry.getKey());
            count = entry.getValue();
            JSONArray p = new JSONArray();
            p.put(id);
            p.put(count);

            json.put(p);
        }

        return json.toString();
    }


}

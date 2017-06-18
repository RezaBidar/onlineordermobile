package ir.rezabidar.onlineorder.parser;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ir.rezabidar.onlineorder.models.CategoryModel;
import ir.rezabidar.onlineorder.models.ProductModel;

/**
 * Created by ReZaBiDaR on 7/4/2016.
 */
public class ProductJsonParser {

    private static final String TAG_CATS = "cats";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_NAME = "name";
    private static final String TAG_PRICE = "price";
    private static final String TAG_UNIT = "unit";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_PIC = "pic";

    public static Map<Integer , List<ProductModel> > parse(String s) {
        Map<Integer , List<ProductModel> > map = new HashMap<>();

        String name = "nulll";

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONObject catsObject = jsonObject.getJSONObject(TAG_CATS);
            JSONObject productsObject = jsonObject.getJSONObject(TAG_PRODUCTS);
            JSONObject cats ;
            Iterator<String> catKeys = productsObject.keys();
            while(catKeys.hasNext())
            {
                List<ProductModel> products = new ArrayList<>();
                String catKey = catKeys.next();
                JSONObject productsJson = productsObject.getJSONObject(catKey);
                Iterator<String> pkeys = productsJson.keys();
                while(pkeys.hasNext())
                {
                    String pkey = pkeys.next();
                    ProductModel product = new ProductModel();
                    JSONObject productJson = productsJson.getJSONObject(pkey);
                    product.setCode(Integer.parseInt(pkey));
                    product.setName(productJson.getString(TAG_NAME));
                    product.setPrice(productJson.getInt(TAG_PRICE));
                    product.setPic(productJson.getString(TAG_PIC));
//                    product.setPic("pic1");


                    products.add(product);

                }

                map.put(Integer.parseInt(catKey) , products);

            }

            return map ;

        } catch (JSONException e) {
            Log.d("reza" , e.getMessage());
            e.printStackTrace();
        }

        return map ;
    }


    public static List<CategoryModel> parseCats(String s){
        List<CategoryModel> cats = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONObject catsObject = jsonObject.getJSONObject(TAG_CATS);
            Iterator<String> keys = catsObject.keys();
            while(keys.hasNext())
            {
                String key = keys.next();
                CategoryModel cat = new CategoryModel();
                cat.setName(catsObject.getString(key));
                cat.setId(Integer.parseInt(key));
                cats.add(cat);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cats ;
    }

}

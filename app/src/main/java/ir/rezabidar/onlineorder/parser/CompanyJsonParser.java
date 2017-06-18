package ir.rezabidar.onlineorder.parser;

import android.content.ComponentName;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ir.rezabidar.onlineorder.models.CategoryModel;
import ir.rezabidar.onlineorder.models.CompanyModel;
import ir.rezabidar.onlineorder.models.ProductModel;

/**
 * Created by ReZaBiDaR on 7/28/2016.
 */
public class CompanyJsonParser {

    private static final String TAG_COMPANY_ID = "ci";
    private static final String TAG_COMPANY_NAME = "cn";
    private static final String TAG_VISITOR_ID = "vi";
    private static final String TAG_VISITOR_NAME = "vn";

    public static List<CompanyModel> parse(String s) {

        List<CompanyModel> companies = new ArrayList<>();

        String name = "nulll";

        try {
            JSONArray jsonArray = new JSONArray(s);
            for (int i = 0 ; i < jsonArray.length() ; i++)
            {
                JSONObject object = jsonArray.getJSONObject(i);
                CompanyModel company = new CompanyModel();
                company.setCompanyId(object.getInt(TAG_COMPANY_ID));
                company.setCompanyName(object.getString(TAG_COMPANY_NAME));
                company.setVisitorId(object.getInt(TAG_VISITOR_ID));
                company.setVisitorName(object.getString(TAG_VISITOR_NAME));
                companies.add(company);
            }

            return companies ;

        } catch (JSONException e) {
            Log.d("reza", e.getMessage());
            e.printStackTrace();
        }

        return companies ;
    }

    public static CompanyModel singleParse(String data)
    {
        CompanyModel company = new CompanyModel();
        try {
                JSONObject object = new JSONObject(data);

                company.setCompanyId(object.getInt(TAG_COMPANY_ID));
                company.setCompanyName(object.getString(TAG_COMPANY_NAME));
                company.setVisitorId(object.getInt(TAG_VISITOR_ID));
                company.setVisitorName(object.getString(TAG_VISITOR_NAME));


            return company ;

        } catch (JSONException e) {
            Log.d("reza", e.getMessage());
            e.printStackTrace();
        }

        return company;
    }
}

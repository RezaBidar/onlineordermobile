package ir.rezabidar.onlineorder.dummydata;

import java.util.ArrayList;
import java.util.List;

import ir.rezabidar.onlineorder.models.CompanyModel;

/**
 * Created by ReZaBiDaR on 7/21/2016.
 */
public class CompanyDummy {

    public static List<CompanyModel> getList(){
        List<CompanyModel> companies = new ArrayList<>();
        companies.add(new CompanyModel(10 , "پخش محمدی" , 18 , "علی مرزبان"));
        companies.add(new CompanyModel(11 , "پخش محمدی" , 10 , "علی مرزبان"));
        companies.add(new CompanyModel(12 , "پخش محمدی" , 8 , "علی مرزبان"));
        companies.add(new CompanyModel(13 , "پخش محمدی" , 5 , "علی مرزبان"));
        return companies;
    }
}

package ir.rezabidar.onlineorder.dummydata;

import java.util.ArrayList;
import java.util.List;

import ir.rezabidar.onlineorder.models.ProductModel;

/**
 * Created by ReZaBiDaR on 3/23/2016.
 */
public class ProductDummy {

    public static List<ProductModel> getList(){
        List<ProductModel> products = new ArrayList<>() ;

        products.add(new ProductModel("چیپس چیتوز",3001 , 21 , "pic1")) ;
        products.add(new ProductModel("عینک ریبون",3002 , 22 , "pic2")) ;
        products.add(new ProductModel("ساعت اپل سفید",3003 , 23 , "pic3")) ;
        products.add(new ProductModel("ادکلن جواد",3004 , 24 , "pic4")) ;
        products.add(new ProductModel("ساعت اپل قرمز",3005 , 25 , "pic5")) ;
        products.add(new ProductModel("برنج محسن",3006 , 26 , "pic6")) ;
        products.add(new ProductModel("سون آپ",3007 , 27 , "pic7")) ;
        products.add(new ProductModel("قابلمه ی منصور",3008 , 28 , "pic8")) ;
        products.add(new ProductModel("شکر یک و یک",3009 , 29 , "pic9")) ;
        products.add(new ProductModel("ادکلن سوسن",3010 , 30 , "pic10")) ;

        return products ;
    }
}

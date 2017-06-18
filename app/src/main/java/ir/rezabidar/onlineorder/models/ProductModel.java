package ir.rezabidar.onlineorder.models;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ReZaBiDaR on 3/21/2016.
 */
public class ProductModel implements Parcelable {
    private String name ;
    private int price ;
    private int code ;
    private String pic ;

    public ProductModel() {
    }


    public ProductModel(String name, int price, int code, String pic) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.pic = pic;
    }

    public ProductModel(Bundle bundle) {
        this.name = bundle.getString("name");
        this.price = bundle.getInt("price");
        this.code = bundle.getInt("code");
        this.pic = bundle.getString("pic");
    }

    protected ProductModel(Parcel in) {
        name = in.readString();
        price = in.readInt();
        code = in.readInt();
        pic = in.readString();
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };

    public int getId() {
        return code;
    }

    public void setId(int id) {
        this.code = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle() ;
        bundle.putString("name" , this.name);
        bundle.putString("pic" , this.pic);
        bundle.putInt("price" , this.price);
        bundle.putInt("code" , this.code);
        return bundle ;
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeInt(code);
        dest.writeString(pic);
    }
}

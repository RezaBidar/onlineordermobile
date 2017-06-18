package ir.rezabidar.onlineorder.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ir.rezabidar.onlineorder.ProductShowTab;
import ir.rezabidar.onlineorder.R;
import ir.rezabidar.onlineorder.datastore.ShoppingCart;
import ir.rezabidar.onlineorder.lib.Constants;
import ir.rezabidar.onlineorder.models.ProductModel;

/**
 * Created by ReZaBiDaR on 3/24/2016.
 */
public class ProductAdapter extends ArrayAdapter<ProductModel> {
    Activity activity ;
    ArrayList<ProductModel> products ;
    private DisplayImageOptions options;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    protected int mCompanyId = 1;



    public ProductAdapter(Context context, ArrayList<ProductModel> products) {
        super(context, -1, products);
        this.activity = (Activity) context ;
        this.products = products ;

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
//                .displayer(new CircleBitmapDisplayer(Color.WHITE, 5))
                .build();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView ;
        final ViewHolder holder ;

        LayoutInflater inflater = activity.getLayoutInflater() ;

        if(convertView == null){
            holder = new ViewHolder() ;
            view = inflater.inflate(R.layout.product_item_row3 , null) ;

            holder.img = (ImageButton) view.findViewById(R.id.list_image);
            holder.tvTitle = (TextView) view.findViewById(R.id.from_name);
            holder.tvPrice = (TextView) view.findViewById(R.id.plist_price_text);
            holder.plus = (ImageButton) view.findViewById(R.id.cart_plus_img) ;
            holder.minus = (ImageButton) view.findViewById(R.id.cart_minus_img) ;
            holder.counter = (TextView) view.findViewById(R.id.cart_product_quantity_tv) ;
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        final int id = products.get(position).getId() ;
        holder.counter.setText(Integer.toString(ShoppingCart.getCount(activity, id,mCompanyId) ));

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c = Integer.parseInt(holder.counter.getText().toString()) ;
                ShoppingCart.addToBasket(activity, id,mCompanyId);
                holder.counter.setText(Integer.toString(ShoppingCart.getCount(activity, id,mCompanyId)));
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c = Integer.parseInt(holder.counter.getText().toString()) ;
                if( c > 0)
                {
                    ShoppingCart.removeFromBasket(activity , id,mCompanyId);
                    holder.counter.setText(Integer.toString(ShoppingCart.getCount(activity, id,mCompanyId) ));

                }
            }
        });

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext() , ProductShowTab.class);
                intent.putParcelableArrayListExtra("products" , products);
                intent.putExtra("position" , position);
                getContext().startActivity(intent);
//                products.remove(position);
//                ProductAdapter.this.notifyDataSetChanged();
            }
        });


        String imgName = products.get(position).getPic();
        String imgUrl = Constants.URL_PRODUCT_ICON + imgName ;
//        String imgUrl = "http://192.168.1.2/laravel/onlineorder/public/pic/products/thumbs/" + imgName ;
        Log.d("reza", imgUrl);
        if(imgName != "null")
            ImageLoader.getInstance().displayImage(imgUrl, holder.img, options, animateFirstListener);
        else
            holder.img.setImageResource(R.drawable.ic_empty);

        //        String mDrawableName = products.get(position).getPic() ;
//        int resId = activity.getResources().getIdentifier(mDrawableName , "drawable", activity.getPackageName()) ;
//        holder.img.setImageResource(resId);


        holder.tvTitle.setText(products.get(position).getName());
        holder.tvPrice.setText(activity.getString(R.string.price, products.get(position).getPrice()));
        return view ;
    }

    static class ViewHolder{
        ImageButton img ;
        TextView tvTitle ;
        TextView tvPrice ;
        ImageButton plus ;
        ImageButton minus ;
        TextView counter ;
    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}

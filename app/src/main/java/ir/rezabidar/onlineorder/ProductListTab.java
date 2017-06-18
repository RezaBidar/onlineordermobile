package ir.rezabidar.onlineorder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ir.rezabidar.onlineorder.adapter.ProductAdapter;
import ir.rezabidar.onlineorder.lib.Constants;
import ir.rezabidar.onlineorder.lib.HttpManager;
import ir.rezabidar.onlineorder.datastore.ShoppingCart;
import ir.rezabidar.onlineorder.models.CategoryModel;
import ir.rezabidar.onlineorder.models.ProductModel;
import ir.rezabidar.onlineorder.parser.ProductJsonParser;

public class ProductListTab extends AppCompatActivity {
    public FloatingActionButton fab ;
    protected Map<Integer , List<ProductModel> > products; // catmodel < catid , products>
    protected List<CategoryModel> cats ;
    protected ProgressDialog progressDialog;
    protected int mCompanyId = 1;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_tab);

        progressDialog = new ProgressDialog(ProductListTab.this,R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        products = new HashMap<>();



        GetDataTask task = new GetDataTask();
        task.execute();

        Button basketBtn = (Button) findViewById(R.id.basketButton);
        basketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProductListTab.this , BasketActivity.class);
                intent.putParcelableArrayListExtra("products" , getProductList());
                intent.putExtra("companyId" , mCompanyId);
                ProductListTab.this.startActivity(intent);
            }
        });

    }

//    public ArrayList<ProductModel> getProductList()
//    {
//        ArrayList<ProductModel> allProducts = new ArrayList<>();
//        for(Map.Entry<Integer , List<ProductModel> > entry : products.entrySet())
//        {
//            for(ProductModel product : entry.getValue())
//            {
//                allProducts.add(product);
//            }
//        }
//
//        return allProducts;
//    }

    public void action(){
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(mViewPager);

        progressDialog.dismiss();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_list_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(ProductListTab.this , ScoreActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            int catId = cats.get(position).getId();
            return PlaceholderFragment.newInstance(position , products.get(catId));
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return cats.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "غلات";
//                case 1:
//                    return "شوینده جات";
//                case 2:
//                    return "بسته بندی جات";
//                case 3:
//                    return "یه چیز دیگه جات";
//                case 4:
//                    return "سس جات";
//            }
//            return null;
            return cats.get(position).getName();
        }

    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        public List<ProductModel> products;
        private ListView lv;
        private TextView emptyTv;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, List<ProductModel> products) {
            PlaceholderFragment fragment = new PlaceholderFragment();

            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            fragment.products = products;
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_product_list_tab, container , false);
            //products = ProductDummy.getList() ;
            if(products != null && products.size() > 0)
            {
                lv = (ListView) rootView.findViewById(android.R.id.list) ;
                emptyTv = (TextView) rootView.findViewById(android.R.id.empty) ;

                final ProductAdapter adapter = new ProductAdapter(getContext(), (ArrayList<ProductModel>) products);
                lv.setAdapter(adapter);
                lv.setItemsCanFocus(true);
                lv.setEmptyView(emptyTv);
            }

            return rootView;
        }


    }

        public class GetDataTask extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... params) {
            // get from url
//            String data = HttpManager.getDate("http://10.0.2.2/laravel/onlineorder/public/products");
//            String data = HttpManager.getDate("http://google.com");
            String data = HttpManager.getProductsDate(Constants.URL_PRODUCTS, ProductListTab.this, mCompanyId);
            if(!data.isEmpty())
            {
                products = ProductJsonParser.parse(data);
                cats = ProductJsonParser.parseCats(data);
            }
            return  data;

        }

        @Override
         protected void onPostExecute(String s) {
            if(s.isEmpty())
                showError();
            else
                action();
//            Toast.makeText(ProductListTab.this , "GOOGLE" , Toast.LENGTH_LONG).show();

        }
    }

    private void showError() {
        Toast.makeText(ProductListTab.this , "Cant reach data" , Toast.LENGTH_SHORT).show();
    }

    public ArrayList<ProductModel> getProductList()
    {
        ArrayList<ProductModel> list = new ArrayList<>();
        for(List<ProductModel> ps : products.values() )
        {
            for(int i = 0 ; i < ps.size() ; i++)
            {
                list.add(ps.get(i));
            }
        }

        return list;
    }
}

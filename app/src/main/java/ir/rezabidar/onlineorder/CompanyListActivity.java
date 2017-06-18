package ir.rezabidar.onlineorder;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ir.rezabidar.onlineorder.dummydata.CompanyDummy;
import ir.rezabidar.onlineorder.fragment.AddCompanyDialogFragment;
import ir.rezabidar.onlineorder.lib.Constants;
import ir.rezabidar.onlineorder.lib.HttpManager;
import ir.rezabidar.onlineorder.models.CompanyModel;
import ir.rezabidar.onlineorder.parser.CompanyJsonParser;

public class CompanyListActivity extends AppCompatActivity implements AddCompanyDialogFragment.DialogCallback{

    private List<CompanyModel> mCompanies;
    ListView listView ;
    TextView emptyTv;
    private ArrayAdapter<CompanyModel> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                AddCompanyDialogFragment newFragment = new AddCompanyDialogFragment();
                newFragment.show(getSupportFragmentManager(), "missiles");
            }
        });


//        mCompanies = CompanyDummy.getList() ;
        mCompanies = new ArrayList<>();

        GetCompanyList getCompanyTask = new GetCompanyList();
        getCompanyTask.execute();

        emptyTv = (TextView) findViewById(android.R.id.empty);
        listView = (ListView) findViewById(android.R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CompanyListActivity.this, ProductListTab.class);
                intent.putExtra("companyId", mCompanies.get(position).getCompanyId());
                intent.putExtra("visitorId", mCompanies.get(position).getVisitorId());
                CompanyListActivity.this.startActivity(intent);
            }
        });

        listView.setEmptyView(emptyTv);



    }

    @Override
    public void addCompanyCallback(int visitorCode) {
        AddCompanyTask task = new AddCompanyTask();
        task.execute(visitorCode);
    }

    public class AddCompanyTask extends AsyncTask<Integer,String,String>{

        @Override
        protected String doInBackground(Integer... params) {
            //get data and parse data
            //if company added then add that to mCompnaies and notify adapter
            String data = HttpManager.getDate(Constants.URL_ADD_COMPNANY , CompanyListActivity.this);
            CompanyModel company = CompanyJsonParser.singleParse(data);
            mCompanies.add(company);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mAdapter.notifyDataSetChanged();
        }
    }

    public class GetCompanyList extends AsyncTask<Integer,String,String>{

        @Override
        protected String doInBackground(Integer... params) {
//            String data = HttpManager.getDate(Constants.URL_COMPNANIES + "?customer_id=" + params[0]);
            String data = HttpManager.getCompaniesData(Constants.URL_COMPNANIES, CompanyListActivity.this);
            if(!data.isEmpty())
                mCompanies = CompanyJsonParser.parse(data);


            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(!s.isEmpty())
                updateList();
            else
                showError();
        }
    }

    private void showError() {
        Toast.makeText(CompanyListActivity.this , "Cant reach data" , Toast.LENGTH_SHORT).show();
    }

    private void updateList() {
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , mCompanies );
        listView.setAdapter(mAdapter);
    }


}




package com.example.thenextepisode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    List<Show> showList = new ArrayList<Show>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListAdapter();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ApiHelper.getAPIKeyAndPutIntoSharedPreferences(getApplicationContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        setListAdapter();
    }

    public void setListAdapter() {
        showList.clear();
        initList();
        ListView listView = (ListView) findViewById(R.id.show_list);
        ShowAdapter simpleAdapter = new ShowAdapter(this, showList);
        listView.setAdapter(simpleAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addTvShow(View view) {
        Intent intent = new Intent(getApplicationContext(), AddTvShow.class);
        startActivity(intent);
    }

    private void initList(){
        AppDatabase db = AppDatabase.getAppDatabase(this);
        ShowDao dao = db.getShowDao();

        showList = dao.getAllShows();

        /*for(Show show : dao.getAllShows()) {
            showList.add(createEmployee("employees", show.getShowName()));
        }
        Log.d("size", String.valueOf(showList.size()));*/

    }

    private HashMap<String, String>createEmployee(String name,String number){
        HashMap<String, String> employeeNameNo = new HashMap<String, String>();
        employeeNameNo.put(name, number);
        return employeeNameNo;
    }
}

package com.example.android.lab12_spinner;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyDialogFragment.MyDialogInterface, AdapterView.OnItemClickListener{

    private final String TAG = "TAG-"+this.getClass().getSimpleName();

    private FloatingActionButton fab;
    private ListView mListView;
    private MyListAdapter mListAdapter;
    private ArrayList<Coffee> mCoffeeList = new ArrayList<>();
    private static final String FILENAME= "userCoffeeList.data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                DialogFragment dialog = new MyDialogFragment();
                dialog.show(getSupportFragmentManager(), "MyDialogFragment");

            }
        });

        restoreData();
        initListView();
    }

    private void initListView() {
        mListView = findViewById(R.id.listView);
        mListView.setEmptyView(findViewById(R.id.empty));
        mListAdapter = new MyListAdapter(this, mCoffeeList);
        mListView.setAdapter(mListAdapter);
        mListView.setOnItemClickListener(this);
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

    @Override
    public void onClickOk(Coffee coffee) {
        Snackbar.make(fab, "收到確定 coffee = "+coffee, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
        mCoffeeList.add(coffee);
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickCancel() {
        Snackbar.make(fab, "收到取消", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    //ListView用的
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Snackbar.make(fab, "點選了第"+i+"項", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    @Override
    protected void onPause() {
        saveData();
        super.onPause();
    }

    private void saveData() {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(mCoffeeList);
        }catch(IOException e){
            Log.d(TAG, e.toString());
            e.printStackTrace();
        }finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            }catch (IOException e){
                Log.d(TAG, e.toString());
                e.printStackTrace();
            }
        }
        Log.d(TAG,"after saveData");
    }

    private void restoreData() {
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = openFileInput(FILENAME);
            ois = new ObjectInputStream(fis);
            mCoffeeList = (ArrayList<Coffee>) ois.readObject();
        }catch(IOException | ClassNotFoundException e){
            Log.d(TAG, e.toString());
            e.printStackTrace();
        }finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            }catch (IOException e){
                Log.d(TAG, e.toString());
                e.printStackTrace();
            }
        }
        Log.d(TAG,"after restoreData");
    }
}

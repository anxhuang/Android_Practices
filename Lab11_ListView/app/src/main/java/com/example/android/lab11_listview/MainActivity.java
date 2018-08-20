package com.example.android.lab11_listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private  final int requestAddPokemon = 1;

    private ListView mainListView;
    private MainListAdapter mainAdapter;
    /*
    private String mItemID;
    private String mItemName;
    private  int mItemResID;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* 預設顯示底下的bar台 目前不用所以註解掉
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                */

                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, requestAddPokemon);

            }
        });

        initListView();
    }

    private void initListView() {
        mainAdapter = new MainListAdapter(this, List<Pokemon>);
        mainListView = findViewById(R.id.listview);
        mainListView.setEmptyView(findViewById(R.id.empty));
        mainListView.setAdapter(mainAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case requestAddPokemon:
                    /*
                    Log.d("TAG-MainActivity-Result", "mItemID:"+mItemID+" mItemName:"+mItemName+" mItemResId:"+mItemResID);
                    */
                    break;
                default:
                    break;
            }
        }
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
}

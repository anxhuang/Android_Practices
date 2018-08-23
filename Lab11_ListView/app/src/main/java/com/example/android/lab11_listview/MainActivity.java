package com.example.android.lab11_listview;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
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
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private final String TAG = "TAG-"+this.getClass().getSimpleName();
    private final String FILENAME = "userPokemanList.data";

    static final String BUNDLE_KEY_UPDATE_POKEMON = "com.example.android.lab11_listview.update_pokemon";
    static final String BUNDLE_KEY_LIST_STATE = "com.example.android.lab11_listview.list_state";

    private final int requestAddPokemon = 1;
    private final int requestUpdatePokemon = 2;

    private ListView mainListView;
    private MainListAdapter mainAdapter;
    private ArrayList<Pokemon> mlist = new ArrayList(); //這個List必須要可序列化才能放InstanceState 所以要用ArrayList宣告
    private Pokemon newPokemon;
    private int mItemIndex;

    //存放至檔案
    private  void saveData(){
        ObjectOutputStream oos = null; //要給null
        try {
            FileOutputStream fos = openFileOutput(FILENAME, MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(mlist); // !!!這個是後面開的所以要關這個 close()會連fos一起關
            //oos.close();
        }catch(IOException e){
            Log.d(TAG, e.toString());
            e.printStackTrace();
        }finally {
            try {
                oos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    //從檔案讀取
    private  void restoreData(){
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = openFileInput(FILENAME);
            ois = new ObjectInputStream(fis);
            mlist = (ArrayList) ois.readObject();
            //ois.close();
        }catch(IOException | ClassNotFoundException e){
            Log.d(TAG, e.toString());
            e.printStackTrace();
        }finally {
            try {
                ois.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

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

        //讀取onSaveInstanceState存放的狀態 寫在init之前
        if(savedInstanceState != null){
            mlist = (ArrayList) savedInstanceState.getSerializable(BUNDLE_KEY_LIST_STATE);
        }else{
            restoreData();
        }

        initListView();
    }

    private void initListView() {
        mainAdapter = new MainListAdapter(this, mlist);
        mainListView = findViewById(R.id.listview);
        mainListView.setEmptyView(findViewById(R.id.empty));
        mainListView.setAdapter(mainAdapter);
        mainListView.setOnItemClickListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case requestAddPokemon:
                    newPokemon = (Pokemon) data.getSerializableExtra(AddActivity.BUNDLE_KEY_ADD_POKEMON); //序列化的資料要用這個get
                    mlist.add(newPokemon);
                    mainAdapter.notifyDataSetChanged(); //通知ListView資料發生異動需要更新
                    Log.d(TAG," onActivityResult in AddPokemon");
                    break;
                case requestUpdatePokemon:
                    newPokemon = (Pokemon) data.getSerializableExtra(BUNDLE_KEY_UPDATE_POKEMON);
                    mlist.set(mItemIndex, newPokemon); //將list中的第mItemIndex項設定為更新後的Pokemon
                    mainAdapter.notifyDataSetChanged();
                    Log.d(TAG,"onActivityResult in UpdatePokemon");
                    break;
                default:
                    break;
            }
        }
    }

    //儲存目前狀態用onSaveInstanceState
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //要寫在super前面
        outState.putSerializable(BUNDLE_KEY_LIST_STATE, mlist);
        super.onSaveInstanceState(outState);
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

    //AdapterView.OnItemClickListener的抽象方法
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int index, long l) { //將i改為index 要設成final才讀得到
        mItemIndex = index;
        Log.d(TAG,"onItemClick 第"+index+"項被點選了");

        new AlertDialog.Builder(this)
                .setMessage("請選擇動作")
                .setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity.this, UpdateActivity.class); //匿名函數內呼叫this要前綴類別名稱
                        intent.putExtra(BUNDLE_KEY_UPDATE_POKEMON, mlist.get(index)); //用list.get(index)取得點選的Pokemon
                        startActivityForResult(intent,requestUpdatePokemon);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //do nothing
                    }
                })
                .setNeutralButton("刪除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mlist.remove(index); //index要設成final才讀得到
                        mainAdapter.notifyDataSetChanged();
                    }
                }).show();

    }
}

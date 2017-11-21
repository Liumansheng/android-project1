package com.example.lanya;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by vvxc on 2016/6/20.
 */
public class BTListActivity extends Activity{

    private BluetoothAdapter ba;
    private Set<BluetoothDevice> pairedDevices;
    private ListView lv;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bt);
        lv=(ListView)findViewById(R.id.list_view);
        button=(Button)findViewById(R.id.button);
        ba=BluetoothAdapter.getDefaultAdapter();
        
        if (!ba.isEnabled()){
            ba.enable();
        }

        getList();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getList();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }

    private void getList() {
        pairedDevices=ba.getBondedDevices();
        ArrayList<BluetoothDevice> btList=new ArrayList();
        for(BluetoothDevice bt : pairedDevices){
            btList.add(bt);

        }

//        final ArrayAdapter adapter = new ArrayAdapter
//                (this,android.R.layout.simple_list_item_1, list);
        MyAdapter adapter=new MyAdapter(BTListActivity.this,btList);
        lv.setAdapter(adapter);
    }

}

package com.example.lanya;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class MyAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<BluetoothDevice> data;
	 private Handler handler;

	 private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	public MyAdapter(Context context,ArrayList<BluetoothDevice> data) {
		// TODO Auto-generated constructor stub
		
		this.context=context;
		this.data=data;
		handler=new Handler();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(final int position,final View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View view=LayoutInflater.from(context).inflate(R.layout.item_list, null);
		
		TextView textView=(TextView)view.findViewById(R.id.item);
		textView.setText(data.get(position).getName()+"  "+data.get(position).getAddress());
		
		
		
	        view.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	 Toast.makeText(context, "1111", Toast.LENGTH_SHORT).show();
	                new Thread(){
	                    @Override
	                    public void run() {
	                    	
	                        try {
	                            handler.post(new Runnable() {
	                                @Override
	                                public void run() {

	                                    Toast.makeText(context, "正在连接", Toast.LENGTH_SHORT).show();
	                                }
	                            });
	                            BTSocket.bluetoothSocket=data.get(position).createRfcommSocketToServiceRecord(MY_UUID);
	                            BTSocket.connectBT();
								Intent intent =new Intent(context,Accept.class);
								context.startActivity(intent);
	                        } catch (IOException e) {
	                            handler.post(new Runnable() {
	                                @Override
	                                public void run() {

	                                    Toast.makeText(context, "连接异常", Toast.LENGTH_SHORT).show();
	                                }
	                            });
	                            e.printStackTrace();
	                        }


	                        super.run();
	                    }
	                }.start();

	            }
	        });
		
		return view;
	}

	

}

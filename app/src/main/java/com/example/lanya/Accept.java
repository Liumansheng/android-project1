package com.example.lanya;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Accept extends Activity {
	 
    BluetoothSocket socket = null;      
    public ConnectedThread thread = null;  
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private  Handler handler;
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accept);
        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        handler=new Handler();

        new ConnectedThread(BTSocket.bluetoothSocket).start();
        
    }
	
	private class ConnectedThread extends Thread {
		
		private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        //��������
        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
     
            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream(); //����������
                tmpOut = socket.getOutputStream();  //����������
            } catch (IOException e) { }
     
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }
     
        public void run() {
            final byte[] buffer=new byte[1];
            int bytes; // bytes returned from read()
            // Keep listening to the InputStream until an exception occurs
            while (true) {        	
                try {                	
                    // Read from the InputStream
                	 bytes = mmInStream.read(buffer); //bytes��������������buffer����������
                     // Send the obtained bytes to the UI activity
   //                 final String str=new String(buffer);
                    final int temp=byteToInt(buffer);
    //                final int finalBytes = bytes;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView1.setText((int)buffer[0]+"");
//                            textView2.setText((int)buffer[1]+"");
//                            textView3.setText((int)buffer[2]+"");
                        }
                    });

                    Thread.sleep(500);

                   
                } catch (Exception e) {
                	System.out.print("read error");
                    break;
                    
                }
            }
        }    
    }
	

    public static int byteToInt(byte[] b){
    	  return (((int)b[0]));
    }
  

    
    @Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
	}
    

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		
	}

}

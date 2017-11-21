package com.example.lanya;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.OutputStream;

public class BTSocket {
	public static BluetoothSocket bluetoothSocket;
    private static OutputStream outputStream;



    public static void connectBT() throws IOException {
            bluetoothSocket.connect();
    }

   

    public  static  void disConect() throws IOException {
        outputStream.close();
        bluetoothSocket.close();
    }

 

    }



package com.cyberz.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.serialport.SerialPort;
import android.util.Log;


import com.cyberz.orangepi3g4ggpio.OrangePiGpioControl.DigitalWrite;
import com.cyberz.orangepi3g4ggpio.OrangePiGpioControl.GPIO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    SerialPort serialPort;
    SendingThread mSendingThread;
    byte[] mBuffer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
           final DigitalWrite digitalWrite = new DigitalWrite();
            digitalWrite.Set(GPIO.GPIO_141_HIGH);
        } catch (Exception e) {
            e.printStackTrace();
        }

/*
       DigitalRead digitalRead =new DigitalRead(GPIO.GPIO_141,GPIO.EN, GPIO.PULLDOWN);
        /*digitalRead.getValueTheard(new GetValueInterface() {
            @Override
            public void runGetValue(int value) {

            }
        });*/


       // Log.d("TAG", "onCreate: "+digitalRead.getValue());


        /*
        SerialPort.setSuPath("/system/xbin/su");
        try {
            serialPort = new SerialPort(new File("/dev/ttyMT1"), 9600);

           // Toast.makeText()
           //serialPort.build();

// DigitalRead/write to serial port - needs to be in different thread!
     
            //serialPort.tryClose();

// close
          //  serialPort.tryClose();
        } catch (IOException e) {
            e.printStackTrace();
        }

       /* mBuffer = new byte[1024];
        Arrays.fill(mBuffer, (byte) 0x55);
        if (serialPort != null) {
            mSendingThread = new SendingThread();
            mSendingThread.start();
        }*/


    }

    private class SendingThread extends Thread {
        InputStream in = serialPort.getInputStream();
        OutputStream out = serialPort.getOutputStream();
        // out.write(10);
         
        @Override
        public void run() {
            OutputStream out = serialPort.getOutputStream();
            while (!isInterrupted()) {
                try {
                    if (out != null) {
                        out.write(mBuffer);
                        Log.e("TAG", "run: "+mBuffer );
                    } else {
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }
    
}

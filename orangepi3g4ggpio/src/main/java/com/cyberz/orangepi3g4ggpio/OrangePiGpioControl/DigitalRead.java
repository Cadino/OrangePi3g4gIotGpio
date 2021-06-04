package com.cyberz.orangepi3g4ggpio.OrangePiGpioControl;


import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DigitalRead {

    //# cat /sys/devices/virtual/misc/mtgpio/pin | grep 141

    private final static String TAG = "DigitalRead Gpio";
    private String mGpioNum;
    private int mGpioPullEn;
    private int mGpioSel;
    private GetValueInterface mGetValueInterface;
    private Service myRunnable;


    public DigitalRead(@GPIO.GpioNum String gpioNum, @GPIO.GpioPullEn int gpioEn, @GPIO.GpioSel int gpioSel) {
        mGpioNum = gpioNum;
        mGpioSel = gpioSel;
        mGpioPullEn = gpioEn;


    }


    public void getValueTheard(GetValueInterface getValueInterface) {
        mGetValueInterface = getValueInterface;
        myRunnable = new Service();
        // new Thread(myRunnable).start();
        final ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(myRunnable);
        // this line will execute immediately, not waiting for your task to complete
        executor.shutdown();


    }

    public int getValue() {
        final String readLineADB;
        String[] resp = null;
        try {
            final Process su = Runtime.getRuntime().exec("su");
            final DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
            outputStream.writeBytes("echo \"-wdir " + mGpioNum + " 0\" > /sys/devices/virtual/misc/mtgpio/pin\n");
            outputStream.flush();
            outputStream.writeBytes("echo \"-wdir " + mGpioNum + " 0\" > /sys/devices/virtual/misc/mtgpio/pin\n");
            outputStream.flush();
            outputStream.writeBytes("echo \"-wpen " + mGpioNum + " " + String.valueOf(mGpioPullEn) + "\" > /sys/devices/virtual/misc/mtgpio/pin\n");
            outputStream.flush();
            outputStream.writeBytes("echo \"-wpsel " + mGpioNum + " " + String.valueOf(mGpioSel) + "\" > /sys/devices/virtual/misc/mtgpio/pin\n");
            outputStream.flush();
            outputStream.writeBytes("cat /sys/devices/virtual/misc/mtgpio/pin | grep " + mGpioNum + "\n");
            DataInputStream dataIn = new DataInputStream(su.getInputStream());
            readLineADB = dataIn.readLine();
            Log.d(TAG, readLineADB.toString());
            resp = readLineADB.split(":");
            outputStream.flush();
            outputStream.writeBytes("exit\n");
            outputStream.flush();
            su.waitFor();
            Log.d(TAG, "echo \"-wdir " + mGpioNum + " 0\" > /sys/devices/virtual/misc/mtgpio/pin\n");
            Log.d(TAG, "echo \"-wpen " + mGpioNum + " " + String.valueOf(mGpioPullEn) + "\" > /sys/devices/virtual/misc/mtgpio/pin\n");
            Log.d(TAG, "echo \"-wpsel " + mGpioNum + " " + String.valueOf(mGpioSel) + "\" > /sys/devices/virtual/misc/mtgpio/pin\n");
            Log.d(TAG, "cat /sys/devices/virtual/misc/mtgpio/pin | grep " + mGpioNum + "\n");
            Log.d(TAG, "DigitalRead" + resp[1].substring(2, 3));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(resp[1].substring(1, 3));

    }

    public void destroyDigitalRead() {
        myRunnable.cancel();
    }

    public class Service implements Runnable {
        private volatile boolean cancelled;

        public Service() {
            // mContext=context;
        }

        @Override
        public void run() {


            while (!isCancelled()) {
                Log.d(TAG, "run: ");
                try {

                    final Process su = Runtime.getRuntime().exec("su");
                    final DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
                    outputStream.writeBytes("echo \"-wdir " + mGpioNum + " 0\" > /sys/devices/virtual/misc/mtgpio/pin\n");
                    outputStream.flush();
                    outputStream.writeBytes("echo \"-wdir " + mGpioNum + " 0\" > /sys/devices/virtual/misc/mtgpio/pin\n");
                    outputStream.flush();
                    outputStream.writeBytes("echo \"-wpen " + mGpioNum + " " + String.valueOf(mGpioPullEn) + "\" > /sys/devices/virtual/misc/mtgpio/pin\n");
                    outputStream.flush();
                    outputStream.writeBytes("echo \"-wpsel " + mGpioNum + " " + String.valueOf(mGpioSel) + "\" > /sys/devices/virtual/misc/mtgpio/pin\n");
                    outputStream.flush();
                    outputStream.writeBytes("cat /sys/devices/virtual/misc/mtgpio/pin | grep " + mGpioNum + "\n");
                    DataInputStream dataIn = new DataInputStream(su.getInputStream());
                    final String readLineADB = dataIn.readLine();
                    Log.d(TAG, readLineADB.toString());
                    final String[] resp = readLineADB.split(":");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    su.waitFor();
                    Log.d(TAG, "echo \"-wdir " + mGpioNum + " 0\" > /sys/devices/virtual/misc/mtgpio/pin\n");
                    Log.d(TAG, "echo \"-wpen " + mGpioNum + " " + String.valueOf(mGpioPullEn) + "\" > /sys/devices/virtual/misc/mtgpio/pin\n");
                    Log.d(TAG, "echo \"-wpsel " + mGpioNum + " " + String.valueOf(mGpioSel) + "\" > /sys/devices/virtual/misc/mtgpio/pin\n");
                    Log.d(TAG, "cat /sys/devices/virtual/misc/mtgpio/pin | grep " + mGpioNum + "\n");
                    mGetValueInterface.runGetValue(Integer.valueOf(resp[1].substring(1, 3)));
                    Log.d(TAG, "DigitalRead" + resp[1].substring(2, 3));
                } catch (IOException | InterruptedException e) {

                    e.printStackTrace();

                }
                //cat /sys/devices/virtual/misc/mtgpio/pin | grep 141
            }
        }


        public void cancel() {
            cancelled = true;
        }

        public boolean isCancelled() {
            return cancelled;
        }
    }


}

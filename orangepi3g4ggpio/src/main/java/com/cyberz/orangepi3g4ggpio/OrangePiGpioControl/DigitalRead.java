package com.cyberz.orangepi3g4ggpio.OrangePiGpioControl;


import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class DigitalRead {

    //# cat /sys/devices/virtual/misc/mtgpio/pin | grep 141

    private final static String TAG = "DigitalRead Gpio";
    private String mGpioNum;
    private int mGpioPullEn;
    private int mGpioSel;


    public DigitalRead(@GPIO.GpioNum String gpioNum, @GPIO.GpioPullEn int gpioEn, @GPIO.GpioSel int gpioSel) {
        mGpioNum = gpioNum;
        mGpioSel = gpioSel;
        mGpioPullEn = gpioEn;
        try {
            final Process su = Runtime.getRuntime().exec("su");
            final DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

            outputStream.writeBytes("echo \"-wdir " + mGpioNum + " 0\" > /sys/devices/virtual/misc/mtgpio/pin\n");
            outputStream.flush();
            outputStream.writeBytes("echo \"-wpen " + mGpioNum + " " + "1" + "\" > /sys/devices/virtual/misc/mtgpio/pin\n");
            outputStream.flush();
            outputStream.writeBytes("echo \"-wpsel " + mGpioNum + " " + "1" + "\" > /sys/devices/virtual/misc/mtgpio/pin\n");
            outputStream.flush();
            outputStream.writeBytes("exit\n");
            outputStream.flush();
            su.waitFor();
            Log.d(TAG, "echo \"-wdir " + mGpioNum + " 0\" > /sys/devices/virtual/misc/mtgpio/pin\n");
            Log.d(TAG, "echo \"-wpen " + mGpioNum + " " + mGpioPullEn + "\" > /sys/devices/virtual/misc/mtgpio/pin\n");
            Log.d(TAG, "echo \"-wpsel " + mGpioNum + " " + mGpioSel + "\" > /sys/devices/virtual/misc/mtgpio/pin\n");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }


    public int getValue() {
        final String readLineADB;
        String[] resp = null;
        try {
            final Process su = Runtime.getRuntime().exec("su");
            final DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
            outputStream.writeBytes("cat /sys/devices/virtual/misc/mtgpio/pin | grep " + mGpioNum + "\n");
            DataInputStream dataIn = new DataInputStream(su.getInputStream());
            readLineADB = dataIn.readLine();
            Log.d(TAG, readLineADB);
            resp = readLineADB.split(":");
            outputStream.flush();
            outputStream.writeBytes("exit\n");
            outputStream.flush();
            su.waitFor();
            Log.d(TAG, "cat /sys/devices/virtual/misc/mtgpio/pin | grep " + mGpioNum + "\n");
            Log.d(TAG, "DigitalRead" + resp[1].substring(2, 3));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(resp[1].substring(2, 3));

    }




}

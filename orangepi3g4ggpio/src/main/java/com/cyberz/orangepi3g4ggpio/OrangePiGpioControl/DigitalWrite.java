package com.cyberz.orangepi3g4ggpio.OrangePiGpioControl;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;

public class DigitalWrite {
    private static final String TAG = "GPIO DigitalWrite";
    private String mGpioNum;

    public DigitalWrite(@GPIO.GpioNum String gpioNum) {
        mGpioNum = gpioNum;

        try {
            final Process su = Runtime.getRuntime().exec("su");
            final DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
            outputStream.writeBytes("echo \"-wdir " + mGpioNum + " 1\" > /sys/devices/virtual/misc/mtgpio/pin\n");
            outputStream.flush();
            Log.d(TAG, "echo \"-wdir " + mGpioNum + " 1\" > /sys/devices/virtual/misc/mtgpio/pin\n");
            outputStream.writeBytes("exit\n");
            outputStream.flush();
            su.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void Set(final @GPIO.GpioControl String control) throws Exception {


        final Process su = Runtime.getRuntime().exec("su");
        final DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
        try {
            outputStream.writeBytes("echo \"-wdout " + mGpioNum + " " + control + "\" > /sys/devices/virtual/misc/mtgpio/pin\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    su.waitFor();

            Log.d(TAG, "echo \"-wdout " + mGpioNum + " " + control + "\" > /sys/devices/virtual/misc/mtgpio/pin\n");

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            }


    }


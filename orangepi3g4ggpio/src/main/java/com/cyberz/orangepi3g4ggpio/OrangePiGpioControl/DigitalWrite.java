package com.cyberz.orangepi3g4ggpio.OrangePiGpioControl;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DigitalWrite {
    private static final String TAG = "GPIO DigitalWrite";

    public DigitalWrite() {
    }

    public void Set(final  @GPIO.GpioControl String source) throws Exception {

        Runnable runSet = new Runnable() {
            @Override
            public void run() {
                try{
                    final Process su = Runtime.getRuntime().exec("su");
                    final DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

                    outputStream.writeBytes("echo \"-wdir "+source.substring(source.length()-2,source.length())+" 1\" > /sys/devices/virtual/misc/mtgpio/pin\n");
                    outputStream.flush();
                    outputStream.writeBytes("echo \"-wdout "+source+"\" > /sys/devices/virtual/misc/mtgpio/pin\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    su.waitFor();
                    Log.d(TAG, "echo \"-wdir "+source.substring(source.length()-2,source.length())+" 1\" > /sys/devices/virtual/misc/mtgpio/pin\n");
                    Log.d(TAG, "echo \"-wdout "+source+"\" > /sys/devices/virtual/misc/mtgpio/pin\n");

                }catch(IOException | InterruptedException e){
                    e.printStackTrace();
                }

            }
        };

        final ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(runSet);
        executor.shutdown();

    }
}

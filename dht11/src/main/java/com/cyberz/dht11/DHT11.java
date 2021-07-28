package com.cyberz.dht11;

import com.cyberz.orangepi3g4ggpio.OrangePiGpioControl.DigitalRead;
import com.cyberz.orangepi3g4ggpio.OrangePiGpioControl.DigitalWrite;
import com.cyberz.orangepi3g4ggpio.OrangePiGpioControl.GPIO;

import java.util.concurrent.TimeUnit;

public class DHT11 {
    private static final String SensorError = "ERROR_TIMEOUT";
    private static final String CheckSumError = "ERROR_CHECKSUM;";
    private String mGpioNum;
    private Object mDTH11emaphore = new Object();

    public DHT11(@GPIO.GpioNum String gpioNum) {
        mGpioNum = gpioNum;
    }

    public void getValues(OnValueGetListener onValueGetListener) {

        synchronized (mDTH11emaphore) {
            byte[] bits = new byte[5];
            int cnt = 7;
            int idx = 0;
            // EMPTY BUFFER
            for (int i = 0; i < 5; i++) {
                bits[i] = 0;
            }


            try {
                DigitalWrite digitalWrite = new DigitalWrite(mGpioNum);
                digitalWrite.Set(GPIO.HIGH);
                mDTH11emaphore.wait(250);
                digitalWrite.Set(GPIO.LOW);
                mDTH11emaphore.wait(18);
                digitalWrite.Set(GPIO.HIGH);
                mDTH11emaphore.wait(40);
            } catch (Exception e) {

            }

            DigitalRead digitalRead = new DigitalRead(mGpioNum, GPIO.EN, GPIO.PULLUP);
                /*int loopCnt = 1000;

                while(digitalRead.getValue() == 0){
                    loopCnt--;
                    if (loopCnt == 0) {
                        onValueGetListener.onError(SensorError);
                    return;}}

                loopCnt = 1000;
                while(digitalRead.getValue() == 1){
                    loopCnt--;
                    if (loopCnt == 0){
                        onValueGetListener.onError(SensorError); return;}}*/
            for (int i = 0; i < 40; i++) {
                int loopCnt = 1000;
                while (digitalRead.getValue() == 0) {
                    loopCnt--;
                    if (loopCnt == 0) {
                        onValueGetListener.onError(SensorError);
                    }
                    return;
                }

                long t = TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis());

                loopCnt = 1000;
                while (digitalRead.getValue() == 1) {
                    loopCnt--;
                    if (loopCnt == 0) {
                        onValueGetListener.onError(SensorError);
                    }
                    return;
                }
                //long t = TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis());

                if ((TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis()) - t) > 40)
                    bits[idx] |= (1 << cnt);
                if (cnt == 0)   // next byte?
                {
                    cnt = 7;    // restart at MSB
                    idx++;      // next byte!
                } else cnt--;
            }

            // WRITE TO RIGHT VARS
            // as bits[1] and bits[3] are allways zero they are omitted in formulas.
            byte humidity = bits[0];
            byte temperature = bits[2];
            onValueGetListener.Onsucess(String.valueOf(humidity), String.valueOf(temperature));

            int sum = bits[0] + bits[2];

            if (bits[4] != sum) {
                onValueGetListener.onError(CheckSumError);
                return;
            }


        }


    }
}

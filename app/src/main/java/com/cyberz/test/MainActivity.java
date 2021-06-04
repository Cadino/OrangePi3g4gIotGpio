package com.cyberz.test;

import android.os.Bundle;
import android.util.Log;

import com.cyberz.orangepi3g4ggpio.OrangePiGpioControl.DigitalRead;
import com.cyberz.orangepi3g4ggpio.OrangePiGpioControl.DigitalWrite;
import com.cyberz.orangepi3g4ggpio.OrangePiGpioControl.GPIO;
import com.cyberz.orangepi3g4ggpio.OrangePiGpioControl.GetValueInterface;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


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


        DigitalRead digitalRead = new DigitalRead(GPIO.GPIO_24, GPIO.EN, GPIO.PULLDOWN);
        digitalRead.getValueTheard(new GetValueInterface() {
            @Override
            public void runGetValue(int value) {

            }
        });


        Log.d("TAG", "onCreate: " + digitalRead.getValue());



    }

    
}

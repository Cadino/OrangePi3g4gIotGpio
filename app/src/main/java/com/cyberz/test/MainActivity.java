package com.cyberz.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cyberz.orangepi3g4ggpio.OrangePiGpioControl.DigitalRead;
import com.cyberz.orangepi3g4ggpio.OrangePiGpioControl.DigitalWrite;
import com.cyberz.orangepi3g4ggpio.OrangePiGpioControl.GPIO;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    DigitalWrite digitalWrite;
    Button btn_on;
    Button btn_off;
    Object mBlinkSemaphore = new Object();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_off = findViewById(R.id.btn_off);
        btn_on = findViewById(R.id.btn_on);
        final DigitalRead digitalRead = new DigitalRead(GPIO.GPIO_27, GPIO.EN, GPIO.PULLUP);
        final DigitalWrite digitalWrite = new DigitalWrite(GPIO.GPIO_141);


        btn_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    digitalWrite.Set(GPIO.HIGH);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        btn_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    digitalWrite.Set(GPIO.HIGH);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }


}

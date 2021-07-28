package com.cyberz.orangepi3g4ggpio.OrangePiGpioControl;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;

public class GPIO {


    public static final int PULLUP = 0;
    public static final int PULLDOWN = 1;
    public static final int EN = 1;
    public static final int DS = 0;
    public static final String GPIO_141 = "141";
    public static final String GPIO_24 = "24";
    public static final String GPIO_109 = "109";
    public static final String GPIO_25= "25";
    public static final String GPIO_139 = "139";
    public static final String GPIO_30 = "30";
    public static final String GPIO_26 = "26";
    public static final String GPIO_56= "56";
    public static final String GPIO_90="90";
    public static final String GPIO_128 = "128";
    public static final String GPIO_58 = "58";
    public static final String GPIO_145 = "145";
    public static final String GPIO_89= "89";
    public static final String GPIO_27="27";
    public static final String GPIO_144 = "144";
    public static final String HIGH = "1";
    public static final String LOW = "0";



    @StringDef(value = {
            HIGH, LOW


    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface GpioControl {
    }
    @IntDef(value = {PULLUP, PULLDOWN})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GpioSel {

    }


    @IntDef(value = {EN, DS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GpioPullEn {
    }

    @StringDef(value = {GPIO_141,GPIO_24, GPIO_109,
            GPIO_25,GPIO_139,GPIO_30,
            GPIO_26,GPIO_56,GPIO_90,
            GPIO_128,GPIO_58,GPIO_145,
            GPIO_89,GPIO_27,GPIO_144})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GpioNum {
    }


}

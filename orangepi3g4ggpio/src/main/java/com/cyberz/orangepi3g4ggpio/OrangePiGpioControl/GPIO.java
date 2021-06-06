package com.cyberz.orangepi3g4ggpio.OrangePiGpioControl;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;

public class GPIO {

    public static final String GPIO_141_HIGH = "141 1";
    public static final String GPIO_141_LOW = "141 0";
    public static final String GPIO_24_HIGH = "24 1";
    public static final String GPIO_24_LOW = "24 0";
    public static final String GPIO_109_HIGH = "109 1";
    public static final String GPIO_109_LOW = "109 0";
    public static final String GPIO_25_HIGH = "25 1";
    public static final String GPIO_25_LOW = "25 0";
    public static final String GPIO_139_HIGH = "139 1";
    public static final String GPIO_139_LOW = "139 0";
    public static final String GPIO_30_HIGH = "30 1";
    public static final String GPIO_30_LOW = "30 0";
    public static final String GPIO_26_HIGH = "26 1";
    public static final String GPIO_26_LOW = "26 0";
    public static final String GPIO_56_HIGH = "56 1";
    public static final String GPIO_56_LOW = "56 0";
    public static final String GPIO_90_HIGH = "90 1";
    public static final String GPIO_90_LOW = "90 0";
    public static final String GPIO_128_HIGH = "128 1";
    public static final String GPIO_128_LOW = "128 0";
    public static final String GPIO_58_HIGH = "58 1";
    public static final String GPIO_58_LOW = "58 0";
    public static final String GPIO_145_HIGH = "26 1";
    public static final String GPIO_145_LOW = "26 0";
    public static final String GPIO_89_HIGH = "89 1";
    public static final String GPIO_89_LOW = "89 0";
    public static final String GPIO_27_HIGH = "27 1";
    public static final String GPIO_27_LOW = "27 0";
    public static final String GPIO_144_HIGH = "144 1";
    public static final String GPIO_144_LOW = "44 0";
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

    private String gpioCommand;

    @GpioControl
    public String getGpo() {
        return gpioCommand;   //Here you can't return simple integer value, It will generate compile time error
    }

    //As setImageSource has argument type of @GpioControl, parameters passed can only be one of both
    // OPEN_CAMERA or OPEN_GALLERY
    public void setGpo(@GpioControl String gpioCommand) {
        this.gpioCommand = gpioCommand;
    }

    @StringDef(value = {
            GPIO_141_HIGH,
            GPIO_141_LOW,
            GPIO_24_HIGH,
            GPIO_24_LOW,
            GPIO_26_HIGH,
            GPIO_26_LOW,
            GPIO_109_HIGH,
            GPIO_109_LOW,
            GPIO_25_HIGH,
            GPIO_25_LOW,
            GPIO_139_HIGH,
            GPIO_139_LOW,
            GPIO_30_HIGH,
            GPIO_30_LOW,
            GPIO_56_HIGH,
            GPIO_56_LOW,
            GPIO_58_HIGH,
            GPIO_58_LOW,
            GPIO_90_HIGH,
            GPIO_90_LOW,
            GPIO_128_HIGH,
            GPIO_128_LOW,
            GPIO_145_HIGH,
            GPIO_145_LOW,
            GPIO_89_HIGH,
            GPIO_89_LOW,
            GPIO_27_HIGH,
            GPIO_27_LOW,
            GPIO_144_HIGH,
            GPIO_144_LOW

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

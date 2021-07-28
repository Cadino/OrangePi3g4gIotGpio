package com.cyberz.stepmotor;

import com.cyberz.orangepi3g4ggpio.OrangePiGpioControl.DigitalWrite;
import com.cyberz.orangepi3g4ggpio.OrangePiGpioControl.GPIO;

import java.util.concurrent.TimeUnit;

import static com.cyberz.orangepi3g4ggpio.OrangePiGpioControl.GPIO.HIGH;
import static com.cyberz.orangepi3g4ggpio.OrangePiGpioControl.GPIO.LOW;
import static java.lang.Math.abs;

public class Stepper {

    DigitalWrite d1;
    DigitalWrite d2;
    DigitalWrite d3;
    DigitalWrite d4;// Only 5 phase motor
    long last_step_time; // timestamp in us of when the last step was taken
    private int direction;            // Direction of rotation
    private long step_delay; // delay between steps, in ms, based on speed
    private int number_of_steps;      // total number of steps this motor can take
    private int pin_count;            // how many pins are in use.
    private int step_number;          // which step the motor is on
    // motor pin numbers:
    private String motor_pin_1;
    private String motor_pin_2;
    private String motor_pin_3;
    private String motor_pin_4;
    private String motor_pin_5;

    public Stepper(int number_of_steps, @GPIO.GpioNum String motor_pin_1, @GPIO.GpioNum String motor_pin_2, @GPIO.GpioNum String motor_pin_3, @GPIO.GpioNum String motor_pin_4) {
        this.step_number = 0;    // which step the motor is on
        this.direction = 0;      // motor direction
        this.last_step_time = 0; // timestamp in us of the last step taken
        this.number_of_steps = number_of_steps; // total number of steps for this motor


        this.motor_pin_1 = motor_pin_1;
        this.motor_pin_2 = motor_pin_2;
        this.motor_pin_3 = motor_pin_3;
        this.motor_pin_4 = motor_pin_4;
        this.motor_pin_5 = "0";
        d1 = new DigitalWrite(motor_pin_1);
        d2 = new DigitalWrite(motor_pin_1);
        d3 = new DigitalWrite(motor_pin_1);
        d4 = new DigitalWrite(motor_pin_1);
        this.pin_count = 4;
    }

    public void step(int steps_to_move) {

        int steps_left = abs(steps_to_move); // how many steps to take
        if (steps_to_move > 0) {
            this.direction = 1;
        }
        if (steps_to_move < 0) {
            this.direction = 0;
        }
        long now = TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis());
        if (now - this.last_step_time >= this.step_delay) {
            this.last_step_time = now;
            if (this.direction == 1) {
                this.step_number++;
                if (this.step_number == this.number_of_steps) {
                    this.step_number = 0;
                }
            } else {
                if (this.step_number == 0) {
                    this.step_number = this.number_of_steps;
                }
                this.step_number--;
            }
            steps_left--;
            // step the motor to step number 0, 1, ..., {3 or 10}
            if (this.pin_count == 5)
                stepMotor(this.step_number % 10);
            else
                stepMotor(this.step_number % 4);


        }
    }

    private void stepMotor(int thisStep) {
        if (this.pin_count == 2) {
            switch (thisStep) {
                case 0:  // 01
                    try {
                        d1.Set(LOW);
                        d2.Set(HIGH);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:  // 11

                    try {
                        d1.Set(HIGH);
                        d2.Set(HIGH);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:  // 10
                    try {
                        d1.Set(HIGH);
                        d2.Set(LOW);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case 3:  // 00
                    try {
                        d1.Set(LOW);
                        d2.Set(LOW);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }
        if (this.pin_count == 4) {
            switch (thisStep) {
                case 0:  // 1010
                    try {
                        d1.Set(HIGH);
                        d2.Set(LOW);
                        d3.Set(HIGH);
                        d4.Set(LOW);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:  // 0110

                    try {
                        d1.Set(LOW);
                        d2.Set(HIGH);
                        d3.Set(HIGH);
                        d4.Set(LOW);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:  //0101

                    try {
                        d1.Set(LOW);
                        d2.Set(HIGH);
                        d3.Set(LOW);
                        d4.Set(HIGH);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case 3:  //1001
                    try {
                        d1.Set(HIGH);
                        d2.Set(LOW);
                        d3.Set(LOW);
                        d4.Set(HIGH);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

      /*  if (this.pin_count == 5) {
        switch (thisStep) {
            case 0:  // 01101

                try {
                d1.Set(HIGH);
                d2.Set(LOW);
                d3.Set(LOW);
                d4.Set(HIGH);
                d
            } catch (Exception e) {
                e.printStackTrace();
            }
                digitalWrite(motor_pin_1, LOW);
                digitalWrite(motor_pin_2, HIGH);
                digitalWrite(motor_pin_3, HIGH);
                digitalWrite(motor_pin_4, LOW);
                digitalWrite(motor_pin_5, HIGH);
                break;
            case 1:  // 01001
                digitalWrite(motor_pin_1, LOW);
                digitalWrite(motor_pin_2, HIGH);
                digitalWrite(motor_pin_3, LOW);
                digitalWrite(motor_pin_4, LOW);
                digitalWrite(motor_pin_5, HIGH);
                break;
            case 2:  // 01011
                digitalWrite(motor_pin_1, LOW);
                digitalWrite(motor_pin_2, HIGH);
                digitalWrite(motor_pin_3, LOW);
                digitalWrite(motor_pin_4, HIGH);
                digitalWrite(motor_pin_5, HIGH);
                break;
            case 3:  // 01010
                digitalWrite(motor_pin_1, LOW);
                digitalWrite(motor_pin_2, HIGH);
                digitalWrite(motor_pin_3, LOW);
                digitalWrite(motor_pin_4, HIGH);
                digitalWrite(motor_pin_5, LOW);
                break;
            case 4:  // 11010
                digitalWrite(motor_pin_1, HIGH);
                digitalWrite(motor_pin_2, HIGH);
                digitalWrite(motor_pin_3, LOW);
                digitalWrite(motor_pin_4, HIGH);
                digitalWrite(motor_pin_5, LOW);
                break;
            case 5:  // 10010
                digitalWrite(motor_pin_1, HIGH);
                digitalWrite(motor_pin_2, LOW);
                digitalWrite(motor_pin_3, LOW);
                digitalWrite(motor_pin_4, HIGH);
                digitalWrite(motor_pin_5, LOW);
                break;
            case 6:  // 10110
                digitalWrite(motor_pin_1, HIGH);
                digitalWrite(motor_pin_2, LOW);
                digitalWrite(motor_pin_3, HIGH);
                digitalWrite(motor_pin_4, HIGH);
                digitalWrite(motor_pin_5, LOW);
                break;
            case 7:  // 10100
                digitalWrite(motor_pin_1, HIGH);
                digitalWrite(motor_pin_2, LOW);
                digitalWrite(motor_pin_3, HIGH);
                digitalWrite(motor_pin_4, LOW);
                digitalWrite(motor_pin_5, LOW);
                break;
            case 8:  // 10101
                digitalWrite(motor_pin_1, HIGH);
                digitalWrite(motor_pin_2, LOW);
                digitalWrite(motor_pin_3, HIGH);
                digitalWrite(motor_pin_4, LOW);
                digitalWrite(motor_pin_5, HIGH);
                break;
            case 9:  // 00101
                digitalWrite(motor_pin_1, LOW);
                digitalWrite(motor_pin_2, LOW);
                digitalWrite(motor_pin_3, HIGH);
                digitalWrite(motor_pin_4, LOW);
                digitalWrite(motor_pin_5, HIGH);
                break;
        }
    }*/
    }

    public void setSpeed(long whatSpeed) {
        this.step_delay = 60L * 1000L * 1000L / this.number_of_steps / whatSpeed;
    }
}

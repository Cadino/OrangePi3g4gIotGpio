# OrangePi3g4gIotGpio
This library help read and write on GPIO on Orange Pi 3g iot and Orange Pi 4g iot 

Prerequisites:
First make sure you have complete root access on your Orange Pi 3g Iot or Orange Pi 4g Iot.
For root your Orange Pi 3g Iot or Orange Pi 4g Iot. Check this post: http://www.orangepi.org/orangepibbsen/forum.php?mod=viewthread&tid=6883&extra=

Actions:


    Steps to import Module in Android Studio 3.3 and lower.

    Go to File > New > Import Module...
    Select the source directory of the Module you want to import and click Finish.
    Open Project Structure and open Module Settings for your project.
    Open the Dependencies tab.
    Click the (+) icon and select Module Dependency. Select the module and click Ok.
    Open your build.gradle file and check that the module is now listed under dependencies.(implementation project(path: ':OrangePi3g4ggpio')

    Steps to import Module in Android Studio 3.4 and higher.

    Go to File > New > Import Module...
    Select the source directory of the Module you want to import and click Finish.
    Open Project Structure Dialog (You can open the PSD by selecting File > Project Structure) and from the left panel click on Dependencies.
    Select the module from the Module(Middle) section In which you want to add module dependency.
    Click the (+) icon from the Declared Dependencies section and click Module Dependency.
    Select the module and click Ok.
    Open your build.gradle file and check that the module is now listed under dependencies.(implementation project(path: ':OrangePi3g4ggpio')
    
    
How to use:

    /*To toogle a output gpio*/
     final DigitalWrite digitalWrite = new DigitalWrite(); //create a gpio output object
     digitalWrite.Set(GPIO.GPIO_141_HIGH); // set the state of GPIO141 to HIGH
     digitalWrite.Set(GPIO.GPIO_141_LOW); // set the state of GPIO141 to LOW
   
  
    /* for read gpio*/
   
    DigitalRead digitalRead = new DigitalRead(GPIO.GPIO_24, GPIO.EN, GPIO.PULLDOWN); //create a gpio input; object the must be have the gpio number, the pull enable (GPIO.EN) and the pull (GPIO.PULLDOWN or GPIO.PULLUP)
    
    

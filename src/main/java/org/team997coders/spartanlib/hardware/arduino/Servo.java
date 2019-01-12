package org.team997coders.spartanlib.hardware.arduino;

import org.team997coders.spartanlib.interfaces.IServo;

import haiku.vm.NativeCHeader;
import haiku.vm.NativeCppFunction;
import haiku.vm.NativeCppMethod;

/**
 * This Servo class implements a Java proxy to the Arduino Servo class, enabling you
 * to interact with a Servo from a Java application. It uses the API provided by the
 * <a href="http://haiku-vm.sourceforge.net/">HaikuVM project</a> to perform this
 * proxy function. See the pages in the project on JNI.
 */
@NativeCHeader(cImpl = "#include <Servo.h>")
public class Servo implements IServo {
  private Servo realSubject;

  public Servo() {
    this(0);
  }

  public Servo(int pinArg) {
    realSubject = Servo.Servo();
    attach(pinArg);
  }

  public Servo(int pinArg, int angleInDegrees) {
    this(pinArg);
    write(angleInDegrees);
  }

  public Servo(int pinArg, int minPulseInuS, int maxPulseInuS) {
    realSubject = Servo.Servo();
    attachWithMinMax(pinArg, minPulseInuS, maxPulseInuS);
  }

  public Servo(int pinArg, int angleInDegrees, int minPulseInuS, int maxPulseInuS) {
    this(pinArg, minPulseInuS, maxPulseInuS);
    write(angleInDegrees);
  }

  @NativeCppMethod
  private native static Servo Servo();

  @NativeCppMethod
  public native byte attach(int pinArg);

  /**
   * This declaration is necessary because C functions cannot be overloaded. Proxies are
   * created as C functios and so this declaration changes the name of the attach function
   * to avoid conflict with the above function.
   * 
   * @param pinArg
   * @param minPulseInuS
   * @param maxPulseInuS
   * @return
   */
  @NativeCppFunction(cImpl="return getRealCppSubject(Servo, obj)->attach(arg1, arg2, arg3);")
  public native byte attachWithMinMax(int pinArg, int minPulseInuS, int maxPulseInuS);

  @NativeCppMethod
  public native byte attached();

  @NativeCppMethod
  public native void detach();

  @NativeCppMethod
  public native byte read();

  @NativeCppMethod
  public native void write(int angleInDegrees);

  @NativeCppMethod
  public native void writeMicroseconds(int uS);
}
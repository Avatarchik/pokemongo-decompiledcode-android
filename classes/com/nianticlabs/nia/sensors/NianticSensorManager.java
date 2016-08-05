package com.nianticlabs.nia.sensors;

import android.content.Context;
import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.view.Display;
import android.view.WindowManager;
import com.nianticlabs.nia.contextservice.ContextService;
import com.nianticlabs.nia.contextservice.ServiceStatus;

public class NianticSensorManager
  extends ContextService
  implements SensorEventListener
{
  private static final float ANGLE_CHANGE_THRESHOLD_DEGREES = 1.0F;
  private static final int DECLINATION_UPDATE_INTERVAL_MSEC = 600000;
  private static final boolean ENABLE_VERBOSE_LOGS = false;
  private static final int MAX_SENSOR_UPDATE_DIFF_MSEC = 5000;
  private static final int MIN_SENSOR_UPDATE_INTERVAL_MSEC = 50;
  private static final float SINE_OF_45_DEGREES = (float)Math.sqrt(2.0D) / 2.0F;
  private static final String TAG = "NianticSensorManager";
  private Sensor accelerometer;
  private float[] accelerometerData = new float[3];
  private long accelerometerReadingMs;
  private float declination;
  private long declinationUpdateTimeMs;
  private final Display display;
  private Sensor gravity;
  private Sensor gyroscope;
  private float lastAzimuthUpdate;
  private float lastPitchUpdate;
  private long lastUpdateTimeMs;
  private Sensor linearAcceleration;
  private Sensor magnetic;
  private float[] magneticData = new float[3];
  private long magnetometerReadingMs;
  private final AngleFilter orientationFilter = new AngleFilter(true);
  private Sensor rotation;
  private float[] rotationData = new float[5];
  private final SensorManager sensorManager;
  private ServiceStatus status = ServiceStatus.UNDEFINED;
  private final float[] tmpMatrix1 = new float[9];
  private final float[] tmpMatrix2 = new float[9];
  private final float[] tmpMatrix3 = new float[9];
  private final float[] tmpOrientationAngles = new float[3];
  
  public NianticSensorManager(Context paramContext, long paramLong)
  {
    super(paramContext, paramLong);
    this.display = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    this.sensorManager = ((SensorManager)paramContext.getSystemService("sensor"));
    this.gravity = this.sensorManager.getDefaultSensor(9);
    this.gyroscope = this.sensorManager.getDefaultSensor(4);
    this.accelerometer = this.sensorManager.getDefaultSensor(1);
    this.magnetic = this.sensorManager.getDefaultSensor(2);
    this.rotation = this.sensorManager.getDefaultSensor(11);
    this.linearAcceleration = this.sensorManager.getDefaultSensor(10);
  }
  
  private void calcMatrixFromRotationVector(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    float f1 = paramArrayOfFloat1[3];
    float f2 = paramArrayOfFloat1[0];
    float f3 = paramArrayOfFloat1[1];
    float f4 = paramArrayOfFloat1[2];
    float f5 = f2 * (2.0F * f2);
    float f6 = f3 * (2.0F * f3);
    float f7 = f4 * (2.0F * f4);
    float f8 = f3 * (2.0F * f2);
    float f9 = f1 * (2.0F * f4);
    float f10 = f4 * (2.0F * f2);
    float f11 = f1 * (2.0F * f3);
    float f12 = f4 * (2.0F * f3);
    float f13 = f1 * (2.0F * f2);
    paramArrayOfFloat2[0] = (1.0F - f6 - f7);
    paramArrayOfFloat2[1] = (f8 - f9);
    paramArrayOfFloat2[2] = (f10 + f11);
    paramArrayOfFloat2[3] = (f8 + f9);
    paramArrayOfFloat2[4] = (1.0F - f5 - f7);
    paramArrayOfFloat2[5] = (f12 - f13);
    paramArrayOfFloat2[6] = (f10 - f11);
    paramArrayOfFloat2[7] = (f12 + f13);
    paramArrayOfFloat2[8] = (1.0F - f5 - f6);
  }
  
  private float computeRotationVectorW(float[] paramArrayOfFloat)
  {
    float f1 = 0.0F;
    int i = paramArrayOfFloat.length;
    for (int j = 0; j < i; j++)
    {
      float f2 = paramArrayOfFloat[j];
      f1 += f2 * f2;
    }
    return (float)Math.sqrt(1.0F - Math.min(f1, 1.0F));
  }
  
  private float getDeclination()
  {
    long l = System.currentTimeMillis();
    if ((600000L + this.declinationUpdateTimeMs > l) && (0 != 0))
    {
      this.declinationUpdateTimeMs = l;
      this.declination = new GeomagneticField((float)null.getLatitude(), (float)null.getLongitude(), (float)null.getAltitude(), l).getDeclination();
    }
    return this.declination;
  }
  
  private native void nativeCompassUpdate(long paramLong, float paramFloat);
  
  private native void nativeSensorUpdate(int paramInt, long paramLong, float[] paramArrayOfFloat);
  
  private void safeCompassUpdate(long paramLong, float paramFloat)
  {
    synchronized (this.callbackLock)
    {
      nativeCompassUpdate(paramLong, paramFloat);
      return;
    }
  }
  
  private void safeSensorUpdate(int paramInt, long paramLong, float[] paramArrayOfFloat)
  {
    synchronized (this.callbackLock)
    {
      nativeSensorUpdate(paramInt, paramLong, paramArrayOfFloat);
      return;
    }
  }
  
  private void startSensorManager()
  {
    if (this.gravity != null) {
      this.sensorManager.registerListener(this, this.gravity, 3, ContextService.getServiceHandler());
    }
    if (this.gyroscope != null) {
      this.sensorManager.registerListener(this, this.gyroscope, 3, ContextService.getServiceHandler());
    }
    if (this.accelerometer != null) {
      this.sensorManager.registerListener(this, this.accelerometer, 2, ContextService.getServiceHandler());
    }
    if (this.magnetic != null) {
      this.sensorManager.registerListener(this, this.magnetic, 2, ContextService.getServiceHandler());
    }
    if (this.rotation != null) {
      this.sensorManager.registerListener(this, this.rotation, 2, ContextService.getServiceHandler());
    }
    if (this.linearAcceleration != null) {
      this.sensorManager.registerListener(this, this.linearAcceleration, 3, ContextService.getServiceHandler());
    }
    this.status = ServiceStatus.INITIALIZED;
  }
  
  private void stopSensorManager()
  {
    this.sensorManager.unregisterListener(this);
    this.status = ServiceStatus.STOPPED;
  }
  
  private boolean updateOrientation(long paramLong, float[] paramArrayOfFloat)
  {
    boolean bool = false;
    int i;
    int j;
    float[] arrayOfFloat;
    switch (this.display.getRotation())
    {
    default: 
      i = 1;
      j = 2;
      arrayOfFloat = this.tmpOrientationAngles;
      if (SensorManager.remapCoordinateSystem(paramArrayOfFloat, i, j, this.tmpMatrix2)) {
        break;
      }
    }
    do
    {
      return bool;
      i = 2;
      j = 129;
      break;
      i = 130;
      j = 1;
      break;
      i = 129;
      j = 130;
      break;
      if (this.tmpMatrix2[7] <= SINE_OF_45_DEGREES) {
        break label240;
      }
    } while (!SensorManager.remapCoordinateSystem(this.tmpMatrix2, 1, 3, this.tmpMatrix3));
    SensorManager.getOrientation(this.tmpMatrix3, arrayOfFloat);
    for (float f1 = (float)Math.toDegrees(arrayOfFloat[1]) - 90.0F;; f1 = (float)Math.toDegrees(arrayOfFloat[1]))
    {
      float f2 = MathUtil.wrapAngle(arrayOfFloat[0] + 0.017453292F * getDeclination());
      float f3 = this.orientationFilter.filter(paramLong, 57.29578F * f2);
      if ((Math.abs(f3 - this.lastAzimuthUpdate) < 1.0F) && (Math.abs(f1 - this.lastPitchUpdate) < 1.0F)) {
        break;
      }
      this.lastAzimuthUpdate = f3;
      this.lastPitchUpdate = f1;
      this.lastUpdateTimeMs = paramLong;
      bool = true;
      break;
      label240:
      SensorManager.getOrientation(this.tmpMatrix2, arrayOfFloat);
    }
  }
  
  private boolean updateOrientationFromRaw(long paramLong)
  {
    boolean bool = false;
    if (50L + this.lastUpdateTimeMs > paramLong) {}
    for (;;)
    {
      return bool;
      if (Math.abs(this.accelerometerReadingMs - this.magnetometerReadingMs) <= 5000L)
      {
        float[] arrayOfFloat = this.tmpMatrix1;
        if (SensorManager.getRotationMatrix(arrayOfFloat, null, this.accelerometerData, this.magneticData)) {
          bool = updateOrientation(paramLong, arrayOfFloat);
        }
      }
    }
  }
  
  private boolean updateOrientationFromRotation(long paramLong)
  {
    if (50L + this.lastUpdateTimeMs > paramLong) {}
    float[] arrayOfFloat;
    for (boolean bool = false;; bool = updateOrientation(paramLong, arrayOfFloat))
    {
      return bool;
      arrayOfFloat = this.tmpMatrix1;
      calcMatrixFromRotationVector(this.rotationData, arrayOfFloat);
    }
  }
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
  
  public void onPause()
  {
    stopSensorManager();
  }
  
  public void onResume()
  {
    startSensorManager();
  }
  
  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    this.status = ServiceStatus.RUNNING;
    long l = System.currentTimeMillis();
    switch (paramSensorEvent.sensor.getType())
    {
    }
    for (;;)
    {
      safeSensorUpdate(paramSensorEvent.sensor.getType(), l, paramSensorEvent.values);
      return;
      this.accelerometerReadingMs = l;
      System.arraycopy(paramSensorEvent.values, 0, this.accelerometerData, 0, this.accelerometerData.length);
      if (updateOrientationFromRaw(l))
      {
        safeCompassUpdate(this.lastUpdateTimeMs, this.lastAzimuthUpdate);
        continue;
        this.magnetometerReadingMs = l;
        System.arraycopy(paramSensorEvent.values, 0, this.magneticData, 0, this.magneticData.length);
        if (updateOrientationFromRaw(l))
        {
          safeCompassUpdate(this.lastUpdateTimeMs, this.lastAzimuthUpdate);
          continue;
          System.arraycopy(paramSensorEvent.values, 0, this.rotationData, 0, Math.min(paramSensorEvent.values.length, this.rotationData.length));
          if (paramSensorEvent.values.length == 3) {
            this.rotationData[3] = computeRotationVectorW(this.rotationData);
          }
          if (updateOrientationFromRotation(l)) {
            safeCompassUpdate(this.lastUpdateTimeMs, this.lastAzimuthUpdate);
          }
        }
      }
    }
  }
  
  public void onStart() {}
  
  public void onStop() {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/sensors/NianticSensorManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
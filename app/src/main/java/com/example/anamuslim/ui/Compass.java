package com.example.anamuslim.ui;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Compass implements SensorEventListener {


    private float azimuthFix;
    private final Sensor gsensor;
    private CompassListener listener;
    private final Sensor msensor;
    private final SensorManager sensorManager;
    private final float[] mGravity = new float[3];
    private final float[] mGeomagnetic = new float[3];
    private final float[] R = new float[9];
    private final float[] I = new float[9];


    public interface CompassListener {
        void onNewAzimuth(float f);
    }

    public Compass(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        this.sensorManager = sensorManager;
        this.gsensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.msensor = this.sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD );
    }

    public void start() {
        this.sensorManager.registerListener(this, this.gsensor, 1);
        this.sensorManager.registerListener(this, this.msensor, 1);
    }

    public void stop() {
        this.sensorManager.unregisterListener(this);
    }

    public void setAzimuthFix(float fix) {
        this.azimuthFix = fix;
    }

    public void resetAzimuthFix() {
        setAzimuthFix(0.0f);
    }

    public void setListener(CompassListener l) {
        this.listener = l;
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            if (event.sensor.getType() == 1) {
                float[] fArr = this.mGravity;
                fArr[0] = (fArr[0] * 0.97f) + (event.values[0] * 0.029999971f);
                float[] fArr2 = this.mGravity;
                fArr2[1] = (fArr2[1] * 0.97f) + (event.values[1] * 0.029999971f);
                float[] fArr3 = this.mGravity;
                fArr3[2] = (fArr3[2] * 0.97f) + (event.values[2] * 0.029999971f);
            }
            if (event.sensor.getType() == 2) {
                float[] fArr4 = this.mGeomagnetic;
                fArr4[0] = (fArr4[0] * 0.97f) + (event.values[0] * 0.029999971f);
                float[] fArr5 = this.mGeomagnetic;
                fArr5[1] = (fArr5[1] * 0.97f) + (event.values[1] * 0.029999971f);
                float[] fArr6 = this.mGeomagnetic;
                fArr6[2] = (fArr6[2] * 0.97f) + (event.values[2] * 0.029999971f);
            }
            if (SensorManager.getRotationMatrix(this.R, this.I, this.mGravity, this.mGeomagnetic)) {
                float[] orientation = new float[3];
                SensorManager.getOrientation(this.R, orientation);
                float degrees = (float) Math.toDegrees((double) orientation[0]);
                float azimuth = degrees;
                float f = ((degrees + this.azimuthFix) + 360.0f) % 360.0f;
                azimuth = f;
                CompassListener compassListener = this.listener;
                if (compassListener != null) {
                    compassListener.onNewAzimuth(f);
                }
            }
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int accuracy) {



    }

}

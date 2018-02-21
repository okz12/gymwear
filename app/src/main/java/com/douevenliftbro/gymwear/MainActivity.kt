package com.douevenliftbro.gymwear

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.widget.TextView
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import java.util.logging.Logger


class MainActivity : WearableActivity(), SensorEventListener {
    /** Called when the activity is first created.  */
    private var mSensorManager : SensorManager ?= null
    private var mAccelerometer : Sensor ?= null
    val Log = Logger.getLogger(MainActivity::class.java.name)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        mSensorManager?.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST)

        Log.info("Init")
        // Enables Always-on
        setAmbientEnabled()
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        Log.info("Sensor Event detected")
        setContentView(R.layout.activity_main)
        var axisX = p0?.values?.get(0)
        var axisY = p0?.values?.get(1)
        var axisZ = p0?.values?.get(2)
        val textAcc = findViewById(R.id.textViewXYZ) as TextView
        println("X=" + axisX.toString() + "\nY=" + axisY.toString() + "\nZ=" + axisZ.toString())
        textAcc.setText("X=" + axisX.toString() + "\nY=" + axisY.toString() + "\nZ=" + axisZ.toString())
    }

}

// Android Samples - A collection of practical examples.
//
// Copyright © Diego Catalano, 2017
// diego.catalano at live.com
//
// Copyright © Vladimir Píccolo Barcelos, 2017
// vpbarcelos at gmail.com
//
// Copyright © Ciniro Nametala, 2017
// ciniro at gmail.com
//
//         Permission is hereby granted, free of charge, to any person obtaining a copy
//         of this software and associated documentation files (the "Software"), to deal
//         in the Software without restriction, including without limitation the rights
//         to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//         copies of the Software, and to permit persons to whom the Software is
//         furnished to do so, subject to the following conditions:
//
//         The above copyright notice and this permission notice shall be included in
//         all copies or substantial portions of the Software.
//
//         THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//         IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//         FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//         AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//         LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//         OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
//         THE SOFTWARE.

package catalano.demoapp.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

import catalano.demoapp.R;

public class SensorActivity extends AppCompatActivity {

    private Random random = new Random();

    private SensorManager sm;
    private Sensor accel;

    private TextView Xaxis;
    private TextView Yaxis;
    private TextView Zaxis;
    private TextView number;

    private SeekBar seek;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        Xaxis = (TextView)findViewById(R.id.txt_sensor_Xaxis);
        Yaxis = (TextView)findViewById(R.id.txt_sensor_Yaxis);
        Zaxis = (TextView)findViewById(R.id.txt_sensor_Zaxis);
        number = (TextView)findViewById(R.id.txt_sensor_number);

        seek = (SeekBar)findViewById(R.id.seek_sensor_force);
        seek.setMax(200);
        seek.setProgress(100);

        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sm.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                Xaxis.setText("X: " + String.valueOf(x));
                Yaxis.setText("Y: " + String.valueOf(y));
                Zaxis.setText("Z: " + String.valueOf(z));

                long curTime = System.currentTimeMillis();
                if(curTime - lastUpdate > 100){
                    long diffTime = (curTime - lastUpdate);
                    lastUpdate = curTime;

                    float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
                    Log.d("Speed", String.valueOf(speed));
                    if(speed > seek.getProgress()){
                        number.setText(String.valueOf(random.nextInt(100)));
                    }
                }

                last_x = x;
                last_y = y;
                last_z = z;

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, accel, SensorManager.SENSOR_DELAY_NORMAL);

    }
}

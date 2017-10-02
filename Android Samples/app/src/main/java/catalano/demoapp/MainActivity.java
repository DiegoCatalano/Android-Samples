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

package catalano.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import catalano.demoapp.activity.ActActivity;
import catalano.demoapp.animation.AnimationActivity;
import catalano.demoapp.basic.BasicActivity;
import catalano.demoapp.music.MusicActivity;
import catalano.demoapp.sensors.SensorActivity;
import catalano.demoapp.video.VideoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lst = (ListView)findViewById(R.id.lstSamples);

        List<Sample> samples = new ArrayList<>(10);
        samples.add(new Sample(R.drawable.ic_action_android,"Hello Android","Basic widgets, how to initialize them and how to send message with the Toast class.", BasicActivity.class));
        samples.add(new Sample(R.drawable.ic_pool,"Animations","Basic animations like rotation, translation and alpha.", AnimationActivity.class));
        samples.add(new Sample(R.drawable.ic_movie,"Video","How to load a video and initialize the MediaController class.", VideoActivity.class));
        samples.add(new Sample(R.drawable.ic_audiotrack,"Music","How to load a mp3 file and set basic controls.", MusicActivity.class));
        samples.add(new Sample(R.drawable.ic_list,"ListView","Create a basic or custom ListView.", null));
        samples.add(new Sample(R.drawable.ic_action_warning,"Alert Dialogs","Example using alert dialogs.", null));
        samples.add(new Sample(R.drawable.chip,"Sensors","Example how to use Accelorometer.", SensorActivity.class));
        samples.add(new Sample(R.drawable.ic_smartphone,"Activity","Load another activity and pass data between it.", null)); // TODO: Working
        samples.add(new Sample(R.drawable.ic_menu,"Action Toolbar","Create a toolbar and put a menu.", null));
        samples.add(new Sample(R.drawable.ic_insert_drive_file,"File Storage","Create a folder and file. Check internal and external storage.", null));

        AdapterMainItems adapter = new AdapterMainItems(samples, this);
        lst.setAdapter(adapter);

    }
}

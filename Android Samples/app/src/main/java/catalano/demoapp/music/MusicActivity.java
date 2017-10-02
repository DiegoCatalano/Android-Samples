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

package catalano.demoapp.music;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import catalano.demoapp.R;

public class MusicActivity extends AppCompatActivity {

    private MediaPlayer mp;
    private AudioManager am;
    private Handler handler;

    private SeekBar time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        //Load a music from the resource
        mp = MediaPlayer.create(this, R.raw.music);

        //region Initialize and setup the play and stop buttons
        Button btnPlay = (Button)findViewById(R.id.btn_music_play);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
            }
        });

        Button btnPause = (Button)findViewById(R.id.btn_music_pause);
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
            }
        });
        //endregion

        //region Setup the volume
        am = (AudioManager)getSystemService(AUDIO_SERVICE);
        int maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);

        //Setup the volume seekBar
        SeekBar volume = (SeekBar)findViewById(R.id.seek_music_volume);
        volume.setMax(maxVolume);
        volume.setProgress(curVolume);
        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //endregion

        //region Setup the update seek bar within duration music
        time = (SeekBar)findViewById(R.id.seek_music_length);
        time.setMax(mp.getDuration());
        time.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mp.seekTo(time.getProgress());
                return false;
            }
        });

        //Update the seekbar position
        handler = new Handler();
        Runnable update = new Runnable() {
            @Override
            public void run() {
                time.setProgress(mp.getCurrentPosition());
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(update, 1000);
        //endregion

    }

    @Override
    protected void onStop() {
        super.onStop();
        mp.stop();
    }
}

package catalano.demoapp.video;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import catalano.demoapp.R;

public class VideoActivity extends AppCompatActivity {

    private VideoView vv;
    private MediaController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        //Initialize and load the video from raw directory
        vv = (VideoView)findViewById(R.id.videoView);
        vv.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));

        //Set media controller, in the bottom of the video view widget.
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        mc = new MediaController(VideoActivity.this);
                        vv.setMediaController(mc);
                        mc.setAnchorView(vv);
                    }
                });
            }
        });

        //Initialize the play button
        Button btnPlay = (Button)findViewById(R.id.btn_Video_Start);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vv.start();
            }
        });

    }
}

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

package catalano.demoapp.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import catalano.demoapp.R;

public class AnimationActivity extends AppCompatActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        //Initialize the imageView
        img = (ImageView)findViewById(R.id.img_animation);

        //region Rotate the view
        Button btnRotate = (Button)findViewById(R.id.btn_animation_rotate);
        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.animate().rotationBy(360).setDuration(2000);
            }
        });
        //endregion

        //region Translate the view
        Button btnTranslate = (Button)findViewById(R.id.btn_animation_translate);
        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.animate().translationXBy(1000).setDuration(2000);
            }
        });
        //endregion

        //region Alpha effect
        Button btnAlpha = (Button)findViewById(R.id.btn_animation_alpha);
        btnAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.animate().alpha(0).setDuration(2000);
            }
        });
        //endregion

        //region Reload the activity
        Button btnRestart = (Button)findViewById(R.id.btn_animation_restart);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());

                //Reload with no animation between the activities
                overridePendingTransition(0, 0);
            }
        });
        //endregion

    }
}

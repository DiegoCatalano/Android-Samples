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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Diego on 30/09/2017.
 */

public class AdapterMainItems extends BaseAdapter {

    private final List<Sample> list;
    private final Context context;

    public AdapterMainItems(List<Sample> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(context).inflate(R.layout.sample_item, parent, false);

        final Sample sample = list.get(position);

        ImageView icon = (ImageView)v.findViewById(R.id.imgSample);
        icon.setImageResource(sample.getImageRes());

        TextView title = (TextView)v.findViewById(R.id.txtTitleItem);
        title.setText(sample.getTitle());

        TextView description = (TextView)v.findViewById(R.id.txtDescriptionItem);
        description.setText(sample.getDescription());

        Button btnGo = (Button)v.findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sample.getCls() != null){
                    Intent intent = new Intent(context, sample.getCls());
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context, "Sorry, we need to write this code", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
}

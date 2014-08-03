package com.zlargon.dragdrog;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ImageView1 : long click
        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData dragData = null;
                MyDragShadowBuilder myDragShadowBuilder = new MyDragShadowBuilder(v);
                v.startDrag(dragData, myDragShadowBuilder, null, 0);
                return true;
            }
        });
    }
}

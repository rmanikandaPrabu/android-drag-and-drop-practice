package com.zlargon.dragdrog;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private View.OnDragListener mOnDragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            ImageView imageView = (ImageView) v;

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    imageView.setColorFilter(Color.RED);
                    break;

                case DragEvent.ACTION_DRAG_ENTERED:
                    imageView.setColorFilter(Color.GREEN);
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    imageView.setColorFilter(Color.BLUE);
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    imageView.setColorFilter(null);
                    break;
            }

            return true; // Change to Green
        }
    };

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

        // ImageView 2 : drag
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView2.setOnDragListener(mOnDragListener);

        // ImageView 3 : drag
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView3.setOnDragListener(mOnDragListener);
    }
}

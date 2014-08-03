package com.zlargon.dragdrog;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mPositionTextView;

    private View.OnDragListener mOnDragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            ImageView imageView = (ImageView) v;

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    imageView.setColorFilter(Color.argb(100, 255, 0, 0)); // Red
                    break;

                case DragEvent.ACTION_DRAG_ENTERED:
                    imageView.setColorFilter(Color.argb(100, 0, 255, 0)); // Green
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    imageView.setColorFilter(Color.argb(100, 0, 0, 255)); // Blue
                    mPositionTextView.setText("EXIT");
                    break;

                case DragEvent.ACTION_DROP:
                    mPositionTextView.setText("Drop");
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    imageView.setColorFilter(null);
                    break;

                case DragEvent.ACTION_DRAG_LOCATION:
                    float x = event.getX();
                    float y = event.getY();

                    String coordinate = String.format("[%.1f, %.1f]", x, y);
                    mPositionTextView.setText(coordinate);
                    break;
            }

            if (v.getId() == R.id.imageView2) {
                return false;
            }

            return true; // Change to Green
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Position Text View
        mPositionTextView = (TextView) findViewById(R.id.textView_position);

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

        // ImageView 4 : drag
        ImageView imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView4.setOnDragListener(mOnDragListener);
    }
}
